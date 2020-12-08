package com.goat.zxt.system.aspectj;


import com.goat.zxt.common.utils.SecurityUtils;
import com.goat.zxt.security.dto.JwtUserDto;
import com.goat.zxt.system.annotation.DataPermission;
import com.goat.zxt.system.entity.BaseEntity;
import com.goat.zxt.system.entity.SysRole;
import com.goat.zxt.system.service.RoleUserService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

/**
 * 数据过滤处理
 */
@Aspect
@Component
public class DataScopeAspect {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    public RoleUserService roleUserService;

    // 全部数据权限
    public static final String DATA_SCOPE_ALL = "1";

    // 自定数据权限
    public static final String DATA_SCOPE_CUSTOM = "2";

    // 部门数据权限
    public static final String DATA_SCOPE_DEPT = "3";

    // 部门及以下数据权限
    public static final String DATA_SCOPE_DEPT_AND_CHILD = "4";

    // 仅本人数据权限
    public static final String DATA_SCOPE_SELF = "5";

    // 数据权限过滤关键字
    public static final String DATA_SCOPE = "dataScope";

    // 配置织入点
    @Pointcut("@annotation(com.goat.zxt.system.annotation.DataPermission)")
    public void dataScopePointCut() {
    }

    @Before("dataScopePointCut()")
    public void doBefore(JoinPoint point)  {
        handleDataScope(point);
    }

    protected void handleDataScope(final JoinPoint joinPoint) {
        log.info("进入数据权限拦截");
        // 获得注解
        DataPermission controllerDataScope = getAnnotationLog(joinPoint);
        if (controllerDataScope == null) {
            return;
        }
        // 获取当前的用户
        JwtUserDto currentUser = SecurityUtils.getCurrentDtoUser();
        // 如果是超级管理员，则不过滤数据
        if (ObjectUtils.isEmpty(currentUser) || currentUser.getSysUser().isAdmin()){
            log.info("是超管, 不过滤数据");
            return;
        }
        dataScopeFilter(joinPoint, currentUser, controllerDataScope.deptAlias(),controllerDataScope.userAlias());
    }

    /**
     * 数据范围过滤
     * @param joinPoint 切点
     * @param user 用户
     * @param deptAlias 部门别名
     * @param userAlias 用户别名
     */
    public void dataScopeFilter(JoinPoint joinPoint, JwtUserDto user, String deptAlias, String userAlias) {
        log.info("进入数据权限过滤");
        StringBuilder sqlString = new StringBuilder();
        for (SysRole role : user.getRoleInfo()) {
            String dataScope = role.getDataScope();
            if (DATA_SCOPE_ALL.equals(dataScope)){ // 全部数据权限
                sqlString = new StringBuilder();
                break;
            }else if (DATA_SCOPE_CUSTOM.equals(dataScope)){ // 自定义数据权限
                sqlString.append(String.format( " OR %s.dept_id IN ( SELECT dept_id FROM sys_role_dept WHERE role_id = %s ) ", deptAlias,role.getRoleId()));
            }else if (DATA_SCOPE_DEPT.equals(dataScope)){ // 本部门数据权限
                sqlString.append(String.format(" OR %s.dept_id = %s ", deptAlias, user.getSysUser().getDeptId()));
            }else if (DATA_SCOPE_DEPT_AND_CHILD.equals(dataScope)){ // 本部门及以下数据权限
                sqlString.append(String.format(" OR %s.dept_id IN ( SELECT dept_id FROM sys_dept WHERE dept_id = %s or find_in_set( %s , ancestors ) )",deptAlias, user.getSysUser().getDeptId(), user.getSysUser().getDeptId()));
            }else if (DATA_SCOPE_SELF.equals(dataScope)){ // 仅本人数据权限
                if (!StringUtils.isEmpty(userAlias)){
                    sqlString.append(String.format(" OR %s.user_id = %s ", userAlias, user.getSysUser().getUserId()));
                }else{
                    // 数据权限为仅本人且没有userAlias别名不查询任何数据
                    sqlString.append(" OR 1=0 ");
                }
            }
        }
        if (!StringUtils.isEmpty(sqlString.toString())) {
            BaseEntity baseEntity;
            for (int i = 0;i < joinPoint.getArgs().length ;i++ ){
                if (joinPoint.getArgs()[i] instanceof BaseEntity){
                    baseEntity= (BaseEntity) joinPoint.getArgs()[i];
                    baseEntity.getParams().put(DATA_SCOPE, " AND (" + sqlString.substring(4) + ")");
                }
            }
        }
    }

    /**
     * 是否存在注解，如果存在就获取
     */
    private DataPermission getAnnotationLog(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        if (method != null) {
            return method.getAnnotation(DataPermission.class);
        }
        return null;
    }
}

package com.goat.zxt.system.entity;


public class SysUser extends BaseEntity{

    private static final long serialVersionUID = -6525908145032868837L;

    private Integer userId;

    private Integer deptId;

    private String userName;

    private String password;

    private String nickName;

    private String phone;

    private String email;

    private Integer status;

    public interface Status {
        int LOCKED = 0;
        int VALID = 1;
    }

    private Integer roleId;

    /** 岗位组 */
    private String[] postIds;

    /**
     * 判断是否为admin用户
     */
    public boolean isAdmin()
    {
        return isAdmin(this.getUserId());
    }

    public static boolean isAdmin(Integer userId) {
        return userId != null && 1L == userId;
    }


    public SysUser() {
    }

    public SysUser(Integer userId)
    {
        this.setUserId(userId);
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String[] getPostIds() {
        return postIds;
    }

    public void setPostIds(String[] postIds) {
        this.postIds = postIds;
    }
}

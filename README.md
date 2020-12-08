# 待完成
    修复用户名可以一致的bug
    
    
#  有时用layui的表单元素真的傻难看傻难看的
    解决方案：
    
    1设置css
    
    .layui-form-label {
        width: 100px;
    }
    2设置css
    
    .layui-input-block {
        margin-left: 130px;
    }
    3修改form的宽度
    
    <form id="form1" class="layui-form" style="width: 60%;margin-top: 20px;">
    
    
# 关于在layui中的table checkbox 默认选中设置
    总共有两种方法：
    
    　方法1：在返回的json中设置LAY_CHECKED为true，页面上的checkbox就是选中状态了。
    
    1 data":[
    2     {"name":"北京市","areaType":"省/直辖市","id":"110000","LAY_CHECKED":true},
    3     {"name":"市辖区","areaType":"地市","id":"110100","LAY_CHECKED":true},
    4     {"name":"县","areaType":"地市","id":"110200","LAY_CHECKED":false}
    5 ]
      方法2：对于方法1，自我觉得写的比较古板，不能自定义key，比如当项目中所返回的数据中没有LAY_CHECKED字段怎么办？为了与其他tree所需字段统一，都将是否选中字段统一定义为checked，比如说返回的是这样的数据：
    
    1 data":[
    2     {"name":"北京市","areaType":"省/直辖市","id":"110000","checked":true},
    3     {"name":"市辖区","areaType":"地市","id":"110100","checked":true},
    4     {"name":"县","areaType":"地市","id":"110200","checked":false}
    5 ]
    注意：这里用的是checked来标识是否选中，而非LAY_CHECKED。
    
    这时候就需要更改下对table渲染的配置了：
    
     1 layui.use('table', function(){
     2     var $ = layui.$;
     3     var table = layui.table;
     4     
     5     //在使用table之前加上下面这句就可以了
     6     table =  $.extend(table, {config: {checkName: 'checked'}});
     7     table.render({
     8  ... 9  }); 10 });
    
    
# MySQL 5.7.8 以后版本居然加入了 json 字段
       执行： SELECT version();  
       结果： 8.0.12
    
  
    
# 增加日志id跟踪日志
    （1）先配上aop的maven依赖（已经有aop就不用再加了），这里要注意spring-aop的版本对应上spring-core的版本：
         <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-aop</artifactId>
            <version>4.3.10.RELEASE</version>
        </dependency>
        <dependency>
            <groupId>org.aspectj</groupId>
            <artifactId>aspectjweaver</artifactId>
            <version>1.8.13</version>
        </dependency>
        
    （2）再添加AOP处理类~！这里只针对controller层。（不太了解aop配置的需要补习一下基本的配置方法）
        见：com.goat.zxt.system.aspectj.SpringAOP
    
    
    
    （3）在项目resources目录下的logback-spring.xml中修改日志pattern（不了解的话，这里需要补充下日志配置的知识），
        添加[%X{requestId}]，requestId就是在切面类中的MCD.put()方法中的key，
           <pattern>
                [ %-5level] [%date{yyyy-MM-dd HH:mm:ss}] [%X{requestId}] %logger{96} [%line] - %msg%n
            </pattern>
    

    
    
    
    
    
    
    
    
    
    
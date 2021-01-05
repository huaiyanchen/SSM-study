# 我的SSM学习
## Spring-mybatis
对应的就是配置文件中的spring-dao的相关配置 
- 配置文件
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       https://www.springframework.org/schema/context/spring-context.xsd">
    <!-- 配置整合mybatis -->
    <!-- 1.关联数据库文件 -->
    <context:property-placeholder location="classpath:database.properties"/>
    <!--    c3p0-->
    <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
        <!-- 配置连接池属性 -->
        <property name="driverClass" value="${jdbc.driver}"/>
        <property name="jdbcUrl" value="${jdbc.url}"/>
        <property name="user" value="${jdbc.username}"/>
        <property name="password" value="${jdbc.password}"/>
        <!-- c3p0连接池的私有属性 -->
        <property name="maxPoolSize" value="30"/>
        <property name="minPoolSize" value="10"/>
        <!-- 关闭连接后不自动commit -->
        <property name="autoCommitOnClose" value="false"/>
        <!-- 获取连接超时时间 -->
        <property name="checkoutTimeout" value="10000"/>
        <!-- 当获取连接失败重试次数 -->
        <property name="acquireRetryAttempts" value="2"/>
    </bean>
    <!-- Mybatis的sqlSessionFactory工具类的创建   -->
    <!--    在基础的 MyBatis 用法中，是通过 SqlSessionFactoryBuilder 来创建 SqlSessionFactory 的。而在 MyBatis-Spring 中，则使用 SqlSessionFactoryBean 来创建。-->
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="dataSource"/>
        <property name="configLocation" value="classpath:mybatis-config.xml"/>
    </bean>
    <!--那么可以通过 MapperScannerConfigurer动态的讲Dao的bean 将接口加入到 Spring 中:-->
    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"/>
        <property name="basePackage" value="com.chy.dao"/>
    </bean>
</beans>
```
- 配置
    - 1.关联数据库文件 
    - 2.数据库连接池配置 c3p0 druid  
    - 3.sqlsessionFactory的创建
        - 其实sqlsessionFactory就是mybatis的中的工具类的创建.点开SqlSessionFactoryBean方法,里面就可以看到实现mybatis工具类的方法. 
  - 4.MapperScannerConfigurer 
    - 自动扫描 将Mapper接口生成代理注入到Spring
    MapperFactoryBean 创建的代理类实现了 UserMapper 接口,并且注入到应用程序中
    把Mapper接口转换成MapperFactoryBean
    其实在SqlSessionFactoryBean 里面就已经实现了注册了 MapperProxyFactory 到Mybatis
    这里做的是把它们托管到spring
      

## spring与service层的配置  
- 配置文件
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:tx="http://www.springframework.org/schema/tx"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       https://www.springframework.org/schema/context/spring-context.xsd
       http://www.springframework.org/schema/tx
       http://www.springframework.org/schema/tx/spring-tx.xsd">
    <!--    自动注册bean
    * 在类的上面加上注解@Componet
      * @Component衍生的注解
      * @Controller：web层
      * @Service：service层
      * @Repository：dao层
    -->
    <context:component-scan base-package="com.chy.service"/>
    <!--配置事务管理器(mybatis采用的是JDBC的事务管理器)-->
    <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="dataSource"/>
    </bean>
    <!--配置基于注解的声明式事务,默认使用注解来管理事务行为-->
    <tx:annotation-driven transaction-manager="transactionManager"/>
</beans>
```
-  component-scan开启注解扫描
    - 作用是把接口实现类自动注册为bean,后面的调试和业务调用可以直接使用
  
- 配置事务和事务的声明
  - 暂时还没有做  
## sprin结合mvc层
- 配置文件 
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/mvc
        https://www.springframework.org/schema/mvc/spring-mvc.xsd
        http://www.springframework.org/schema/context
        https://www.springframework.org/schema/context/spring-context.xsd">
    <!-- 配置SpringMVC -->
    <!-- 1.开启SpringMVC注解驱动 -->
    <mvc:annotation-driven/>
    <!-- 2.静态资源默认servlet配置-->
    <mvc:default-servlet-handler/>
    <!-- 3.配置jsp 显示ViewResolver视图解析器 -->
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="viewClass" value="org.springframework.web.servlet.view.JstlView"/>
        <property name="prefix" value="/WEB-INF/jsp/"/>
        <property name="suffix" value=".jsp"/>
    </bean>
    <!-- 4.扫描web相关的bean -->
    <context:component-scan base-package="com.chy.controller"/>
</beans>
```
-  annotation-driven开启mvc的注解驱动
    - 开启 处理器映射器和处理器是适配器 HandlerMapping和 HandlerAdapter
-  component-scan开启注解扫描  交给IOC容器管理注册Bean
    - 将自己的类交给SpringIOC容器，注册bean,相当于下面的代码 
  ```xml
  <!--Handler-->
    <bean id="/hello" class="com.kuang.controller.HelloController"/>
  ```
-  视图解析器配置 把ModelandView处理成前端ViewResolver视图解析器可识别的文件   

## web.xml的配置  
- 配置文件
```xml
   <servlet>
        <servlet-name>dispatcherServlet</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:spring/spring.xml</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>dispatcherServlet</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>

    <filter>
        <filter-name>CharacterEncodingFilter</filter-name>
        <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
        <init-param>
            <param-name>encoding</param-name>
            <param-value>utf-8</param-value>
        </init-param>
    </filter>
    <filter-mapping>
        <filter-name>CharacterEncodingFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
```
主要就是配置前端控制器  DispaServlet























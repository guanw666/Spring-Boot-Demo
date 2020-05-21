## SpringBoot Demo

### 资料

[视频](https://www.bilibili.com/video/av50200264?from=search&seid=15458781763533442613)

[Spring Guides](https://spring.io/guides)

[connecting-to-github-with-ssh](https://help.github.com/en/articles/connecting-to-github-with-ssh)

[BootStrap Quick Start](https://v3.bootcss.com/getting-started/)

[Sign With Github](https://developer.github.com/apps/building-oauth-apps/)

[mybatis-spring-boot-autoconfigure](http://www.mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/)

[lombok](https://projectlombok.org)

[thymeleaf-doc](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html)

[springboot docs](https://docs.spring.io/spring-boot/docs/2.0.0.RC1/reference/htmlsingle/)

[spring docs](https://docs.spring.io/spring/docs/5.0.4.RELEASE/spring-framework-reference/web.html)

[mybatis generator](http://www.mybatis.org/generator)

### 参考页面

[elasticsearch.cn](https://elasticsearch.cn/)

### 部署流程
- git
- jdk
- maven
- mysql
#### 步骤
- yum update
- yum install git
- mkdir App
- cd App
- git clone https://github.com/guanw666/Spring-Boot-Demo.git
- yum install maven
- mvn -v
- java -version
- mvn clean compile package
- systemctl start mariadb
- mysql
- use myql
- update user set password = password("new password") where user = 'root';
- flush privileges;
- exit
- mysql -u root -p
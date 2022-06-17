# Kaddo BOM and server-side library

**Kaddo BOM 是一个基于 Spring Boot 和 Spring Cloud 的快速开发框架！它的目标是为企业级项目构建提供一套完善的开发框架和管理体系，使项目开发更加简单、高效。**

## 概述

在项目构建时，经常会用到第三方库，开发人员通常都是根据经验或直接选择较新的版本，随意性较大。 随着业务复杂度的增加，集成的第三方库会越来越多，依赖关系也会变得更加复杂。除非做过完整的兼容性测试，保证集成第三方库的版本不会出问题，且后续集成或升级都不会出问题，否则就有可能存在较大的隐性风险（如版本冲突）。

Kaddo BOM 的主要目的就在于帮助我们解决这些问题。开发人员在添加第三方库时，不需要关心版本号，Kaddo BOM（基于 Spring Boot 和 Spring Cloud）会帮助我们提供一个最优的版本，而且该版本是经过严格测试的，可以更好地兼容其它的组件。同时，你也可以根据自己的需要来升级或降级依赖版本。

Kaddo BOM 是完全兼容 Spring Boot 和 Spring Cloud，致力于提供基础开发组件的封装和编写完善的使用文档，通过 Spring Boot 从根本上简化开发体检。

## 快速开始

在你的 Maven 项目中添加 Kaddo BOM 依赖管理：

```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">

    <modelVersion>4.0.0</modelVersion>

    <groupId>com.example</groupId>
    <artifactId>your-application</artifactId>
    <version>1.0.0-SNAPSHOT</version>

    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>com.kaddo</groupId>
                <artifactId>kaddo-dependencies</artifactId>
                <version>${kaddo-framework.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>

    <!-- Dependency declarations -->

</project>
```

或者，在你的 Spring Boot 项目中将其作为父模块引入：

```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">

    <modelVersion>4.0.0</modelVersion>

    <groupId>com.example</groupId>
    <artifactId>your-application</artifactId>
    <version>1.0.0-SNAPSHOT</version>

    <parent>
        <groupId>com.kaddo</groupId>
        <artifactId>kaddo-dependencies</artifactId>
        <version>${kaddo-framework.version}</version>
        <relativePath/>
    </parent>

    <!-- Dependency declarations -->

</project>
```

注意：Spring Boot 项目想要生成可执行 JAR 文件，需要在启动程序的 pom.xml 中要添加 Spring Boot Maven 插件：

```
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
        </plugin>
    </plugins>
</build>
```

在集成 Kaddo BOM 提供的依赖管理后，你仍然可以像平时一样添加第三方库，只是无需再关心依赖包的版本。需要注意的是，添加 Kaddo BOM 没有引入的第三方库，仍然需要指定版本号。

```
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-core</artifactId>
</dependency>
```

## 覆盖版本

Kaddo BOM (基于 Spring Boot 和 Spring Cloud) 项目集成的第三方库的版本信息都是使用 `<properties />` 来定义的，这就使得你可以很容易的升级或降级某个依赖包的版本。在你的项目中指定依赖包的版本号：

```
<properties>
  <foo.version>1.2.0.RELEASE</foo.version>
</properties>
```

注意：在使用 Kaddo BOM 项目的依赖管理时，应根据项目的实际运行环境来合理选择版本号，除非必须，否则不建议修改版本信息。在修改依赖包的版本信息时，可通过 IDE 点击坐标来查看依赖包对应的属性名。

## 已知问题

由于 Google Guava 类库的广泛使用，引用不同的项目时可能存在不兼容情况。这时候需要我们手动指定合适的版本号以保证项目能够正常运行。Kaddo BOM 引用的 Google Guava 版本是：

```
<properties>
  <guava.version>31.0.1-jre</guava.version>
</properties>
```

## 依赖版本

Spring Boot 的依赖版本清单可查看文档：https://docs.spring.io/spring-boot/docs/current/reference/html/dependency-versions.html

Kaddo BOM 的依赖版本清单如下：

| Group ID | Artifact ID | Version | Version Property  | Documentation |
| --- | --- | --- | --- | --- |
| org.springframework.boot | spring-boot-starter-parent | 2.6.8 | spring-boot.version | [Spring Boot](https://spring.io/projects/spring-boot) |
| org.springframework.cloud | spring-cloud-dependencies | 2021.0.3 | spring-cloud.version | [Spring Cloud](https://spring.io/projects/spring-cloud) |
| org.redisson | redisson-spring-boot-starter | 3.17.3 | redisson.version | [Redisson](https://github.com/redisson/redisson) |
|  |  |  |  |  |
| com.alibaba | druid-spring-boot-starter | 1.2.9 | alibaba-druid.version | [Druid](https://github.com/alibaba/druid) |
| com.alibaba | fastjson | 1.2.83 | alibaba-fastjson.version | [Fastjson](https://github.com/alibaba/fastjson) |
| com.ctrip.framework.apollo | apollo-client | 2.0.0 | apollo-client.version | [ApolloConfig](https://www.apolloconfig.com/) |
| org.bouncycastle | bcpkix-jdk15on | 1.70 | bouncycastle.version | [Bouncy Castle Crypto](https://github.com/open-keychain/bouncycastle) |
| org.bouncycastle | bcprov-jdk15to18 | 1.70 | bouncycastle.version | [Bouncy Castle Crypto](https://github.com/open-keychain/bouncycastle) |
| com.google.guava | guava | 31.1-jre | guava.version | [Guava](https://github.com/google/guava) |
| cn.hutool | hutool-all | 5.8.2 | hutool.version | [Hutool](https://hutool.cn/) |
| org.mybatis.spring.boot | mybatis-spring-boot-starter | 2.2.2 | mybatis-springboot.version | [MyBatis Spring-Boot-Starter](https://github.com/mybatis/spring-boot-starter) |
| com.github.pagehelper | pagehelper-spring-boot-starter | 1.4.2 | mybatis-pagehelper.version | [PageHelper Spring-Boot-Starter](https://github.com/pagehelper/pagehelper-spring-boot) |
| com.baomidou | mybatis-plus-boot-starter | 3.5.2 | mybatis-plus.version | [MyBatis-Plus](https://baomidou.com/) |
| org.zalando | problem-spring-web | 0.27.1 | problem-spring-web.version | [Zalando Problem](https://github.com/zalando/problem/) |
| org.zalando | problem-spring-webflux | 0.27.1 | problem-spring-webflux.version | [Zalando Problem](https://github.com/zalando/problem/) |
| com.squareup.retrofit2 | retrofit | 2.9.0 | retrofit2.version | [Retrofit](https://square.github.io/retrofit/) |
| com.squareup.retrofit2 | converter-jackson | 2.9.0 | retrofit2.version | [Retrofit](https://square.github.io/retrofit/) |
| org.springdoc | springdoc-openapi-ui | 1.6.9 | springdoc.version | [SpringDoc](https://springdoc.org/) |
|  |  |  |  |  |

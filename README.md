# Kaddo BOM

**Kaddo BOM 是一个基于 Spring Boot 和 Spring Cloud 的快速开发框架！它的目标是为企业级项目构建提供一套完善的开发框架和管理体系，使项目开发更加简单、高效。**

## 背景

在项目构建时，经常会用到第三方库，开发人员通常都是根据经验或直接选择较新的版本，随意性较大。 随着业务复杂度的增加，集成的第三方库会越来越多，依赖关系也会变得更加复杂。除非做过完整的兼容性测试，保证集成第三方库的版本不会出问题，且后续集成或升级都不会出问题，否则就有可能存在较大的隐性风险（如版本冲突）。

Kaddo BOM 的主要目的就在于帮助我们解决这些问题。开发人员在添加第三方库时，不需要关心版本号，Kaddo BOM (Spring Boot Dependencies)会帮助我们提供一个最优的版本，而且该版本是经过严格测试的，可以更好地兼容其它的组件。同时，你也可以根据自己的需要来升级或降级依赖版本。

## 主要特征

1. 完全兼容 Spring Boot 和 Spring Cloud，通过 Spring Boot 从根本上简化开发体验。
2. 一个平台，多种工作负载 - 构建Web，集成，批处理，响应式或大数据应用。
3. 开箱即用。
4. 精心策划和协调一致的依赖关系。
5. 模块化平台，允许开发人员只部署他们所需的依赖。
6. 支持嵌入式、传统应用服务器和PaaS部署。
7. 只依赖Java SE，并且支持 Groovy, Grails 和一些 Java EE。
8. 适用于存在的依赖管理工具，例如 Maven 和 Gradle。
9. 提供对基础开发组件的封装。
10. 简单高效，适用于企业级项目开发。

## 环境要求

Kaddo BOM 是基于 Spring Boot 和 Spring Cloud 构建的，所以环境要求与 Spring Boot 一致。

| 名称 | 版本 | 备注 |
|:---|:---|:---|
| Java | 1.8+ | [Java Downloads \| Oracle](https://www.oracle.com/java/technologies/downloads/) |
| Maven | 3.3.9+ | [Apache Maven](http://maven.apache.org/) |
| Spring Boot | 2.6.8 | [官方文档](https://spring.io/projects/spring-boot#learn) |
| Spring Cloud | 2021.0.2 | [官方文档](https://spring.io/projects/spring-cloud#learn) |
| Redisson | 3.17.1 | [官方文档](https://github.com/redisson/redisson) |

## 快速开始

在 Maven 项目中添加 Kaddo BOM 依赖管理：

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
                <version>0.0.6-SNAPSHOT</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>

    <!-- Dependency declarations -->

</project>
```

或者，在 Spring Boot 项目中将其作为父模块引入：

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
        <version>0.0.6-SNAPSHOT</version>
        <relativePath/>
    </parent>

    <!-- Dependency declarations -->

</project>
```

注意：如果是 Spring Boot 项目，想要生成可执行 JAR 文件，需要在启动程序的 pom.xml 中要添加 Spring Boot Maven 插件：

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

Kaddo BOM (Spring Boot Dependencies) 项目集成的第三方库的版本信息都是使用 `<properties />`
来定义的，这就使得你可以很容易的升级或降级某个依赖包的版本。在你的项目中指定依赖包的版本号：

```
<properties>
  <foo.version>1.2.0.RELEASE</foo.version>
</properties>
```

注意：在使用 Kaddo BOM 项目的依赖管理时，应根据项目的实际运行环境来合理选择版本号，除非必须，否则不建议修改版本信息。在修改依赖包的版本信息时，可通过IDE点击坐标来查看依赖包对应的属性名。

## 已知问题

由于 Google Guava 类库的广泛使用，引用不同的项目时可能存在不兼容情况。这时候需要我们手动指定合适的版本号以保证项目能够正常运行。Kaddo BOM 引用的 Google Guava 版本是：

```
<properties>
  <guava.version>31.0.1-jre</guava.version>
</properties>
```

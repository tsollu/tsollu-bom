# Spring Boot HikariCP

[HikariCP](https://github.com/brettwooldridge/HikariCP) ��һ�������ܵ� JDBC ���ӳ������

Spring Boot 2.x ������ΪĬ�ϵ����ӳ��������Ŀ����� `spring-boot-starter-jdbc` �� `spring-boot-starter-data-jpa`  ģ���HikariCP �����ᱻ�Զ����롣

## ����ʹ��

1������� Spring Boot ��Ŀ������������ã�

```
<!-- JDBC -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-jdbc</artifactId>
</dependency>
```

���³��õ� ORM ������Ѿ������� JDBC ����������Ҫ�ظ����룺

- spring-boot-starter-data-jpa
- mybatis-spring-boot-starter
- mybatis-plus-boot-starter

2���������Դ���ã�

```
## Spring HikaraDataSource Configuration
# spring.datasource.type=com.zaxxer.hikari.HikariDataSource
# spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
# spring.datasource.name=HikariCP-1
spring.datasource.url=jdbc:mysql://localhost:3306/dbname?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8&autoReconnect=true&autoReconnectForPools=true&noAccessToProcedureBodies=true&allowMultiQueries=true&zeroDateTimeBehavior=convertToNull
spring.datasource.username=root
spring.datasource.password=123456
spring.datasource.hikari.connection-timeout=30000
spring.datasource.hikari.idle-timeout=600000
spring.datasource.hikari.max-lifetime=1800000
spring.datasource.hikari.maximum-pool-size=100
spring.datasource.hikari.minimum-idle=10
spring.datasource.hikari.pool-name=HikaraPool-1
```

## ���ӳ�����

1���� Spring Boot ��Ŀ�У�һ���򵥵� Spring DataSource ���ã�ͨ��ֻ��Ҫ�������ݿ����ӡ��û�������������������

```
## Spring DataSourceProperties

# �������ӳ����ͣ�Ĭ���Զ���ȡ����ѡ��
spring.datasource.type=com.zaxxer.hikari.HikariDataSource

# �������ݿ�������Ĭ���Զ���ȡ����ѡ��
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# ����Ψһ������Դ���ƣ�������񣬶�������ȡ name ��ֵ����ѡ��
spring.datasource.generate-unique-name=true

# ��������Դ���ƣ�Ĭ�ϻ�����Ψһ������Դ���ƣ��磺HikariPool-1����ѡ��
spring.datasource.name=HikariCP-1

# �������ݿ����ӣ���ѡ��
spring.datasource.url=jdbc:mysql://localhost:3306/dbname?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8&autoReconnect=true&autoReconnectForPools=true&noAccessToProcedureBodies=true&allowMultiQueries=true&zeroDateTimeBehavior=convertToNull

# �������ݿ��û�������ѡ��
spring.datasource.username=root

# �������ݿ����루��ѡ��
spring.datasource.password=123456
```

2���� Spring Boot ��Ŀ�У� Spring DataSource ��ʹ��Ĭ�ϵ��������� HikaraCP ���ݿ����ӳء�����Ҳ����ͨ�������ļ����Ż� HikariCP ����������������ӳش�С�����á�

```
## Spring HikariConfig

# �����Զ��ύ - Ĭ��ֵ��true
spring.datasource.hikari.auto-commit=true

# ���Ӳ��Բ�ѯ - Using the JDBC4 <code>Connection.isValid()</code> method to test connection validity can be more efficient on some databases and is recommended.
# ��������������֧��JDBC4��ǿ�ҽ��鲻Ҫ���ô����ԡ�
spring.datasource.hikari.connection-test-query=select 1

# ���ӳ�ʱʱ�� - Ĭ��ֵ��30�롣
spring.datasource.hikari.connection-timeout=30000

# ���ӳ����������õ��ʱ�� - Ĭ��ֵ��10����
spring.datasource.hikari.idle-timeout=600000

# һ����������ʱ�������룩����ʱ��û��ʹ�����ͷ� - Ĭ��ֵ��30����
spring.datasource.hikari.max-lifetime=1800000

# ���ӳ��������������������������ú�ʹ���е����� - Ĭ��ֵ��10
spring.datasource.hikari.maximum-pool-size=100

# ���ӳ����������С���������� - Ĭ��ֵ��10��
spring.datasource.hikari.minimum-idle=10

# ���ӱ����Ի���ʱ�� - Ĭ��ֵ��5�롣
spring.datasource.hikari.validation-timeout=5000

# ָ�����ӳص����� - Ĭ���Զ�����
spring.datasource.hikari.pool-name=HikaraPool-1
```

## ������Դ����

1����Ӷ�����Դ���ã�

```
## Spring HikaraDataSource Configuration
# spring.datasource.one.driver-class-name=com.mysql.cj.jdbc.Driver
# spring.datasource.one.name=HikariCP-1
spring.datasource.one.url=jdbc:mysql://localhost:3306/kaddo-sit?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8&autoReconnect=true&autoReconnectForPools=true&noAccessToProcedureBodies=true&allowMultiQueries=true&zeroDateTimeBehavior=convertToNull
spring.datasource.one.username=root
spring.datasource.one.password=123456
spring.datasource.one.hikari.connection-timeout=30000
spring.datasource.one.hikari.idle-timeout=600000
spring.datasource.one.hikari.max-lifetime=1800000
spring.datasource.one.hikari.maximum-pool-size=100
spring.datasource.one.hikari.minimum-idle=10
spring.datasource.one.hikari.pool-name=HikaraPool-1

## Spring HikaraDataSource Configuration
# spring.datasource.two.driver-class-name=com.mysql.cj.jdbc.Driver
# spring.datasource.two.name=HikariCP-2
spring.datasource.two.url=jdbc:mysql://localhost:3306/kaddo-uat?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8&autoReconnect=true&autoReconnectForPools=true&noAccessToProcedureBodies=true&allowMultiQueries=true&zeroDateTimeBehavior=convertToNull
spring.datasource.two.username=root
spring.datasource.two.password=123456
spring.datasource.two.hikari.connection-timeout=30000
spring.datasource.two.hikari.idle-timeout=600000
spring.datasource.two.hikari.max-lifetime=1800000
spring.datasource.two.hikari.maximum-pool-size=100
spring.datasource.two.hikari.minimum-idle=10
spring.datasource.two.hikari.pool-name=HikaraPool-2
```

2������������Դ��

```
@SuppressWarnings("unchecked")
protected static <T> T createDataSource(DataSourceProperties properties, Class<? extends DataSource> type) {
	return (T) properties.initializeDataSourceBuilder().type(type).build();
}

@Bean
@Primary
@ConfigurationProperties("spring.datasource.one")
public DataSourceProperties dataSourcePropertiesOne() {
	return new DataSourceProperties();
}

@Bean
@Primary
@ConfigurationProperties("spring.datasource.one.hikari")
public HikariDataSource dataSourceOne(DataSourceProperties properties) {
	HikariDataSource dataSource = createDataSource(properties, HikariDataSource.class);
	if (StringUtils.hasText(properties.getName())) {
		dataSource.setPoolName(properties.getName());
	}
	return dataSource;
}

@Bean
@ConfigurationProperties("spring.datasource.two")
public DataSourceProperties dataSourcePropertiesTwo() {
	return new DataSourceProperties();
}

@Bean
@ConfigurationProperties("spring.datasource.two.hikari")
public HikariDataSource dataSourceTwo(@Qualifier("dataSourcePropertiesTwo") DataSourceProperties properties) {
	HikariDataSource dataSource = createDataSource(properties, HikariDataSource.class);
	if (StringUtils.hasText(properties.getName())) {
		dataSource.setPoolName(properties.getName());
	}
	return dataSource;
}
```

3��ʹ�ö�����Դ��

```
@Autowired
private HikariDataSource dataSourceOne;

@Autowired
@Qualifier("dataSourceTwo")
private HikariDataSource dataSourceTwo;
```

���ö�����Դ��ע�����

* ���ö�����Դʱ�����ͨ�� @Primary ָ��Ĭ������Դ��
* ��������Դ����ʱ������ʹ�� HikariDataSource ���� DataSource��
* ��������Դ����ʱ��ע��ͨ�� @Qualifier("dataSourcePropertiesTwo") ��ָ������Դ���������Զ���
* ��������Դ����ʱ��ע�ⴴ�� Bean �ķ����������ͨ�� @Bean("dataSourceOne") ��ָ������Դ��������ơ�
* ������Դͨ����� ORM ���һ��ʹ�ã�����ɲο� Kaddo ��ܵ� ORM ���á�

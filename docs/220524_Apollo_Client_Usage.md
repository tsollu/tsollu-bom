# Apollo Client ʹ��ָ��

Apollo�������ޣ���һ��ɿ��ķֲ�ʽ���ù������ģ�������Я�̿���з������ܹ����л�����Ӧ�ò�ͬ��������ͬ��Ⱥ�����ã������޸ĺ��ܹ�ʵʱ���͵�Ӧ�öˣ����Ҿ߱��淶��Ȩ�ޡ�������������ԣ�������΢�������ù�������

����˻��� Spring Boot �� Spring Cloud ��������������ֱ�����У�����Ҫ���ⰲװ Tomcat ��Ӧ��������

Java �ͻ��˲������κο�ܣ��ܹ����������� Java ����ʱ������ͬʱ�� Spring/Spring Boot ����Ҳ�нϺõ�֧�֡�

�����Ʒ������鿴�ٷ��ĵ���https://www.apolloconfig.com/

**`kaddo-components-apollo` ģ���ǻ��� Spring Boot �� Apollo Client �����Ŀ��ټ��ɷ������ص������� Spring Boot ��ʹ�� @ConfigurationProperties ע��ʱ���ò��������µ����⡣**

## ����ʹ��

1������� Spring Boot ��Ŀ������������ã�

```
<!-- Apollo Client >=2.0.0 -->
<dependency>
  <groupId>com.kaddo</groupId>
  <artifactId>kaddo-components-apollo</artifactId>
  <version>${kaddo-framework.version}</version>
</dependency>
```

2��������ò�����

```
## Apollo Config
# �໷��ʱ����Ҫͨ������������ָ�����û�����-Denv=DEV
app.id=YOUR-APP-ID
apollo.bootstrap.enabled=true
apollo.bootstrap.eagerLoad.enabled=true
apollo.bootstrap.namespaces=application
apollo.meta=http://config-service-url
apollo.cache-dir=./config-cache
apollo.cluster=default
```

## ���ò������

Apollo Client ֧��ͨ������������ Spring Boot application.properties / bootstrap.properties �����ļ����á�

������ Apollo Client ���ò�������ϸ˵�����鿴�ٷ��ĵ����˽�������������Ϣ��https://www.apolloconfig.com/#/zh/usage/java-sdk-user-guide

```
# [��ѡ] ���� Apollo ����
# [ע��] ���������ʹ���� @EnableApolloConfig ע�⣬�����ý�������Ч��
apollo.bootstrap.enabled=true

# [��ѡ] ָ�� app.id
# AppId��Ӧ�õ������Ϣ���Ǵӷ���˻�ȡ���õ�һ����Ҫ��Ϣ��
# ����������-Dapp.id=YOUR-APP-ID
# ϵͳ������APP_ID=YOUR-APP-ID
app.id=YOUR-APP-ID

# [��ѡ] ָ�����÷�������ַ
# Ϊ��ʵ�� meta server �ĸ߿��ã��Ƽ�ͨ�� SLB ����̬���ؾ��⡣
# ����������-Dapollo.meta=http://config-service-url
# ϵͳ������APOLLO_META=http://config-service-url
# �����������JAR�ļ���ָ������Ҫע���ʽ�ǣ�java -Dapollo.meta=http://config-service-url -jar xxx.jar
apollo.meta=http://config-service-url

# [��ѡ] ָ�����û���
# ����������-Denv=DEV
# ϵͳ������ENV=DEV
# �໷��ʱ���ò�������ͨ������������ϵͳ������ָ����
# ���������JAR�ļ�����Ҫע���ʽ��java -Denv=DEV -jar xxx.jar

# [��ѡ] ָ����Ⱥ����
# Apollo֧�����ð��ռ�Ⱥ���֣�Ҳ����˵����һ��AppId��һ���������Բ�ͬ�ļ�Ⱥ�����в�ͬ�����á�
# �������û�ҵ�ָ����Ⱥ�����Ĭ�ϵļ�Ⱥ��default�����ء�
# ����������-Dapollo.cluster=SomeCluster
apollo.cluster=default

# [��ѡ] �����ڴ��е��������Ƿ񱣳ֺ�ҳ���ϵ�˳��һ�£�Ĭ��false
# ��Щ������ǿ�����������˳����spring cloud zuul��·�ɹ��򣩣����������������Կ��� OrderedProperties ������ʹ���ڴ��е�����˳���ҳ���Ͽ�����һ�¡�
# -Dapollo.property.order.enable=true
# apollo.property.order.enable=false

# [��ѡ] �� Apollo ���ü����ᵽ��ʼ����־ϵͳ֮ǰ��������ᵼ�� Apollo �����������޷�ͨ����־�ķ�ʽ�����
apollo.bootstrap.eagerLoad.enabled=true

# [��ѡ] �Զ��建��·��
# ����������-Dapollo.cache-dir=/opt/data/config-cache
# ϵͳ������APOLLO_CACHE_DIR=/opt/data/config-cache
apollo.cache-dir=/opt/data/config-cache

# [��ѡ] ���÷�����Կ�������������Ҫ��ʹ�÷�����Կ����������ã�
# ����������-Dapollo.access-key.secret=1cf998c4e2ad4704b45a98a509d15719
# ϵͳ������APOLLO_ACCESS_KEY_SECRET=1cf998c4e2ad4704b45a98a509d15719
# ���������JAR�ļ�����Ҫע���ʽ��java -Dapollo.access-key.secret=1cf998c4e2ad4704b45a98a509d15719 -jar xxx.jar
# apollo.access-key.secret=1cf998c4e2ad4704b45a98a509d15719

# [��ѡ] ָ�����ص������ռ䣬Ĭ�ϻ���� application
apollo.bootstrap.namespaces=application,FX.apollo,application.yml
```

Environment ����ͨ������3�ַ�ʽ������һ�����ã�

**1��ͨ�� Java System Property (�Ƽ�)**

����ͨ�� Java �� System Property env ��ָ��������

- �� Java ���������ű��У�����ָ�� -Denv=YOUR-ENVIRONMENT
- ��������� JAR �ļ�����Ҫע���ʽ�� java -Denv=YOUR-ENVIRONMENT -jar xxx.jar
- ע�� key ΪȫСд

**2��ͨ������ϵͳ�� System Environment**

- ������ͨ������ϵͳ�� System Environment ENV ��ָ�� ENV=YOUR-ENVIRONMENT
- ע�� key Ϊȫ��д

**3��ͨ�������ļ�**

���һ���Ƽ��ķ�ʽ��ͨ�������ļ���ָ�� env=YOUR-ENVIRONMENT

- ���� Mac/Linux��Ĭ���ļ�λ��Ϊ /opt/settings/server.properties
- ���� Windows��Ĭ���ļ�λ��Ϊ C:\opt\settings\server.properties

## ���ػ���·��

Apollo �ͻ��˻�Ѵӷ���˻�ȡ���������ڱ����ļ�ϵͳ����һ�ݣ��������������񲻿��ã������粻ͨ��ʱ����Ȼ�ܴӱ��ػָ����ã���Ӱ��Ӧ���������С�

���ػ���·��Ĭ��λ������·����������ȷ�� /opt/data �� C:\opt\data\ Ŀ¼���ڣ���Ӧ���ж�дȨ�ޡ�

- Mac/Linux: /opt/data/{appId}/config-cache
- Windows: C:\opt\data\{appId}\config-cache

**���ߣ�ָ�������ļ�·�����Ƽ�����**

```
apollo.cache-dir=./config-cache
```

���������ļ�����������ļ�����ʽ�����ڱ��ػ���·���£�

```
{appId}+{cluster}+{namespace}.properties
```

- appId ����Ӧ���Լ��� appId���ɹ淶�������򣩣��� 100004458
- cluster ����Ӧ��ʹ�õļ�Ⱥ��һ���ڱ���ģʽ��û���������õĻ������� default
- namespace ����Ӧ��ʹ�õ����� namespace��һ���� application

---

## �� Spring �е�ʹ��

Spring Ӧ��ͨ����ʹ�� Placeholder ��ע�����ã�ʹ�ø�ʽ�� ${someKey:someDefaultValue}��ð��ǰ����� key��ð�ź������Ĭ��ֵ��

_������ʵ��ʹ��ʱ��������Ĭ��ֵ���������� key û�ж��嵼������ʱ����_

�� Apollo v0.10.0 ��ʼ�İ汾֧�� Placeholder ������ʱ�Զ����¡�

**1��������һ�� TestJavaConfigBean��ͨ�� Java Config �ķ�ʽ����ʹ�� @Value �ķ�ʽע�룺**

```
public class TestJavaConfigBean {

  @Value("${timeout:100}")
  private int timeout;
  private int batch;
 
  @Value("${batch:200}")
  public void setBatch(int batch) {
    this.batch = batch;
  }
 
  public int getTimeout() {
    return timeout;
  }
 
  public int getBatch() {
    return batch;
  }
}
```

�� Configuration ���а�������ķ�ʽʹ�ã�����Ӧ��Ĭ�ϵ� application namespace ���� timeout �� batch ���������

```
@Configuration
@EnableApolloConfig
public class AppConfig {

  @Bean
  public TestJavaConfigBean javaConfigBean() {
    return new TestJavaConfigBean();
  }

}
```

**2��Spring Boot �ṩ�� @ConfigurationProperties ������ע�뵽 bean �����С�**

Apollo Ҳ֧�����ַ�ʽ����������ӻ�� redis.cache.expireSeconds �� redis.cache.commandTimeout �ֱ�ע�뵽 SampleRedisConfig �� expireSeconds �� commandTimeout �ֶ��С�

```
@ConfigurationProperties(prefix = "redis.cache")
public class SampleRedisConfig {

  private int expireSeconds;
  private int commandTimeout;

  public void setExpireSeconds(int expireSeconds) {
    this.expireSeconds = expireSeconds;
  }

  public void setCommandTimeout(int commandTimeout) {
    this.commandTimeout = commandTimeout;
  }
}
```

�� Configuration ���а�������ķ�ʽʹ�ã�����Ӧ��Ĭ�ϵ� application namespace ���� redis.cache.expireSeconds �� redis.cache.commandTimeout ���������

```
@Configuration
@EnableApolloConfig
public class AppConfig {

  @Bean
  public SampleRedisConfig sampleRedisConfig() {
    return new SampleRedisConfig();
  }
}
```

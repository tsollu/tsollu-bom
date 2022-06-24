# MyBatis-Plus

[MyBatis-Plus](https://baomidou.com/) ��һ�� MyBatis ����ǿ���ߣ��� MyBatis �Ļ�����ֻ����ǿ�����ı䣬Ϊ�򻯿��������Ч�ʶ�����

kaddo-components-mybatis-plus ģ��ά����һ�׽�Ϊ���õ� MyBatis-Plus ���ã�֧������Ŀ�����п��ٹ�����ʹ�á�

## ���ٿ�ʼ

1������� Spring Boot ��Ŀ����� Maven ������

```
<dependency> 
    <groupId>com.kaddo</groupId>
    <artifactId>kaddo-components-mybatis-plus</artifactId> 
    <version>${kaddo.version}</version>
</dependency>
```

2��Ȼ�󣬸������ݿ�ѡ���������� H2 ���ݿ�Ϊ����

```
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>runtime</scope>
</dependency>
```

3���� `application.properties` �����ļ������ H2 ���ݿ��������ã�

```
# DataSource Configuration
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.schema=classpath:db/schema-h2.sql
spring.datasource.data=classpath:db/data-h2.sql
spring.datasource.url=jdbc:h2:mem:test
spring.datasource.username=root
spring.datasource.password=test
```

4���� `application.properties` �����ļ������ MyBatis-Plus ���ã�

```
## MyBatis-Plus Configuration
mybatis-plus.type-aliases-package=com.baomidou.mybatisplus.samples.quickstart.model
mybatis-plus.mapper-locations=classpath*:/mapper/**/*.xml
mybatis-plus.configuration.map-underscore-to-camel-case=true
mybatis-plus.configuration.default-executor-type=reuse
mybatis-plus.configuration.default-fetch-size=100
mybatis-plus.configuration.default-statement-timeout=30
mybatis-plus.configuration.cache-enabled=true
```

5���� Spring Boot ����������� `@MapperScan` ע�⣬ɨ�� Mapper �ļ��У�

```
@SpringBootApplication
@MapperScan("com.baomidou.mybatisplus.samples.quickstart.mapper")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
```

6����дʵ��������ݲ����

```
@Data
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
```

��д Mapper ���µ� `UserMapper` �ӿڣ�

```
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
```

7����ʼʹ�ã�

```
@SpringBootTest
public class SampleTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        Assert.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }

}
```

## �������

MyBatis �������ļ������˻�����Ӱ�� MyBatis ��Ϊ�����ú�������Ϣ���ٷ������ĵ���https://mybatis.org/mybatis-3/zh/configuration.html

```
## MyBatis-Plus Configuration
# ָ�� MyBatis XML �����ļ���λ�ã�Ĭ��δ���á�
# ע�⣺Spring Boot ��Ŀ��֧�� config-location �� configuration.* ͬʱ���֣���˲��Ƽ�ʹ�ø����ԡ�
mybatis-plus.config-location=classpath*:mybatis-config.xml

# MyBatis Mapper ����Ӧ�� XML �ļ�λ�ã����� Mapper ����Ӧ�� XML �ļ�λ�á�
# Ĭ��ֵ��classpath*:/mapper/**/*.xml�������Ӣ�Ķ��ŷָ���
mybatis-plus.mapper-locations=classpath*:/mapper/**/*.xml

# MyBaits ������ɨ��·����ͨ�������Կ��Ը����е���ע�������Ĭ��δ���á�
# Ҳ����ʹ��ע�� @Alias("author") ��ָ����ı�����
mybatis-plus.type-aliases-package=com.baomidou.mybatisplus.samples.quickstart.model

# TypeHandler ɨ��·������������˸����ԣ�SqlSessionFactoryBean ��Ѹð��������ע��Ϊ��Ӧ�� TypeHandler��
# ͨ�������Զ�������ת����Ĭ��δ���á�
mybatis-plus.type-handlers-package=

# ����ʱ�Ƿ��� MyBatis XML �ļ��Ĵ��ڣ�Ĭ�ϲ���顣
mybatis-plus.check-config-location=false

# ͨ�������Կ�ָ�� MyBatis ��ִ������MyBatis ��ִ�����ܹ������֣�
# ExecutorType.SIMPLE����ִ�������Ͳ�����������飬Ϊÿ������ִ�д���һ���µ�Ԥ������䣨PreparedStatement��
# ExecutorType.REUSE����ִ�������ͻḴ��Ԥ������䣨PreparedStatement��
# ExecutorType.BATCH����ִ�������ͻ�����ִ�����еĸ������
# Ĭ��ֵ��SIMPLE
mybatis-plus.executor-type=SIMPLE

# �Ƿ����Զ��շ���������camel case��ӳ�䣬Ĭ��ֵ��true
mybatis-plus.configuration.map-underscore-to-camel-case=true

# Ĭ��ö�ٴ����࣬��������˸����ԣ�ö�ٽ�ͳһʹ��ָ�����������д���
# Ĭ��Ϊ�洢ö��������ơ�
mybatis-plus.configuration.default-enum-type-handler=org.apache.ibatis.type.EnumTypeHandler

# MyBatis �Զ�ӳ����ԣ�ͨ�������ÿ�ָ�� MyBatis �Ƿ���������Զ�ӳ�����ݱ��ֶ����������ԣ��ܹ��� 3 �ֿ�ѡֵ��
# AutoMappingBehavior.NONE���������Զ�ӳ��
# AutoMappingBehavior.PARTIAL��ֻ�Է�Ƕ�׵� resultMap �����Զ�ӳ��
# AutoMappingBehavior.FULL�������е� resultMap �������Զ�ӳ��
# Ĭ��ֵ��PARTIAL
mybatis-plus.configuration.auto-mapping-behavior=PARTIAL

# ���� Mybatis �������棬Ĭ��ֵ��true��
mybatis-plus.configuration.cache-enabled=true

# Ϊ�����Ľ������ȡ������fetchSize������һ������ֵ���˲���ֻ�����ڲ�ѯ�����б����ǡ�
mybatis-plus.configuration.default-fetch-size=100

# ���ó�ʱʱ�䣬���������ݿ������ȴ����ݿ���Ӧ��������
mybatis-plus.configuration.default-statement-timeout=30
```

## ע��

MyBatis-Plus �ṩ��һЩע�⣬�����ο���https://baomidou.com/pages/223848/

- @TableName
- @TableId
- @TableField
- @Version
- @EnumValue
- @TableLogic
- @SqlParser
- @KeySequence
- @InterceptorIgnore
- @OrderBy

## ���Ĺ���

ʹ�� MyBatis-Plus �ṩ����ǿ����ʱ������Ҫ�� MyBatis-Plus ��һ�����˽⣬����������ʵʵ�� MyBatis ��ԭ�����ܡ�

> MyBatis-Plus �ṩ����ǿ���ܾ���һ���ı����ԣ���Ȼ�Ƽ�ʹ�� MyBatis XML ���õķ�ʽ������ SQL �������������

1��CRUD �ӿ�

- IService<T>: Service CRUD �ӿ�
- BaseMapper<T>: Mapper CRUD �ӿ�
- ActiveRecord ģʽ���ڼ̳� BaseMapper<T> �ӿں�ʵ����̳� Model<T> ���ɽ��� CRUD ������

2������������

- AbstractWrapper
- QueryWrapper
- UpdateWrapper

3����������

**�������ɲ��Ա���ʹ�� INPUT**

����֧�֣�

* DB2KeyGenerator
* H2KeyGenerator
* KingbaseKeyGenerator
* OracleKeyGenerator
* PostgreKeyGenerator

�������֧�ֲ�����������󣬿�ʵ�� IKeyGenerator �ӿ���������չ.

```
@KeySequence(value = "SEQ_ORACLE_STRING_KEY", clazz = String.class)
public class YourEntity {

    @TableId(value = "ID_STR", type = IdType.INPUT)
    private String idStr;

}
```

## ��չ

�Ǳ�Ҫ��ʹ�á�

## ���

MyBatis-Plus �ṩ�˶��������ɲ鿴�ٷ��ĵ���https://baomidou.com/pages/2976a3/

kaddo-components-mybatis-plus ģ���ṩ��һ�������Ĭ�����ã�

```
@Configuration(proxyBeanMethods = false)
@ConditionalOnClass({SqlSessionFactory.class, SqlSessionFactoryBean.class})
public class KaddoMybatisPlusAutoConfiguration {

    /**
     * Ĭ�ϵ� MyBatis-Plus ������ã������������������Ŀ���������ã��鿴 MyBatis-Plus �ٷ��ĵ��˽�������顣
     */
    @Bean
    @ConditionalOnMissingBean(MybatisPlusInterceptor.class)
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // ��ҳ�������ҳ��ҳ��������5000�� - Ĭ�������ƣ�
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor();
        paginationInnerInterceptor.setMaxLimit(5000L);
        interceptor.addInnerInterceptor(paginationInnerInterceptor);
        // �ֹ����������ʵ������ֶ��ϼ��� @Version ע�⣩
        OptimisticLockerInnerInterceptor optimisticLockerInnerInterceptor = new OptimisticLockerInnerInterceptor();
        interceptor.addInnerInterceptor(optimisticLockerInnerInterceptor);
        // ��ȫ�������ɾ���������� update �� delete ��䣬��ֹ�����ȫ����º�ɾ����
        BlockAttackInnerInterceptor blockAttackInnerInterceptor = new BlockAttackInnerInterceptor();
        interceptor.addInnerInterceptor(blockAttackInnerInterceptor);
        return interceptor;
    }

}
```

### ��ҳ�����ʹ��

```
IPage<UserVo> selectPageVo(IPage<?> page, Integer state);
// or (class MyPage extends Ipage<UserVo>{ private Integer state; })
MyPage selectPageVo(MyPage page);
// or
List<UserVo> selectPageVo(IPage<UserVo> page, Integer state);
```

```
<select id="selectPageVo" resultType="xxx.xxx.xxx.UserVo">
    SELECT id,name FROM user WHERE state=#{state}
</select>
```

- ������������� IPage ����ε� IPage ����Ϊnull,��Ϊ ���ص�IPage == ��ε�IPage
- ������������� List ����ε� IPage ����Ϊ null(Ϊ null �򲻷�ҳ),����Ҫ���ֶ� ��ε�IPage.setRecords(���ص� List);
- ��� xml ��Ҫ�� page ��ȡֵ,��Ҫ `page.����` ��ȡ

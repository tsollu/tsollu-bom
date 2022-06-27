# MyBatis Spring Boot Starter

MyBatis ��һ������ĳ־ò��ܣ���֧���Զ��� SQL���洢�����Լ��߼�ӳ�䡣MyBatis ����˼������е� JDBC �����Լ����ò����ͻ�ȡ������Ĺ�����MyBatis ����ͨ���򵥵� XML ��ע�������ú�ӳ��ԭʼ���͡��ӿں� Java POJO��Plain Old Java Objects����ͨ��ʽ Java ����Ϊ���ݿ��еļ�¼��

https://mybatis.org/mybatis-3/zh/index.html

[MyBatis-Spring-Boot-Starter](https://github.com/mybatis/spring-boot-starter/blob/master/mybatis-spring-boot-autoconfigure/src/site/zh/markdown/index.md) ���԰����������� [Spring Boot](https://spring.io/projects/spring-boot) ֮�Ϲ��� MyBatis Ӧ�á�

[MyBatis-PageHelper](https://github.com/pagehelper/Mybatis-PageHelper) �� MyBatis ���ķ�ҳ�����

## ���ٿ�ʼ

1������� Spring Boot ��Ŀ����� Maven ������

```
<dependency> 
    <groupId>com.kaddo</groupId>
    <artifactId>kaddo-components-mybatis</artifactId> 
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

4���� `application.properties` �����ļ������ MyBatis ���ã����ò����� MyBatis-Plus ����һ����MyBatis �ٷ������ĵ���[https://mybatis.org/mybatis-3/zh/configuration.html](https://mybatis.org/mybatis-3/zh/configuration.html)����

```
## MyBatis Configuration
mybatis.type-aliases-package=com.mybatis.samples.quickstart.model
mybatis.mapper-locations=classpath*:/mapper/**/*.xml
mybatis.configuration.map-underscore-to-camel-case=true
mybatis.configuration.default-executor-type=reuse
mybatis.configuration.default-fetch-size=100
mybatis.configuration.default-statement-timeout=30
mybatis.configuration.cache-enabled=true
```

5���� Spring Boot ����������� `@MapperScan` ע�⣬ɨ�� Mapper �ļ��У�

```
@SpringBootApplication
@MapperScan("com.mybatis.samples.quickstart.mapper")
public class SampleMybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SampleMybatisApplication.class, args);
    }

}
```

6����д���ݲ���󣬼�������������� mapper ��

```
@Mapper
public interface CityMapper {

  @Select("SELECT * FROM CITY WHERE state = #{state}")
  City findByState(@Param("state") String state);

}
```

7����ֻ��Ҫ����һ�� Spring boot Ӧ�ã��������������� mapper ע���ȥ�� Spring 4.3 ���Ͽ��ã���

```
@SpringBootApplication
//@MapperScan("com.mybatis.samples.quickstart.mapper")
public class SampleMybatisApplication implements CommandLineRunner {

  private final CityMapper cityMapper;

  public SampleMybatisApplication(CityMapper cityMapper) {
    this.cityMapper = cityMapper;
  }

  public static void main(String[] args) {
    SpringApplication.run(SampleMybatisApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    System.out.println(this.cityMapper.findByState("CA"));
  }

}
```

## ���ʹ�÷�ҳ���

�Ƽ�����д��������鿴�ٷ��ĵ���https://github.com/pagehelper/Mybatis-PageHelper/blob/master/wikis/zh/HowToUse.md

```
// Mapper�ӿڷ�ʽ�ĵ��ã��Ƽ�����ʹ�÷�ʽ��
PageHelper.startPage(1, 10);
List<User> list = userMapper.selectIf(1);

// ISelect �ӿڷ�ʽ
// jdk6,7�÷��������ӿ�
Page<User> page = PageHelper.startPage(1, 10).doSelectPage(new ISelect() {
    @Override
    public void doSelect() {
        userMapper.selectGroupBy();
    }
});

// jdk8 lambda�÷�
Page<User> page = PageHelper.startPage(1, 10).doSelectPage(()-> userMapper.selectGroupBy());

// Ҳ����ֱ�ӷ��� PageInfo��ע�� doSelectPageInfo ������ doSelectPage
pageInfo = PageHelper.startPage(1, 10).doSelectPageInfo(new ISelect() {
    @Override
    public void doSelect() {
        userMapper.selectGroupBy();
    }
});

// ��Ӧ��lambda�÷�
pageInfo = PageHelper.startPage(1, 10).doSelectPageInfo(() -> userMapper.selectGroupBy());

// count��ѯ������һ����ѯ����count��
long total = PageHelper.count(new ISelect() {
    @Override
    public void doSelect() {
        userMapper.selectLike(user);
    }
});

// lambda
total = PageHelper.count(()->userMapper.selectLike(user));
```

## ����ƪ - ����

https://mybatis.org/mybatis-3/zh/configuration.html

MyBatis �������ļ������˻�����Ӱ�� MyBatis ��Ϊ�����ú�������Ϣ�� �����ĵ��Ķ���ṹ���£�

- configuration�����ã�
- properties�����ԣ�
- settings�����ã�
- typeAliases�����ͱ�����
- typeHandlers�����ʹ�������
- objectFactory�����󹤳���
- plugins�������
- environments���������ã�
  - environment������������
  - transactionManager�������������
  - dataSource������Դ��
- databaseIdProvider�����ݿ⳧�̱�ʶ��
- mappers��ӳ������

> ��ʾ���� Spring Boot ��Ŀ�м��� MyBatis-Spring-Boot-Starter �󣬲������� mybatis-config.xml �����ļ������еĴ󲿷����ö�����ʡ�ԣ����б�Ҫ�˽⡣

## ����ƪ - XMLӳ���ļ�

https://mybatis.org/mybatis-3/zh/sqlmap-xml.html

MyBatis ������ǿ�������������ӳ�䣬��������ħ�����ڡ����������쳣ǿ��ӳ������ XML �ļ����Ե���Լ򵥡����������������ͬ���ܵ� JDBC ������жԱȣ������������ʡ���˽��� 95% �Ĵ��롣MyBatis �����ڼ���ʹ�óɱ������û��ܸ�רע�� SQL ���롣

SQL ӳ���ļ�ֻ�к��ٵļ�������Ԫ�أ�����Ӧ�������˳���г�����

- resultMap �C ������δ����ݿ������м��ض��������Ҳ����ǿ���Ԫ�ء�
- sql �C �ɱ�����������õĿ��������顣
- insert �C ӳ�������䡣
- update �C ӳ�������䡣
- delete �C ӳ��ɾ����䡣
- select �C ӳ���ѯ��䡣

**�߼������ӳ�䣺**

```
<!-- �ǳ����ӵĽ��ӳ�� -->
<resultMap id="detailedBlogResultMap" type="Blog">
  <constructor>
    <idArg column="blog_id" javaType="int"/>
  </constructor>
  <result property="title" column="blog_title"/>
  <association property="author" javaType="Author">
    <id property="id" column="author_id"/>
    <result property="username" column="author_username"/>
    <result property="password" column="author_password"/>
    <result property="email" column="author_email"/>
    <result property="bio" column="author_bio"/>
    <result property="favouriteSection" column="author_favourite_section"/>
  </association>
  <collection property="posts" ofType="Post">
    <id property="id" column="post_id"/>
    <result property="subject" column="post_subject"/>
    <association property="author" javaType="Author"/>
    <collection property="comments" ofType="Comment">
      <id property="id" column="comment_id"/>
    </collection>
    <collection property="tags" ofType="Tag" >
      <id property="id" column="tag_id"/>
    </collection>
    <discriminator javaType="int" column="draft">
      <case value="1" resultType="DraftPost"/>
    </discriminator>
  </collection>
</resultMap>
```

**1������**

```
<resultMap id="blogResult" type="Blog">
  <id property="id" column="blog_id" />
  <result property="title" column="blog_title"/>
  <association property="author" javaType="Author">
    <id property="id" column="author_id"/>
    <result property="username" column="author_username"/>
    <result property="password" column="author_password"/>
    <result property="email" column="author_email"/>
    <result property="bio" column="author_bio"/>
  </association>
</resultMap>
```

**2������**

```
<resultMap id="blogResult" type="Blog">
  <id property="id" column="blog_id" />
  <result property="title" column="blog_title"/>
  <collection property="posts" ofType="Post">
    <id property="id" column="post_id"/>
    <result property="subject" column="post_subject"/>
    <result property="body" column="post_body"/>
  </collection>
</resultMap>
```

**3��������**

```
<resultMap id="vehicleResult" type="Vehicle">
  <id property="id" column="id" />
  <result property="vin" column="vin"/>
  <result property="year" column="year"/>
  <result property="make" column="make"/>
  <result property="model" column="model"/>
  <result property="color" column="color"/>
  <discriminator javaType="int" column="vehicle_type">
    <case value="1" resultMap="carResult"/>
    <case value="2" resultMap="truckResult"/>
    <case value="3" resultMap="vanResult"/>
    <case value="4" resultMap="suvResult"/>
  </discriminator>
</resultMap>
```

## ����ƪ - ��̬SQL

https://mybatis.org/mybatis-3/zh/dynamic-sql.html

��̬ SQL �� MyBatis ��ǿ������֮һ�������ʹ�ù� JDBC ���������ƵĿ�ܣ���Ӧ���������ݲ�ͬ����ƴ�� SQL ����ж�ʹ�࣬����ƴ��ʱҪȷ������������ӱ�Ҫ�Ŀո񣬻�Ҫע��ȥ���б����һ�������Ķ��š����ö�̬ SQL�����Գ��װ�������ʹ�ࡣ

ʹ�ö�̬ SQL ����һ�����£��������������κ� SQL ӳ������е�ǿ��Ķ�̬ SQL ���ԣ�MyBatis ��������������һ���Ե������ԡ�

�����֮ǰ�ù� JSTL ���κλ����� XML ���Ե��ı�����������Զ�̬ SQL Ԫ�ؿ��ܻ�о�������ʶ���� MyBatis ֮ǰ�İ汾�У���Ҫ��ʱ���˽������Ԫ�ء���������ǿ��Ļ��� OGNL �ı��ʽ��MyBatis 3 �滻��֮ǰ�Ĵ󲿷�Ԫ�أ���󾫼���Ԫ�����࣬����Ҫѧϰ��Ԫ�������ԭ����һ�뻹Ҫ�١�

* if
* choose (when, otherwise)
* trim (where, set)
* foreach

1��if - ʹ�ö�̬ SQL ����龰�Ǹ����������� where �Ӿ��һ���֡�

```
<select id="findActiveBlogLike"
     resultType="Blog">
  SELECT * FROM BLOG WHERE state = ��ACTIVE��
  <if test="title != null">
    AND title like #{title}
  </if>
  <if test="author != null and author.name != null">
    AND author_name like #{author.name}
  </if>
</select>
```

2��choose (when, otherwise)

```
<select id="findActiveBlogLike"
     resultType="Blog">
  SELECT * FROM BLOG WHERE state = ��ACTIVE��
  <choose>
    <when test="title != null">
      AND title like #{title}
    </when>
    <when test="author != null and author.name != null">
      AND author_name like #{author.name}
    </when>
    <otherwise>
      AND featured = 1
    </otherwise>
  </choose>
</select>
```

3��trim (where, set)

```
<selectid="findActiveBlogLike"
     resultType="Blog">
  SELECT * FROM BLOG
  <where>
    <iftest="state != null">
         state = #{state}
    </if>
    <iftest="title != null">
        AND title like #{title}
    </if>
    <iftest="author != null and author.name != null">
        AND author_name like #{author.name}
    </if>
  </where>
</select>
```

*where* Ԫ��ֻ������Ԫ�ط����κ����ݵ�����²Ų��� ��WHERE�� �Ӿ䡣���ң����Ӿ�Ŀ�ͷΪ ��AND�� �� ��OR����*where* Ԫ��Ҳ�Ὣ����ȥ����

��� *where* Ԫ�����������Ĳ�̫һ������Ҳ����ͨ���Զ��� trim Ԫ�������� *where* Ԫ�صĹ��ܡ����磬�� *where* Ԫ�صȼ۵��Զ��� trim Ԫ��Ϊ��

```
<trim prefix="WHERE" prefixOverrides="AND |OR ">
  ...
</trim>
```

4��foreach - ��̬ SQL ����һ������ʹ�ó����ǶԼ��Ͻ��б������������ڹ��� IN ��������ʱ�򣩡�

```
<select id="selectPostIn" resultType="domain.blog.Post">
  SELECT *
  FROM POST P
  <where>
    <foreach item="item" index="index" collection="list"
        open="ID in (" separator="," close=")" nullable="true">
          #{item}
    </foreach>
  </where>
</select>
```

## ����ƪ - JavaAPI

https://mybatis.org/mybatis-3/zh/java-api.html

- SqlSession - ʹ�� MyBatis ����Ҫ Java �ӿھ��� SqlSession�������ͨ������ӿ���ִ�������ȡӳ����ʵ���͹�������
- SqlSessionFactory - SqlSessionFactory �������������� SqlSession ʵ����

### ע��

MyBatis �ṩ�˺� XML ���õ�ͬ��ע�⣬���˾��û��� XML ���ø���ֱ�ۡ������Ƽ���

- `@CacheNamespace`
- `@Property`
- `@CacheNamespaceRef`
- `@ConstructorArgs`
- `@Arg`
- `@TypeDiscriminator`
- `@Case`
- `@Results`
- `@Result`
- `@One`
- `@Many`
- `@MapKey`
- `@Options`

* `@Insert`
* `@Update`
* `@Delete`
* `@Select`
* `@Param`
* `@SelectKey`
* `@ResultMap`
* `@ResultType`
* `@Flush`

�������չʾ�����ʹ�� @SelectKey ע�����ڲ���ǰ��ȡ���ݿ����е�ֵ��

```
@Insert("insert into table3 (id, name) values(#{nameId}, #{name})")
@SelectKey(statement="call next value for TestSequence", keyProperty="nameId", before=true, resultType=int.class)
int insertTable3(Name name);
```

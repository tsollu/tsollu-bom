# MySQL URL ���Ӳ�������

MySQL URL ���ӿ���Я��������ͨ������ģ�����£�

```
# ͨ��URL�������Ӳ���
spring.datasource.url=jdbc:mysql://localhost:3306/dbname?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8&autoReconnect=true&autoReconnectForPools=true&failOverReadOnly=false&allowMultiQueries=true&zeroDateTimeBehavior=convertToNull
```

## ����˵��

�����ǲ��ֱȽϳ��õĲ�����

### serverTimezone

����ʱ����

MySQL�߰汾����������ÿ��ܻ������⡣UTC��ʾȫ���׼ʱ�䣬���ڽ���ʹ���й���׼ʱ�䣺

```
# �Ϻ�ʱ��
serverTimezone=Asia/Shanghai

# ����ʱ�䶫����
serverTimezone=GMT%2B8
```

### characterEncoding

�����ַ����ı������͡�

```
characterEncoding=utf-8
```

### useUnicode

�Ƿ�ʹ�ñ��뼯��

```
useUnicode=true
```

### autoReconnect

�����ݿ������ж�ʱ���Ƿ��Զ��������ӣ�ȱʡΪ false��

```
autoReconnect=true
```

����������������

- maxReconnects���� autoReconnect ����Ϊ true ʱ���������ӵĴ�����ȱʡΪ 3 �Ρ�
- initialTimeout���� autoReconnect ����Ϊ true ʱ����������֮���ʱ������ȱʡΪ 2 �롣

### autoReconnectForPools

�Ƿ�ʹ��������ݿ����ӳص��������ԡ�ȱʡΪ false��

```
autoReconnectForPools=true
```

### failOverReadOnly

�Զ������ɹ��������Ƿ�����Ϊֻ����ȱʡΪ true��

�� autoReconnect ����Ϊ true ʱ�����齫�˲�������Ϊ false��

```
failOverReadOnly=false
```

### noAccessToProcedureBodies

JDBC ���ô洢����ʱ��Ҫ�� show create procudure Ȩ�޻����б� mysql.proc �� select ��Ȩ�ޣ�������Ӹò�����ȱʡΪ false��

```
noAccessToProcedureBodies=true
```

�����������һЩӰ�죺

- ���ô洢����ʱ����û�����ͼ�飬��Ϊ�ַ������ͣ��������еĲ�����Ϊ in ���ͣ������ڵ��� registerOutParameter ʱ�����׳��쳣��
- �洢���̵Ĳ�ѯ����޷�ʹ�� getXXX(String parameterName) ����ʽ��ȡ��ֻ��ͨ�� getXXX(int parameterIndex) �ķ�ʽ��ȡ��

���õķ�ʽ�ǣ��� MySQL ��ͨ�û�������Ӧ��Ȩ�ޡ�

```
grant select on mysql.proc to 'user'@%;
```

### useSSL

MySQL�߰汾֧���Ƿ�ʹ�� useSSL��ȱʡΪ true��

```
useSSL=true
```

### allowMultiQueries

ȱʡΪ false��

1. ������ SQL ����Я���ֺţ�ʵ�ֶ����ִ�С�
2. ����ִ��������ͬʱ������� SQL ��䡣

```
allowMultiQueries=true
```

### zeroDateTimeBehavior

datetime �ֶε�ֵȫ��Ϊ 0 ʱ�Ĵ���ʽ��

```
zeroDateTimeBehavior=convertToNull
```

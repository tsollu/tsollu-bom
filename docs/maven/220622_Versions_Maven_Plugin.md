# Versions Maven Plugin

��Ŀ�汾�Ź��������Ƕ�ģ����Ŀ�İ汾�ű����Spring Boot �������Ѿ������˸ò���������ٴ����롣

```
<plugin>
  <groupId>org.codehaus.mojo</groupId>
  <artifactId>versions-maven-plugin</artifactId>
  <version>${versions-maven-plugin.version}</version>
</plugin>
```

������Ҫ�����

```
# ��ʾ���е���������
mvn versions:display-dependency-updates

# ��ʾ��Ŀ�Ĳ������
mvn versions:display-plugin-updates

# ��ʾ��Ŀ�����Ը���
mvn versions:display-property-updates

# ������Ŀ�汾��
mvn versions:set -DnewVersion=1.0.1-SNAPSHOT -DgenerateBackupPoms=true

# �������԰汾��
mvn versions:set-property -Dproperty=kaddo.version -DnewVersion=1.0.1-SNAPSHOT -DgenerateBackupPoms=true

# �Ƴ� pom.xml.versionsBackup �����ļ�
mvn versions:commit

# ���� pom.xml.versionsBackup �����ļ��ع�
mvn versions:revert
```

�ٷ��ĵ���

- [Versions Maven Plugin](https://www.mojohaus.org/versions-maven-plugin/index.html)

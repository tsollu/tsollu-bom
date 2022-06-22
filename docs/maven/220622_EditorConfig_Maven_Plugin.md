# EditorConfig Maven Plugin

EditorConfig ����������Ա�ڲ�ͬ�ı༭���� IDE ֮�䶨���ά��һ�µı�����ʽ��

---

[EditorConfig](https://editorconfig.org/) helps maintain consistent coding styles for multiple developers working on the same project across various editors and IDEs. The EditorConfig project consists of **a file format** for defining coding styles and a collection of **text editor plugins** that enable editors to read the file format and adhere to defined styles. EditorConfig files are easily readable and they work nicely with version control systems.

[editorconfig-maven-plugin](https://ec4j.github.io/editorconfig-maven-plugin/index.html) is a Maven plugin for checking whether project files comply with format rules defined in [.editorconfig](https://editorconfig.org/) files and eventually also for fixing the violations.

## ����ʹ��

1������� Spring Boot ��Ŀ��Ŀ¼����� `.editorconfig` �ļ���

```
# EditorConfig helps developers define and maintain consistent
# coding styles between different editors and IDEs
# https://editorconfig.org/

root = true

[*]

# Change these settings to your own preference
indent_style = space
indent_size = 4

# We recommend you to keep these unchanged
end_of_line = lf
charset = utf-8
trim_trailing_whitespace = true
insert_final_newline = true

[*.md]
trim_trailing_whitespace = false
```

2�����ɲ������

```
<build>
	<pluginManagement>
		<plugins>
			<plugin>
				<groupId>org.ec4j.maven</groupId>
				<artifactId>editorconfig-maven-plugin</artifactId>
				<version>0.1.1</version>
				<executions>
					<execution>
						<id>check</id>
						<phase>verify</phase>
						<goals>
							<goal>check</goal>
						</goals>
					</execution>
				</executions>
				<configuration>
					<excludes>
						<!-- Note that maven submodule directories and many non-source file patterns are excluded by default -->
						<!-- see https://github.com/ec4j/editorconfig-maven-plugin/blob/master/ec4j-lint-api/src/main/java/org/ec4j/maven/lint/api/Constants.java#L37 -->
						<!-- You can exclude further files from processing: -->
						<!-- <exclude>src/main/**/*.whatever</exclude> -->
					</excludes>
					<!-- All files are included by default: -->
					<!-- <includes> -->
					<!--   <include>**</include> -->
					<!-- </includes> -->
				</configuration>
			</plugin>
		</plugins>
	</pluginManagement>
</build>
```

3������Ŀ��ʹ�ã�Kaddo-����Ѽ��ɲ������ʹ�ø��ӷ��㣩��

```
<build>
	<plugins>
		<plugin>
			<groupId>org.ec4j.maven</groupId>
			<artifactId>editorconfig-maven-plugin</artifactId>
		</plugin>
	</plugins>
</build>
```

4��ִ�����

```
mvn editorconfig:format
```
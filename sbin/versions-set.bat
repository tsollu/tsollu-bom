::设置项目版本号的脚本

@echo off
echo.
echo [INFO] mvnw versions:set
echo.

cd ..
call mvnw versions:set          -DnewVersion=0.1.1-SNAPSHOT -DgenerateBackupPoms=false
call mvnw versions:set-property -DnewVersion=0.1.1-SNAPSHOT -DgenerateBackupPoms=false -Dproperty=tsollu.version

cd sbin
pause

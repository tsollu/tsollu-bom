::设置项目版本号的脚本

@echo off
echo.
echo [INFO] mvnw versions:set
echo.

cd ..
call mvnw versions:set          -DnewVersion=2022.7.20-SNAPSHOT -DgenerateBackupPoms=false
call mvnw versions:set-property -DnewVersion=2022.7.20-SNAPSHOT -DgenerateBackupPoms=false -Dproperty=tsollu.version

cd sbin
pause

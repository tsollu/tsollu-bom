::项目版本号变更命令

@echo off
echo.
echo [INFO] VERSIONS set
echo.

cd ..
call mvnw versions:set          -DnewVersion=0.1.1-SNAPSHOT -DgenerateBackupPoms=false
call mvnw versions:set-property -DnewVersion=0.1.1-SNAPSHOT -DgenerateBackupPoms=false -Dproperty=kaddo.version

cd sbin
pause

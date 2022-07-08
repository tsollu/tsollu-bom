::本地打包命令

@echo off
echo.
echo [INFO] PACKAGE
echo.

cd ..
call mvnw clean package -DcreateChecksum=true -DskipTests -Dgpg.skip -U

cd sbin
pause

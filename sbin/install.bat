::本地安装命令

@echo off
echo.
echo [INFO] INSTALL
echo.

cd ..
call mvnw clean install -DcreateChecksum=true -DskipTests -Dgpg.skip -U

cd sbin
pause

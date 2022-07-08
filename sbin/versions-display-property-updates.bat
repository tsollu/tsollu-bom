::显示项目属性更新的命令

@echo off
echo.
echo [INFO] VERSIONS display-property-updates
echo.

cd ..
call mvnw versions:display-property-updates

cd sbin
pause

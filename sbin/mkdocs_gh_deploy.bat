::部署项目文档

@echo off
echo.
echo [INFO] mkdocs gh-deploy --force
echo.

cd ..
call mkdocs gh-deploy --force

cd sbin
pause

@echo off
REM ============================================================
REM  add-driver-module.bat
REM  Adiciona o driver PostgreSQL ao WildFly 13
REM ============================================================

setlocal
set "WILDFLY_HOME=C:\kitDesenvolvimento\instaladoires\wildfly-13.0.0.Final"
set "DRIVER_JAR=postgresql-42.2.28.jre7.jar"
set "MODULE_DIR=%WILDFLY_HOME%\modules\org\postgresql\main"

echo.
echo ================================================
echo  Registrando driver PostgreSQL no WildFly...
echo ================================================
echo.

if not exist "%MODULE_DIR%" mkdir "%MODULE_DIR%"

echo Copiando driver para %MODULE_DIR% ...
copy "%~dp0%DRIVER_JAR%" "%MODULE_DIR%" >nul

(
    echo ^<module xmlns="urn:jboss:module:1.1" name="org.postgresql"^>
    echo     ^<resources^>
    echo         ^<resource-root path="%DRIVER_JAR%"/^>
    echo     ^</resources^>
    echo     ^<dependencies^>
    echo         ^<module name="javax.api"/^>
    echo         ^<module name="javax.transaction.api"/^>
    echo     ^</dependencies^>
    echo ^</module^>
) > "%MODULE_DIR%\module.xml"

echo.
echo Driver PostgreSQL registrado com sucesso em:
echo %MODULE_DIR%
echo.
pause
endlocal

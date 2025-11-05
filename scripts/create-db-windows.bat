@echo off
REM Cria o banco DBPROJETOTESTE automaticamente

set PGPASSWORD=admin
set PSQL_PATH="C:\Program Files\PostgreSQL\18\bin\psql.exe"

echo Criando banco DBPROJETOTESTE...
%PSQL_PATH% -U postgres -h localhost -tc "SELECT 1 FROM pg_database WHERE datname='DBPROJETOTESTE';" | findstr /C:"1" >nul
if %ERRORLEVEL% equ 0 (
    echo Banco DBPROJETOTESTE ja existe.
) else (
    %PSQL_PATH% -U postgres -h localhost -c "CREATE DATABASE \"DBPROJETOTESTE\";"
    echo Banco DBPROJETOTESTE criado com sucesso.
)

pause

@echo off
REM ==========================================================
REM Cria os índices do banco DBPROJETOTESTE
REM ==========================================================

set PGPASSWORD=admin
set PSQL_PATH="C:\Program Files\PostgreSQL\18\bin\psql.exe"
set DB_NAME=DBPROJETOTESTE
set SQL_FILE=%~dp0create-indexes.sql

echo Criando índices no banco %DB_NAME%...
%PSQL_PATH% -U postgres -h localhost -d %DB_NAME% -f "%SQL_FILE%"
if %ERRORLEVEL% neq 0 (
    echo ❌ Erro ao criar índices.
    pause
    exit /b %ERRORLEVEL%
)
echo ✅ Índices criados com sucesso.

pause

@echo off
REM ==========================================================
REM Gera 1 milhão de registros de pacientes no banco DBPROJETOTESTE
REM ==========================================================

set PGPASSWORD=admin
set PSQL_PATH="C:\Program Files\PostgreSQL\18\bin\psql.exe"
set DB_NAME=DBPROJETOTESTE
set SQL_FILE=%~dp0popula_tabelas.sql

echo ==========================================================
echo 🧬 Iniciando geração de pacientes no banco %DB_NAME%
echo ==========================================================
echo.

REM Verifica se o arquivo SQL existe
if not exist "%SQL_FILE%" (
    echo ❌ ERRO: O arquivo %SQL_FILE% não foi encontrado.
    echo Certifique-se de que ele está no mesmo diretório que este .bat.
    pause
    exit /b 1
)

REM Executa o script SQL diretamente
echo ⏳ Executando o script para gerar pacientes...
%PSQL_PATH% -U postgres -h localhost -d %DB_NAME% -f "%SQL_FILE%"

if %ERRORLEVEL% neq 0 (
    echo ❌ Erro ao gerar pacientes.
    pause
    exit /b %ERRORLEVEL%
)

echo ✅ Pacientes gerados com sucesso!
echo.
pause
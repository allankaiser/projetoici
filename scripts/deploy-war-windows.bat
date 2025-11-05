
@echo off
set WILDFLY_HOME=C:\kitDesenvolvimento\instaladoires\wildfly-13.0.0.Final
set PROJECT_WAR=%~dp0..\target\projeto-teste.war
echo Deploying %PROJECT_WAR% ...
"%WILDFLY_HOME%\bin\jboss-cli.bat" --connect --command="deploy %PROJECT_WAR% --force"
pause

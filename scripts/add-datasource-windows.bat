
@echo off
set WILDFLY_HOME=C:\kitDesenvolvimento\instaladoires\wildfly-13.0.0.Final
"%WILDFLY_HOME%\bin\jboss-cli.bat" --connect --command="/subsystem=datasources/data-source=ProjetoTesteDS:add(jndi-name=java:/jdbc/ProjetoTesteDS,driver-name=postgresql,connection-url=jdbc:postgresql://localhost:5432/DBPROJETOTESTE,user-name=postgres,password=admin,enabled=true)"
pause

Projeto projeto-teste

Aplicação Java EE 8 com WildFly 13, PrimeFaces, e PostgreSQL, contendo um CRUD completo de Paciente e suporte a criação automática do banco DBPROJETOTESTE.

⚙️ 1. Requisitos

    Antes de iniciar, certifique-se de ter:

    Java 8 (JDK 1.8) instalado e configurado (java -version)

    Apache Maven 3.6+

    PostgreSQL instalado e rodando (porta padrão 5432)

    WildFly 13.0.0.Final instalado em:

    C:\kitDesenvolvimento\instaladoires\wildfly-13.0.0.Final



🐘 2. Banco de Dados: DBPROJETOTESTE

    O projeto já possui um script automático que cria o banco antes do deploy.

    🔹 Script de criação automática
=========================================================================================
    1. Iniciar manualmente o servidor antes
    Abra um Prompt de Comando e rode:
    C:\kitDesenvolvimento\instaladoires\wildfly-13.0.0.Final\bin\standalone.bat
    Deixe o servidor rodando nessa janela (não feche).

    2. Execute no prompt:
    cd C:\kitDesenvolvimento\projeto\projetoici\scripts
    create-db-windows.bat
    .\create-db-windows.bat
    O script usa:
    Usuário: postgres
    Senha: admin
    Banco: DBPROJETOTESTE
    Se o banco já existir, ele será ignorado automaticamente.

🧱 3. Adicionar o driver PostgreSQL ao WildFly
    O driver JDBC é necessário para o datasource.
    🪄 Passo automático:
    Execute o comando:
    cd C:\kitDesenvolvimento\projeto\projetoici\scripts
    add-driver-module.bat
    Esse script:
    Copia o postgresql-42.x.x.jar para o módulo do WildFly
    Registra o módulo org.postgresql no servidor

🌐 4. Registrar o DataSource no WildFly
    Execute:
    cd C:\kitDesenvolvimento\projeto\projetoici\scripts
    add-datasource-windows.bat
    Este comando cria o datasource JNDI:
    java:/jdbc/ProjetoTesteDS
    Configurações:
    URL: jdbc:postgresql://localhost:5432/DBPROJETOTESTE
    Driver: postgresql
    Usuário: postgres
    Senha: admin
    Você pode validar a configuração:
    C:\kitDesenvolvimento\instaladoires\wildfly-13.0.0.Final\bin\jboss-cli.bat --connect --command="/subsystem=datasources:read-resource"cd

🏗️ 5. Build do projeto
    No diretório do projeto, execute:
    mvn clean package -Pwith-driver
    O profile with-driver embute o driver do PostgreSQL dentro do .war.
    O arquivo gerado estará em:
    target\projeto-teste.war

🚀 6. Deploy automático via CLI
    Com o WildFly rodando (standalone.bat), execute:
    cd C:\kitDesenvolvimento\projeto\projetoici\scripts
    deploy-war-windows.bat
    Esse script:
    Verifica se o WAR existe
    Conecta ao WildFly
    Executa o deploy forçado do .war

    7. Criar índices
    Execute este passo após o deploy.
    cd C:\kitDesenvolvimento\projeto\projetoici\scripts
    create-indexes-windows.bat

    8. rodar script de insert

✅ 8. Acessando o sistema

Após o deploy CRIAÇÃO DOS INDICES, acesse no navegador:

👉 http://localhost:8080/projeto-teste









O projeto inclui uma tela inicial index.xhtml e o CRUD de Paciente (/pacientes.xhtml).

🧩 8. Estrutura de diretórios
projeto-teste/
├── pom.xml
├── README.md
├── src/
│   ├── main/java/com/exemplo/
│   │   ├── model/Paciente.java
│   │   ├── dao/PacienteDAO.java
│   │   ├── bean/PacienteBean.java
│   └── main/webapp/
│       ├── index.xhtml
│       ├── pacientes.xhtml
│       └── WEB-INF/
│           ├── web.xml
│           └── faces-config.xml
└── scripts/
    ├── create-db-windows.bat
    ├── add-driver-module.bat
    ├── add-datasource-windows.bat
    └── deploy-war-windows.bat

🧩 9. Logs úteis

Caso haja erro no deploy, consulte:

C:\kitDesenvolvimento\instaladoires\wildfly-13.0.0.Final\standalone\log\server.log

🧠 10. Dicas finais

Certifique-se de que o PostgreSQL está rodando antes de executar o deploy.

O banco DBPROJETOTESTE será criado automaticamente, mas se desejar recriá-lo, rode novamente create-db-windows.bat.

Se mudar o caminho do WildFly, edite as variáveis no início dos scripts .bat.




SEGUE MEU CHECKLIST PARA CONSIDERAÇÕES: 
1 Verifique se os seguintes arquivos estão corretos:

Receita.java

Tem @Entity OK

Relacionamento com Paciente via @ManyToOne OK 

Relacionamento com ReceitaItem via @OneToMany(mappedBy="receita", cascade=CascadeType.ALL, orphanRemoval=true) OK MAS CONTENDO A CONFIGURAÇÃO fetch = FetchType.LAZY

dataPrescricao é LocalDate OK 

ReceitaItem.java

Tem @ManyToOne com Receita OK

Tem @ManyToOne com Medicamento  OK 

Medicamento.java e Paciente.java

Ambos são @Entity com @Id e @GeneratedValue OK

2. Persistência (persistence.xml)

Confira se o seu persistence.xml: ADICIONEI 
    <class>com.exemplo.model.Medicamento</class>
    <class>com.exemplo.model.Receita</class>
    <class>com.exemplo.model.ReceitaItem</class>
E TENHO UMA PROPRIEDADE A MAIS, A PROPRIEDADE E <property name="hibernate.dialect" value="org.hibernate.dialect.PostgreSQLDialect"/>

3. Beans e Services

ReceitaBean → está anotado com @Named e @ViewScoped OK

ReceitaService, PacienteService, MedicamentoService, ReceitaDAO → todos com @Stateless OK

Injeções @Inject todas com classes válidas e do mesmo pacote base: PARECE OK, O PROJETO NÃO APRESENTA ERRO DE PACOTE

4. Converters JSF OK

APOS CONFERIR O CHEKLIST ACIMA FIZ O BUILD DO PROJETO E O DEPLOY.

TERIA ALGUMA OBSERVAÇAO, GOSTARIA DE VER O LOG DO DEPLOY? 



bacana a pagina de receitas abriu, cliquei no botao "Nova receita" e o Dialog abriu.
segue o trecho do Dialog:
<p:dialog id="dlgReceita"
                        widgetVar="dlgReceita"
                        header="Nova Receita"
                        modal="true"
                        width="700"
                        appendTo="@(body)"
                        resizable="false">

                    <!-- 🔸 Removemos o <h:form> interno -->
                    <p:panelGrid columns="2" columnClasses="label,value" styleClass="grid-form" cellpadding="5">

                        <!-- Paciente -->
                        <p:outputLabel for="paciente" value="Paciente:" />
                        <p:autoComplete id="paciente"
                                        value="#{receitaBean.receita.paciente}"
                                        completeMethod="#{receitaBean.buscarPacientes}"
                                        var="p"
                                        itemLabel="#{p.nome} - CPF: #{p.cpf}"
                                        itemValue="#{p}"
                                        converter="pacienteConverter"
                                        forceSelection="true"
                                        dropdown="true"
                                        placeholder="Digite o nome ou CPF..."
                                        minQueryLength="2"
                                        cache="false"
                                        scrollHeight="300"/>

                        <!-- Medicamento -->
                        <p:outputLabel for="medicamento" value="Medicamento:" />
                        <p:autoComplete id="medicamento"
                                        value="#{receitaBean.novoItem.medicamento}"
                                        completeMethod="#{receitaBean.buscarMedicamentos}"
                                        var="m"
                                        itemLabel="#{m.nome}"
                                        itemValue="#{m}"
                                        converter="medicamentoConverter"
                                        forceSelection="true"
                                        dropdown="true"
                                        placeholder="Digite o nome do medicamento..."
                                        minQueryLength="2"
                                        cache="false"
                                        scrollHeight="300"/>

                        <!-- Quantidade -->
                        <p:outputLabel for="qtde" value="Quantidade:" />
                        <p:spinner id="qtde"
                                value="#{receitaBean.novoItem.quantidade}"
                                min="1"
                                max="999"
                                step="1"
                                style="width:100px" />

                        <p:outputLabel />
                        <p:commandButton value="Adicionar Medicamento"
                                        icon="pi pi-plus"
                                        action="#{receitaBean.adicionarItem}"
                                        update="itensReceita"
                                        process="@form"
                                        styleClass="p-button-outlined p-button-info" />
                    </p:panelGrid>

                    <!-- Lista de Itens -->
                    <p:dataTable id="itensReceita"
                                value="#{receitaBean.receita.itens}"
                                var="item"
                                emptyMessage="Nenhum medicamento adicionado"
                                style="margin-top:15px">

                        <p:column headerText="Medicamento">
                            <h:outputText value="#{item.medicamento.nome}" />
                        </p:column>

                        <p:column headerText="Quantidade" style="width:100px;text-align:center;">
                            <h:outputText value="#{item.quantidade}" />
                        </p:column>

                        <p:column style="width:50px;text-align:center;">
                            <p:commandButton icon="pi pi-trash"
                                            action="#{receitaBean.removerItem(item)}"
                                            update="itensReceita"
                                            title="Remover"
                                            styleClass="p-button-danger p-button-rounded p-button-sm" />
                        </p:column>
                    </p:dataTable>

                    <p:separator style="margin-top:10px;margin-bottom:10px;" />

                    <p:commandButton value="Salvar Receita"
                                    icon="pi pi-check"
                                    action="#{receitaBean.salvar}"
                                    update="tabelaReceitas"
                                    oncomplete="if(!args.validationFailed) PF('dlgReceita').hide();"
                                    styleClass="p-button-success" />

                </p:dialog>
Entao para teste, não selecionei nem um medicamento, segue trecho seleção de medicamento:
<p:autoComplete id="medicamento"
                                        value="#{receitaBean.novoItem.medicamento}"
                                        completeMethod="#{receitaBean.buscarMedicamentos}"
                                        var="m"
                                        itemLabel="#{m.nome}"
                                        itemValue="#{m}"
                                        converter="medicamentoConverter"
                                        forceSelection="true"
                                        dropdown="true"
                                        placeholder="Digite o nome do medicamento..."
                                        minQueryLength="2"
                                        cache="false"
                                        scrollHeight="300"/>

Logo entao cliquei no botao ""Adicionar Medicamento"" segue trecho do botao:
<p:commandButton value="Adicionar Medicamento"
                                        icon="pi pi-plus"
                                        action="#{receitaBean.adicionarItem}"
                                        update="itensReceita"
                                        process="@form"
                                        styleClass="p-button-outlined p-button-info" />
Assim eu esperava receber a mensagem informando "Selecione um medicamento.", conforme o metodo da receitaBean.adicionarItem,
segue trecho do metodo:
public void adicionarItem() {
        if (novoItem.getMedicamento() == null) {
            addMensagem("Selecione um medicamento.", FacesMessage.SEVERITY_WARN);
            return;
        }

        boolean duplicado = receita.getItens().stream()
                .anyMatch(item -> item.getMedicamento().equals(novoItem.getMedicamento()));

        if (duplicado) {
            addMensagem("Este medicamento já foi adicionado.", FacesMessage.SEVERITY_WARN);
            return;
        }

        novoItem.setReceita(receita);
        receita.getItens().add(novoItem);
        novoItem = new ReceitaItem();
    }
Mas em tela nao foi exibida a mensagem. Teria possivel considerações sobre o ocorrido?








    
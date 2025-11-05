package com.exemplo.bean;

import com.exemplo.model.*;
import com.exemplo.service.*;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@ViewScoped
public class ReceitaBean implements Serializable {

    //TODO REMOVER 
    private static final Logger LOGGER = Logger.getLogger(ReceitaBean.class.getName());

    static {
        ConsoleHandler handler = new ConsoleHandler();
        handler.setLevel(Level.ALL);
        LOGGER.addHandler(handler);
        LOGGER.setLevel(Level.ALL);
    }
    //TODO REMOVER 


    private static final long serialVersionUID = 1L;

    @Inject
    private ReceitaService receitaService;

    @Inject
    private PacienteService pacienteService;

    @Inject
    private MedicamentoService medicamentoService;

    private Receita receita;
    private ReceitaItem novoItem;

    private List<Receita> receitas;

    @PostConstruct
    public void init() {
        receitas = receitaService.listar();
        receita = new Receita();
        novoItem = new ReceitaItem();
    }

    public void novo() {
        receita = new Receita();
        receita.setDataPrescricao(LocalDate.now());
        receita.setItens(new ArrayList<>());
        novoItem = new ReceitaItem();
    }

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

    public void removerItem(ReceitaItem item) {
        receita.getItens().remove(item);
    }

    public void salvar() {
        try {
            if (receita.getPaciente() == null) {
                addMensagem("Selecione um paciente.", FacesMessage.SEVERITY_ERROR);
                return;
            }

            if (receita.getItens().isEmpty()) {
                addMensagem("Adicione ao menos um medicamento à receita.", FacesMessage.SEVERITY_ERROR);
                return;
            }

            receitaService.salvar(receita);
            receitas = receitaService.listar();

            addMensagem("Receita salva com sucesso!", FacesMessage.SEVERITY_INFO);
            novo();
        } catch (Exception e) {
            addMensagem("Erro ao salvar receita: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    // 🔍 AutoComplete de Pacientes
    public List<Paciente> buscarPacientes(String termo) {
        return pacienteService.buscarPorNomeOuCpf(termo);
    }

    // 🔍 AutoComplete de Medicamentos
    public List<Medicamento> buscarMedicamentos(String termo) {
        return medicamentoService.buscarPorNome(termo);
    }

    private void addMensagem(String msg, FacesMessage.Severity severity) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, msg, null));
    }

    // Getters e Setters
    public Receita getReceita() { return receita; }
    public void setReceita(Receita receita) { this.receita = receita; }

    public ReceitaItem getNovoItem() { return novoItem; }
    public void setNovoItem(ReceitaItem novoItem) { this.novoItem = novoItem; }

    public List<Receita> getReceitas() { return receitas; }
    public void setReceitas(List<Receita> receitas) { this.receitas = receitas; }
}

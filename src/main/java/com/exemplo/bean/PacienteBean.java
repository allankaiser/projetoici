package com.exemplo.bean;

import com.exemplo.model.Paciente;
import com.exemplo.service.PacienteService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.PersistenceException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Named
@ViewScoped
public class PacienteBean implements Serializable {

    private Paciente paciente;
    private List<Paciente> pacientes;
    private String nomeFiltro;
    private String cpfFiltro;
    private List<String> sexos;

    private int first = 0;
    private int pageSize = 10;
    private long totalRecords = 0;

    @Inject
    private PacienteService service;

    @PostConstruct
    public void init() {
        sexos = Arrays.asList("Masculino", "Feminino", "Outro");
        novo();
        carregarLista();
    }

    public void novo() {
        paciente = new Paciente();
        paciente.setDtInclusao(LocalDate.now());
        paciente.setAtivo(true);
        nomeFiltro = null;
        cpfFiltro = null;
    }

    public void carregarLista() {
        pacientes = service.buscarComFiltro(nomeFiltro, cpfFiltro, first, pageSize);
        totalRecords = service.countComFiltro(nomeFiltro, cpfFiltro);
    }

    public void pesquisar() {
        this.first = 0;
        carregarLista();
    }

    public void paginar(int newFirst) {
        if (newFirst < 0) newFirst = 0;
        this.first = newFirst;
        carregarLista();
    }

    public void salvar() {
        try {
            service.salvar(paciente);
            carregarLista();
            novo();
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Paciente salvo com sucesso!"));
        } catch (PersistenceException ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", ex.getMessage()));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Erro ao salvar paciente."));
        }
    }

    public void editar(Paciente p) {
        paciente = p;
        if (p.getSexo() != null) {
            String s = p.getSexo().trim().toUpperCase();
            if (s.startsWith("M")) {
                paciente.setSexo("Masculino");
            } else if (s.startsWith("F")) {
                paciente.setSexo("Feminino");
            } else if (s.startsWith("O")) {
                paciente.setSexo("Outro");
            }
        }
    }

     public void excluir(Paciente p) {
        try {
            service.excluir(p);
            carregarLista();
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Paciente excluído com sucesso!"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Erro ao excluir paciente."));
        }
    }

    // getters and setters
    public Paciente getPaciente() { return paciente; }
    public List<Paciente> getPacientes() { return pacientes; }
    public String getNomeFiltro() { return nomeFiltro; }
    public void setNomeFiltro(String nomeFiltro) { this.nomeFiltro = nomeFiltro; }
    public String getCpfFiltro() { return cpfFiltro; }
    public void setCpfFiltro(String cpfFiltro) { this.cpfFiltro = cpfFiltro; }
    public List<String> getSexos() {
        return sexos;
    }

    public void setSexos(List<String> sexos) {
        this.sexos = sexos;
    }

    public int getFirst() { return first; }
    public int getPageSize() { return pageSize; }
    public void setPageSize(int pageSize) { this.pageSize = pageSize; }
    public long getTotalRecords() { return totalRecords; }
}

    

package com.exemplo.bean;

import com.exemplo.dto.RelatorioMedicamentoDTO;
import com.exemplo.model.Paciente;
import com.exemplo.service.RelatorioService;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class RelatorioMedicamentosBean implements Serializable {

    private Paciente pacienteSelecionado;
    private List<RelatorioMedicamentoDTO> listaRelatorio;

    @Inject
    private RelatorioService relatorioService;

    @PostConstruct
    public void init() {
        listaRelatorio = new ArrayList<>();
    }

    public void filtrar() {
        listaRelatorio.clear();
        if (pacienteSelecionado == null) {
            //relatorioService.addMensagem("Selecione um paciente para filtrar.", "warn");
            return;
        }
        listaRelatorio = relatorioService.buscarPorPaciente(pacienteSelecionado);
    }

    public List<Paciente> buscarPacientes(String nome) {
        return relatorioService.buscarPacientesPorNome(nome);
    }

    // Getters e Setters
    public Paciente getPacienteSelecionado() {
        return pacienteSelecionado;
    }

    public void setPacienteSelecionado(Paciente pacienteSelecionado) {
        this.pacienteSelecionado = pacienteSelecionado;
    }

    public List<RelatorioMedicamentoDTO> getListaRelatorio() {
        return listaRelatorio;
    }

    public void setListaRelatorio(List<RelatorioMedicamentoDTO> listaRelatorio) {
        this.listaRelatorio = listaRelatorio;
    }
}

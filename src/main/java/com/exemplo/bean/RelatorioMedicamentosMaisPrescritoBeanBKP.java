package com.exemplo.bean;

import com.exemplo.dto.MedicamentoMaisPrescritoDTO;
import com.exemplo.dto.RelatorioPacienteDTO;
import com.exemplo.service.RelatorioService;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class RelatorioMedicamentosMaisPrescritoBeanBKP implements Serializable {

    private List<MedicamentoMaisPrescritoDTO> medicamentosMaisPrescritos;
    private List<RelatorioPacienteDTO> pacientesMaisMedicamentos;

    @Inject
    private RelatorioService relatorioService;

    private String abaAtiva;

    @PostConstruct
    public void init() {
        carregarMedicamentosMaisPrescritos(); // Aba inicial
        abaAtiva = "medicamentos";
    }

    public void carregarMedicamentosMaisPrescritos() {
        medicamentosMaisPrescritos = relatorioService.medicamentosMaisPrescritos();
        abaAtiva = "medicamentos";
    }

     public void carregarPacientesMaisMedicamentos() {
        pacientesMaisMedicamentos = relatorioService.buscarPacientesComMaisMedicamentos();
        abaAtiva = "pacientes";
    }

    public List<MedicamentoMaisPrescritoDTO> getMedicamentosMaisPrescritos() {
        return medicamentosMaisPrescritos;
    }

    public List<RelatorioPacienteDTO> getPacientesMaisMedicamentos() {
        return pacientesMaisMedicamentos;
    }

    public String getAbaAtiva() {
        return abaAtiva;
    }

    public void setAbaAtiva(String abaAtiva) {
        this.abaAtiva = abaAtiva;
    }
}

package com.exemplo.bean;

import com.exemplo.dto.MedicamentoMaisPrescritoDTO;
import com.exemplo.dto.RelatorioPacienteDTO;
import com.exemplo.service.RelatorioService;
import org.primefaces.event.TabChangeEvent;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class RelatorioMedicamentosMaisPrescritoBean implements Serializable {

    private List<MedicamentoMaisPrescritoDTO> medicamentosMaisPrescritos;
    private List<RelatorioPacienteDTO> pacientesMaisMedicamentos;

    @Inject
    private RelatorioService relatorioService;

    private int activeIndex = 0;

    @PostConstruct
    public void init() {
        carregarMedicamentosMaisPrescritos();
        activeIndex = 0; // aba inicial
    }

    public void carregarMedicamentosMaisPrescritos() {
        medicamentosMaisPrescritos = relatorioService.medicamentosMaisPrescritos();
        
    }

    public void carregarPacientesMaisMedicamentos() {
        pacientesMaisMedicamentos = relatorioService.buscarPacientesComMaisMedicamentos();
    }

    public void onTabChange(TabChangeEvent event) {
        if (event.getTab().getId().equals("abaMedicamentos")) {
            activeIndex = 0;
            carregarMedicamentosMaisPrescritos();
        } else {
            activeIndex = 1;
            carregarPacientesMaisMedicamentos();
        }
    }

    public int getActiveIndex() {
        return activeIndex;
    }

    public void setActiveIndex(int activeIndex) {
        this.activeIndex = activeIndex;
    }

    public List<MedicamentoMaisPrescritoDTO> getMedicamentosMaisPrescritos() {
        return medicamentosMaisPrescritos;
    }

    public List<RelatorioPacienteDTO> getPacientesMaisMedicamentos() {
        return pacientesMaisMedicamentos;
    }
}

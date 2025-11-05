package com.exemplo.service;

import com.exemplo.dto.MedicamentoMaisPrescritoDTO;
import com.exemplo.dto.RelatorioMedicamentoDTO;
import com.exemplo.dto.RelatorioPacienteDTO;
import com.exemplo.dao.ReceitaDAO;
import com.exemplo.model.Paciente;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class RelatorioService {

    @Inject
    private ReceitaDAO receitaDAO;

    public List<RelatorioMedicamentoDTO> buscarPorPaciente(Paciente paciente) {
        return receitaDAO.contarMedicamentosPorPaciente(paciente);
    }

    public List<Paciente> buscarPacientesPorNome(String nome) {
        return receitaDAO.buscarPacientesPorNome(nome);
    }

    public List<MedicamentoMaisPrescritoDTO> medicamentosMaisPrescritos() {
        return receitaDAO.medicamentosMaisPrescritos();
    }

    public List<RelatorioPacienteDTO> buscarPacientesComMaisMedicamentos() {
        return receitaDAO.buscarPacientesComMaisMedicamentos();
    }
}

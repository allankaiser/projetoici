package com.exemplo.service;

import com.exemplo.dao.PacienteDAO;
import com.exemplo.model.Paciente;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import java.time.LocalDate;
import java.util.List;

@Stateless
public class PacienteService {

    @Inject
    private PacienteDAO dao;

    public void salvar(Paciente p) {
        // Remove tudo que não é número
        if (p.getCpf() != null) {
            p.setCpf(p.getCpf().replaceAll("\\D", ""));
        }

        // Converte sexo para apenas 1 caractere (M ou F)
        if (p.getSexo() != null) {
            String s = p.getSexo().trim().toUpperCase();
            if (s.startsWith("M")) {
                p.setSexo("M");
            } else if (s.startsWith("F")) {
                p.setSexo("F");
            } else if (s.startsWith("O")) {
                p.setSexo("O");
            } else {
                throw new PersistenceException("Sexo inválido. Use Masculino ou Feminino.");
            }
        }

        // validação simples de duplicidade de CPF
        Paciente existente = dao.porCpf(p.getCpf());
        if (existente != null && (p.getIdPaciente() == null || !existente.getIdPaciente().equals(p.getIdPaciente()))) {
            throw new PersistenceException("CPF já cadastrado");
        }
        if (p.getDtInclusao() == null) {
            p.setDtInclusao(LocalDate.now());
        }
        dao.salvar(p);
    }

    public void excluir(Paciente p) {
        dao.excluir(p);
    }

    public List<Paciente> listar(int first, int pageSize) {
        return dao.listar(first, pageSize);
    }

    public Long count() {
        return dao.count();
    }
    
    public List<Paciente> buscarPorNomeOuCpf(String filtro) {
        return dao.buscarPorNomeOuCpf(filtro);
    }

    public List<Paciente> buscarComFiltro(String nome, String cpf, int first, int pageSize) {
        return dao.buscarComFiltro(nome, cpf, first, pageSize);
    }

    public Long countComFiltro(String nome, String cpf) {
        return dao.countComFiltro(nome, cpf);
    }

    public Paciente buscarPorId(Long id) {
        return dao.buscarPorId(id);
    }
}

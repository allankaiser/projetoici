package com.exemplo.dao;

import com.exemplo.dto.MedicamentoMaisPrescritoDTO;
import com.exemplo.dto.RelatorioMedicamentoDTO;
import com.exemplo.dto.RelatorioPacienteDTO;
import com.exemplo.model.Paciente;
import com.exemplo.model.Receita;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ReceitaDAO {

    @PersistenceContext
    private EntityManager em;

    public void salvar(Receita receita) {
        if (receita.getIdReceita() == null) {
            em.persist(receita);
        } else {
            em.merge(receita);
        }
    }

    public List<Receita> listar() {
        return em.createQuery(
            "SELECT DISTINCT r FROM Receita r " +
            "JOIN FETCH r.paciente " +
            "LEFT JOIN FETCH r.itens i " +
            "LEFT JOIN FETCH i.medicamento", Receita.class)
            .getResultList();
    }

    public List<RelatorioMedicamentoDTO> contarMedicamentosPorPaciente(Paciente paciente) {
        String jpql = "SELECT new com.exemplo.dto.RelatorioMedicamentoDTO(r.paciente.nome, COUNT(i)) " +
                    "FROM Receita r JOIN r.itens i " +
                    "WHERE r.paciente = :paciente " +
                    "GROUP BY r.paciente.nome";
        return em.createQuery(jpql, RelatorioMedicamentoDTO.class)
                .setParameter("paciente", paciente)
                .getResultList();
    }

    public List<Paciente> buscarPacientesPorNome(String nome) {
        String jpql = "SELECT p FROM Paciente p WHERE LOWER(p.nome) LIKE LOWER(:nome)";
        return em.createQuery(jpql, Paciente.class)
                .setParameter("nome", "%" + nome + "%")
                .getResultList();
    }

    public List<MedicamentoMaisPrescritoDTO> medicamentosMaisPrescritos() {
        return em.createQuery(
            "SELECT new com.exemplo.dto.MedicamentoMaisPrescritoDTO(m.nome, COUNT(i.medicamento.idMedicamento)) " +
            "FROM ReceitaItem i " +
            "JOIN i.medicamento m " +
            "GROUP BY m.nome " +
            "ORDER BY COUNT(i.medicamento.idMedicamento) DESC",
            MedicamentoMaisPrescritoDTO.class)
        .setMaxResults(2)
        .getResultList();
    }

    public List<RelatorioPacienteDTO> buscarPacientesComMaisMedicamentos() {
    return em.createQuery(
        "SELECT new com.exemplo.dto.RelatorioPacienteDTO(p.nome, COUNT(i.medicamento)) " +
        "FROM Receita r " +
        "JOIN r.paciente p " +
        "JOIN r.itens i " +
        "GROUP BY p.nome " +
        "ORDER BY COUNT(i.medicamento) DESC",
        RelatorioPacienteDTO.class)
        .setMaxResults(2)
        .getResultList();
}

}

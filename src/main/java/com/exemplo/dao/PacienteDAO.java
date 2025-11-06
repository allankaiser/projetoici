package com.exemplo.dao;

import com.exemplo.model.Paciente;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import java.util.Collections;
import java.util.List;

@Stateless
public class PacienteDAO {

    @PersistenceContext(unitName = "ProjetoTestePU")
    private EntityManager em;

    public void salvar(Paciente p) {
        if (p.getIdPaciente() == null) em.persist(p);
        else em.merge(p);
    }

    public void excluir(Paciente p) {
        p.setAtivo(false);
        em.merge(p);
    }

    public Paciente porCpf(String cpf) {
        if (cpf == null) return null;
        TypedQuery<Paciente> q = em.createQuery(
            "SELECT p FROM Paciente p WHERE p.cpf = :cpf", Paciente.class
        );
        q.setParameter("cpf", cpf.replaceAll("\\D", ""));
        List<Paciente> list = q.getResultList();
        return list.isEmpty() ? null : list.get(0);
    }

    public List<Paciente> listar(int first, int pageSize) {
        TypedQuery<Paciente> q = em.createQuery("SELECT p FROM Paciente p ORDER BY p.nome", Paciente.class);
        q.setFirstResult(first);
        q.setMaxResults(pageSize);
        return q.getResultList();
    }

    public Long count() {
        TypedQuery<Long> q = em.createQuery("SELECT COUNT(p) FROM Paciente p", Long.class);
        return q.getSingleResult();
    }

    public List<Paciente> buscarComFiltro(String nome, String cpf, int first, int pageSize) {
        String jpql = "SELECT p FROM Paciente p WHERE " +
                    "(:nome = '' OR LOWER(p.nome) LIKE LOWER(CONCAT('%', :nome, '%'))) AND " + 
                    "(:cpf = '' OR p.cpf = :cpf) ORDER BY p.nome ";

        TypedQuery<Paciente> q = em.createQuery(jpql, Paciente.class);

        q.setParameter("nome", (nome != null && !nome.trim().isEmpty()) ? nome.trim() : "");
        q.setParameter("cpf", (cpf != null && !cpf.trim().isEmpty()) ? cpf.replaceAll("\\D", "") : "");

        q.setFirstResult(first);
        q.setMaxResults(pageSize);

        return q.getResultList();
    }

    public Long countComFiltro(String nome, String cpf) {
        String jpql = "SELECT COUNT(p) FROM Paciente p WHERE " +
                "(:nome = '' OR LOWER(p.nome) LIKE LOWER(CONCAT('%', :nome, '%'))) AND " +
                "(:cpf = '' OR p.cpf = :cpf)";

        TypedQuery<Long> q = em.createQuery(jpql, Long.class);

        q.setParameter("nome", (nome != null && !nome.trim().isEmpty()) ? nome.trim() : "");
        q.setParameter("cpf", (cpf != null && !cpf.trim().isEmpty()) ? cpf.replaceAll("\\D", "") : "");

        return q.getSingleResult();
    }

    public Paciente buscarPorId(Long id) {
        return em.find(Paciente.class, id);
    }

    public List<Paciente> buscarPorNomeOuCpf(String filtro) {
        if (filtro == null || filtro.trim().isEmpty()) {
            return Collections.emptyList();
        }

        String filtroFormatado = "%" + filtro.toLowerCase() + "%";

        return em.createQuery(
                "SELECT p FROM Paciente p " +
                "WHERE LOWER(p.nome) LIKE :filtro " + "ORDER BY p.nome",
                Paciente.class
            )
            .setParameter("filtro", filtroFormatado)
            .getResultList();
    }

}

package com.exemplo.dao;

import com.exemplo.model.Medicamento;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class MedicamentoDAO {

    @PersistenceContext
    private EntityManager em;

    public void salvar(Medicamento m) {
        if (m.getIdMedicamento() == null) {
            em.persist(m);
        } else {
            em.merge(m);
        }
    }

    public void excluir(Medicamento m) {
        Medicamento ref = em.find(Medicamento.class, m.getIdMedicamento());
        if (ref != null) {
            em.remove(ref);
        }
    }

    public List<Medicamento> listar() {
        return em.createQuery("SELECT m FROM Medicamento m ORDER BY m.idMedicamento DESC", Medicamento.class)
                 .setMaxResults(100) // evita carregar tudo acidentalmente
                 .getResultList();
    }

    /**
     * Busca paginada com filtro de nome.
     */
    public List<Medicamento> buscarComFiltro(String nome, int first, int pageSize) {
        StringBuilder jpql = new StringBuilder("SELECT m FROM Medicamento m WHERE 1=1");

        if (nome != null && !nome.trim().isEmpty()) {
            jpql.append(" AND LOWER(m.nome) LIKE LOWER(CONCAT('%', :nome, '%'))");
        }

        jpql.append(" ORDER BY m.idMedicamento DESC");

        TypedQuery<Medicamento> query = em.createQuery(jpql.toString(), Medicamento.class);

        if (nome != null && !nome.trim().isEmpty()) {
            query.setParameter("nome", nome.trim());
        }

        query.setFirstResult(first);
        query.setMaxResults(pageSize);

        return query.getResultList();
    }

    public Long countComFiltro(String nome) {
        StringBuilder jpql = new StringBuilder("SELECT COUNT(m) FROM Medicamento m WHERE 1=1");

        if (nome != null && !nome.trim().isEmpty()) {
            jpql.append(" AND LOWER(m.nome) LIKE LOWER(CONCAT('%', :nome, '%'))");
        }

        TypedQuery<Long> query = em.createQuery(jpql.toString(), Long.class);

        if (nome != null && !nome.trim().isEmpty()) {
            query.setParameter("nome", nome.trim());
        }

        return query.getSingleResult();
    }

    /**
     * Busca medicamentos por nome (parcial, ignorando maiúsculas/minúsculas),
     * com resultado limitado para evitar sobrecarga no autocomplete.
     */
    public List<Medicamento> buscarPorNome(String nome) {
        return em.createQuery(
                "SELECT m FROM Medicamento m " +
                "WHERE LOWER(m.nome) LIKE LOWER(:nome) " +
                "ORDER BY m.nome",
                Medicamento.class)
            .setParameter("nome", "%" + nome.trim() + "%")
            .setMaxResults(20) // limita os resultados para performance
            .getResultList();
    }

    public Medicamento buscarPorId(Long id) {
        return em.find(Medicamento.class, id);
    }

}

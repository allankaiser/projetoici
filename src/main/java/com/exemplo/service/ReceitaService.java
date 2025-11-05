package com.exemplo.service;

import com.exemplo.dao.ReceitaDAO;
import com.exemplo.model.Receita;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class ReceitaService {

    @Inject
    private ReceitaDAO dao;

    private static final Logger LOGGER = Logger.getLogger(ReceitaService.class.getName());

    @Transactional
    public void salvar(Receita receita) {
        if (receita == null) {
            throw new IllegalArgumentException("Receita não pode ser nula.");
        }

        LOGGER.info(() -> "Salvando receita do paciente: " +
                (receita.getPaciente() != null ? receita.getPaciente().getNome() : "Desconhecido"));

        dao.salvar(receita);
    }

    public List<Receita> listar() {
        List<Receita> receitas = dao.listar();
        LOGGER.info(() -> "Listadas " + receitas.size() + " receitas.");
        return receitas;
    }
}

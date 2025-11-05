package com.exemplo.service;

import com.exemplo.dao.MedicamentoDAO;
import com.exemplo.model.Medicamento;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class MedicamentoService {

    @Inject
    private MedicamentoDAO dao;

    public void salvar(Medicamento m) {
        if (m.getDataCadastro() == null) m.setDataCadastro(LocalDateTime.now());
        dao.salvar(m);
    }

    public void excluir(Medicamento m) { dao.excluir(m); }
    public List<Medicamento> listar() { return dao.listar(); }
    public List<Medicamento> buscarComFiltro(String nome, int first, int pageSize) { return dao.buscarComFiltro(nome, first, pageSize); }
    public Long countComFiltro(String nome) { return dao.countComFiltro(nome); }
    // Busca otimizada apenas por nome (para autoComplete)
    public List<Medicamento> buscarPorNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            //TODO
            return new ArrayList<>(); // evita consultar tudo
        }
        return dao.buscarPorNome(nome);
    }

    public Medicamento buscarPorId(Long id) {
        return dao.buscarPorId(id);
    }
}

package com.exemplo.bean;

import com.exemplo.model.Medicamento;
import com.exemplo.service.MedicamentoService;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Named
@ViewScoped
public class MedicamentoBean implements Serializable {

    private Medicamento medicamento;
    private LazyDataModel<Medicamento> lazyMedicamentos;

    private String nomeFiltro;

    @Inject
    private MedicamentoService service;

    @PostConstruct
    public void init() {
        novo();
        buscarComFiltro();
    }

    public void novo() {
        nomeFiltro = null;
        medicamento = new Medicamento();
        medicamento.setControlado(null);
        medicamento.setDataCadastro(LocalDateTime.now());
    }

    public void buscarComFiltro() {
        lazyMedicamentos = new LazyDataModel<Medicamento>() {

            @Override
            public List<Medicamento> load(int first, int pageSize, String sortField,
                                          SortOrder sortOrder, Map<String, Object> filters) {

                // usa o filtro digitado no campo de pesquisa
                List<Medicamento> resultados = service.buscarComFiltro(nomeFiltro, first, pageSize);
                long total = service.countComFiltro(nomeFiltro);

                this.setRowCount((int) total);
                return resultados;
            }
        };
    }

    public void salvar() {
        try {
            service.salvar(medicamento);
            novo();
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Medicamento salvo com sucesso!"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Erro ao salvar medicamento."));
        }
    }

    public void editar(Medicamento m) {
        medicamento = m;
    }

    public void excluir(Medicamento m) {
        try {
            service.excluir(m);
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Medicamento excluído com sucesso!"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Erro ao excluir medicamento."));
        }
    }

    // Getters
    public Medicamento getMedicamento() { return medicamento; }
    public LazyDataModel<Medicamento> getLazyMedicamentos() { return lazyMedicamentos; }
    public String getNomeFiltro() { return nomeFiltro; }
    public void setNomeFiltro(String nomeFiltro) { this.nomeFiltro = nomeFiltro; }
}

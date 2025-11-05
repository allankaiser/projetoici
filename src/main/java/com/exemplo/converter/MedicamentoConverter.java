package com.exemplo.converter;

import com.exemplo.model.Medicamento;
import com.exemplo.service.MedicamentoService;

import javax.enterprise.inject.spi.CDI;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "medicamentoConverter", managed = true)
public class MedicamentoConverter implements Converter<Medicamento> {

    private MedicamentoService getService() {
        return CDI.current().select(MedicamentoService.class).get();
    }

    @Override
    public Medicamento getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        try {
            Long id = Long.valueOf(value);
            return getService().buscarPorId(id);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Medicamento medicamento) {
        if (medicamento == null || medicamento.getIdMedicamento() == null) {
            return "";
        }
        return medicamento.getIdMedicamento().toString();
    }
}

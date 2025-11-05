package com.exemplo.converter;

import com.exemplo.model.Paciente;
import com.exemplo.service.PacienteService;

import javax.enterprise.inject.spi.CDI;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "pacienteConverter", managed = true)
public class PacienteConverter implements Converter<Paciente> {

    private PacienteService getService() {
        return CDI.current().select(PacienteService.class).get();
    }

    @Override
    public Paciente getAsObject(FacesContext context, UIComponent component, String value) {
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
    public String getAsString(FacesContext context, UIComponent component, Paciente paciente) {
        if (paciente == null || paciente.getIdPaciente() == null) {
            return "";
        }
        return paciente.getIdPaciente().toString();
    }
}

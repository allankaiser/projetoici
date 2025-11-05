package com.exemplo.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@FacesConverter(value = "localDateTimeConverter")
public class LocalDateTimeConverter implements Converter<LocalDateTime> {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    @Override
    public String getAsString(FacesContext context, UIComponent component, LocalDateTime value) {
        return (value != null) ? value.format(FORMATTER) : "";
    }

    @Override
    public LocalDateTime getAsObject(FacesContext context, UIComponent component, String value) {
        return (value != null && !value.trim().isEmpty())
                ? LocalDateTime.parse(value, FORMATTER)
                : null;
    }
}

package com.exemplo.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(
    name = "RECEITA_ITEM",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id_receita", "id_medicamento"})
    }
)
public class ReceitaItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_receita_item")
    private Long idReceitaItem;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_receita", nullable = false)
    private Receita receita;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_medicamento", nullable = false)
    private Medicamento medicamento;

    // Getters e Setters
    public Long getIdReceitaItem() { return idReceitaItem; }
    public void setIdReceitaItem(Long idReceitaItem) { this.idReceitaItem = idReceitaItem; }

    public Receita getReceita() { return receita; }
    public void setReceita(Receita receita) { this.receita = receita; }

    public Medicamento getMedicamento() { return medicamento; }
    public void setMedicamento(Medicamento medicamento) { this.medicamento = medicamento; }

    // equals e hashCode baseados em medicamento e receita
    // Isso evita duplicações na lista da Receita
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReceitaItem)) return false;
        ReceitaItem that = (ReceitaItem) o;
        return Objects.equals(receita, that.receita) &&
               Objects.equals(medicamento, that.medicamento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(receita, medicamento);
    }
}

    package com.exemplo.model;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "MEDICAMENTO")
public class Medicamento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_medicamento")
    private Long idMedicamento;

    @NotNull
    @Size(min = 2, max = 150)
    @Column(nullable = false, length = 150)
    private String nome;

    @NotNull(message = "O campo 'Controlado' é obrigatório.")
    @Column(nullable = false)
    private Integer controlado;
    
    @Size(max = 255)
    @Column(length = 255)
    private String posologia;

    @NotNull
    @Column(name = "data_cadastro", nullable = false)
    private LocalDateTime dataCadastro;

    public Long getIdMedicamento() { return idMedicamento; }
    public void setIdMedicamento(Long idMedicamento) { this.idMedicamento = idMedicamento; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public Integer getControlado() { return controlado; }
    public void setControlado(Integer controlado) { this.controlado = controlado; }
    public String getPosologia() { return posologia; }
    public void setPosologia(String posologia) { this.posologia = posologia; }
    public LocalDateTime getDataCadastro() { return dataCadastro; }
    public void setDataCadastro(LocalDateTime dataCadastro) { this.dataCadastro = dataCadastro; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Medicamento)) return false;
        Medicamento that = (Medicamento) o;
        return idMedicamento != null && idMedicamento.equals(that.getIdMedicamento());
    }

    @Override
    public int hashCode() {
        return 31;
    }

}

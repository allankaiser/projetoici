package com.exemplo.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "PACIENTE", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"cpf"})
})
public class Paciente implements Serializable {

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_paciente")
    private Long idPaciente;

    @NotNull
    @Size(min = 2, max = 100)
    @Column(nullable = false, length = 100)
    private String nome;

    @NotNull
    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

    @NotNull
    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @NotNull
    @Column(nullable = false, length = 1)
    private String sexo; // 'M', 'F' ou 'O'

    @NotNull
    @Column(name = "dt_inclusao", nullable = false)
    private LocalDate dtInclusao;

    @NotNull
    @Column(nullable = false)
    private Boolean ativo = true;

    // getters and setters
    public Long getIdPaciente() { return idPaciente; }
    public void setIdPaciente(Long idPaciente) { this.idPaciente = idPaciente; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }
    
    public String getSexo() { return sexo; }    
    public void setSexo(String sexo) { this.sexo = sexo; }

    public LocalDate getDtInclusao() { return dtInclusao; }
    public void setDtInclusao(LocalDate dtInclusao) { this.dtInclusao = dtInclusao; }

    public Boolean getAtivo() { return ativo; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }
}

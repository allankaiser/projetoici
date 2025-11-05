package com.exemplo.dto;

public class RelatorioPacienteDTO {
    private String nomePaciente;
    private Long quantidadeMedicamentos;

    public RelatorioPacienteDTO(String nomePaciente, Long quantidadeMedicamentos) {
        this.nomePaciente = nomePaciente;
        this.quantidadeMedicamentos = quantidadeMedicamentos;
    }

    public String getNomePaciente() {
        return nomePaciente;
    }

    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }

    public Long getQuantidadeMedicamentos() {
        return quantidadeMedicamentos;
    }

    public void setQuantidadeMedicamentos(Long quantidadeMedicamentos) {
        this.quantidadeMedicamentos = quantidadeMedicamentos;
    }
}

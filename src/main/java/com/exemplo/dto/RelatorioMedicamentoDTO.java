package com.exemplo.dto;

public class RelatorioMedicamentoDTO {

    private String nomePaciente;
    private Long quantidadeMedicamentos;

    public RelatorioMedicamentoDTO(String nomePaciente, Long quantidadeMedicamentos) {
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

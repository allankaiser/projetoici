package com.exemplo.dto;

public class MedicamentoMaisPrescritoDTO {

    private String nomeMedicamento;
    private Long quantidadePrescricoes;

    public MedicamentoMaisPrescritoDTO(String nomeMedicamento, Long quantidadePrescricoes) {
        this.nomeMedicamento = nomeMedicamento;
        this.quantidadePrescricoes = quantidadePrescricoes;
    }

    public String getNomeMedicamento() {
        return nomeMedicamento;
    }

    public void setNomeMedicamento(String nomeMedicamento) {
        this.nomeMedicamento = nomeMedicamento;
    }

    public Long getQuantidadePrescricoes() {
        return quantidadePrescricoes;
    }

    public void setQuantidadePrescricoes(Long quantidadePrescricoes) {
        this.quantidadePrescricoes = quantidadePrescricoes;
    }
}

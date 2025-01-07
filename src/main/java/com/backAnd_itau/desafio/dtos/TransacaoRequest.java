package com.backAnd_itau.desafio.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class TransacaoRequest {
    private BigDecimal valor;
    private OffsetDateTime dataHora;

    public TransacaoRequest(OffsetDateTime dataHora, BigDecimal valor) {
        this.dataHora = dataHora;
        this.valor = valor;
    }

    public TransacaoRequest() {
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public OffsetDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(OffsetDateTime dataHora) {
        this.dataHora = dataHora;
    }
}

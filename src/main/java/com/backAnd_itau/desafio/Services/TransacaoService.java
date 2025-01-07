package com.backAnd_itau.desafio.Services;


import com.backAnd_itau.desafio.dtos.TransacaoRequest;
import com.backAnd_itau.desafio.Exceptions.TransacaoInvalidaException;
import com.backAnd_itau.desafio.entity.Transacao;
import com.backAnd_itau.desafio.repository.TransacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class TransacaoService {

    private final TransacaoRepository transacaoRepository;

    public TransacaoService(TransacaoRepository transacaoRepository) {
        this.transacaoRepository = transacaoRepository;
    }

    @Transactional
    public ResponseEntity<Void> processarTransacao(TransacaoRequest transacaoRequest) {
        validarPayload(transacaoRequest);
        validarRegrasNegocio(transacaoRequest);
        executarTransacao(transacaoRequest);

        return ResponseEntity.ok().build();
    }

    private void validarPayload(TransacaoRequest transacao) {
        if (Objects.isNull(transacao)) {
            throw new TransacaoInvalidaException("Payload da transação não pode ser nulo");
        }

        if (Objects.isNull(transacao.getValor())) {
            throw new TransacaoInvalidaException("Valor da transação é obrigatório");
        }

        if (Objects.isNull(transacao.getDataHora())) {
            throw new TransacaoInvalidaException("Data/hora da transação é obrigatória");
        }
    }

    private void validarRegrasNegocio(TransacaoRequest transacao) {
        validarDataFutura(transacao.getDataHora());
        validarValorNegativo(transacao.getValor());
    }

    private void validarDataFutura(OffsetDateTime dataHora) {
        if (dataHora.isAfter(OffsetDateTime.now())) {
            throw new TransacaoInvalidaException("Data/hora não pode ser futura");
        }
    }

    private void validarValorNegativo(BigDecimal valor) {
        if (valor.compareTo(BigDecimal.ZERO) < 0) {
            throw new TransacaoInvalidaException("Valor da transação não pode ser negativo");
        }
    }

    private void executarTransacao(TransacaoRequest transacao) {
        try {
            transacaoRepository.save(transacao);
        } catch (Exception e) {
            throw new TransacaoInvalidaException("Erro ao persistir transação: " + e.getMessage());
        }
    }

    public List<Transacao> todasTransacoes() {
        return new ArrayList<>(transacaoRepository.findAll().values());
    }

    public ResponseEntity<Void> limpar() {
        transacaoRepository.limpar();
        return ResponseEntity.ok().build();
    }

}
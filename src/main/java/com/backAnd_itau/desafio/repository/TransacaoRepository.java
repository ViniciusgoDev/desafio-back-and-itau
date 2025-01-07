package com.backAnd_itau.desafio.repository;

import com.backAnd_itau.desafio.dtos.TransacaoRequest;
import com.backAnd_itau.desafio.entity.Transacao;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class TransacaoRepository {


    private final Map<Long, Transacao> transacoes = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public Transacao save(TransacaoRequest transacaoRequest) {
        Long id = idGenerator.getAndIncrement();
        Transacao transacao = new Transacao(
                id,
                transacaoRequest.getValor(),
                transacaoRequest.getDataHora()
        );
        transacoes.put(id, transacao);
        return transacao;
    }
    public Map<Long, Transacao> findAll() {
        return transacoes;
    }
    public void limpar(){
        transacoes.clear();
    }
}

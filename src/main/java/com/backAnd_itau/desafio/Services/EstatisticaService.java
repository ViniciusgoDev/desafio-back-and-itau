package com.backAnd_itau.desafio.Services;

import com.backAnd_itau.desafio.dtos.EstatisticaDTO;
import com.backAnd_itau.desafio.entity.Transacao;
import com.backAnd_itau.desafio.repository.TransacaoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.DoubleStream;

@Service
public class EstatisticaService {

    private final TransacaoRepository transacaoRepository;

    public EstatisticaService(TransacaoRepository transacaoRepository) {
        this.transacaoRepository = transacaoRepository;
    }



    public EstatisticaDTO estatistica() {

        ArrayList<Transacao> transacoes = new ArrayList<>(transacaoRepository.findAll().values());


          Integer intervaloMaximoEmSegundos = 60;

        final var horaInicial = OffsetDateTime.now().minusSeconds(intervaloMaximoEmSegundos);




        if (transacoes.isEmpty()) {
            return new EstatisticaDTO();
        } else {

            final BigDecimal[] valoresFiltrados = transacoes.stream()
                    .filter(t -> t.getDataHora().isAfter(horaInicial) || t.getDataHora().equals(horaInicial))
                    .map(t -> t.getValor()).toArray(BigDecimal[]::new);
            DoubleStream doubleStream = Arrays.stream(valoresFiltrados).mapToDouble(BigDecimal::doubleValue);
            return new EstatisticaDTO(doubleStream.summaryStatistics());
        }
    }

}

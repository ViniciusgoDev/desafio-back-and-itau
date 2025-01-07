package com.backAnd_itau.desafio.controllers;

import com.backAnd_itau.desafio.Services.EstatisticaService;
import com.backAnd_itau.desafio.dtos.EstatisticaDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.DoubleSummaryStatistics;

@RestController
@RequestMapping("/Estatisticas")
public class EstatisticasController {

    private final  EstatisticaService estatisticaService;

    public EstatisticasController(EstatisticaService estatisticaService) {
        this.estatisticaService = estatisticaService;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public EstatisticaDTO gerarEstatisticas(){
        return estatisticaService.estatistica();
    }
}

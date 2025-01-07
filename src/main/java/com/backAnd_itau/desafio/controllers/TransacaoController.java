package com.backAnd_itau.desafio.controllers;

import com.backAnd_itau.desafio.Services.TransacaoService;
import com.backAnd_itau.desafio.dtos.TransacaoRequest;
import com.backAnd_itau.desafio.entity.Transacao;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    private final TransacaoService transacaoService;

    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }


    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> transacao(@RequestBody TransacaoRequest transacaoRequest) {
        if (transacaoRequest.getValor() == null || transacaoRequest.getDataHora() == null) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
        return transacaoService.processarTransacao(transacaoRequest);
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
            public List<Transacao> findAll(){
              return transacaoService.todasTransacoes();
    }

    @DeleteMapping
    public ResponseEntity<Void>limpar(){
      return transacaoService.limpar();
    }
}

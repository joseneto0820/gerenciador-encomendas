package br.com.gerenciador.gerenciadorencomendas.internal.encomenda.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/encomenda")
public class EncomendaController {
    @PostMapping
    public void cadastroEncomenda(@RequestBody EncomendaDto encomendaDto){
        return cadastroEncomenda
    }
}

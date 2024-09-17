package com.microservico.NotificacaoService.controller;

import com.microservico.NotificacaoService.model.NotificacaoRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notificacoes")
public class NotificacaoController {

    
    @PostMapping
    public ResponseEntity<String> enviarNotificacao(@RequestBody NotificacaoRequest request) {
        
        System.out.println("Enviando notificação para: " + request.getEmail());
        System.out.println("Assunto: " + request.getAssunto());
        System.out.println("Mensagem: " + request.getMensagem());

        return new ResponseEntity<>("Notificação enviada com sucesso!", HttpStatus.OK);
    }
}

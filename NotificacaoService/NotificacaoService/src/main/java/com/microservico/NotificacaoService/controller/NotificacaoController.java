package com.microservico.NotificacaoService.controller;

import com.microservico.NotificacaoService.model.NotificacaoRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@RestController
@RequestMapping("/notificacoes")
public class NotificacaoController {
	private static final Logger logger = LoggerFactory.getLogger(NotificacaoController.class);
    
    @PostMapping
    public ResponseEntity<String> enviarNotificacao(@RequestBody NotificacaoRequest request) {
    	
    	
        
        logger.info("Enviando notificação para: {}", request.getEmail());
        logger.info("Assunto: {}", request.getAssunto());
        logger.info("Mensagem: {}", request.getMensagem());



        return new ResponseEntity<>("Notificação enviada com sucesso!", HttpStatus.OK);
    }
}

package com.microservico.NotificacaoService.service;

import com.microservico.NotificacaoService.model.NotificacaoRequest;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class NotificacaoService {

  
    @RabbitListener(queues = "notificacoes")
    public void processarNotificacao(NotificacaoRequest notificacao) {
        
        System.out.println("Enviando notificação para: " + notificacao.getEmail());
        System.out.println("Assunto: " + notificacao.getAssunto());
        System.out.println("Mensagem: " + notificacao.getMensagem());
    }
}

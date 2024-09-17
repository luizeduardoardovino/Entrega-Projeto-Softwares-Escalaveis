package com.projeto.AT.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificacaoRequest {
    
    private String email;
    private String assunto;
    private String mensagem;
}

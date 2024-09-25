package com.projeto.AT.controller;

import com.projeto.AT.model.Livro;


import com.projeto.AT.model.NotificacaoRequest;
import com.projeto.AT.model.Usuario;
import com.projeto.AT.service.LivroService;
import com.projeto.AT.service.UsuarioService;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import jakarta.validation.Valid;

@Validated
@Tag(name = "Livros", description = "Gerenciamento de livros na API")
@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroService livroService;
    

    @Autowired
    private UsuarioService usuarioService;
    

   
    @Autowired
    private RabbitTemplate rabbitTemplate;  

    @Operation(summary = "Criar um novo livro", description = "Este endpoint permite a criação de um novo livro no sistema. Se o tema do livro coincidir com as preferências dos usuários cadastrados, uma notificação será enviada.")
    @PostMapping
    public Livro criarLivro(@RequestBody Livro livro) {
        
        Livro novoLivro = livroService.salvarLivro(livro);

        
        List<Usuario> usuariosInteressados = usuarioService.buscarPorPreferencias(novoLivro.getTema());
        Set<String> emailsNotificados = new HashSet<>();

        
        for (Usuario usuario : usuariosInteressados) {
            if (!emailsNotificados.contains(usuario.getEmail())) {
                NotificacaoRequest notificacao = new NotificacaoRequest(usuario.getEmail(),
                        "Novo Livro no Tema: " + novoLivro.getTema(),
                        "O livro '" + novoLivro.getTitulo() + "' foi adicionado à livraria.");

               
                rabbitTemplate.convertAndSend("notificacoes", notificacao);

                emailsNotificados.add(usuario.getEmail());
            }
        }

        return novoLivro;
    }
    


    // Listar todos os livros
    @GetMapping
    @Operation(summary = "Listar todos os livros", description = "Este endpoint retorna uma lista de todos os livros cadastrados.")
   public List<Livro> listarLivros() {
        return livroService.listarLivros();
    }

    // Buscar livro por ID
    @GetMapping("/{id}")
    @Operation(summary = "Buscar livro por ID", description = "Este endpoint retorna um livro específico pelo seu ID.")
     public ResponseEntity<Livro> buscarPorId(@PathVariable Long id) {
        Optional<Livro> livro = livroService.buscarPorId(id);
        return livro.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Deletar livro por ID
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar livro por ID", description = "Este endpoint permite deletar um livro específico pelo seu ID.")
      public ResponseEntity<Void> deletarLivro(@PathVariable Long id) {
        Optional<Livro> livro = livroService.buscarPorId(id);
        if (livro.isPresent()) {
            livroService.deletarLivro(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

package com.projeto.AT.controller;

import com.projeto.AT.model.Livro;
import com.projeto.AT.model.NotificacaoRequest;
import com.projeto.AT.model.Usuario;
import com.projeto.AT.service.LivroService;
import com.projeto.AT.service.UsuarioService;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroService livroService;
    

    @Autowired
    private UsuarioService usuarioService;
    

   
    @Autowired
    private RabbitTemplate rabbitTemplate;  

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
    public List<Livro> listarLivros() {
        return livroService.listarLivros();
    }

    // Buscar livro por ID
    @GetMapping("/{id}")
    public ResponseEntity<Livro> buscarPorId(@PathVariable Long id) {
        Optional<Livro> livro = livroService.buscarPorId(id);
        return livro.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Deletar livro por ID
    @DeleteMapping("/{id}")
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

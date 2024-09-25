package com.projeto.AT.controller;

import com.projeto.AT.model.Usuario;		
import com.projeto.AT.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@Validated
@Tag(name = "Usuários", description = "Gerenciamento de usuários na API")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

 
    @Operation(summary = "Criar um novo usuário", description = "Este endpoint permite a criação de um novo usuário no sistema.")
    @PostMapping
    public Usuario criarUsuario(@RequestBody Usuario usuario) {
        return usuarioService.salvarUsuario(usuario);
    }

   
    @GetMapping
    @Operation(summary = "Listar todos os usuários", description = "Este endpoint retorna uma lista de todos os usuários cadastrados.")
    public List<Usuario> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }

    
    @Operation(summary = "Buscar usuário por ID", description = "Este endpoint retorna um usuário específico pelo seu ID.")
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.buscarPorId(id);
        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

   
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar usuário por ID", description = "Este endpoint permite deletar um usuário específico pelo seu ID.")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.buscarPorId(id);
        if (usuario.isPresent()) {
            usuarioService.deletarUsuario(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

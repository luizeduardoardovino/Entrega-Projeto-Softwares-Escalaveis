package com.projeto.AT.controller;

import com.projeto.AT.model.Autor;	
import com.projeto.AT.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@Tag(name = "Autores", description = "Gerenciamento de autores na API")
@RestController
@RequestMapping("/autores")
public class AutorController {

    @Autowired
    private AutorService autorService;

   
    @PostMapping
    @Operation(summary = "Criar um novo autor", description = "Este endpoint permite a criação de um novo autor no sistema.")
    public Autor criarAutor(@Valid @RequestBody Autor autor) {
        return autorService.salvarAutor(autor);
    }


    @Operation(summary = "Listar todos os autores", description = "Este endpoint retorna uma lista de todos os autores cadastrados.")
    @GetMapping
    public List<Autor> listarAutores() {	
        return autorService.listarAutores();
    }

   
    @GetMapping("/{id}")
    @Operation(summary = "Buscar autor por ID", description = "Este endpoint retorna um autor específico pelo seu ID.")
    public ResponseEntity<Autor> buscarPorId(@PathVariable Long id) {
        Optional<Autor> autor = autorService.buscarPorId(id);
        return autor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

   
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar autor por ID", description = "Este endpoint permite deletar um autor específico pelo seu ID.")
    public ResponseEntity<Void> deletarAutor(@PathVariable Long id) {
        Optional<Autor> autor = autorService.buscarPorId(id);
        if (autor.isPresent()) {
            autorService.deletarAutor(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

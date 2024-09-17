package com.projeto.AT.controller;

import com.projeto.AT.model.Autor;
import com.projeto.AT.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/autores")
public class AutorController {

    @Autowired
    private AutorService autorService;

   
    @PostMapping
    public Autor criarAutor(@RequestBody Autor autor) {
        return autorService.salvarAutor(autor);
    }


    @GetMapping
    public List<Autor> listarAutores() {
        return autorService.listarAutores();
    }

   
    @GetMapping("/{id}")
    public ResponseEntity<Autor> buscarPorId(@PathVariable Long id) {
        Optional<Autor> autor = autorService.buscarPorId(id);
        return autor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

   
    @DeleteMapping("/{id}")
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

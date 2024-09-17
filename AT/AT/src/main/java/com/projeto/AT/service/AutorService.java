package com.projeto.AT.service;

import com.projeto.AT.model.Autor;
import com.projeto.AT.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

   
    public Autor salvarAutor(Autor autor) {
        return autorRepository.save(autor);
    }

  
    public List<Autor> listarAutores() {
        return autorRepository.findAll();
    }

   
    public Optional<Autor> buscarPorId(Long id) {
        return autorRepository.findById(id);
    }

    
    public void deletarAutor(Long id) {
        autorRepository.deleteById(id);
    }
}

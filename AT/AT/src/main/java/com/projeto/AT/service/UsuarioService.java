package com.projeto.AT.service;

import com.projeto.AT.model.Usuario;
import com.projeto.AT.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

   
    public Usuario salvarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

   
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    
    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

   
    public List<Usuario> buscarPorPreferencias(String tema) {
        return usuarioRepository.findByPreferenciasContaining(tema);
    }

    
    public void deletarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}

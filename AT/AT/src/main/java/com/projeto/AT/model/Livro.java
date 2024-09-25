package com.projeto.AT.model;

import lombok.AllArgsConstructor;	
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O título é obrigatório")
    @Size(min = 2, max = 200, message = "O título deve ter entre 2 e 200 caracteres")
    private String titulo;
    @NotBlank(message = "O ISBN é obrigatório")
    @Size(min = 10, max = 13, message = "O ISBN deve ter entre 10 e 13 caracteres")
   private String isbn;

    @NotBlank(message = "O tema é obrigatório")
    @Size(max = 100, message = "O tema deve ter no máximo 100 caracteres")
    private String tema;

    @ManyToOne 
    @JoinColumn(name = "autor_id", referencedColumnName = "id")
    @NotNull(message = "O autor é obrigatório")
    private Autor autor;
}

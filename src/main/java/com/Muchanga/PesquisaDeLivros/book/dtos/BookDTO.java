package com.Muchanga.PesquisaDeLivros.book.dtos;

import com.Muchanga.PesquisaDeLivros.user.models.User;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;


public record BookDTO(
        @NotBlank User user,
        @NotBlank(message = "O titulo é obrigatório")String title,
        @NotBlank (message = "Nome do autor é obrigatório")String author,
        @NotBlank (message = "O genero é obrigatório")String genre,
        @NotBlank (message = "A Descricao é obrigatório")String description,
        Date PublicationDate,
        @NotBlank (message = "O ano da publicao é obrigatorio") int publicationYear



        ) {

}

package com.Muchanga.PesquisaDeLivros.book.dtos;



import java.util.Date;

import javax.validation.constraints.NotBlank;

import com.Muchanga.PesquisaDeLivros.user.models.user.UserModel;


public record BookDTO(
        @NotBlank UserModel user,
        @NotBlank(message = "O titulo é obrigatório")String title,
        @NotBlank (message = "Nome do autor é obrigatório")String author,
        @NotBlank (message = "O genero é obrigatório")String genre,
        @NotBlank (message = "A Descricao é obrigatório")String description,
        Date PublicationDate,
        @NotBlank (message = "O ano da publicao é obrigatorio") int publicationYear



        ) {

}

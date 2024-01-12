package com.Muchanga.PesquisaDeLivros.book.dtos;

import org.hibernate.validator.constraints.NotBlank;

import java.sql.Date;

public record BookUpdateDTO(

        @NotBlank(message = "O título é obrigatório") String title,
        @NotBlank(message = "Nome do autor é obrigatório") String author,
        @NotBlank(message = "O gênero é obrigatório") String genre,
        @NotBlank(message = "A descrição é obrigatória") String description,
        Date publicationDate,
        @NotBlank(message = "O ano da publicação é obrigatório") int publicationYear


) {
}

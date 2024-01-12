package com.Muchanga.PesquisaDeLivros.book.repository;

import com.Muchanga.PesquisaDeLivros.book.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    //Metodo para Porcurar por genero
    List<Book> findByGenreIgnoreCase(String genre);
    // Metodo para procurar por titulo
    List<Book> findByTitleContainingIgnoreCase(String title);
}

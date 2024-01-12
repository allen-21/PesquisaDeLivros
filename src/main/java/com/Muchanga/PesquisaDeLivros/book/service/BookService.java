package com.Muchanga.PesquisaDeLivros.book.service;

import com.Muchanga.PesquisaDeLivros.book.dtos.BookDTO;
import com.Muchanga.PesquisaDeLivros.book.dtos.BookUpdateDTO;
import com.Muchanga.PesquisaDeLivros.book.models.Book;
import com.Muchanga.PesquisaDeLivros.book.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> buscarTodos(){
        return this.bookRepository.findAll();
    }
    public List<Book> buscarPorGenero(String genre){
        return this.bookRepository.findByGenreIgnoreCase(genre);
    }
    public List<Book> buscarPorTitulo(String title){
        return this.bookRepository.findByTitleContainingIgnoreCase(title);
    }
    public Book adicionarLivro(BookDTO data){
        Book newBook = new Book(data);
        this.saveBook(newBook);
        return newBook;
    }
    public Book update(Long id, BookUpdateDTO data){
        Optional<Book> book = this.bookRepository.findById(id);
        if(book.isPresent()){
            Book existingBook = book.get();

            existingBook.setTitle(data.title());
            existingBook.setAuthor(data.author());
            existingBook.setGenre(data.genre());
            existingBook.setDescription(data.description());
            existingBook.setPublicationDate(data.publicationDate());
            existingBook.setPublicationYear(data.publicationYear());


            return bookRepository.save(existingBook);
        } else {
            throw new EntityNotFoundException("Livro não encontrado com o ID: " + id);

        }
    }
    public void remove (Long id){
     Book book = bookRepository.findById(id).get();
     this.bookRepository.delete(book);
    }
    public void saveBook(Book book){
        this.bookRepository.save(book);
    }
}

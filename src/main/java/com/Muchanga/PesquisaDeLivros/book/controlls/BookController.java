package com.Muchanga.PesquisaDeLivros.book.controlls;

import com.Muchanga.PesquisaDeLivros.book.dtos.BookDTO;
import com.Muchanga.PesquisaDeLivros.book.dtos.BookUpdateDTO;
import com.Muchanga.PesquisaDeLivros.book.models.Book;
import com.Muchanga.PesquisaDeLivros.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity<Book> adicionar(@RequestBody BookDTO book){
        Book newBook = bookService.adicionarLivro(book);
        return new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBook(){
        List<Book> books = this.bookService.buscarTodos();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Book> update(@PathVariable Long id, @RequestBody BookUpdateDTO bookUpdateDTO){
        Book updateBook = this.bookService.update(id, bookUpdateDTO);
        return ResponseEntity.ok(updateBook);
    }

    @GetMapping("/title")
    public ResponseEntity<List<Book>> searchBooksByTitle(@RequestParam String title) {
        List<Book> books = bookService.buscarPorTitulo(title);
        return ResponseEntity.ok(books);
    }
    @GetMapping("/renge")
    public ResponseEntity<List<Book>> searchBooksByRenge(@RequestParam String genre) {
        List<Book> books = bookService.buscarPorGenero(genre);
        return ResponseEntity.ok(books);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.remove(id);
        return ResponseEntity.noContent().build();
    }

}

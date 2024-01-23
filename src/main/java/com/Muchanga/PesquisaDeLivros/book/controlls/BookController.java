package com.Muchanga.PesquisaDeLivros.book.controlls;


import com.Muchanga.PesquisaDeLivros.book.dtos.BookUpdateDTO;
import com.Muchanga.PesquisaDeLivros.book.models.Book;
import com.Muchanga.PesquisaDeLivros.book.service.BookService;
import com.Muchanga.PesquisaDeLivros.user.Service.UserService;
import com.Muchanga.PesquisaDeLivros.user.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/{userId}/add-book")
    public ResponseEntity<String> addBookToUser(@PathVariable Long userId, @RequestBody Book book) {
        try {

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String username = userDetails.getUsername();
            userService.addBookToUser(userId, book);           
            return ResponseEntity.ok("Livro adicionado com sucesso ao usuário");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }



    @GetMapping("/todos")
    public ResponseEntity<List<Book>> getAllBooks() {
        try {
            List<Book> allBooks = bookService.getAllBooks();
            allBooks.forEach(book -> book.setUser(null));
            return ResponseEntity.ok(allBooks);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
        }
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

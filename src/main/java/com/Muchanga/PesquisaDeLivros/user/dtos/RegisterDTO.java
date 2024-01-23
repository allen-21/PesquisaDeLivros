package com.Muchanga.PesquisaDeLivros.user.dtos;



import java.util.List;

import com.Muchanga.PesquisaDeLivros.book.models.Book;
import com.Muchanga.PesquisaDeLivros.user.models.user.UserRole;

public record RegisterDTO(String login, String password, UserRole role, List<Book> books) {
}

package com.Muchanga.PesquisaDeLivros.user.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Muchanga.PesquisaDeLivros.book.models.Book;
import com.Muchanga.PesquisaDeLivros.book.repository.BookRepository;
import com.Muchanga.PesquisaDeLivros.user.models.user.UserModel;
import com.Muchanga.PesquisaDeLivros.user.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;

      public UserModel getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof UserModel) {
            return (UserModel) authentication.getPrincipal();
        }
        return null;
    }

    @Transactional
    public void addBookToUser(Long userId, Book book){
        Optional<UserModel> optionalUser = userRepository.findById(userId);

        if(optionalUser.isPresent()){
            UserModel user = optionalUser.get();
            user.addBook(book);
            bookRepository.save(book);
            
        }else{
            throw new EntityNotFoundException("Usuario nao enocntrado com ID:" + userId);
        }
    }
}

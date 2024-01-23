package com.Muchanga.PesquisaDeLivros.book.models;

import com.Muchanga.PesquisaDeLivros.book.dtos.BookDTO;
import com.Muchanga.PesquisaDeLivros.user.models.user.UserModel;

import jakarta.persistence.*;
import lombok.*;


import java.util.Date;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "books")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "userid")
    @Cascade(CascadeType.ALL)
    private UserModel user;
    private String title;
    private String author;
    private String genre;
    private String description;
    private int publicationYear;

    public Book (BookDTO data){
        this.user = data.user();
        this.title = data.title();
        this.author = data.author();
        this.genre = data.genre();
        this.description = data.description();
        this.publicationYear = data.publicationYear();
    }
}

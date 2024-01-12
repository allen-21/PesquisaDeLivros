package com.Muchanga.PesquisaDeLivros.book.models;

import com.Muchanga.PesquisaDeLivros.book.dtos.BookDTO;
import com.Muchanga.PesquisaDeLivros.user.models.User;
import jakarta.persistence.*;
import lombok.*;


import java.util.Date;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user_id;
    private String title;
    private String author;
    private String genre;
    private String description;
    @Temporal(TemporalType.DATE)
    private Date PublicationDate = new Date();
    private int publicationYear;

    public Book (BookDTO data){
        this.user_id = data.user();
        this.title = data.title();
        this.author = data.author();
        this.genre = data.genre();
        this.description = data.description();
        this.PublicationDate = data.PublicationDate();
        this.publicationYear = data.publicationYear();
    }
}

package kz.muminov.iitu.library.entity;

import kz.muminov.iitu.library.enums.BookStatus;
import kz.muminov.iitu.library.enums.Genre;

import javax.persistence.*;
import java.util.List;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private BookStatus bookStatus = BookStatus.RETURNED;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;

    @OneToMany(mappedBy = "book")
    private List<IssuedBooks> issuedBooks;

    public Book(String title, String description, Genre genre, Author author) {
        this.title = title;
        this.description = description;
        this.genre = genre;
        this.author = author;
    }

    public Book(Long id, String title, String description, Genre genre, Author author) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.genre = genre;
        this.author = author;
    }

    public Book() {
    }

    public BookStatus getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(BookStatus bookStatus) {
        this.bookStatus = bookStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", bookStatus=" + bookStatus +
                ", genre=" + genre +
                ", author=" + author +
                '}';
    }

    //    @Override
//    public String toString() {
//        return  "ID: " + id + '\n' +
//                "Title: " + title + '\n' +
//                "Description: " + description + '\n' +
//                "Genre: " + " " + genre + '\n' +
//                "Author: " + author.getName() + '\n' +
//                "Availability: " + bookStatus + '\n' +
//                "- - - - - - - - - - - - - - - - - - - - ";
//    }
}

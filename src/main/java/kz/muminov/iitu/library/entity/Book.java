package kz.muminov.iitu.library.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonBackReference(value = "author")
    private Author author;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    @JsonIgnore
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

    public BookStatus getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(BookStatus bookStatus) {
        this.bookStatus = bookStatus;
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

    public List<IssuedBooks> getIssuedBooks() {
        return issuedBooks;
    }

    public void setIssuedBooks(List<IssuedBooks> issuedBooks) {
        this.issuedBooks = issuedBooks;
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
}

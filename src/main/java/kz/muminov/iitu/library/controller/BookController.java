package kz.muminov.iitu.library.controller;

import kz.muminov.iitu.library.entity.Book;
import kz.muminov.iitu.library.serivce.AuthorService;
import kz.muminov.iitu.library.serivce.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;

    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping("/all")
    public List<Book> showAllBooks() {
        return bookService.showAllBooks();
    }

    @PostMapping("")
    public Book addNewBook(@RequestBody Book book){
        return bookService.addBook(book);
    }

    @GetMapping("/{id}")
    public Book findBookById(@PathVariable Long id){
        return bookService.findBookById(id).orElse(null);
    }

    @GetMapping("")
    public List<Book> findBook(@RequestParam String search){
        search = search.toLowerCase();
        return bookService.findBook(search);
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
        return "The book with id " + id + " is successfully deleted";
    }


}

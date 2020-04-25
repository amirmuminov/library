package kz.muminov.iitu.library.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kz.muminov.iitu.library.entity.Book;
import kz.muminov.iitu.library.serivce.AuthorService;
import kz.muminov.iitu.library.serivce.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@Api(value = "Book Management System")
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;

    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping("/all")
    @ApiOperation(value = "Get all books", response = List.class)
    public List<Book> showAllBooks() {
        return bookService.showAllBooks();
    }

    @PostMapping("")
    @ApiOperation(value = "Save new author to the database", response = Book.class)
    public Book addNewBook(@ApiParam(value = "Book object to be saved in the database", required = true) @RequestBody Book book){
        return bookService.addBook(book);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Find book by id", response = Book.class)
    public Book findBookById(@ApiParam(value = "ID to find the book") @PathVariable Long id){
        return bookService.findBookById(id).orElse(null);
    }

    @GetMapping("")
    @ApiOperation(value = "Search book by title, description or author name", response = List.class)
    public List<Book> findBook(@ApiParam(value = "Search using this string") @RequestParam String search){
        search = search.toLowerCase();
        return bookService.findBook(search);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete book by id", response = String.class)
    public String deleteBook(@ApiParam(value = "ID for deleting the book") @PathVariable Long id){
        bookService.deleteBook(id);
        return "The book with id " + id + " is successfully deleted";
    }

}

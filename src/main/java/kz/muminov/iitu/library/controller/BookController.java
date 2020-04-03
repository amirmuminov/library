package kz.muminov.iitu.library.controller;

import kz.muminov.iitu.library.entity.Author;
import kz.muminov.iitu.library.entity.Book;
import kz.muminov.iitu.library.entity.User;
import kz.muminov.iitu.library.enums.Genre;
import kz.muminov.iitu.library.serivce.BookService;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Scanner;

@Component
public class BookController {

    private Scanner in = new Scanner(System.in);
    //Services
    private final BookService bookService;

    //Controllers
    private final AuthorController authorController;

    public BookController(BookService bookService, AuthorController authorController) {
        this.bookService = bookService;
        this.authorController = authorController;
    }

    public void showMenu(){
        System.out.println("1. Show all books");
        System.out.println("2. Add new book");
        System.out.println("3. Find book");
    }

    public void choice(int choice){
        switch (choice){
            case 1:
                showAllBooks();
                break;
            case 2:
                addNewBook();
                break;
            case 3:
                findBook();
                break;
            default:
                System.out.println("There is no such option");
                break;
        }
    }

    public void showAllBooks(){
        bookService.showAllBooks();
    }

    public void addNewBook(){
        System.out.println("Enter book title: ");
        String title = in.nextLine();
        System.out.println("Enter book description: ");
        String description = in.nextLine();
        System.out.println("Enter the genre number: ");
        showAllGenres();
        int choice = in.nextInt();
        Genre genre = chooseGenre(choice);
        authorController.showAllAuthors();
        Optional<Author> optionalAuthor = authorController.getAuthorById();
        Author author = optionalAuthor.orElse(null);
        Book book = new Book(title, description, genre, author);
        bookService.addBook(book);
    }

    public Book findBookById(){
        System.out.println("Enter book ID: ");
        Long id = in.nextLong();
        return bookService.findBookById(id).orElse(null);
    }

    public void findBook(){
        System.out.println("Find book by author name, title or description");
        String search = in.nextLine();
        search = search.toLowerCase();
        bookService.findBook(search);
    }

    public Genre chooseGenre(int choice){
        switch (choice){
            case 1:
                return Genre.COMEDY;
            case 2:
                return Genre.FANTASY;
            case 3:
                return Genre.HISTORY;
            case 4:
                return Genre.POLITICS;
            default:
                return null;
        }
    }

    public void showAllGenres(){
        System.out.println("1. " + Genre.COMEDY);
        System.out.println("2. " + Genre.FANTASY);
        System.out.println("3. " + Genre.HISTORY);
        System.out.println("4. " + Genre.POLITICS);
    }

}

package kz.muminov.iitu.library.controller;

import kz.muminov.iitu.library.entity.Book;
import kz.muminov.iitu.library.entity.IssuedBooks;
import kz.muminov.iitu.library.entity.User;
import kz.muminov.iitu.library.serivce.BookService;
import kz.muminov.iitu.library.serivce.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final BookService bookService;


    public UserController(UserService userService, BookService bookService) {
        this.userService = userService;
        this.bookService = bookService;
    }

    @GetMapping("/api/all")
    public List<User> showAllUsers(){
        return userService.showAllUsers();
    }

    @GetMapping("/api/{id}")
    public User findUserById(@PathVariable Long id){
        return userService.findUserById(id).orElse(null);
    }

    @PutMapping("/api/{id}")
    public User editUser(@RequestBody User user, @PathVariable Long id){
        user.setId(id);
        return userService.saveUser(user);
    }

    @PostMapping("/issue/{userId}/{bookId}")
    public IssuedBooks issueBook(@PathVariable Long userId, @PathVariable Long bookId){
        Optional<User> optUser = userService.findUserById(userId);
        Optional<Book> optBook = bookService.findBookById(bookId);
        if(optBook.isPresent() && optUser.isPresent())
            return userService.issueBook(optBook.get(), optUser.get());
        return null;

    }

    @PutMapping("/return/{userId}/{bookId}")
    public IssuedBooks returnBook(@PathVariable Long userId, @PathVariable Long bookId){
        Optional<User> optUser = userService.findUserById(userId);
        Optional<Book> optBook = bookService.findBookById(bookId);
        if(optBook.isPresent() && optUser.isPresent())
            return userService.returnBook(optBook.get(), optUser.get());
        return null;
    }

    @PostMapping("/register")
    public User createUser(@RequestBody User user){
        return userService.saveUser(user);
    }

}

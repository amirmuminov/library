package kz.muminov.iitu.library.controller;

import kz.muminov.iitu.library.entity.IssuedBooks;
import kz.muminov.iitu.library.entity.User;
import kz.muminov.iitu.library.serivce.BookService;
import kz.muminov.iitu.library.serivce.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final BookService bookService;


    public UserController(UserService userService, BookService bookService) {
        this.userService = userService;
        this.bookService = bookService;
    }

    @GetMapping("/all")
    public List<User> showAllUsers(){
        return userService.showAllUsers();
    }

    @GetMapping("/{id}")
    public User findUserById(@PathVariable Long id){
        return userService.findUserById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public User editUser(@RequestBody User user, @PathVariable Long id){
        user.setId(id);
        return userService.saveUser(user);
    }

    @PostMapping("/issue")
    public IssuedBooks issueBook(@RequestBody IssuedBooks issuedBooks){
//        Optional<User> user = userService.findUserById(issuedBooks.getUser().getId());
//        Optional<Book> book = bookService.findBookById(issuedBooks.getBook().getId());
//        if(userService.ifDataIsEmpty(user, book)){
//            issuedBooks.setUser(user.get());
//            issuedBooks.setBook(book.get());
//        }
        return userService.issueBook(issuedBooks);
    }

    @PutMapping("/return")
    public IssuedBooks returnBook(@RequestBody IssuedBooks issuedBooks){
        return userService.returnBook(issuedBooks);
    }


}

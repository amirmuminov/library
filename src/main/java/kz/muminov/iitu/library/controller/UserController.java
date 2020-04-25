package kz.muminov.iitu.library.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@Api(value = "User Management System")
public class UserController {

    private final UserService userService;
    private final BookService bookService;


    public UserController(UserService userService, BookService bookService) {
        this.userService = userService;
        this.bookService = bookService;
    }

    @GetMapping("/api/all")
    @ApiOperation(value = "Get all users", response = List.class)
    public List<User> showAllUsers(){
        return userService.showAllUsers();
    }

    @GetMapping("/api/{id}")
    @ApiOperation(value = "Get user by id", response = User.class)
    public User findUserById(@ApiParam(value = "ID to find the user", required = true) @PathVariable Long id){
        return userService.findUserById(id).orElse(null);
    }

    @PutMapping("/api/{id}")
    @ApiOperation(value = "Edit user")
    public User editUser(@ApiParam(value = "User's object to update existing user") @RequestBody User user,
                         @ApiParam(value = "ID to find the existing user") @PathVariable Long id){
        user.setId(id);
        return userService.saveUser(user);
    }

    @PostMapping("/issue/{userId}/{bookId}")
    @ApiOperation(value = "Issue a certain book to a certain user", response = IssuedBooks.class)
    public IssuedBooks issueBook(@ApiParam(value = "User's ID for issuing book to this user") @PathVariable Long userId,
                                 @ApiParam(value = "Book's ID for issuing book the user") @PathVariable Long bookId){
        Optional<User> optUser = userService.findUserById(userId);
        Optional<Book> optBook = bookService.findBookById(bookId);
        if(optBook.isPresent() && optUser.isPresent())
            return userService.issueBook(optBook.get(), optUser.get());
        return null;

    }

    @PutMapping("/return/{userId}/{bookId}")
    @ApiOperation(value = "Return a certain book by a certain user", response = IssuedBooks.class)
    public IssuedBooks returnBook(@ApiParam(value = "User's ID who wants to return the book") @PathVariable Long userId,
                                  @ApiParam(value = "Book's ID which wants to return the user") @PathVariable Long bookId){
        Optional<User> optUser = userService.findUserById(userId);
        Optional<Book> optBook = bookService.findBookById(bookId);
        if(optBook.isPresent() && optUser.isPresent())
            return userService.returnBook(optBook.get(), optUser.get());
        return null;
    }

    @PostMapping("/register")
    @ApiOperation(value = "Registration of the new user")
    public User createUser(@ApiParam(value = "User's object which will be saved to the database") @RequestBody User user){
        return userService.saveUser(user);
    }

}

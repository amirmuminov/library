package kz.muminov.iitu.library.controller;

import kz.muminov.iitu.library.entity.Book;
import kz.muminov.iitu.library.entity.User;
import kz.muminov.iitu.library.enums.BookStatus;
import kz.muminov.iitu.library.serivce.UserService;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class UserController {

    private Scanner in = new Scanner(System.in);
    //Services
    private final UserService userService;

    //Controllers
    private final BookController bookController;


    public UserController(UserService userService, BookController bookController) {
        this.userService = userService;
        this.bookController = bookController;
    }

    public void showMenu(){
        System.out.println("1. Issue book");
        System.out.println("2. Show all users");
        System.out.println("3. Return book");
    }

    public void choice(int choice){
        switch (choice){
            case 1:
                issueBook();
                break;
            case 2:
                shooAllUsers();
                break;
            case 3:
                returnBook();
                break;
            default:
                System.out.println("There is no such option");
                break;
        }
    }

    public void shooAllUsers(){
        userService.showAllUsers();
    }

    public User findUserById(){
        System.out.println("Enter user ID: ");
        Long id = in.nextLong();
        return userService.findUserById(id).orElse(null);
    }

    public void issueBook(){
        User user = findUserById();
        Book book = bookController.findBookById();
        userService.issueBook(user, book);
    }

    public void returnBook(){
        User user = findUserById();
        Book book = bookController.findBookById();
        userService.returnBook(user, book);
    }

//    private boolean ifDataIsEmpty(User user, Book book){
//        boolean isEmpty = false;
//        if (user == null){
//            isEmpty = true;
//            System.out.println("Invalid ID for the user");
//        }
//        if (book == null){
//            isEmpty = true;
//            System.out.println("Invalid ID for the book");
//        }
//        return !isEmpty;
//    }

}

package kz.muminov.iitu.library.controller;

import kz.muminov.iitu.library.entity.Author;
import kz.muminov.iitu.library.serivce.AuthorService;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Scanner;

@Component
public class AuthorController {

    private Scanner in = new Scanner(System.in);
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    public void showMenu(){
        System.out.println("1. Show all authors");
        System.out.println("2. Add new book");
    }

    public void choice(int choice){
        switch (choice){
            case 1:
                showAllAuthors();
                break;
            case 2:
                getAuthorById();
                break;
            default:
                System.out.println("There is no such option");
                break;
        }
    }

    public void showAllAuthors(){
        authorService.showAllAuthors();
    }

    public Optional<Author> getAuthorById(){
        System.out.println("Enter author id: ");
        Long id = in.nextLong();
        return authorService.getAuthorById(id);
    }


}

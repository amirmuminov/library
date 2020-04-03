package kz.muminov.iitu.library;

import kz.muminov.iitu.library.config.AnnotationConfiguration;
import kz.muminov.iitu.library.controller.AuthorController;
import kz.muminov.iitu.library.controller.BookController;
import kz.muminov.iitu.library.controller.UserController;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SpringApplication.run(LibraryApplication.class, args);

        Scanner in = new Scanner(System.in);
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AnnotationConfiguration.class);
        AuthorController authorController = context.getBean("authorController", AuthorController.class);
        BookController bookController = context.getBean("bookController", BookController.class);
        UserController userController = context.getBean("userController", UserController.class);

        showMenu();
        //Choose the entity
        int choice = in.nextInt();

        while(choice != 0){
            switch (choice){
                case 1:
                    bookController.showMenu();
                    int menuControllerChoice = in.nextInt();
                    bookController.choice(menuControllerChoice);
                    break;
                case 2:
                    authorController.showMenu();
                    menuControllerChoice = in.nextInt();
                    authorController.choice(menuControllerChoice);
                    break;
                case 3:
                    userController.showMenu();
                    menuControllerChoice = in.nextInt();
                    userController.choice(menuControllerChoice);
                    break;
                default:
                    System.out.println("There is no such option");
            }
            showMenu();
            choice = in.nextInt();
        }

    }

    private static void showMenu(){
        System.out.println("0. Exit");
        System.out.println("1. Book");
        System.out.println("2. Author");
        System.out.println("3. User");
    }

}

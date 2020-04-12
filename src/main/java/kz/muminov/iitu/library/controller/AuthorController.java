package kz.muminov.iitu.library.controller;

import kz.muminov.iitu.library.entity.Author;
import kz.muminov.iitu.library.serivce.AuthorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/all")
    public List<Author> showAllAuthors() {
        return authorService.showAllAuthors();
    }

    @GetMapping("/{id}")
    public Author getAuthorById(@PathVariable Long id){
        return authorService.getAuthorById(id).orElse(null);
    }


}

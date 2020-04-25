package kz.muminov.iitu.library.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kz.muminov.iitu.library.entity.Author;
import kz.muminov.iitu.library.serivce.AuthorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/authors")
@Api(value = "Author Management System")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("api/all")
    @ApiOperation(value = "Get all authors", response = List.class, httpMethod = "GET")
    public List<Author> showAllAuthors() {
        return authorService.showAllAuthors();
    }

    @GetMapping("api/{id}")
    @ApiOperation(value = "Get author by id", response = Author.class)
    public Author getAuthorById(@ApiParam(value = "ID to get author by id", required = true) @PathVariable Long id){
        return authorService.getAuthorById(id).orElse(null);
    }

}

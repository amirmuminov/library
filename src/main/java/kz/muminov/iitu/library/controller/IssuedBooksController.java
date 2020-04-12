package kz.muminov.iitu.library.controller;

import kz.muminov.iitu.library.entity.IssuedBooks;
import kz.muminov.iitu.library.serivce.IssuedBooksService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/issued")
public class IssuedBooksController {

    private final IssuedBooksService issuedBooksService;

    public IssuedBooksController(IssuedBooksService issuedBooksService) {
        this.issuedBooksService = issuedBooksService;
    }

    @GetMapping("/all")
    public List<IssuedBooks> findAll(){
        return issuedBooksService.findAll();
    }

    @GetMapping("/{id}")
    public IssuedBooks findById(@PathVariable Long id){
        return issuedBooksService.findById(id);
    }

}

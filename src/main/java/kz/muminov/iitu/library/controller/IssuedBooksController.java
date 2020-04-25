package kz.muminov.iitu.library.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kz.muminov.iitu.library.entity.IssuedBooks;
import kz.muminov.iitu.library.serivce.IssuedBooksService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/issued")
@Api(value = "Issued Book Management System")
public class IssuedBooksController {

    private final IssuedBooksService issuedBooksService;

    public IssuedBooksController(IssuedBooksService issuedBooksService) {
        this.issuedBooksService = issuedBooksService;
    }

    @GetMapping("/all")
    @ApiOperation(value = "Find all issued books", response = List.class)
    public List<IssuedBooks> findAll(){
        return issuedBooksService.findAll();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Find issued book by id", response = IssuedBooks.class)
    public IssuedBooks findById(@ApiParam(value = "ID for getting issued book", required = true) @PathVariable Long id){
        return issuedBooksService.findById(id);
    }

}

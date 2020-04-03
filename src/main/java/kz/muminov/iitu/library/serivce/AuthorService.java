package kz.muminov.iitu.library.serivce;

import kz.muminov.iitu.library.entity.Author;
import kz.muminov.iitu.library.repository.AuthorRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public void showAllAuthors(){
        for(Author author: authorRepository.findAll()){
            System.out.println("Author's ID: " + author.getId() + " " + "Author's name: " + author.getName());
        }
    }

    public Optional<Author> getAuthorById(Long id){
        return authorRepository.findById(id);
    }

}

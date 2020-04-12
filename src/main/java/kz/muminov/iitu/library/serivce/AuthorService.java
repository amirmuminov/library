package kz.muminov.iitu.library.serivce;

import kz.muminov.iitu.library.entity.Author;
import kz.muminov.iitu.library.repository.AuthorRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> showAllAuthors(){
        return authorRepository.findAll();
    }

    public Optional<Author> getAuthorById(Long id){
        return authorRepository.findById(id);
    }

}

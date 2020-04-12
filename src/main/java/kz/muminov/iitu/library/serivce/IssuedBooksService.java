package kz.muminov.iitu.library.serivce;

import kz.muminov.iitu.library.entity.IssuedBooks;
import kz.muminov.iitu.library.repository.IssuedBooksRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class IssuedBooksService {

    private final IssuedBooksRepository issuedBooksRepository;

    public IssuedBooksService(IssuedBooksRepository issuedBooksRepository) {
        this.issuedBooksRepository = issuedBooksRepository;
    }

    public List<IssuedBooks> findAll(){
        return issuedBooksRepository.findAll();
    }

    public IssuedBooks findById(Long id) {
        return issuedBooksRepository.findById(id).orElse(null);
    }
}

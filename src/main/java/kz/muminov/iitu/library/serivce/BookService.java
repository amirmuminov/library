package kz.muminov.iitu.library.serivce;

import kz.muminov.iitu.library.entity.Book;
import kz.muminov.iitu.library.entity.IssuedBooks;
import kz.muminov.iitu.library.enums.BookStatus;
import kz.muminov.iitu.library.repository.BookRepository;
import kz.muminov.iitu.library.repository.IssuedBooksRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class BookService {

    private final BookRepository bookRepository;
    private final IssuedBooksRepository issuedBooksRepository;

    public BookService(BookRepository bookRepository, IssuedBooksRepository issuedBooksRepository) {
        this.issuedBooksRepository = issuedBooksRepository;
        this.bookRepository = bookRepository;
    }

    public void showAllBooks(){
        for (Book book: bookRepository.findAll()){
            System.out.println(book.toString());
        }
    }

    public void addBook(Book book){
        bookRepository.save(book);
    }

    public void findBook(String search){
        for (Book book: bookRepository.findAllByTitleIsContainingOrDescriptionIsContainingOrAuthorNameIsContainingIgnoreCase(search)){
            System.out.println(book.toString());
        }
    }

    public Optional<Book> findBookById(Long id){
        return bookRepository.findById(id);
    }

    @Scheduled(cron="*/10 * * * * *")
    public void checkIfBookDueToDate() {
        for (IssuedBooks issuedBooks: issuedBooksRepository.findByExpectedReturnDateBeforeAndActualReturnDate(LocalDate.now(), null)){
            Book book = issuedBooks.getBook();
            book.setBookStatus(BookStatus.OVER_DUE_DATE);
            bookRepository.save(book);
        }
    }

}

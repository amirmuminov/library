package kz.muminov.iitu.library.serivce;

import kz.muminov.iitu.library.entity.Book;
import kz.muminov.iitu.library.entity.IssuedBooks;
import kz.muminov.iitu.library.entity.User;
import kz.muminov.iitu.library.enums.BookStatus;
import kz.muminov.iitu.library.enums.Status;
import kz.muminov.iitu.library.repository.BookRepository;
import kz.muminov.iitu.library.repository.IssuedBooksRepository;
import kz.muminov.iitu.library.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final IssuedBooksRepository issuedBooksRepository;

    public UserService(UserRepository userRepository, BookRepository bookRepository, IssuedBooksRepository issuedBooksRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.issuedBooksRepository = issuedBooksRepository;
    }

    public Optional<User> findUserById(Long id){
        return userRepository.findById(id);
    }

    public IssuedBooks issueBook(IssuedBooks issuedBooks){
        if (issuedBooks.getBook().getBookStatus() == BookStatus.RETURNED) {
            Book book = issuedBooks.getBook();
            book.setBookStatus(BookStatus.ISSUED);
            bookRepository.save(book);
            return issuedBooksRepository.save(issuedBooks);
        }
        else{
            System.out.println("The book is not available");
            return null;
        }
    }

    public IssuedBooks returnBook(IssuedBooks issuedBooks){
        if (issuedBooks.getBook().getBookStatus() == BookStatus.ISSUED || issuedBooks.getBook().getBookStatus() == BookStatus.OVER_DUE_DATE) {
            issuedBooks = issuedBooksRepository.findByUserAndBookAndActualReturnDate(issuedBooks.getUser(), issuedBooks.getBook(), null);
            if (issuedBooks != null) {
                issuedBooks.setActualReturnDate(LocalDate.now());
                if (issuedBooks.getActualReturnDate().isAfter(issuedBooks.getExpectedReturnDate())) {
                    issuedBooks.getUser().setStatus(Status.UNSCRUPULOUS);
                    userRepository.save(issuedBooks.getUser());
                }
                issuedBooks.getBook().setBookStatus(BookStatus.RETURNED);
                bookRepository.save(issuedBooks.getBook());
                return issuedBooksRepository.save(issuedBooks);
            } else {
                System.out.println("It's not possible to return the book. Please check user id and book id");
            }
        }else{
            System.out.println("It's not possible to return the book. Please check user id and book id");
        }
        return issuedBooks;
    }

    public boolean ifDataIsEmpty(Optional<User> user, Optional<Book> book){
        return user.isPresent() && book.isPresent();
    }

    public List<User> showAllUsers(){
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }
}

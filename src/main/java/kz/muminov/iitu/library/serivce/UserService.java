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

    public void issueBook(User user, Book book){
        if (ifDataIsEmpty(user, book)){
            if (book.getBookStatus() == BookStatus.RETURNED) {
                IssuedBooks issuedBooks = new IssuedBooks(user, book);
                issuedBooksRepository.save(issuedBooks);
                book.setBookStatus(BookStatus.ISSUED);
                bookRepository.save(book);
            }
            else{
                System.out.println("The book is not available");
            }
        }
    }

    public void returnBook(User user, Book book){
        if (ifDataIsEmpty(user, book)) {
            if (book.getBookStatus() == BookStatus.ISSUED || book.getBookStatus() == BookStatus.OVER_DUE_DATE) {
                IssuedBooks issuedBooks = issuedBooksRepository.findByUserAndBookAndActualReturnDate(user, book, null);
                if (issuedBooks != null) {
                    issuedBooks.setActualReturnDate(LocalDate.now());
                    if (issuedBooks.getActualReturnDate().isAfter(issuedBooks.getExpectedReturnDate())) {
                        user.setStatus(Status.UNSCRUPULOUS);
                        userRepository.save(user);
                    }
                    issuedBooksRepository.save(issuedBooks);
                    book.setBookStatus(BookStatus.RETURNED);
                    bookRepository.save(book);
                } else {
                    System.out.println("It's not possible to return the book. Please check user id and book id");
                }
            }else{
                System.out.println("It's not possible to return the book. Please check user id and book id");
            }
        }
    }

    private boolean ifDataIsEmpty(User user, Book book){
        boolean isEmpty = false;
        if (user == null){
            isEmpty = true;
            System.out.println("Invalid ID for the user");
        }
        if (book == null){
            isEmpty = true;
            System.out.println("Invalid ID for the book");
        }
        return !isEmpty;
    }

    public void showAllUsers(){
        for (User user: userRepository.findAll()){
            System.out.println(user.toString());
        }
    }

}

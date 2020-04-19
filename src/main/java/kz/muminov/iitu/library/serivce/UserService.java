package kz.muminov.iitu.library.serivce;

import kz.muminov.iitu.library.entity.Book;
import kz.muminov.iitu.library.entity.IssuedBooks;
import kz.muminov.iitu.library.entity.User;
import kz.muminov.iitu.library.enums.BookStatus;
import kz.muminov.iitu.library.enums.Role;
import kz.muminov.iitu.library.enums.Status;
import kz.muminov.iitu.library.repository.BookRepository;
import kz.muminov.iitu.library.repository.IssuedBooksRepository;
import kz.muminov.iitu.library.repository.RoleRepository;
import kz.muminov.iitu.library.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final IssuedBooksRepository issuedBooksRepository;
    private final RoleRepository roleRepository;

//    public UserService(UserRepository userRepository,
//                       BookRepository bookRepository,
//                       IssuedBooksRepository issuedBooksRepository,
//                       BCryptPasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.bookRepository = bookRepository;
//        this.issuedBooksRepository = issuedBooksRepository;
//        this.passwordEncoder = passwordEncoder;
//    }

    public UserService(UserRepository userRepository,
                       BookRepository bookRepository,
                       IssuedBooksRepository issuedBooksRepository,
                       RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.issuedBooksRepository = issuedBooksRepository;
        this.roleRepository = roleRepository;
    }

    public Optional<User> findUserById(Long id){
        return userRepository.findById(id);
    }

    public IssuedBooks issueBook(Book book, User user){
        if (book.getBookStatus() == BookStatus.RETURNED) {
            book.setBookStatus(BookStatus.ISSUED);
            bookRepository.save(book);
            IssuedBooks issuedBooks = new IssuedBooks(user, book);
            return issuedBooksRepository.save(issuedBooks);
        }
        else{
            System.out.println("The book is not available");
            return null;
        }
    }

    public IssuedBooks returnBook(Book book, User user){
        if (book.getBookStatus() == BookStatus.ISSUED || book.getBookStatus() == BookStatus.OVER_DUE_DATE) {
            IssuedBooks issuedBooks = issuedBooksRepository.findByUserAndBookAndActualReturnDate(user, book, null);
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
        return null;
    }


    public List<User> showAllUsers(){
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        user.setPassword(passwordEncoder().encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null)
            throw new UsernameNotFoundException("User with username: " + username + " is not found");

        return user;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}

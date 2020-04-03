package kz.muminov.iitu.library.repository;

import kz.muminov.iitu.library.entity.Book;
import kz.muminov.iitu.library.entity.IssuedBooks;
import kz.muminov.iitu.library.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IssuedBooksRepository extends JpaRepository<IssuedBooks, Long> {

    public IssuedBooks findByUserAndBookAndActualReturnDate(User user, Book book, LocalDate actualReturnDate);

    public List<IssuedBooks> findByExpectedReturnDateBeforeAndActualReturnDate(LocalDate nowDate, LocalDate actualReturnDate);

}

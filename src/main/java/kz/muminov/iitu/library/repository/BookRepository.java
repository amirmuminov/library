package kz.muminov.iitu.library.repository;

import kz.muminov.iitu.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b WHERE lower(b.author.name) LIKE %:search% OR lower(b.title) LIKE %:search% OR lower(b.description) LIKE %:search%")
    List<Book> findAllByTitleIsContainingOrDescriptionIsContainingOrAuthorNameIsContainingIgnoreCase(@Param("search") String search);

}

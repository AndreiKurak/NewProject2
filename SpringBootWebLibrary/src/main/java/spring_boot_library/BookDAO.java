package spring_boot_library;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import spring_boot_library.Book;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookDAO extends JpaRepository<Book, Integer> {

    List<Book> findAllByAuthorContainsAndTitleContainsAndYearContains(String author, String title, String year);

    <S extends Book> S save(S entity);


    Book findById(int id);
}

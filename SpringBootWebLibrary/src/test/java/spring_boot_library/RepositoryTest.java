package spring_boot_library;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    BookDAO bookDAO;

    @Test
    public void test() {
        // given
        Book book = new Book( "author", "title", "year");
        entityManager.persist(book);
        entityManager.flush();

        // when
        Book found = bookDAO.findById(book.getId());

        // then
        assertThat(found.getAuthor())
                .isEqualTo(book.getAuthor());
    }
}

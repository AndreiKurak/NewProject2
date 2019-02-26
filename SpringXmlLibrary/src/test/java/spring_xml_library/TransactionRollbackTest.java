package spring_xml_library;

import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:database_test_config.xml")
public class TransactionRollbackTest {   //without annotation @Transaction

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private BookDAO bookDAO;

    JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Test //must enable tx:annotation-driven
    public void shouldWorkWithoutTransactionalAnnotationAndMakeRollback() throws Exception{

        Book book = new Book("author", "title", "2018");
        bookDAO.add(book);
        throw new RuntimeException();

        //assertThat(JdbcTestUtils.countRowsInTable(jdbcTemplate, "library")).isEqualTo(4);

    }

    @Test
    @Transactional
    @Rollback(false)
    public void shouldRollback() /*throws Exception */ {

        Book book = new Book("author", "title", "2017");
        bookDAO.add(book);

        throw new RuntimeException();

        //assertThat(JdbcTestUtils.countRowsInTable(jdbcTemplate, "library")).isEqualTo(4);
    }

    @AfterTransaction
    public void after() {
        assertThat(JdbcTestUtils.countRowsInTable(jdbcTemplate, "library")).isEqualTo(0);
        jdbcTemplate.execute("DELETE FROM doc_register_test.library");
    }
}

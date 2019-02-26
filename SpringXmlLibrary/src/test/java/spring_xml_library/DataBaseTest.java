package spring_xml_library;

import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:database_test_config.xml")
public class DataBaseTest {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    LibService libService;

    JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Before
    public void before() {

    }

    @Test
    @Transactional
    //@Commit
    public void libServiceAddMethodShouldAddNewBookIntoTable() throws Exception{
        Book book = new Book("author", "title", "2018");
        libService.add(book);
        sessionFactory.getCurrentSession().flush();

        assertThat(JdbcTestUtils.countRowsInTable(jdbcTemplate, "library")).isEqualTo(1);
    }

    @Test
    @Transactional
    public void libServiceListMethodShouldReturnListWithCorrectCountOfBooks() throws Exception {
        Book book = new Book("author2", "title2", "2018");
        libService.add(book);
        sessionFactory.getCurrentSession().flush();


        assertThat(libService.list().size()).isEqualTo(1);
    }

    @After
    public void after() {
        jdbcTemplate.execute("DELETE FROM doc_register_test.library");
    }
}

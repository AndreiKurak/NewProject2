package spring_xml_library;

import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
public class TxAnnotationDrivenTest {   //without annotation @Transaction

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private LibService libService;

    JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Test
    public void test() throws Exception{

        Book book = new Book("author", "title", "2018");
        libService.add(book);
        throw new RuntimeException();

        //assertThat(JdbcTestUtils.countRowsInTable(jdbcTemplate, "library")).isEqualTo(4);

    }

    @Test
    @Transactional
    @Commit
    public void test2() throws Exception{
        Book book = new Book("author", "title", "2018");
        libService.add(book);
        throw new Exception();

        //assertThat(JdbcTestUtils.countRowsInTable(jdbcTemplate, "library")).isEqualTo(4);
    }

    @After
    public void after() {
        //jdbcTemplate.execute("DELETE FROM doc_register_test.library");
    }
}

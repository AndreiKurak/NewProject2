package examples_test;

import examples_test.chapter1_8.BasicDataSource;
import examples_test.chapter1_8.Messenger;
import examples_test.chapter1_8.SourceFactory;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ContainerExtensionPointsTest1_8 {

    @Test
    public void test() {
        ApplicationContext appContext = new ClassPathXmlApplicationContext("config1_8.xml");
        Messenger messenger = appContext.getBean("messenger", Messenger.class);
    }

    @Test
    public void shouldSetPropertyValueFromPropertyFile() {
        ApplicationContext appContext = new ClassPathXmlApplicationContext("config1_8.xml");
        BasicDataSource dataSource = appContext.getBean("dataSource", BasicDataSource.class);
        assertThat(dataSource.getUsername()).isEqualTo("root");
    }

    @Test
    public void shouldReturnFactory() {
        ApplicationContext appContext = new ClassPathXmlApplicationContext("config1_8.xml");
        assertThat(appContext.getBean("&sourceFactory")).isInstanceOf(SourceFactory.class);
    }
}

package examples_test;

import examples_test.chapter1_4.CommandManager;
import examples_test.chapter1_4.ExampleBean;
import examples_test.chapter1_4.ExampleBean2;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DependenciesTest1_4 {

    @Test
    public void shouldCreateDependencyObjects() {
        ApplicationContext appContext = new ClassPathXmlApplicationContext("example_config1_4.xml");
        ExampleBean exampleBean = appContext.getBean("exampleBean", ExampleBean.class);
        assertThat(exampleBean.getInt()).isEqualTo(1);
    }

    @Test
    public void shouldMergeParentCollection() {
        ApplicationContext appContext = new ClassPathXmlApplicationContext("example_config1_4.xml");
        ExampleBean exampleBean = appContext.getBean("beanChild", ExampleBean.class);
        assertThat(exampleBean.getCollection().containsKey("key3")).isEqualTo(true);
    }

    @Test
    public void shouldInitializeAutowiredBean() {
        ApplicationContext appContext = new ClassPathXmlApplicationContext("example_config1_4.xml");
        ExampleBean2 exampleBean2 = appContext.getBean("exampleBean2", ExampleBean2.class);
        assertThat(exampleBean2.getAutowiredBean()).isNotNull();
    }

    @Test
    public void shouldCreateTwoDifferentBeans() {
        ApplicationContext appContext = new ClassPathXmlApplicationContext("example_config1_4.xml");
        CommandManager commandManager = appContext.getBean("commandManager", CommandManager.class);
        assertThat(commandManager.process("string")).isNotEqualTo(commandManager.process("string"));
    }
}

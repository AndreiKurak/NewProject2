package examples_test;

import examples_test.chapter1_6.ExampleBean;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CustomizingNatureOfBeansTest1_6 {

    @Test
    public void shouldExecuteMethodInit() {
        ApplicationContext appContext = new ClassPathXmlApplicationContext("lifecycle1_6.xml");
        ExampleBean exampleBean = (ExampleBean) appContext.getBean("exampleBean");
        assertThat(exampleBean.getMessage()).isEqualTo("init");
    }

    @Test
    public void shouldExecuteMethodCleanup() {
        ApplicationContext appContext = new ClassPathXmlApplicationContext("lifecycle1_6.xml");
        ExampleBean exampleBean = (ExampleBean) appContext.getBean("exampleBean");
        ((ClassPathXmlApplicationContext) appContext).close();
        assertThat(exampleBean.getMessage()).isEqualTo("cleanup");
    }
}

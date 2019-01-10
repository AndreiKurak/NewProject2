package examples_test;

import examples_test.chapter1_1_to_1_3.ServiceBean;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ContainerOverviewTest1_2 {

    @Test
    public void shouldReturnTrueBecauseAppContextContainsBeanOfXmlThatWasImportedInAnotherXml() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("importing1_1_to_1_3.xml");
        boolean isContains = applicationContext.containsBean("serviceBean");
        assertThat(isContains).isEqualTo(true);
    }

    @Test
    public void shouldCreateBeanWithMethodGetBean() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("services1_1_to_1_3.xml");
        ServiceBean serviceBean = applicationContext.getBean("serviceBean", ServiceBean.class);
        assertThat(serviceBean.toString()).isEqualTo("ServiceBean");
    }
}

package examples_test;

import examples_test.chapter1_1_to_1_3.ServiceBean;
import examples_test.chapter1_1_to_1_3.ServiceBeanFactory;
import examples_test.chapter1_1_to_1_3.SomeBean;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanOverviewTest1_3 {

    @Test
    public void shouldReturnTrueBecauseBeanHasAliasBesidesId() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("importing1_1_to_1_3.xml");
        boolean isContains = applicationContext.containsBean("serviceBean2");
        assertThat(isContains).isEqualTo(true);
    }

    @Test
    public void shouldCreateBeanWithItsStaticFactoryMethod() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("importing1_1_to_1_3.xml");
        SomeBean someBean = SomeBean.createInstance();
        assertThat(someBean.toString()).isEqualTo("SomeBean");
    }

    @Test
    public void shouldCreateBeanByFactoryInstance() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("services1_1_to_1_3.xml");
        ServiceBeanFactory serviceBeanFactory = applicationContext.getBean("beanFactory", ServiceBeanFactory.class);
        ServiceBean someBean = serviceBeanFactory.createServiceBeanInstance();
        assertThat(someBean.toString()).isEqualTo("ServiceBean");
    }
}

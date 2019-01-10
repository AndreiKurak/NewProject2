package examples_test;

import examples_test.chapter1_7.ChildBean;
import examples_test.chapter1_7.ParentBean;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanDefinitionInheritanceTest1_7 {

    @Test
    public void shouldOverrideParentPropertyValue() {
        ApplicationContext appContext = new ClassPathXmlApplicationContext("inheritance1_7.xml");
        ParentBean child = appContext.getBean("child", ChildBean.class);
        assertThat(child.getName()).isEqualTo("child");
    }
}

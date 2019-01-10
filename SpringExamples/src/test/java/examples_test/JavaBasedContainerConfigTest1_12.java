package examples_test;

import examples_test.chapter1_12.AppConfig;
import examples_test.chapter1_12.MyService;
import examples_test.chapter1_12.OtherConfig;
import examples_test.chapter1_12.TestComponent;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JavaBasedContainerConfigTest1_12 {

    @Test
    public void shouldInitBeanThroughConfigClass() {
        ApplicationContext appContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MyService myService = (MyService) appContext.getBean("myService");
        assertThat(myService).isNotNull();
    }

    @Test
    public void shouldInitBeanThroughComponent() {
        ApplicationContext appContext = new AnnotationConfigApplicationContext(TestComponent.class);
        MyService myService = appContext.getBean(MyService.class);
        assertThat(myService).isNotNull();
    }

    @Test
    public void should3() {
        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
        appContext.register(AppConfig.class);
        appContext.register(OtherConfig.class);
        appContext.refresh();
        MyService myService = appContext.getBean(MyService.class);
        assertThat(myService).isNotNull();
    }

    @Test
    public void shouldInitBeanWithComponentScanning() {
        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
        appContext.scan("examples_test.chapter1_12");
        appContext.refresh();
        MyService myService = appContext.getBean(MyService.class);
        assertThat(myService).isNotNull();
    }
}

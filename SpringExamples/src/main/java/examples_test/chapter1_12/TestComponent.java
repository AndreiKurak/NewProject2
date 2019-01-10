package examples_test.chapter1_12;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class TestComponent {
    
    MyService myService;

    @Bean
    public MyService myService() {
        return new MyService();
    }

    /*@Autowired
    public void setMyService(MyService myService) {
        this.myService = myService;
    }*/
}

package examples_test.chapter1_12;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "examples_test.chapter1_12")
public class AppConfig {

    @Bean
    public MyService myService() {
        return new MyService();
    }

    @Bean(initMethod = "init")
    public TransferService transferService(/*MyService myService*/) {
        return new TransferService(myService());
    }
}


/*
<beans>
<bean id="myService" class="com.acme.services.MyServiceImpl"/>
</beans>*/

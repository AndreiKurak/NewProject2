package spring_common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public WebParametersParser webParametersParser() {
        return new WebParametersParser();
    }
}

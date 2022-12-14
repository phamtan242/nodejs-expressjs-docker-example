package Proto;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfigure {
    @Bean(name = "topicName")
    public String hazelcastTopicName() {
        return "hazelCast";
    }
}
package demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfigure {
    // @Bean(name = "pubSubListener")
    // // @DependsOn("hazelcastInstance")
    // public PubsubListener pubSubListener() {
    //     return new PubsubListener();
    // }

    @Bean(name = "topicName")
    public String hazelcastTopicName() {
        return "hazelCast";
    }
}
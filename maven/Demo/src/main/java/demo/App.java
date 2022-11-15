package demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import demo.pubsub.PubsubListener;


/**
 * Hello world!
 *
 */
@SpringBootApplication
public class App 
{
    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            String[] beanNames = ctx.getBeanDefinitionNames();
            System.out.println("Initiated total " + beanNames.length + " beans!");
            // Arrays.sort(beanNames);
            // for (String beanName : beanNames) {
            //     System.out.println(beanName);
            // }

            // start pubsub
            PubsubListener pubSubListener = (PubsubListener) ctx.getBean("pubsubListener");
            pubSubListener.consume();
        };
    }
}

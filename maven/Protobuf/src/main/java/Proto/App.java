package Proto;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import Proto.services.PubsubListener;
import Proto.protos.AddressBookProtos;
import Proto.protos.AddressBookProtos.Person;

import java.util.Random;
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


            String email = "j@baeldung.com";
            int id = new Random().nextInt();
            String name = "Michael Program";
            String number = "01234567890";
            AddressBookProtos.Person person = AddressBookProtos.Person.newBuilder()
                .setId(id)
                .setName(name)
                .setEmail(email)
                .addNumbers(number)
                .build();

            System.out.println("name = " + person.getName());
        };
    }
}
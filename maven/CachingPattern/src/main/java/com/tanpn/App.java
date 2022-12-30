package com.tanpn;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * SpringBootApplication
 *
 */
@SpringBootApplication
public class App 
{
    public static void main( String[] args ) throws Exception
    {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            String[] beanNames = ctx.getBeanDefinitionNames();
            System.out.println("Initiated total " + beanNames.length + " beans!");
            java.util.Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }
            
            // ICache<String, String> lvSimpleCaching = (ICache) ctx.getBean("serverCacheTemplate");
            // lvSimpleCaching.set("name1", "my-name");

            // String lvValue = (String) lvSimpleCaching.get("name");
            // System.out.println("Cached value: " + lvValue);

            // Thread.sleep(5000);

            // BookService lvBookService = (BookService) ctx.getBean("bookService");
            // lvBookService.doSomething();


        };
    }
}

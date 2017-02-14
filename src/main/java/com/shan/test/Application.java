package com.shan.test;
import com.shan.test.model.Contact;
import com.shan.test.repository.ContactRepository;
import org.jboss.resteasy.plugins.server.servlet.HttpServletDispatcher;
import org.jboss.resteasy.plugins.server.servlet.ResteasyBootstrap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import javax.servlet.ServletContextListener;

@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
public class Application {
    private static final Logger log = LoggerFactory.getLogger(Application.class);
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public ServletContextListener restEasyBootstrap() {
        return new ResteasyBootstrap();
    }
    @Bean
    public ServletRegistrationBean restEasyServlet() {
        final ServletRegistrationBean registrationBean = new ServletRegistrationBean();
        registrationBean.setServlet(new HttpServletDispatcher());
        registrationBean.setName("restEasy-servlet");
        registrationBean.addUrlMappings("/*");
        registrationBean.addInitParameter("javax.ws.rs.Application", "com.shan.test.JaxrsApplication");
        return registrationBean;
    }
    @Bean
    public CommandLineRunner demo(ContactRepository repository) {
        return (args) -> {
            // save a couple of customers
            repository.save(new Contact("Jack", "Bauer"));
            repository.save(new Contact("Chloe", "O'Brian"));
            repository.save(new Contact("Kim", "Bauer"));
            repository.save(new Contact("David", "Palmer"));
            repository.save(new Contact("Michelle", "Dessler"));

            // fetch all customers
            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (Contact customer : repository.findAll()) {
                log.info(customer.toString());
            }
            log.info("");

            // fetch an individual customer by ID
            Contact customer = repository.findOne(1L);
            log.info("Customer found with findOne(1L):");
            log.info("--------------------------------");
            log.info(customer.toString());
            log.info("");

            // fetch customers by last name
            log.info("Customer found with findByLastName('Bauer'):");
            log.info("--------------------------------------------");
            for (Contact bauer : repository.findByLastName("Bauer")) {
                log.info(bauer.toString());
            }
            log.info("");
        };
    }
}

package com.shan.demo;
import com.shan.demo.model.Category;
import com.shan.demo.model.Contact;
import com.shan.demo.model.Relationship;
import com.shan.demo.repository.CategoryRepository;
import com.shan.demo.repository.ContactRepository;
import com.shan.demo.repository.RelationshipRepository;
import com.shan.demo.services.ContactService;
import com.shan.demo.services.CategoryService;
import com.shan.demo.services.RelationshipService;
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
import java.util.Date;

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
        registrationBean.addInitParameter("javax.ws.rs.Application", "com.shan.demo.JaxrsApplication");
        return registrationBean;
    }
    @Bean
    public CommandLineRunner demo(ContactService addressBook, RelationshipService relationshipService, CategoryService categoryService,
                                  ContactRepository contactRepository, RelationshipRepository relationshipRepository, CategoryRepository categoryRepository) {
        return (args) -> {
            // Save Few Contacts
            Contact contact = new Contact("Shan", "Srinivasan");
            contact.setEmail("shansrinivasan@yahoo.com");
            contact.setCellPhone("4046421154");
            contact.setDob(new Date());
            contact.setFax("4046421153");
            contact.setHomePhone("4046421155");
            addressBook.createContact(contact);
            contact = new Contact("DemofirstName", "DemolastName");
            contact.setEmail("demo@yahoo.com");
            contact.setCellPhone("3046421154");
            contact.setDob(new Date());
            contact.setFax("3046421153");
            contact.setHomePhone("3046421155");
            addressBook.createContact(contact);
            contact = new Contact("TestfirstName", "TestlastName");
            contact.setEmail("demo@gmail.com");
            contact.setCellPhone("2046421154");
            contact.setDob(new Date());
            contact.setFax("2046421153");
            contact.setHomePhone("2046421155");
            addressBook.createContact(contact);

            //Establish Relationships
            Contact shan = contactRepository.findByFirstNameAndLastNameContainingIgnoringCaseOrderByFirstName("Shan","Srinivasan").stream().findFirst().get();
            Contact demo = contactRepository.findByFirstNameAndLastNameContainingIgnoringCaseOrderByFirstName("DemofirstName","DemolastName").stream().findFirst().get();
            Relationship relationship = new Relationship();
            relationship.setSource(shan);
            relationship.setTarget(demo);
            relationship.setType("Friend");
            relationshipService.createRelationship(relationship);


            //Create Few Categories
            Category category  = new Category();
            category.setName("Business");
            categoryService.createCategory(category);
            category  = new Category();
            category.setName("Personal");
            categoryService.createCategory(category);


            //Add Contacts to Categories.
            Category businessCategory = categoryRepository.findByNameIgnoringCase("Business").stream().findFirst().get();
            Category personalCategory = categoryRepository.findByNameIgnoringCase("Personal").stream().findFirst().get();

            categoryService.addContact(businessCategory.getId(),shan.getId());
            categoryService.addContact(personalCategory.getId(),demo.getId());

            //Tag Contacts
            addressBook.tagContact(shan.getId(),"Tagging as Main");
            addressBook.tagContact(demo.getId(),"Tagging as Demo");


        };
    }
}

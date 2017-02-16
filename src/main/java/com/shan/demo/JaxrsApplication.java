package com.shan.demo;

import com.shan.demo.services.*;
import io.swagger.jaxrs.config.BeanConfig;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by ssrinivasan on 2/13/2017.
 */
@Component
@ApplicationPath("/addressbook/")
public class JaxrsApplication  extends Application {
    private final Set<Object> singletons = new HashSet<Object>();

    public JaxrsApplication() {
        this.init();
    }

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet();
        //resources.add(FirstResource.class);
        //resources.add(SecondResource.class);
        //...
        resources.add(ContactService.class);
        resources.add(io.swagger.jaxrs.listing.ApiListingResource.class);
        resources.add(io.swagger.jaxrs.listing.SwaggerSerializers.class);
        return resources;
    }
    protected void init() {
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.2");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setHost("localhost:8080");
        beanConfig.setBasePath("/");
        beanConfig.setResourcePackage("io.swagger.resources");
        beanConfig.setScan(true);
         this.singletons.add(ContactService.class);
         this.singletons.add(RelationshipService.class);
         this.singletons.add(TagService.class);
         this.singletons.add(CategoryService.class);
         this.singletons.add(AddressBookService.class);
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}

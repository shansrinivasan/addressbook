package com.shan.test;

import com.shan.test.services.ContactService;
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

    protected void init() {
         this.singletons.add(ContactService.class);
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}

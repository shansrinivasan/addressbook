package com.shan.test.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.shan.test.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by ssrinivasan on 2/13/2017.
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "addressbook")
@Path("/addressbook")
@Service
public class AddressBookService {

    @XmlElement(name = "contacts")
    private String uri1 = "/contacts";

    @XmlElement(name = "relationships")
    private String uri2 = "/relationships/";

    @XmlElement(name = "tags")
    private String uri3 = "/tags/";

    public String getUri3() {
		return uri3;
	}

	public void setUri3(String uri3) {
		this.uri3 = uri3;
	}

	public String getUri1() {
        return uri1;
    }

    public void setUri1(String uri1) {
        this.uri1 = uri1;
    }

    public String getUri2() {
        return uri2;
    }

    public void setUri2(String uri2) {
        this.uri2 = uri2;
    }


    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_XML)
    public AddressBookService getServiceInfo() {
        return new AddressBookService();
    }
}

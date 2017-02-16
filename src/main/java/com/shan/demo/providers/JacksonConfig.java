package com.shan.demo.providers;

import java.text.SimpleDateFormat;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Provider  
@Produces(MediaType.APPLICATION_JSON)  
public class JacksonConfig implements ContextResolver<ObjectMapper>  
{  
   private ObjectMapper objectMapper;  
  
  
   public JacksonConfig() throws Exception  
   {  
      this.objectMapper = new ObjectMapper();  
      this.objectMapper.setDateFormat(new SimpleDateFormat("MM-dd-yyyy HH:mm:ss"));  
      this.objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);  
   }  
  
  
   public ObjectMapper getContext(Class<?> objectType)  
   {  
      return objectMapper;  
   }  
}
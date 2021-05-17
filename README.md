# addressbook
The code can be cloned/downloaded from the below GitHub URL
https://github.com/shansrinivasan/addressbook

Instructions to run the application(Maven/REST EASY/Spring Boot/JPA/Groovy):
**The application requires Java 1.8
***DEMO**RAMAN my change is the best 

1) git clone from the above URL or extract the attached zip file.
2) Open pom.xml import as maven project
3) Run main class com.shan.demo.Application 

This should boot up the whole application and the application should be accessible at the below URL
http://localhost:8080/addressbook-management
Chrome browser recommended
Application is HATEOAS based, RESTEASY based REST implementation.
The management page should list all the operations and the links should be navigable from the browser itself.
The resources are self referenced and it should be self explanatory.

Note:
1) com.shan.demo.Application class has a demo method which loads few application test data. More data can be added by modifying the code in this method. There are better and cleaner ways to load the test data, I took this route to keep it quick and simple.
2) Sample Groovy test case is added in the src/test/groovy folder. 
3) Application uses in memory database and managed by spring JPA. So depending on the use cases, performance issues, volume of data, the DB and Object design will be refactored. this is just initial implementation for the requirements. There are tools to extract the DB scripts to use any other database.
4) With http://localhost:8080/contacts/1/relationship/2 we can implement recursive family tree/relatonship tree with any UI. I did not cover that part of implementation. Simple recursive code can give all the contacts in the relationship.
5) This is implemented purely as a POC/spike task.


Sample output of the address book-management json, on click of these links, one should be able to navigate the resources in the system.
By default i have set to be formatted output, so the responses should look good in most of the browsers.
{
  "links" : [ {
    "rel" : "self",
    "href" : "http://localhost:8080/addressbook-management"
  } ],
  "content" : [ {
    "uri" : "contacts",
    "links" : [ {
      "rel" : "self",
      "href" : "http://localhost:8080/contacts"
    } ]
  }, {
    "uri" : "categories",
    "links" : [ {
      "rel" : "self",
      "href" : "http://localhost:8080/categories"
    } ]
  }, {
    "uri" : "relationships",
    "links" : [ {
      "rel" : "self",
      "href" : "http://localhost:8080/relationships"
    } ]
  }, {
    "uri" : "tags",
    "links" : [ {
      "rel" : "self",
      "href" : "http://localhost:8080/tags"
    } ]
  } ]
}

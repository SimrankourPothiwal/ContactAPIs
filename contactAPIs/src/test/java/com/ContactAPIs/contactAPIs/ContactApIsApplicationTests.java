package com.ContactAPIs.contactAPIs;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import com.ContactAPIs.contactAPIs.Models.Address;
import com.ContactAPIs.contactAPIs.Models.Contact;
import com.ContactAPIs.contactAPIs.Models.Name;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = ContactApIsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ContactApIsApplicationTests {
	
	@Autowired
    private TestRestTemplate restTemplate;

	@LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }
//	@Test
//	void contextLoads() {
//	}
	
	@Test
    public void testGetAllContacts() {
    HttpHeaders headers = new HttpHeaders();
       HttpEntity<String> entity = new HttpEntity<String>(null, headers);
       ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/contacts",
       HttpMethod.GET, entity, String.class);  
       assertNotNull(response.getBody());
    }

	 @Test
	 public void testGetContactById() {
	   Contact Contact = restTemplate.getForObject(getRootUrl() + "/contacts/1", Contact.class);
	   System.out.println(Contact.getName());
	   assertNotNull(Contact);
	 }
	 
	 @Test
	    public void testCreateContact() {
		 	Name name = new Name();
		 	name.setFirst("Harold");
		 	name.setMiddle("Francis");
		 	name.setLast("Gilkey");
		 	Address address = new Address();
		 	address.setCity("Cannon");
		 	address.setState("Delaware");
	        Contact contact = new Contact();
	        contact.setName(name);
	        contact.setAddress(address);
	        contact.setEmail("harold.gilkey@yahoo.com");
	        
	        ResponseEntity<Contact> postResponse = restTemplate.postForEntity(getRootUrl() + "/contacts", contact, Contact.class);
	        assertNotNull(postResponse);
	        assertNotNull(postResponse.getBody());
	    }

	    @Test
	    public void testUpdateContact() {
	        int id = 1;
	        Contact contact = restTemplate.getForObject(getRootUrl() + "/contacts/" + id, Contact.class);
	        contact.setEmail("harold.francis@yahoo.com");
	        restTemplate.put(getRootUrl() + "/Contacts/" + id, contact);
	        Contact updatedContact = restTemplate.getForObject(getRootUrl() + "/Contacts/" + id, Contact.class);
	        assertNotNull(updatedContact);
	    }

	    @Test
	    public void testDeleteContact() {
	         int id = 2;
	         Contact contact = restTemplate.getForObject(getRootUrl() + "/Contacts/" + id, Contact.class);
	         assertNotNull(contact);
	         restTemplate.delete(getRootUrl() + "/Contacts/" + id);
	         try {
	              contact = restTemplate.getForObject(getRootUrl() + "/Contacts/" + id, Contact.class);
	         } catch (final HttpClientErrorException e) {
	              assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
	         }
	    }
	
	
	
}

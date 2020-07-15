package com.ContactAPIs.contactAPIs.Controllers;

import com.ContactAPIs.contactAPIs.Models.Contact;
import com.ContactAPIs.contactAPIs.Service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ContactController {
    @Autowired
    private ContactService contactService;
    @GetMapping("/contacts/{id}")
    public Contact getArticleById(@PathVariable("id") Integer id) {
        Contact contact = contactService.getContactById(id);
        return contact;
    }
    @GetMapping("/contacts")
    public List<Contact> getAllArticles() {
        List<Contact> list = contactService.findAll();
        return list;
    }
    @PostMapping("/contacts")
    public Contact addArticle(@RequestBody Contact contact) {
        return contactService.saveContact(contact);

    }
    @PutMapping("/contacts/{id}")
    public Contact updateArticle(@RequestBody Contact contact,@PathVariable("id") Integer id) {
        return contactService.updateContact(contact,id);
    }
    @DeleteMapping("/contacts/{id}")
    public void deleteArticle(@PathVariable("id") Integer id) {
        contactService.deleteById(id);
    }
}

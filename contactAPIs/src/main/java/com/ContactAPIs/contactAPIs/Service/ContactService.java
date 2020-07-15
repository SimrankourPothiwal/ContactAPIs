package com.ContactAPIs.contactAPIs.Service;

import com.ContactAPIs.contactAPIs.Models.Contact;
import com.ContactAPIs.contactAPIs.Repository.ContactRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactService {
    @Autowired
    private ContactRepo contactRepository;

    public List<Contact> findAll() {

        Iterable it = contactRepository.findAll();

        List users = new ArrayList<Contact>();
        it.forEach(e -> users.add(e));

        return users;
    }
    public Contact getContactById(int id) {
        Contact obj = contactRepository.findById(id).get();
        return obj;
    }
    public Contact saveContact(Contact contact) {
        contactRepository.save(contact);
        return contact;
    }
    public Contact updateContact(Contact contact,int id) {
        Contact obj = contactRepository.findById(id).get();
        obj.setAddress(contact.getAddress());
        obj.setEmail(contact.getEmail());
        obj.setName(contact.getName());
        obj.setPhone(contact.getPhone());
        contactRepository.save(obj);
        return obj;
    }
    public void deleteById(int id) {

        contactRepository.deleteById(id);
    }
}

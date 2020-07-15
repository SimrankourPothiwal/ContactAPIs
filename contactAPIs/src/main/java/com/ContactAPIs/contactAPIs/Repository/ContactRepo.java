package com.ContactAPIs.contactAPIs.Repository;

import com.ContactAPIs.contactAPIs.Models.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepo extends CrudRepository<Contact,Integer> {
}

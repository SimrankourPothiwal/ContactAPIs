package com.ContactAPIs.contactAPIs.Models;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
import java.util.List;

@Entity
public class Contact {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private Name name;
    private Address address;
    @ElementCollection
    @CollectionTable(name = "phones", joinColumns = @JoinColumn(name = "id"), foreignKey = @ForeignKey(name = "phone_contact_id"))
    private List<Phone> phone;
    private String email;

    public Contact(int id) {
        this.id = id;
    }

    public Contact(int id, Name name, Address address, List<Phone> phone, String email) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Phone> getPhone() {
        return phone;
    }

    public void setPhone(List<Phone> phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Contact() {
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id='" + id + '\'' +
                ", name=" + name +
                ", address=" + address +
                ", phone=" + phone +
                ", email='" + email + '\'' +
                '}';
    }
}

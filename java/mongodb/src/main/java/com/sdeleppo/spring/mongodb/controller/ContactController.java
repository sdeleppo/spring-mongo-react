package com.sdeleppo.spring.mongodb.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sdeleppo.spring.mongodb.model.Contact;
import com.sdeleppo.spring.mongodb.repository.ContactRepository;


@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class ContactController {

  @Autowired
  ContactRepository contactRepository;

  //if called, respond with list of contacts
  @GetMapping("/contacts")
  public ResponseEntity<List<Contact>> getAllContacts(@RequestParam(required = false) String name) {
    try {
    	List<Contact> contacts = new ArrayList<Contact>();
    	//if no name entered, add all contacts to list, otherwise only add contacts with specified name
    	if (name == null)
    		contactRepository.findAll().forEach(contacts::add);
    	else {
    		contactRepository.findByNameContaining(name).forEach(contacts::add);
    	}

    	if (contacts.isEmpty()) {
    		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    	}
      
    	return new ResponseEntity<>(contacts, HttpStatus.OK);
    	} catch (Exception e) {
    		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    	}
  }
  
  //respond with contact with specified ID
  @GetMapping("/contacts/{id}")
  public ResponseEntity<Contact> getContactById(@PathVariable("id") String id) {
    Optional<Contact> contactData = contactRepository.findById(id);

    if (contactData.isPresent()) {
      return new ResponseEntity<>(contactData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  //save new contact to database and respond with new contact
  @PostMapping("/contacts")
  public ResponseEntity<Contact> createContact(@RequestBody Contact contact) {
    try {
      Contact _contact = contactRepository.save(new Contact(contact.getName(), contact.getPhone(), contact.getAddress()));
      return new ResponseEntity<>(_contact, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  //update contact with requested data fields
  @PutMapping("/contacts/{id}")
  public ResponseEntity<Contact> updateContact(@PathVariable("id") String id, @RequestBody Contact contact) {
    Optional<Contact> contactData = contactRepository.findById(id);

    if (contactData.isPresent()) {
      Contact _contact = contactData.get();
      _contact.setName(contact.getName());
      _contact.setPhone(contact.getPhone());
      _contact.setAddress(contact.getAddress());
      return new ResponseEntity<>(contactRepository.save(_contact), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  //delete contact from database by ID, respond with no content
  @DeleteMapping("/contacts/{id}")
  public ResponseEntity<HttpStatus> deleteContact(@PathVariable("id") String id) {
    try {
      contactRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  //delete all contacts from database, respond with no content
  @DeleteMapping("/contacts")
  public ResponseEntity<HttpStatus> deleteAllContacts() {
    try {
      contactRepository.deleteAll();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}

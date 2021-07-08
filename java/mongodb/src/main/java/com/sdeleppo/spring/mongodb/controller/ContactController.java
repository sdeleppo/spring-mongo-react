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

  @GetMapping("/contacts")
  public ResponseEntity<List<Contact>> getAllContacts(@RequestParam(required = false) String name) {
    try {
      List<Contact> contacts = new ArrayList<Contact>();

      if (name == null)
        contactRepository.findAll().forEach(contacts::add);
      else
        contactRepository.findByNameContaining(name).forEach(contacts::add);

      if (contacts.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(contacts, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/contacts/{id}")
  public ResponseEntity<Contact> getContactById(@PathVariable("id") String id) {
    Optional<Contact> contactData = contactRepository.findById(id);

    if (contactData.isPresent()) {
      return new ResponseEntity<>(contactData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/contacts")
  public ResponseEntity<Contact> createContact(@RequestBody Contact contact) {
    try {
      Contact _contact = contactRepository.save(new Contact(contact.getName(), contact.getPhone()));
      return new ResponseEntity<>(_contact, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/contacts/{id}")
  public ResponseEntity<Contact> updateContact(@PathVariable("id") String id, @RequestBody Contact contact) {
    Optional<Contact> contactData = contactRepository.findById(id);

    if (contactData.isPresent()) {
      Contact _contact = contactData.get();
      _contact.setName(contact.getName());
      _contact.setPhone(contact.getPhone());
      return new ResponseEntity<>(contactRepository.save(_contact), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/contacts/{id}")
  public ResponseEntity<HttpStatus> deleteContact(@PathVariable("id") String id) {
    try {
      contactRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

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

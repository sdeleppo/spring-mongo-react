package com.sdeleppo.spring.mongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sdeleppo.spring.mongodb.model.Contact;

//grants access to MongoRepository methods 
public interface ContactRepository extends MongoRepository<Contact, String> {
	//custom finder method by name
	List<Contact> findByNameContaining(String name);
}

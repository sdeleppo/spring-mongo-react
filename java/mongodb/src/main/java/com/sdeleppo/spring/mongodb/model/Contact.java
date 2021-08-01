package com.sdeleppo.spring.mongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "contacts")
public class Contact {
  @Id
  private String id;

  private String name;
  private String phone;
  private String address;

  public Contact() {

  }

  public Contact(String name, String phone, String address) {
    this.name = name;
    this.phone = phone;
    this.address =  address;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getAddress() {
	  return address;
  }

  public void setAddress(String address) {
	  this.address = address;
  }
  
  @Override
  public String toString() {
    return "Contact [id=" + id + ", name=" + name + ", phone=" + phone + ", address=" + address + "]";
  }
}
package com.sdeleppo.spring.mongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "contacts")
public class Contact {
  @Id
  private String id;

  private String name;
  private String phone;

  public Contact() {

  }

  public Contact(String name, String phone) {
    this.name = name;
    this.phone = phone;
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


  @Override
  public String toString() {
    return "Contact [id=" + id + ", name=" + name + ", phone=" + phone + "]";
  }
}
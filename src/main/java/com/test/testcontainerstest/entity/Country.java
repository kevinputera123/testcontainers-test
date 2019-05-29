package com.test.testcontainerstest.entity;

import javax.persistence.*;

@Entity
@Table(name = "country")
public class Country {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;

  protected Country() {}

  public Country(String name) {
    this.name = name;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Country{" +
        "id=" + id +
        ", name='" + name + '\'' +
        '}';
  }
}

package com.komodo.productservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;

    public Product(Long id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public Product() {

    }


    // getters and setters for id, name, and email
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

// toString method for debugging purposes

    public String toString() {
        return "User [id=" + id + ", name=" + name + ", type=" + type + "]";
    }
}

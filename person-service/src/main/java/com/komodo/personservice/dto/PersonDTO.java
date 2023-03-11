package com.komodo.personservice.dto;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class PersonDTO implements Serializable {
    private Long id;
    private String name;
    private int age;
    private Boolean isActive;

    public PersonDTO(String name, int age, Boolean isActive) {
        this.name = name;
        this.age = age;
        this.isActive = isActive;
    }
}

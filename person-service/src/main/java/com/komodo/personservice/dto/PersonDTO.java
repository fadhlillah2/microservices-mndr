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
    private boolean isActive;
}

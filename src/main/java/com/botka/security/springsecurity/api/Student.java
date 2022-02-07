package com.botka.security.springsecurity.api;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student {

    private Integer id;
    private String name;
    private String surname;

}

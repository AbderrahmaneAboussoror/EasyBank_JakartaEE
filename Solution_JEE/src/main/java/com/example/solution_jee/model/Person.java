package com.example.solution_jee.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Person {
    protected String _firstName;
    protected String _lastName;
    protected LocalDate _birthDate;
    protected String _phoneNumber;

}

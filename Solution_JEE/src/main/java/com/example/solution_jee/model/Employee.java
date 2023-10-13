package com.example.solution_jee.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public final class Employee extends Person {
    private String _code;
    private String _email;
    private LocalDate _recruitedAt;

    public Employee(String code, String firstName, String lastName, LocalDate birthDate, String phoneNumber, String email, LocalDate recruitedAt) {
        super(firstName, lastName, birthDate, phoneNumber);
        set_code(code);
        set_email(email);
        set_recruitedAt(recruitedAt);
    }
}

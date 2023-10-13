package com.example.solution_jee.model;

import lombok.*;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public final class Client extends Person{
    private String _code;
    private String _address;

    public Client(String code, String firstName, String lastName, LocalDate birthDate, String phoneNumber, String address) {
        super(firstName, lastName, birthDate, phoneNumber);
        set_code(code);
        set_address(address);
    }
}

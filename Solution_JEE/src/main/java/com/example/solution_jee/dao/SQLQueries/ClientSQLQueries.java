package com.example.solution_jee.dao.SQLQueries;

import lombok.Getter;
import lombok.NoArgsConstructor;
@NoArgsConstructor
public class ClientSQLQueries {
    @Getter
    private static final String _FIND_ALL = "SELECT * FROM client";
    @Getter
    private static final String _FIND_BY_ID = "SELECT * FROM client WHERE code = ?";
    @Getter
    private static final String _INSERT_CLIENT = "INSERT INTO client(code, first_name, last_name, birth_date, phone_number, address) VALUES(?, ?, ?, ?, ?, ?) RETURNING *;";
    @Getter
    private static final String _UPDATE_CLIENT = "UPDATE client SET first_name = ?, last_name = ?, birth_date = ?, phone_number = ?, address = ? WHERE code = ?;";
    @Getter
    private static final String _DELETE_CLIENT = "DELETE FROM client WHERE code = ?;";
}

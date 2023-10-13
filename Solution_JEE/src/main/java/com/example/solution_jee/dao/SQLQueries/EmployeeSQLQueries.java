package com.example.solution_jee.dao.SQLQueries;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class EmployeeSQLQueries {
    @Getter
    private static final String _FIND_ALL = "SELECT * FROM employee";
    @Getter
    private static final String _FIND_BY_ID = "SELECT * FROM employee WHERE code = ?";
    @Getter
    private static final String _INSERT_EMPLOYEE = "INSERT INTO employee(code, first_name, last_name, birth_date, phone_number, email, recruited_at) VALUES(?, ?, ?, ?, ?, ?, ?) RETURNING *;";
    @Getter
    private static final String _UPDATE_EMPLOYEE = "UPDATE employee SET first_name = ?, last_name = ?, birth_date = ?, phone_number = ?, email = ?, recruited_at = ? WHERE code = ? RETURNING *;";
    @Getter
    private static final String _DELETE_EMPLOYEE = "DELETE FROM employee WHERE code = ?;";
}

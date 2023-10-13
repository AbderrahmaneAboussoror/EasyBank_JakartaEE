package com.example.solution_jee.dao.script.employee;

import com.example.solution_jee.model.Employee;
import com.example.solution_jee.model.Person;
import com.example.solution_jee.util.db.DatabaseConnection;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Named;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static com.example.solution_jee.dao.SQLQueries.EmployeeSQLQueries.*;

@Named
@ApplicationScoped
public class EmployeeDAO implements IEmployeeDAO{
    @Override
    public List<Person> findAll() {
        List<Person> employees = new ArrayList<>();
        try(
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(get_FIND_ALL());
                ResultSet resultSet = preparedStatement.executeQuery();
        ) {

            while (resultSet.next()) {
                Person employee = employeeInfo(resultSet);
                employees.add(employee);
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }

        return employees;
    }

    @Override
    public Optional<Person> findById(String s) {
        try(
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(get_FIND_BY_ID());
                ResultSet resultSet = preparedStatement.executeQuery()
        ) {

            if (resultSet.next()) {
                Person employee = employeeInfo(resultSet);
                return Optional.of(employee);
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Person> save(Person entity) {
        try(
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(get_INSERT_EMPLOYEE());
        ) {

            if (entity instanceof Employee e) {
                ResultSet resultSet = employeePreparing(preparedStatement, e);
                if (resultSet.next()) {
                    Person employee = employeeInfo(resultSet);
                    return Optional.of(employee);
                }
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Person> update(Person entity) {
        try(
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(get_UPDATE_EMPLOYEE());
        ) {

            if (entity instanceof Employee e) {
                ResultSet resultSet = employeeUpdatePreparing(preparedStatement, e);
                if (resultSet.next()) {
                    Person employee = employeeInfo(resultSet);
                    return Optional.of(employee);
                }
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public boolean delete(String s) {
        try(
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(get_DELETE_EMPLOYEE());
        ) {

            preparedStatement.setString(1, s);
            int employeeDeleted = preparedStatement.executeUpdate();
            return employeeDeleted > 0;

        }catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    private Employee employeeInfo(ResultSet resultSet) throws SQLException {
        String code = resultSet.getString("code");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        LocalDate birthDate = resultSet.getDate("birth_date").toLocalDate();
        String phoneNumber = resultSet.getString("phone_number");
        String email = resultSet.getString("email");
        LocalDate recruitedAt = resultSet.getDate("recruited_at").toLocalDate();
        return new Employee(code, firstName, lastName, birthDate, phoneNumber, email, recruitedAt);
    }
    private ResultSet employeePreparing(PreparedStatement preparedStatement, Employee e) throws SQLException {
        preparedStatement.setString(1, e.get_code());
        preparedStatement.setString(2, e.get_firstName());
        preparedStatement.setString(3, e.get_lastName());
        preparedStatement.setDate(4, Date.valueOf(e.get_birthDate()));
        preparedStatement.setString(5, e.get_phoneNumber());
        preparedStatement.setString(6, e.get_email());
        preparedStatement.setDate(7, Date.valueOf(e.get_recruitedAt()));
        return preparedStatement.executeQuery();
    }
    private ResultSet employeeUpdatePreparing(PreparedStatement preparedStatement, Employee e) throws SQLException {
        preparedStatement.setString(1, e.get_firstName());
        preparedStatement.setString(2, e.get_lastName());
        preparedStatement.setDate(3, java.sql.Date.valueOf(e.get_birthDate()));
        preparedStatement.setString(4, e.get_phoneNumber());
        preparedStatement.setString(5, e.get_email());
        preparedStatement.setDate(6, java.sql.Date.valueOf(e.get_recruitedAt()));
        preparedStatement.setString(7, e.get_code());
        return preparedStatement.executeQuery();
    }
}

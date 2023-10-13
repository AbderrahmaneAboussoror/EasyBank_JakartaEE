package com.example.solution_jee.dao.script.client;

import com.example.solution_jee.dao.SQLQueries.ClientSQLQueries;
import com.example.solution_jee.model.Client;
import com.example.solution_jee.model.Person;
import com.example.solution_jee.util.db.DatabaseConnection;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Named;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static com.example.solution_jee.dao.SQLQueries.ClientSQLQueries.*;

@Named
@ApplicationScoped
public class ClientDAO implements IClientDAO{
    @Override
    public List<Person> findAll() {
        List<Person> clients = new ArrayList<>();
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(get_FIND_ALL());
            ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Person client = clientInfo(resultSet);
                clients.add(client);
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }

    @Override
    public Optional<Person> findById(String s) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(get_FIND_BY_ID());)
        {
            preparedStatement.setString(1, s);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Person client = clientInfo(resultSet);
                return Optional.of(client);
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
                PreparedStatement preparedStatement = connection.prepareStatement(get_INSERT_CLIENT());
                ) {
            if (entity instanceof Client c) {
                ResultSet resultSet = clientPreparing(preparedStatement, c);
                if (resultSet.next()) {
                    Person client = clientInfo(resultSet);
                    return Optional.of(client);
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
                PreparedStatement preparedStatement = connection.prepareStatement(get_UPDATE_CLIENT());
        ) {

            if (entity instanceof Client c) {
                ResultSet resultSet = clientUpdatePreparing(preparedStatement, c);
                if (resultSet.next()) {
                    Person client = clientInfo(resultSet);
                    return Optional.of(client);
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
                PreparedStatement preparedStatement = connection.prepareStatement(get_DELETE_CLIENT());
        ) {

            preparedStatement.setString(1, s);
            int clientDeleted = preparedStatement.executeUpdate();
            return clientDeleted > 0;

        }catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    private Client clientInfo(ResultSet resultSet) throws SQLException {
        String code = resultSet.getString("code");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        LocalDate birthDate = resultSet.getDate("birth_date").toLocalDate();
        String phoneNumber = resultSet.getString("phone_number");
        String address = resultSet.getString("address");
        return new Client(code, firstName, lastName, birthDate, phoneNumber, address);
    }
    private ResultSet clientPreparing(PreparedStatement preparedStatement, Client c) throws SQLException {
        preparedStatement.setString(1, c.get_code());
        preparedStatement.setString(2, c.get_firstName());
        preparedStatement.setString(3, c.get_lastName());
        preparedStatement.setDate(4, java.sql.Date.valueOf(c.get_birthDate()));
        preparedStatement.setString(5,c.get_phoneNumber());
        preparedStatement.setString(6, c.get_address());
        return preparedStatement.executeQuery();
    }
    private ResultSet clientUpdatePreparing(PreparedStatement preparedStatement, Client c) throws SQLException {
        preparedStatement.setString(1, c.get_firstName());
        preparedStatement.setString(2, c.get_lastName());
        preparedStatement.setDate(3, java.sql.Date.valueOf(c.get_birthDate()));
        preparedStatement.setString(4, c.get_phoneNumber());
        preparedStatement.setString(5, c.get_address());
        preparedStatement.setString(6, c.get_code());
        return preparedStatement.executeQuery();
    }
}

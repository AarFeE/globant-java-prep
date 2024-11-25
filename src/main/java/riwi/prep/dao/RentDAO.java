package riwi.prep.dao;

import riwi.prep.enums.RentState;
import riwi.prep.model.RentEntity;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RentDAO {

    public void getRentById(Connection connection, int id) throws SQLException {
        String sql = "SELECT * FROM Rents WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                System.out.println("Client ID: " + resultSet.getInt("id_client"));
                System.out.println("Machine ID: " + resultSet.getInt("id_machine"));
                System.out.println("State: " + resultSet.getString("state"));
                System.out.println("Beginning Date: " + resultSet.getDate("beginning_date"));
                System.out.println("Ending Date: " + resultSet.getDate("ending_date"));
            }
        };
    }

    public List<RentEntity> getAllRentals(Connection connection) throws SQLException {
        String sql = "SELECT * FROM Rents";
        List<RentEntity> rents = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                RentEntity rent = new RentEntity();
                rent.setId(resultSet.getInt("id"));
                rent.setClientId(resultSet.getInt("id_client"));
                rent.setMachineId(resultSet.getInt("id_machine"));
                rent.setState(RentState.valueOf(resultSet.getString("state")));
                rent.setBeginningDate(LocalDate.parse(resultSet.getDate("beginning_date").toString()));
                rent.setEndingDate(LocalDate.parse(resultSet.getDate("ending_date").toString()));
                rents.add(rent);
            }
        };
        return rents;
    }

    public List<RentEntity> getActiveRentals(Connection connection) throws SQLException {
        String sql = "SELECT * FROM Rents WHERE state = 'ACTIVE'";
        List<RentEntity> rents = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                RentEntity rent = new RentEntity();
                rent.setId(resultSet.getInt("id"));
                rent.setClientId(resultSet.getInt("id_client"));
                rent.setMachineId(resultSet.getInt("id_machine"));
                rent.setState(RentState.valueOf(resultSet.getString("state")));
                rent.setBeginningDate(LocalDate.parse(resultSet.getDate("beginning_date").toString()));
                rent.setEndingDate(LocalDate.parse(resultSet.getDate("ending_date").toString()));
                rents.add(rent);
            }
        };
        return rents;
    }

    public void createRent (Connection connection, RentEntity rent) throws SQLException {
        String sql = "INSERT INTO Rents (id_client, id_machine, state, beginning_date, ending_date) VALUES (?, ?, ?::rent_state, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, rent.getClientId());
            statement.setInt(2, rent.getMachineId());
            statement.setString(3, RentState.ACTIVE.name());
            statement.setDate(4, new java.sql.Date(Date.valueOf(rent.getBeginningDate()).getTime()));
            statement.setDate(5, new java.sql.Date(Date.valueOf(rent.getEndingDate()).getTime()));

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Rent successfully added!");
            }
        };
    }

    public void updateRent(Connection connection, RentEntity rent) throws SQLException {
        String sql = "UPDATE Rent SET state = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, rent.getState().toString());
            statement.setInt(2, rent.getId());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Rent successfully updated!");
            }
        };
    }

    public void disableRent(Connection connection, int id) throws SQLException {
        String sql = "UPDATE Rents SET state = 'INOPERATIVE' WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Rent successfully updated!");
            }
        };
    }
}

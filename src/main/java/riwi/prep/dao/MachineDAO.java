package riwi.prep.dao;

import riwi.prep.enums.MachineState;
import riwi.prep.model.MachineEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MachineDAO {

    public List<MachineEntity> getAllMachines(Connection connection) throws SQLException {
        String sql = "SELECT * FROM Machines";
        List<MachineEntity> machines = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                MachineEntity machine = new MachineEntity(
                        resultSet.getInt("id"),
                        resultSet.getString("model"),
                        resultSet.getString("series_number"),
                        MachineState.valueOf(resultSet.getString("state"))
                );
                machines.add(machine);
            }
        };
        return machines;
    }

    public MachineEntity getMachineById(Connection connection, int id) throws SQLException {
        String sql = "SELECT * FROM Machines WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new MachineEntity(
                        resultSet.getInt("id"),
                        resultSet.getString("model"),
                        resultSet.getString("series_number"),
                        MachineState.valueOf(resultSet.getString("state"))
                );
            }
        };
        return null;
    }

    public MachineEntity getMachineBySerialNumber(Connection connection, String serialNumber) throws SQLException {
        System.out.println(serialNumber);
        String sql = "SELECT * FROM Machines WHERE series_number = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, serialNumber);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new MachineEntity(
                        resultSet.getInt("id"),
                        resultSet.getString("model"),
                        resultSet.getString("series_number"),
                        MachineState.valueOf(resultSet.getString("state"))
                );
            }
        };
        return null;
    }

    public void createMachine(Connection connection, MachineEntity machine) throws SQLException {
        String sql = "INSERT INTO Machines (model, series_number, state) VALUES (?, ?, ?::machine_state)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, machine.getModel());
            statement.setString(2, machine.getSeriesNumber());
            statement.setString(3, machine.getState().name());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Machine successfully added!");
            }
        }
    }

    public void updateMachine(Connection connection, MachineEntity machine) throws SQLException {
        String sql = "UPDATE Machines SET model = ?, series_number = ?, state = ?::machine_state WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, machine.getModel());
            statement.setString(2, machine.getSeriesNumber());
            statement.setString(3, machine.getState().name());
            statement.setInt(4, machine.getId());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Machine successfully updated!");
            }
        };
    }

    public void deleteMachine(Connection connection, int machineId) throws SQLException {
        String sql = "DELETE FROM Machines WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, machineId);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Machine successfully deleted!");
            }
        }
    }
}

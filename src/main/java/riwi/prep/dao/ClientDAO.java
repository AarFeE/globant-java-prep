package riwi.prep.dao;

import riwi.prep.model.ClientEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {

    public List<ClientEntity> getAllClients(Connection connection) throws SQLException {
        String sql = "SELECT * FROM Clients";  // Query to select all clients
        List<ClientEntity> clients = new ArrayList<>();  // List to store the retrieved clients

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();  // Execute the query

            while (resultSet.next()) {
                // For each row in the result set, create a new ClientEntity object
                ClientEntity client = new ClientEntity(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getInt("phone"),
                        resultSet.getString("address")
                );
                // Add the client to the list
                clients.add(client);
            }
        }

        return clients;  // Return the list of all clients
    }

    // Retrieve client by ID
    public ClientEntity getClientById(Connection connection, int clientId) throws SQLException {
        String sql = "SELECT * FROM Clients WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, clientId);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new ClientEntity(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getInt("phone"),
                        resultSet.getString("address")
                );
            }
        }
        return null;
    }

    // Update client details
    public void updateClient(Connection connection, ClientEntity client) throws SQLException {
        String sql = "UPDATE Clients SET name = ?, phone = ?, email = ?, address = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, client.getName());
            statement.setInt(2, client.getPhone());
            statement.setString(3, client.getEmail());
            statement.setString(4, client.getAddress());
            statement.setInt(5, client.getId());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Client successfully updated!");
            }
        }
    }

    // Create a new client
    public void createClient(Connection connection, ClientEntity client) throws SQLException {
        String sql = "INSERT INTO Clients (name, phone, email, address) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, client.getName());
            statement.setInt(2, client.getPhone());
            statement.setString(3, client.getEmail());
            statement.setString(4, client.getAddress());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                // Get the generated ID of the new client
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        client.setId(generatedKeys.getInt(1)); // Set the generated ID to the client entity
                    }
                }
                System.out.println("Client successfully added with ID: " + client.getId());
            }
        }
    }

    // Delete a client by ID
    public void deleteClient(Connection connection, int clientId) throws SQLException {
        String sql = "DELETE FROM Clients WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, clientId);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Client successfully deleted!");
            }
        }
    }
}

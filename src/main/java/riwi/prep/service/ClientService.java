package riwi.prep.service;

import riwi.prep.dao.ClientDAO;
import riwi.prep.model.ClientEntity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ClientService {
    private final ClientDAO clientDAO;

    public ClientService() {
        this.clientDAO = new ClientDAO();
    }

    // Create a new client
    public void createClient(Connection connection, ClientEntity client) throws SQLException {
        clientDAO.createClient(connection, client);
    }

    // Retrieve a client by ID
    public ClientEntity getClientById(Connection connection, int clientId) throws SQLException {
        return clientDAO.getClientById(connection, clientId);
    }

    // Retrieve all clients
    public List<ClientEntity> getAllClients(Connection connection) throws SQLException {
        return clientDAO.getAllClients(connection);
    }

    // Update a client
    public void updateClient(Connection connection, ClientEntity client) throws SQLException {
        clientDAO.updateClient(connection, client);
    }

    // Delete a client by ID
    public void deleteClient(Connection connection, int clientId) throws SQLException {
        clientDAO.deleteClient(connection, clientId);
    }
}

package riwi.prep.menu;

import riwi.prep.model.ClientEntity;
import riwi.prep.service.ClientService;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ClientMenu {
    private final ClientService clientService;

    public ClientMenu() {
        this.clientService = new ClientService();
    }

    public void showMenu(Connection connection) throws SQLException {
        while (true) {
            String[] options = {"Create Client", "View All Clients", "Update Client", "Delete Client", "Exit"};
            int choice = JOptionPane.showOptionDialog(null, "Select an option:", "Client Menu",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            switch (choice) {
                case 0:
                    createClient(connection);
                    break;
                case 1:
                    viewAllClients(connection);
                    break;
                case 2:
                    updateClient(connection);
                    break;
                case 3:
                    deleteClient(connection);
                    break;
                case 4:
                    return; // Exit
                default:
                    break;
            }
        }
    }

    private void createClient(Connection connection) throws SQLException {
        String name = JOptionPane.showInputDialog("Enter Client Name:");
        String phoneStr = JOptionPane.showInputDialog("Enter Client Phone:");
        String email = JOptionPane.showInputDialog("Enter Client Email:");
        String address = JOptionPane.showInputDialog("Enter Client Address:");

        try {
            int phone = Integer.parseInt(phoneStr);
            ClientEntity client = new ClientEntity(name, email, phone, address);
            clientService.createClient(connection, client);
            JOptionPane.showMessageDialog(null, "Client added successfully with ID: " + client.getId());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid phone number entered!");
        }
    }

    private void viewAllClients(Connection connection) throws SQLException {
        List<ClientEntity> clients = clientService.getAllClients(connection);
        StringBuilder clientList = new StringBuilder("Client List:\n");
        for (ClientEntity client : clients) {
            clientList.append(client.getName()).append(" - ").append(client.getEmail()).append("\n");
        }
        JOptionPane.showMessageDialog(null, clientList.toString());
    }

    private void updateClient(Connection connection) throws SQLException {
        String clientIdStr = JOptionPane.showInputDialog("Enter client ID to update:");
        try {
            int clientId = Integer.parseInt(clientIdStr);
            ClientEntity client = clientService.getClientById(connection, clientId);
            if (client == null) {
                JOptionPane.showMessageDialog(null, "Client not found!");
                return;
            }

            String name = JOptionPane.showInputDialog("Enter New Name (leave blank to keep):", client.getName());
            String phoneStr = JOptionPane.showInputDialog("Enter New Phone (leave blank to keep):", client.getPhone());
            String email = JOptionPane.showInputDialog("Enter New Email (leave blank to keep):", client.getEmail());
            String address = JOptionPane.showInputDialog("Enter New Address (leave blank to keep):", client.getAddress());

            if (!name.isEmpty()) client.setName(name);
            if (!phoneStr.isEmpty()) client.setPhone(Integer.parseInt(phoneStr));
            if (!email.isEmpty()) client.setEmail(email);
            if (!address.isEmpty()) client.setAddress(address);

            clientService.updateClient(connection, client);
            JOptionPane.showMessageDialog(null, "Client updated successfully!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid phone number format!");
        }
    }

    private void deleteClient(Connection connection) throws SQLException {
        String clientIdStr = JOptionPane.showInputDialog("Enter client ID to delete:");
        try {
            int clientId = Integer.parseInt(clientIdStr);
            clientService.deleteClient(connection, clientId);
            JOptionPane.showMessageDialog(null, "Client deleted successfully!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid client ID format!");
        }
    }
}

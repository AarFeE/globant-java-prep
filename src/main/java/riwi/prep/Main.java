package riwi.prep;

import riwi.prep.dao.ClientDAO;
import riwi.prep.menu.ClientMenu;
import riwi.prep.menu.MachineMenu;
import riwi.prep.menu.RentMenu;
import riwi.prep.model.ClientEntity;
import riwi.prep.utils.DBConnection;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try (Connection connection = DBConnection.getConnection()) {
            while (true) {
                // Main menu options for the user to navigate through
                String[] options = {"Client Menu", "Machine Menu", "Rent Menu", "Exit"};
                int choice = JOptionPane.showOptionDialog(null, "Select an option:", "Main Menu",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

                switch (choice) {
                    case 0:
                        // Navigate to Client Menu
                        ClientMenu clientMenu = new ClientMenu();
                        clientMenu.showMenu(connection);
                        break;
                    case 1:
                        // Navigate to Machine Menu
                        MachineMenu machineMenu = new MachineMenu();
                        machineMenu.showMenu(connection);
                        break;
                    case 2:
                        // Navigate to Rent Menu
                        RentMenu rentMenu = new RentMenu();
                        rentMenu.showMenu(connection);
                        break;
                    case 3:
                        // Exit the application
                        JOptionPane.showMessageDialog(null, "Goodbye!");
                        return; // Exit the program
                    default:
                        break;
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error connecting to the database: " + e.getMessage());
        }
    }
}

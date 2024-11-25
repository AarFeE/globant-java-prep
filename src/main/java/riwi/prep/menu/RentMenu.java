package riwi.prep.menu;

import riwi.prep.enums.RentState;
import riwi.prep.model.RentEntity;
import riwi.prep.service.RentService;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

public class RentMenu {

    private final RentService rentService;

    public RentMenu() {
        this.rentService = new RentService();
    }

    public void showMenu(Connection connection) throws SQLException {
        while (true) {
            String[] options = {"Create Rent", "View All Rentals", "Deactivate Rent", "Exit"};
            int choice = JOptionPane.showOptionDialog(null, "Select an option:", "Rent Menu",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            switch (choice) {
                case 0:
                    createRent(connection);
                    break;
                case 1:
                    viewAllRentals(connection);
                    break;
                case 2:
                    deactivateRent(connection);
                    break;
                case 3:
                    return; // Exit
                default:
                    break;
            }
        }
    }

    private void createRent(Connection connection) throws SQLException {
        String clientIdStr = JOptionPane.showInputDialog("Enter Client ID:");
        String machineSerial = JOptionPane.showInputDialog("Enter Machine Serial Number:");
        String startDate = JOptionPane.showInputDialog("Enter Rent Start Date (YYYY-MM-DD):");
        String endDate = JOptionPane.showInputDialog("Enter Rent End Date (YYYY-MM-DD):");

        RentEntity rent = new RentEntity(LocalDate.parse(startDate), LocalDate.parse(endDate), RentState.ACTIVE, Integer.parseInt(machineSerial), Integer.parseInt(clientIdStr));
        rentService.createRent(connection, rent);
        JOptionPane.showMessageDialog(null, "Rent created successfully!");
    }

    private void viewAllRentals(Connection connection) throws SQLException {
        List<RentEntity> rentals = rentService.getAllRentals(connection);
        StringBuilder rentList = new StringBuilder("Rentals:\n");
        for (RentEntity rent : rentals) {
            rentList.append(rent.getClientId()).append(" - ").append(rent.getMachineId()).append("\n");
        }
        JOptionPane.showMessageDialog(null, rentList.toString());
    }

    private void deactivateRent(Connection connection) throws SQLException {
        String rentIdStr = JOptionPane.showInputDialog("Enter Rent ID to deactivate:");
        rentService.deactivateRent(connection, Integer.parseInt(rentIdStr));
        JOptionPane.showMessageDialog(null, "Rent deactivated successfully!");
    }
}


package riwi.prep.menu;

import riwi.prep.enums.MachineState;
import riwi.prep.model.MachineEntity;
import riwi.prep.service.MachineService;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class MachineMenu {

    private final MachineService machineService;

    public MachineMenu() {
        this.machineService = new MachineService();
    }

    public void showMenu(Connection connection) throws SQLException {
        while (true) {
            String[] options = {"Create Machine", "View All Machines", "Update Machine", "Delete Machine", "Exit"};
            int choice = JOptionPane.showOptionDialog(null, "Select an option:", "Machine Menu",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            switch (choice) {
                case 0:
                    createMachine(connection);
                    break;
                case 1:
                    viewAllMachines(connection);
                    break;
                case 2:
                    updateMachine(connection);
                    break;
                case 3:
                    deleteMachine(connection);
                    break;
                case 4:
                    return; // Exit
                default:
                    break;
            }
        }
    }

    private void createMachine(Connection connection) throws SQLException {
        String model = JOptionPane.showInputDialog("Enter Machine Model:");
        String serialNumber = JOptionPane.showInputDialog("Enter Machine Serial Number:");
        String state = JOptionPane.showInputDialog("Enter Machine State (AVAILABLE/RENTED):");

        MachineEntity machine = new MachineEntity(model, serialNumber, MachineState.valueOf(state));
        machineService.createMachine(connection, machine);
        JOptionPane.showMessageDialog(null, "Machine added successfully with Serial Number: " + machine.getSeriesNumber());
    }

    private void viewAllMachines(Connection connection) throws SQLException {
        List<MachineEntity> machines = machineService.getAllMachines(connection);
        StringBuilder machineList = new StringBuilder("Machine List:\n");
        for (MachineEntity machine : machines) {
            machineList.append(machine.getModel()).append(" - ").append(machine.getSeriesNumber()).append("\n");
        }
        JOptionPane.showMessageDialog(null, machineList.toString());
    }

    private void updateMachine(Connection connection) throws SQLException {
        String serialNumber = JOptionPane.showInputDialog("Enter Serial Number of Machine to update:");
        MachineEntity machine = machineService.getMachineBySerialNumber(connection, serialNumber);
        if (machine == null) {
            JOptionPane.showMessageDialog(null, "Machine not found!");
            return;
        }

        String model = JOptionPane.showInputDialog("Enter New Model (leave blank to keep):", machine.getModel());
        String state = JOptionPane.showInputDialog("Enter New State (AVAILABLE/RENTED):", machine.getState());

        if (!model.isEmpty()) machine.setModel(model);
        if (!state.isEmpty()) machine.setState(MachineState.valueOf(state));

        machineService.updateMachine(connection, machine);
        JOptionPane.showMessageDialog(null, "Machine updated successfully!");
    }

    private void deleteMachine(Connection connection) throws SQLException {
        try {
            String serialNumber = JOptionPane.showInputDialog("Enter Serial Number of Machine to delete:");
            int theSerialNumber = Integer.parseInt(serialNumber);
            machineService.deleteMachine(connection, theSerialNumber);
            JOptionPane.showMessageDialog(null, "Machine deleted successfully!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid serial number format!");
        }
    }
}



package riwi.prep.service;

import riwi.prep.dao.MachineDAO;
import riwi.prep.model.MachineEntity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class MachineService {
    private final MachineDAO machineDAO;

    public MachineService() {
        this.machineDAO = new MachineDAO();
    }

    // Create a new machine
    public void createMachine(Connection connection, MachineEntity machine) throws SQLException {
        machineDAO.createMachine(connection, machine);
    }

    // Retrieve all machines
    public List<MachineEntity> getAllMachines(Connection connection) throws SQLException {
        return machineDAO.getAllMachines(connection);
    }

    // Retrieve machine by ID
    public MachineEntity getMachineById(Connection connection, int machineId) throws SQLException {
        return machineDAO.getMachineById(connection, machineId);
    }

    // Retrieve machine by serial number
    public MachineEntity getMachineBySerialNumber(Connection connection, String serialNumber) throws SQLException {
        return machineDAO.getMachineBySerialNumber(connection, serialNumber);
    }

    // Update a machine
    public void updateMachine(Connection connection, MachineEntity machine) throws SQLException {
        machineDAO.updateMachine(connection, machine);
    }

    // Delete a machine by ID
    public void deleteMachine(Connection connection, int machineId) throws SQLException {
        machineDAO.deleteMachine(connection, machineId);
    }
}

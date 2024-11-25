package riwi.prep.service;

import riwi.prep.dao.RentDAO;
import riwi.prep.model.MachineEntity;
import riwi.prep.model.RentEntity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class RentService {
    private final RentDAO rentDAO;

    public RentService() {
        this.rentDAO = new RentDAO();
    }

    MachineService machineService = new MachineService();

    // Create a new rent
    public void createRent(Connection connection, RentEntity rent) throws SQLException {
        try {
            MachineEntity machine = machineService.getMachineBySerialNumber(connection, String.valueOf(rent.getMachineId()));
            if (machine == null) {
                throw new SQLException("Machine not found there");
            }
            rent.setMachineId(machine.getId());
            machineService.updateMachine(connection, machine);
        } catch (SQLException e) {
            throw new SQLException("Machine not found here");
        }
        rentDAO.createRent(connection, rent);
    }

    // Retrieve all active rentals
    public List<RentEntity> getActiveRentals(Connection connection) throws SQLException {
        return rentDAO.getActiveRentals(connection);
    }

    // Retrieve all rentals (active and soft deleted)
    public List<RentEntity> getAllRentals(Connection connection) throws SQLException {
        return rentDAO.getAllRentals(connection);
    }

    // Soft delete a rental
    public void deactivateRent(Connection connection, int rentId) throws SQLException {
        rentDAO.disableRent(connection, rentId);
    }
}

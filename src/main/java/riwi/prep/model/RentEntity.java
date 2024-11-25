package riwi.prep.model;

import riwi.prep.enums.RentState;

import java.sql.Date;
import java.time.LocalDate;

public class RentEntity {
    private int id;
    private LocalDate beginningDate;
    private LocalDate endingDate;
    private RentState state;
    private int machineId;
    private int clientId;

    public RentEntity() {
    }

    public RentEntity(LocalDate beginningDate, LocalDate endingDate, RentState state, int machineId, int clientId) {
        this.beginningDate = beginningDate;
        this.endingDate = endingDate;
        this.state = state;
        this.machineId = machineId;
        this.clientId = clientId;
    }

    public RentEntity(int id, LocalDate beginningDate, LocalDate endingDate, RentState state, int machineId, int clientId) {
        this.id = id;
        this.beginningDate = beginningDate;
        this.endingDate = endingDate;
        this.state = state;
        this.machineId = machineId;
        this.clientId = clientId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getBeginningDate() {
        return beginningDate;
    }

    public void setBeginningDate(LocalDate beginningDate) {
        this.beginningDate = beginningDate;
    }

    public LocalDate getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(LocalDate endingDate) {
        this.endingDate = endingDate;
    }

    public RentState getState() {
        return state;
    }

    public void setState(RentState state) {
        this.state = state;
    }

    public int getMachineId() {
        return machineId;
    }

    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }
}

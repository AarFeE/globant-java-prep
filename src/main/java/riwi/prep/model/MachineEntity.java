package riwi.prep.model;

import riwi.prep.enums.MachineState;

public class MachineEntity {
    private int id;
    private String model;
    private String series_number;
    private MachineState state;

    public MachineEntity() {
    }

    public MachineEntity(String model, String series_number, MachineState state) {
        this.model = model;
        this.series_number = series_number;
        this.state = state;
    }

    public MachineEntity(int id, String model, String series_number, MachineState state) {
        this.id = id;
        this.model = model;
        this.series_number = series_number;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSeriesNumber() {
        return series_number;
    }

    public void setSeriesNumber(String series_number) {
        this.series_number = series_number;
    }

    public MachineState getState() {
        return state;
    }

    public void setState(MachineState state) {
        this.state = state;
    }
}

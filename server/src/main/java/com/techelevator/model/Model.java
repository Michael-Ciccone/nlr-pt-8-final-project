package com.techelevator.model;

import jakarta.validation.constraints.NotBlank;

public class Model {

    private int id;

    @NotBlank(message="The model name field must not be blank.")
    private String modelName;
    @NotBlank(message="The manufacturer name field must not be blank.")
    private String manufacturerName;
    @NotBlank(message="The maintenance schedule field must not be blank.")
    private String maintenanceSchedule;
    private String modelDescription;

    public Model() {

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public void setMaintenanceSchedule(String maintenanceSchedule) {
        this.maintenanceSchedule = maintenanceSchedule;
    }

    public Model(int id, String modelName, String manufacturerName, String maintenanceSchedule, String modelDescription) {
        this.id = id;
        this.modelName = modelName;
        this.manufacturerName = manufacturerName;
        this.maintenanceSchedule = maintenanceSchedule;
        this.modelDescription = modelDescription;
    }
    public String getModelDescription() {
        return modelDescription;
    }

    public void setModelDescription(String modelDescription) {
        this.modelDescription = modelDescription;
    }

    public int getId() {
        return id;
    }

    public String getModelName() {
        return modelName;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public String getMaintenanceSchedule() {
        return maintenanceSchedule;
    }

    @Override
    public String toString() {
        return "model{" +
                "id=" + id +
                ", modelName='" + modelName + '\'' +
                ", manufacturerName='" + manufacturerName + '\'' +
                ", maintenanceSchedule='" + maintenanceSchedule + '\'' +
                ", modelDescription='" + modelDescription + '\'' +
                '}';
    }
}

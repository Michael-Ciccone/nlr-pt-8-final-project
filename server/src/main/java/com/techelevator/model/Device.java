package com.techelevator.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;
public class Device {

    private int id;
    @NotBlank(message="The serial number field must not be blank.")
    private String serialNumber;


    @NotNull(message="The model ID field must not be blank.")
    private int modelId;
    @NotNull(message="The owning department field must not be blank.")
    private int owningDepartment;
    @Past(message="The install date field must not be in the future.")
    private LocalDate installDate;
    private String picture;

    public Device() {

    }

    public Device(int id, String serialNumber, int modelId, int owningDepartment, LocalDate installDate, String picture) {
        this.id = id;
        this.serialNumber = serialNumber;
        this.modelId = modelId;
        this.owningDepartment = owningDepartment;
        this.installDate = installDate;
        this.picture = picture;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    public void setOwningDepartment(int owningDepartment) {
        this.owningDepartment = owningDepartment;
    }

    public void setInstallDate(LocalDate installDate) {
        this.installDate = installDate;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public int getModelId() {
        return modelId;
    }

    public int getOwningDepartment() {
        return owningDepartment;
    }

    public LocalDate getInstallDate() {
        return installDate;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", serialNumber='" + serialNumber + '\'' +
                ", modelId=" + modelId +
                ", owningDepartment=" + owningDepartment +
                ", installDate=" + installDate +
                ", picture=" + picture +
                '}';
    }
}

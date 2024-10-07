package com.techelevator.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Department {

    private int id;
    @NotBlank(message="The department name field must not be blank.")
    private String departmentName;
    @NotNull(message="The maintenance month field must not be null.")
    private int maintenanceMonth;
    @NotNull(message="The assigned technician field must not be null.")
    private int assignedTechnician;

    public void setId(int id) {
        this.id = id;
    }


    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public void setMaintenanceMonth(int maintenanceMonth) {
        this.maintenanceMonth = maintenanceMonth;
    }

    public void setAssignedTechnician(int assignedTechnician) {
        this.assignedTechnician = assignedTechnician;
    }

    public Department() {

    }

    public Department(int id, String departmentName, int maintenanceMonth, int assignedTechnician) {
        this.id = id;
        this.departmentName = departmentName;
        this.maintenanceMonth = maintenanceMonth;
        this.assignedTechnician = assignedTechnician;
    }

    public int getId() {
        return id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public int getMaintenanceMonth() {
        return maintenanceMonth;
    }

    public int getAssignedTechnician() {
        return assignedTechnician;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", departmentName='" + departmentName + '\'' +
                ", maintenanceMonth=" + maintenanceMonth +
                ", assignedTechnician=" + assignedTechnician +
                '}';
    }
}

package com.techelevator.dao;

import com.techelevator.model.Device;

import java.time.LocalDate;
import java.util.List;

public interface DeviceDao {

    List<Device> getAllDevices();


    Device getDeviceById(int id);

    List<Device> getDevicesByOwningDepartment(int departmentId);

    List <Device> getDevicesByManufacturer(String mfgName);
    List<Device> getDevicesInstalledInDateRange (LocalDate startDate, LocalDate endDate);

    Device createDevice(Device device);

    Device updateDevice(Device device);

    int deleteDeviceById(int id);
}

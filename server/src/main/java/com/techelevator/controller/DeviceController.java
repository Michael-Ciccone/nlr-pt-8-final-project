package com.techelevator.controller;


import com.techelevator.dao.DeviceDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Device;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/devices")
public class DeviceController {

    private final DeviceDao deviceDao;

    public DeviceController(DeviceDao deviceDao) {
        this.deviceDao = deviceDao;
    }

    @GetMapping
    public List<Device> getAllDevices() {
        return deviceDao.getAllDevices();
    }

    @GetMapping(path = "/{id}")
    public Device getDeviceById(@PathVariable int id) {
        return deviceDao.getDeviceById(id);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(path = "/search")
    public List <Device> getDevicesByManufacturer(@RequestParam String mfgName) {
        return deviceDao.getDevicesByManufacturer(mfgName);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(path = "/dateSearch")
    public List <Device> getDevicesByInstallDateRange(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        return deviceDao.getDevicesInstalledInDateRange(startDate, endDate);
    }

    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Device createDevice(@Valid @RequestBody Device device) {
        return deviceDao.createDevice(device);
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping(path = "/{id}")
    public Device updateDevice(@Valid @RequestBody Device device, @PathVariable int id) {

        device.setId(id);
        try {
            return deviceDao.updateDevice(device);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Device Not Found");
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/{id}")
    @PreAuthorize("isAuthenticated()")
    public void deleteDevice(@PathVariable int id) {
        deviceDao.deleteDeviceById(id);
    }
}

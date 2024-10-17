package com.techelevator.controller;

import com.techelevator.dao.DepartmentDao;
import com.techelevator.dao.DeviceDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Department;
import com.techelevator.model.Device;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentDao departmentDao;
    private final DeviceDao deviceDao;

    public DepartmentController(DepartmentDao departmentDao, DeviceDao deviceDao) {
        this.departmentDao = departmentDao;
        this.deviceDao = deviceDao;
    }

    @GetMapping
    public List<Department> getAllDepartments() {
        return departmentDao.getAllDepartments();
    }

    @GetMapping(path = "/{departmentId}/devices")
    public List<Device> getDevicesByOwningDepartment(@PathVariable int departmentId) {
        return deviceDao.getDevicesByOwningDepartment(departmentId);
    }

    @GetMapping(path = "/{id}")
    public Department getDepartmentById(@PathVariable int id) {
        return departmentDao.getDepartmentById(id);
    }


    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Department createDepartment(@Valid @RequestBody Department department) {
        return departmentDao.createDepartment(department);
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping(path = "/{id}")
    public Department updateDepartment(@Valid @RequestBody Department department, @PathVariable int id) {

        department.setId(id);
        try {
            return departmentDao.updateDepartment(department);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Department Not Found");
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/{id}")
    @PreAuthorize("isAuthenticated()")
    public void deleteDepartment(@PathVariable int id) {
        departmentDao.deleteDepartmentById(id);
    }
}

package com.techelevator.controller;

import com.techelevator.dao.TechnicianDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Technician;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5173")
@RequestMapping("/technicians")
public class TechnicianController {

    private final TechnicianDao technicianDao;

    public TechnicianController(TechnicianDao technicianDao) {
        this.technicianDao = technicianDao;
    }

    @GetMapping
    public List<Technician> getAllTechnicians() {
        return technicianDao.getAllTechnicians();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Technician createTechnician(@Valid @RequestBody Technician technician) {
        return technicianDao.createTechnician(technician);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(path = "/{id}")
    public Technician updateTechnician(@Valid @RequestBody Technician technician, @PathVariable int id) {

        technician.setId(id);
        try {
            return technicianDao.updateTechnician(technician);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Technician Not Found");
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteTechnician(@PathVariable int id) {
        technicianDao.deleteTechnicianById(id);
    }
}

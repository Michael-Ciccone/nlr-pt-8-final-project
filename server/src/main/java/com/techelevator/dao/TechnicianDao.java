package com.techelevator.dao;

import com.techelevator.model.Technician;

import java.util.List;

public interface TechnicianDao {

    List<Technician> getAllTechnicians();

    Technician getTechnicianById(int id);

    Technician createTechnician(Technician technician);

    Technician updateTechnician(Technician technician);

    int deleteTechnicianById(int id);
}

package com.techelevator.dao;

import com.techelevator.model.Department;

import java.util.List;

public interface DepartmentDao {

    List<Department> getAllDepartments();

    Department getDepartmentById(int id);


    Department createDepartment(Department department);

    Department updateDepartment(Department department);

    int deleteDepartmentById(int id);
}

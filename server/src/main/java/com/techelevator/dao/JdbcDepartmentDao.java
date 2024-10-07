package com.techelevator.dao;

import com.techelevator.model.Department;
import com.techelevator.exception.DaoException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class JdbcDepartmentDao implements DepartmentDao {
    private final JdbcTemplate template;

    private RowMapper<Department> mapper = new RowMapper<Department>() {
        @Override
        public Department mapRow(ResultSet rs, int rowNum) throws SQLException {

            int id = rs.getInt("department_id");
            String departmentName = rs.getString("department_name");
            int maintenanceMonth = rs.getInt("base_maintenance_month");
            int assignedTechnician = rs.getInt("assigned_technician");
            Department department = new Department(id, departmentName, maintenanceMonth, assignedTechnician);
            return department;
        }
    };

    public JdbcDepartmentDao(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    private static Department mapRowToDepartment(SqlRowSet rowSet) {

        int id = rowSet.getInt("department_id");
        String departmentName = rowSet.getString("department_name");
        int maintenanceMonth = rowSet.getInt("base_maintenance_month");
        int assignedTechnician = rowSet.getInt("assigned_technician");
        Department department = new Department(id, departmentName, maintenanceMonth, assignedTechnician);
        return department;
    }

    @Override
    public List<Department> getAllDepartments() {

        try {
            return template.query("SELECT * from department", mapper);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Uh oh! Could not connect to the database.", e);
        }

    }

    @Override
    public Department getDepartmentById(int id) {

        Department department = null;
        try {
            SqlRowSet results = template.queryForRowSet("SELECT * FROM department WHERE department_id = ?;", id);
            if (results.next()) {
                department = mapRowToDepartment(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Uh oh! Could not connect to the database.", e);
        }
        return department;
    }


    @Override
    public Department createDepartment(Department department) {
        Department newDepartment = null;
        String sql = "INSERT INTO department (department_name, base_maintenance_month, assigned_technician) VALUES (?, ?, ?) RETURNING department_id";

        try {
            Integer newDepartmentId = template.queryForObject(sql, int.class, department.getDepartmentName(), department.getMaintenanceMonth(), department.getAssignedTechnician());
            newDepartment = getDepartmentById(newDepartmentId);
            return newDepartment;
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Uh oh! Could not connect to the database.", e);
        }
    }

    @Override
    public Department updateDepartment(Department department) {
        Department updatedDepartment = null;
        String sql = "UPDATE department SET department_name = ?, base_maintenance_month = ?, assigned_technician = ? WHERE department_id = ?";

        try {
            template.update(sql, department.getDepartmentName(), department.getMaintenanceMonth(), department.getAssignedTechnician(), department.getId());
            updatedDepartment = getDepartmentById(department.getId());
            return updatedDepartment;
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Uh oh! Could not connect to the database.", e);
        }
    }

    @Override
    public int deleteDepartmentById(int id) {

        int numberOfRows = 0;

        String deleteDepartmentDevice = "DELETE FROM device WHERE owning_department = ?";
        String deleteDepartment = "DELETE FROM department WHERE department_id = ?";

        try {
            template.update(deleteDepartmentDevice, id);
            numberOfRows = template.update(deleteDepartment, id);
            return numberOfRows;
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Uh oh! Could not connect to the database.", e);
        }
    }
}

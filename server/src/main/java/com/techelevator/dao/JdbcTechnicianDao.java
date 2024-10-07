package com.techelevator.dao;

import com.techelevator.model.Technician;
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
public class JdbcTechnicianDao implements TechnicianDao {
    private final JdbcTemplate template;

    private RowMapper<Technician> mapper = new RowMapper<Technician>() {
        @Override
        public Technician mapRow(ResultSet rs, int rowNum) throws SQLException {
            int id = rs.getInt("technician_id");
            String fullName = rs.getString("full_name");
            String emailAddress = rs.getString("email_address");
            Technician technician = new Technician(id, fullName, emailAddress);
            return technician;
        }
    };

    public JdbcTechnicianDao(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    private static Technician mapRowToTechnician(SqlRowSet rowSet) {
        int id = rowSet.getInt("technician_id");
        String fullName = rowSet.getString("full_name");
        String emailAddress = rowSet.getString("email_address");
        Technician technician = new Technician(id, fullName, emailAddress);
        return technician;
    }

    @Override
    public List<Technician> getAllTechnicians() {

        try {
            return template.query("SELECT * from technician", mapper);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Uh oh! Could not connect to the database.", e);
        }

    }

    @Override
    public Technician getTechnicianById(int id) {

        Technician technician = null;
        try {
            SqlRowSet results = template.queryForRowSet("SELECT * FROM technician WHERE technician_id = ?;", id);
            if (results.next()) {
                technician = mapRowToTechnician(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Uh oh! Could not connect to the database.", e);
        }
        return technician;
    }

    @Override
    public Technician createTechnician(Technician technician) {
        Technician newTechnician = null;
        String sql = "INSERT INTO technician (full_name, email_address) VALUES (?, ?) RETURNING technician_id";

        try {
            Integer newTechnicianId = template.queryForObject(sql, int.class, technician.getFullName(), technician.getEmailAddress());
            newTechnician = getTechnicianById(newTechnicianId);
            return newTechnician;
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Uh oh! Could not connect to the database.", e);
        }
    }

    @Override
    public Technician updateTechnician(Technician technician) {
        Technician updatedTechnician = null;
        String sql = "UPDATE technician SET full_name = ?, email_address = ? WHERE technician_id = ?";

        try {
            template.update(sql, technician.getFullName(), technician.getEmailAddress(), technician.getId());
            updatedTechnician = getTechnicianById(technician.getId());
            return updatedTechnician;
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Uh oh! Could not connect to the database.", e);
        }
    }

    @Override
    public int deleteTechnicianById(int id) {

        int numberOfRows = 0;

        String deleteTechnicianDepartment = "DELETE FROM department WHERE assigned_technician = ?";
        String deleteTechnician = "DELETE FROM technician WHERE technician_id = ?";

        try {
            template.update(deleteTechnicianDepartment, id);
            numberOfRows = template.update(deleteTechnician, id);
            return numberOfRows;
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Uh oh! Could not connect to the database.", e);
        }
    }
}

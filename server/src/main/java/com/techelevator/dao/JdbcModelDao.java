package com.techelevator.dao;

import com.techelevator.model.Model;
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
public class JdbcModelDao implements ModelDao {
    private final JdbcTemplate template;

    private RowMapper<Model> mapper = new RowMapper<Model>() {
        @Override
        public Model mapRow(ResultSet rs, int rowNum) throws SQLException {
            int id = rs.getInt("model_id");
            String modelName = rs.getString("model_name");
            String manufacturerName = rs.getString("manufacturer_name");
            String maintenanceSchedule = rs.getString("maintenance_schedule");
            String modelDescription = rs.getString("model_description");
            Model model = new Model(id, modelName, manufacturerName, maintenanceSchedule, modelDescription);
            return model;
        }
    };

    public JdbcModelDao(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    private static Model mapRowToModel(SqlRowSet rowSet) {
        int id = rowSet.getInt("model_id");
        String modelName = rowSet.getString("model_name");
        String manufacturerName = rowSet.getString("manufacturer_name");
        String maintenanceSchedule = rowSet.getString("maintenance_schedule");
        String modelDescription = rowSet.getString("model_description");
        Model model = new Model(id, modelName, manufacturerName, maintenanceSchedule, modelDescription);
        return model;
    }

    @Override
    public List<Model> getAllModels() {

        try {
            return template.query("SELECT * FROM model;", mapper);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Uh oh! Could not connect to the database.", e);
        }

    }

    @Override
    public Model getModelById(int id) {

        Model model = null;

        try {
            SqlRowSet results = template.queryForRowSet("SELECT * FROM model WHERE model_id = ?;", id);
            if (results.next()) {
                model = mapRowToModel(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Uh oh! Could not connect to the database.", e);
        }
        return model;

    }


    @Override
    public Model createModel(Model model) {
        Model newModel = null;
        String sql = "insert into model (model_name, manufacturer_name, maintenance_schedule, model_description) values (?, ?, ?, ?) RETURNING model_id";

        try {
            Integer newModelId = template.queryForObject(sql, int.class, model.getModelName(), model.getManufacturerName(), model.getMaintenanceSchedule(), model.getModelDescription());
            newModel = getModelById(newModelId);
            return newModel;
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Uh oh! Could not connect to the database.", e);
        }
    }

    @Override
    public Model updateModel(Model model) {
        Model updatedModel = null;
        String sql = "UPDATE model SET model_name = ?, manufacturer_name = ?, maintenance_schedule = ?, model_description = ? WHERE model_id = ?";

        try {
            template.update(sql, model.getModelName(), model.getManufacturerName(), model.getMaintenanceSchedule(), model.getModelDescription(), model.getId());
            updatedModel = getModelById(model.getId());
            return updatedModel;
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Uh oh! Could not connect to the database.", e);
        }
    }

    @Override
    public int deleteModelById(int id) {

        int numberOfRows = 0;

        String deleteModelDevice = "DELETE FROM device WHERE model_id = ?";
        String deleteModel = "DELETE FROM model WHERE model_id = ?";

        try {
            template.update(deleteModelDevice, id);
            numberOfRows = template.update(deleteModel, id);
            return numberOfRows;
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Uh oh! Could not connect to the database.", e);
        }
    }
}

package com.techelevator.dao;

import com.techelevator.model.Device;
import com.techelevator.exception.DaoException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Component
public class JdbcDeviceDao implements DeviceDao {
    private final JdbcTemplate template;

    private RowMapper<Device> mapper = new RowMapper<Device>() {
        @Override
        public Device mapRow(ResultSet rs, int rowNum) throws SQLException {

            int id = rs.getInt("device_id");
            String serialNumber = rs.getString("serial_number");
            int modelId = rs.getInt("model_id");
            int owningDepartment = rs.getInt("owning_department");
            LocalDate installDate = rs.getDate("install_date").toLocalDate();
            Device device = new Device(id, serialNumber, modelId, owningDepartment, installDate);
            return device;

        }
    };

    public JdbcDeviceDao(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    private static Device mapRowToDevice(SqlRowSet rowSet) {
        int id = rowSet.getInt("device_id");
        String serialNumber = rowSet.getString("serial_number");
        int modelId = rowSet.getInt("model_id");
        int owningDepartment = rowSet.getInt("owning_department");
        LocalDate installDate = rowSet.getDate("install_date").toLocalDate();
        Device device = new Device(id, serialNumber, modelId, owningDepartment, installDate);
        return device;
    }

    @Override
    public List<Device> getAllDevices() {

        try {
            return template.query("SELECT * from device", mapper);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Uh oh! Could not connect to the database.", e);
        }

    }

    @Override
    public Device getDeviceById(int id) {

        Device device = null;
        try {
            SqlRowSet results = template.queryForRowSet("SELECT * FROM device WHERE device_id = ?;", id);
            if (results.next()) {
                device = mapRowToDevice(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Uh oh! Could not connect to the database.", e);
        }
        return device;

    }

    @Override
    public List<Device> getDevicesByOwningDepartment (int departmentId){

        try {
            return template.query("SELECT * FROM device WHERE owning_department = ?;", mapper, departmentId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Uh oh! Could not connect to the database.", e);
        }

    }

    @Override
    public List<Device> getDevicesInstalledInDateRange (LocalDate startDate, LocalDate endDate){

        try {
            return template.query("select * from device WHERE install_date BETWEEN ? AND ?;", mapper, startDate, endDate);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Uh oh! Could not connect to the database.", e);
        }

    }

    @Override
    public List <Device> getDevicesByManufacturer(String mfgName) {

        mfgName = "%" + mfgName + "%";

        try {
            return template.query("select * from device WHERE device.model_id IN (SELECT model.model_id from model WHERE manufacturer_name ILIKE ?);", mapper, mfgName);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Uh oh! Could not connect to the database.", e);
        }
    }

    @Override
    public Device createDevice(Device device) {
        Device newDevice = null;
        String sql = "INSERT INTO device (serial_number, model_id, owning_department, install_date) VALUES (?, ?, ?, ?) RETURNING device_id";

        try {
            Integer newDeviceId = template.queryForObject(sql, int.class, device.getSerialNumber(), device.getModelId(), device.getOwningDepartment(), device.getInstallDate());
            newDevice = getDeviceById(newDeviceId);
            return newDevice;
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Uh oh! Could not connect to the database.", e);
        }
    }

    @Override
    public Device updateDevice(Device device) {
        Device updatedDevice = null;
        String sql = "UPDATE device SET serial_number = ?, model_id = ?, owning_department = ?, install_date = ? WHERE device_id = ?";

        try {
            template.update(sql, device.getSerialNumber(), device.getModelId(), device.getOwningDepartment(), device.getInstallDate(), device.getId());
            updatedDevice = getDeviceById(device.getId());
            return updatedDevice;
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Uh oh! Could not connect to the database.", e);
        }
    }

    @Override
    public int deleteDeviceById(int id) {

        int numberOfRows = 0;

        String deleteDevice = "DELETE FROM device WHERE device_id = ?";

        try {
            numberOfRows = template.update(deleteDevice, id);
            return numberOfRows;
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Uh oh! Could not connect to the database.", e);
        }
    }
}

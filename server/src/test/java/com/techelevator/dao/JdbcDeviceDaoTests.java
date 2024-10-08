package com.techelevator.dao;


import com.techelevator.model.Device;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;


import java.time.LocalDate;

import static org.junit.Assert.assertNull;
public class JdbcDeviceDaoTests extends BaseDaoTests {

    private static final Device DEVICE_1 = new Device(1, "sn1", 4, 1, LocalDate.parse("2002-08-05"), "pic1");
    private static final Device DEVICE_2 = new Device(2, "sn2", 2, 3, LocalDate.parse("2018-11-23"), "pic2");
    private static final Device DEVICE_3 = new Device(3, "sn3", 3, 2, LocalDate.parse("2014-01-21"), "pic3");
    private static final Device DEVICE_4 = new Device(4, "sn4", 1, 3, LocalDate.parse("2001-06-04"), "pic4");
    private JdbcDeviceDao dao;

    @Before
    public void setup() {
        dao = new JdbcDeviceDao(dataSource);
    }

    @Test
    public void getDeviceById_with_valid_id_returns_correct_device() {

        Device device = dao.getDeviceById(DEVICE_1.getId());

        Assert.assertNotNull("getDeviceById(" + DEVICE_1.getId() + ") returned null", device);
        assertDevicesMatch("getDeviceById(" + DEVICE_1.getId() + ") returned wrong or partial data", DEVICE_1, device);
    }

    @Test
    public void getDevicesById_with_invalid_id_returns_null_device() {

        Device device = dao.getDeviceById(90000000);
        assertNull("getModelById with invalid id returned a model rather than null.", device);
    }


    @Test
    public void createDevice_creates_device() {

        Device newDevice = new Device();
        newDevice.setSerialNumber("abc123");
        newDevice.setModelId(2);
        newDevice.setOwningDepartment(2);
        newDevice.setInstallDate(LocalDate.parse("2009-03-08"));
        newDevice.setPicture("pic6");

        Device createdDevice = dao.createDevice(newDevice);

        Assert.assertNotNull("createDevice returned a null device.", createdDevice);
        Assert.assertTrue("createDevice did not return a device with id set.", createdDevice.getId() > 0);
        Assert.assertEquals("createDevice did not return a device with the correct serial number.", "abc123", newDevice.getSerialNumber());
        Assert.assertEquals("createDevice did not return a device with the correct model ID.", 2, newDevice.getModelId());
        Assert.assertEquals("createDevice did not return a device with the correct owning department.", 2, newDevice.getOwningDepartment());
        Assert.assertEquals("createDevice did not return a device with the correct install date.", LocalDate.parse("2009-03-08"), newDevice.getInstallDate());
        Assert.assertEquals("createDevice did not return a device with the correct picture url.", "pic6", newDevice.getPicture());

        Device retrievedDevice = getDeviceByIdForTestVerification(createdDevice.getId());
        Assert.assertNotNull("createDevice does not appear to have correctly persisted the newly created device. It could not be found by id.", retrievedDevice);
        assertDevicesMatch("createDevice does does not appear to have fully persisted the newly created device. The retrieved device is incorrect/incomplete.", createdDevice, retrievedDevice);
    }

    @Test
    public void updateDevice_updates_device() {

        Device existingDevice = new Device();
        existingDevice.setId(DEVICE_2.getId());
        existingDevice.setSerialNumber("xyz789");
        existingDevice.setModelId(3);
        existingDevice.setOwningDepartment(3);
        existingDevice.setInstallDate(LocalDate.parse("2016-07-01"));
        existingDevice.setPicture("pic7");

        Device updatedDevice = dao.updateDevice(existingDevice);

        Assert.assertNotNull("updateDevice returned a null device.", updatedDevice);
        assertDevicesMatch("updateDevice returned an incorrect/incomplete device.", updatedDevice, existingDevice);

        Device retrievedDevice = getDeviceByIdForTestVerification(DEVICE_2.getId());
        assertDevicesMatch("updateDevice does not appear to have fully persisted the updated device. The retrieved device is incorrect/incomplete.", updatedDevice, retrievedDevice);
    }

    @Test
    public void deleteDeviceById_deletes_device() {

        int recordsAffected = dao.deleteDeviceById(DEVICE_3.getId());
        Assert.assertEquals("deleteDeviceById did not return the correct number of rows affected.", 1, recordsAffected);
        Device retrievedDevice = getDeviceByIdForTestVerification(DEVICE_3.getId());
        Assert.assertNull("deleteDeviceById did not remove device from database.", retrievedDevice);

    }

    private static Device mapValuesToDevice(int deviceId, String serialNumber, int modelId, int owningDepartment, LocalDate installDate, String picture) {

        Device device = new Device();
        device.setId(deviceId);
        device.setSerialNumber(serialNumber);
        device.setModelId(modelId);
        device.setOwningDepartment(owningDepartment);
        device.setInstallDate(installDate);
        device.setPicture(picture);

        return device;

    }

    private void assertDevicesMatch(String message, Device expected, Device actual) {
        Assert.assertEquals(message, expected.getId(), actual.getId());
        Assert.assertEquals(message, expected.getSerialNumber(), actual.getSerialNumber());
        Assert.assertEquals(message, expected.getModelId(), actual.getModelId());
        Assert.assertEquals(message, expected.getOwningDepartment(), actual.getOwningDepartment());
        Assert.assertEquals(message, expected.getInstallDate(), actual.getInstallDate());
        Assert.assertEquals(message, expected.getPicture(), actual.getPicture());
    }

    private Device getDeviceByIdForTestVerification(int id) {

        Device device = null;
        String sql = "SELECT * FROM device WHERE device_id = ?";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);

        if (results.next()) {
            device = mapValuesToDevice(results.getInt("device_id"), results.getString("serial_number"), results.getInt("model_id"), results.getInt("owning_department"), results.getDate("install_date").toLocalDate(), results.getString("picture"));
        }
        return device;
    }
}

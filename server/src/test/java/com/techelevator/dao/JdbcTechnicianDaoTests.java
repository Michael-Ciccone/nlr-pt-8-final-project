package com.techelevator.dao;


import com.techelevator.model.Technician;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;



import static org.junit.Assert.assertNull;

public class JdbcTechnicianDaoTests extends BaseDaoTests{

    private static final Technician TECHNICIAN_1 = new Technician(1, "Technician 1", "tech1@yahoo.com");
    private static final Technician TECHNICIAN_2 = new Technician(2, "Technician 2", "tech2@yahoo.com");
    private static final Technician TECHNICIAN_3 = new Technician(3, "Technician 3", "tech3@yahoo.com");
    private static final Technician TECHNICIAN_4 = new Technician(4, "Technician 4", "tech4@yahoo.com");

    private JdbcTechnicianDao dao;

    @Before
    public void setup() {
        dao = new JdbcTechnicianDao(dataSource);
    }
    @Test
    public void getTechnicianById_with_valid_id_returns_correct_technician() {

        Technician technician = dao.getTechnicianById(TECHNICIAN_1.getId());

        Assert.assertNotNull("getTechnicianById(" + TECHNICIAN_1.getId() + ") returned null", technician);
        assertTechniciansMatch("getTechnicianById(" + TECHNICIAN_1.getId() + ") returned wrong or partial data", TECHNICIAN_1, technician);
    }
    @Test
    public void getTechnicianById_with_invalid_id_returns_null_technician() {

        Technician technician = dao.getTechnicianById(90000000);
        assertNull("getTechnicianById with invalid id returned a technician rather than null.", technician);
    }


    @Test
    public void createTechnician_creates_technician() {

        Technician newTechnician = new Technician();
        newTechnician.setFullName("Tom Beerbower");
        newTechnician.setEmailAddress("tom@yahoo.com");

        Technician createdTechnician = dao.createTechnician(newTechnician);

        Assert.assertNotNull("createTechnician returned a null technician.", createdTechnician);
        Assert.assertTrue("createTechnician did not return a technician with id set.", createdTechnician.getId() > 0);
        Assert.assertEquals("createTechnician did not return a technician with the correct full name.", "Tom Beerbower", newTechnician.getFullName());
        Assert.assertEquals("createTechnician did not return a technician with the correct email address.", "tom@yahoo.com", newTechnician.getEmailAddress());

        Technician retrievedTechnician = getTechnicianByIdForTestVerification(createdTechnician.getId());
        Assert.assertNotNull("createTechnician does not appear to have correctly persisted the newly created technician. It could not be found by id.", retrievedTechnician);
        assertTechniciansMatch("createTechnician does does not appear to have fully persisted the newly created technician. The retrieved technician is incorrect/incomplete.", createdTechnician, retrievedTechnician);
    }

    @Test
    public void updateTechnician_updates_technician() {

        Technician existingTechnician = new Technician();
        existingTechnician.setId(TECHNICIAN_4.getId());
        existingTechnician.setFullName("Michael Ciccone");
        existingTechnician.setEmailAddress("mc@yahoo.com");

        Technician updatedTechnician = dao.updateTechnician(existingTechnician);

        Assert.assertNotNull("updateTechnician returned a null technician.", updatedTechnician);
        assertTechniciansMatch("updateTechnician returned an incorrect/incomplete technician.", updatedTechnician, existingTechnician);

        Technician retrievedTechnician = getTechnicianByIdForTestVerification(TECHNICIAN_4.getId());
        assertTechniciansMatch("updateTechnician does not appear to have fully persisted the updated technician. The retrieved technician is incorrect/incomplete.", updatedTechnician, retrievedTechnician);
    }

    @Test
    public void deleteTechnicianById_deletes_technician() {

        int recordsAffected = dao.deleteTechnicianById(TECHNICIAN_4.getId());
        Assert.assertEquals("deleteTechnicianById did not return the correct number of rows affected.", 1, recordsAffected);
        Technician retrievedTechnician = getTechnicianByIdForTestVerification(TECHNICIAN_4.getId());
        Assert.assertNull("deleteTechnicianById did not remove technician from database.", retrievedTechnician);

    }

    private static Technician mapValuesToTechnician(int technicianId, String fullName, String emailAddress) {

        Technician technician = new Technician();
        technician.setId(technicianId);
        technician.setFullName(fullName);
        technician.setEmailAddress(emailAddress);

        return technician;

    }

    private void assertTechniciansMatch(String message, Technician expected, Technician actual) {
        Assert.assertEquals(message, expected.getId(), actual.getId());
        Assert.assertEquals(message, expected.getFullName(), actual.getFullName());
        Assert.assertEquals(message, expected.getEmailAddress(), actual.getEmailAddress());
    }

    private Technician getTechnicianByIdForTestVerification(int id) {

        Technician technician = null;
        String sql = "SELECT * FROM technician WHERE technician_id = ?";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);

        if (results.next()) {
            technician = mapValuesToTechnician(results.getInt("technician_id"), results.getString("full_name"), results.getString("email_address"));
        }
        return technician;
    }
}

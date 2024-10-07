package com.techelevator.dao;


import com.techelevator.model.Department;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;


import static org.junit.Assert.assertNull;
public class JdbcDepartmentDaoTests extends BaseDaoTests{

    private static final Department DEPARTMENT_1 = new Department(1, "Department 1", 1, 1);
    private static final Department DEPARTMENT_2 = new Department(2, "Department 2", 2, 2);
    private static final Department DEPARTMENT_3 = new Department(3, "Department 3", 3, 3);
    private static final Department DEPARTMENT_4 = new Department(4, "Department 4", 4, 3);

    private JdbcDepartmentDao dao;


    @Before
    public void setup() {
        dao = new JdbcDepartmentDao(dataSource);
    }

    @Test
    public void getDepartmentById_with_valid_id_returns_correct_department() {

        Department department = dao.getDepartmentById(DEPARTMENT_1.getId());

        Assert.assertNotNull("getDepartmentById(" + DEPARTMENT_1.getId() + ") returned null", department);
        assertDepartmentsMatch("getDepartmentById(" + DEPARTMENT_1.getId() + ") returned wrong or partial data", DEPARTMENT_1, department);
    }

    @Test
    public void getDepartmentById_with_invalid_id_returns_null_department() {

        Department department = dao.getDepartmentById(90000000);
        assertNull("getDepartmentById with invalid id returned a department rather than null.", department);
    }


    @Test
    public void createDepartment_creates_department() {

        Department newDepartment = new Department();
        newDepartment.setDepartmentName("Behavioral Health");
        newDepartment.setMaintenanceMonth(5);
        newDepartment.setAssignedTechnician(2);

        Department createdDepartment = dao.createDepartment(newDepartment);

        Assert.assertNotNull("createDepartment returned a null department.", createdDepartment);
        Assert.assertTrue("createDepartment did not return a department with id set.", createdDepartment.getId() > 0);
        Assert.assertEquals("createDepartment did not return a department with the correct department name.", "Behavioral Health", newDepartment.getDepartmentName());
        Assert.assertEquals("createDepartment did not return a department with the correct maintenance month.", 5, newDepartment.getMaintenanceMonth());
        Assert.assertEquals("createDepartment did not return a department with the correct assigned technician.", 2, newDepartment.getAssignedTechnician());

        Department retrievedDepartment = getDepartmentByIdForTestVerification(createdDepartment.getId());
        Assert.assertNotNull("createDepartment does not appear to have correctly persisted the newly created department. It could not be found by id.", retrievedDepartment);
        assertDepartmentsMatch("createDepartment does does not appear to have fully persisted the newly created department. The retrieved department is incorrect/incomplete.", createdDepartment, retrievedDepartment);
    }

    @Test
    public void updateDepartment_updates_department() {

        Department existingDepartment = new Department();
        existingDepartment.setId(DEPARTMENT_2.getId());
        existingDepartment.setDepartmentName("Microbiology");
        existingDepartment.setMaintenanceMonth(9);
        existingDepartment.setAssignedTechnician(3);

        Department updatedDepartment = dao.updateDepartment(existingDepartment);

        Assert.assertNotNull("updateDepartment returned a null department.", updatedDepartment);
        assertDepartmentsMatch("updateDepartment returned an incorrect/incomplete department.", updatedDepartment, existingDepartment);

        Department retrievedDepartment = getDepartmentByIdForTestVerification(DEPARTMENT_2.getId());
        assertDepartmentsMatch("updateDepartment does not appear to have fully persisted the updated department. The retrieved department is incorrect/incomplete.", updatedDepartment, retrievedDepartment);
    }

    @Test
    public void deleteDepartmentById_deletes_department() {

        int recordsAffected = dao.deleteDepartmentById(DEPARTMENT_3.getId());
        Assert.assertEquals("deleteDepartmentById did not return the correct number of rows affected.", 1, recordsAffected);
        Department retrievedDepartment = getDepartmentByIdForTestVerification(DEPARTMENT_3.getId());
        Assert.assertNull("deleteDepartmentById did not remove department from database.", retrievedDepartment);

    }

    private static Department mapValuesToDepartment(int departmentId, String departmentName, int maintenanceMonth, int assignedTech) {

        Department department = new Department();
        department.setId(departmentId);
        department.setDepartmentName(departmentName);
        department.setMaintenanceMonth(maintenanceMonth);
        department.setAssignedTechnician(assignedTech);

        return department;

    }

    private void assertDepartmentsMatch(String message, Department expected, Department actual) {
        Assert.assertEquals(message, expected.getId(), actual.getId());
        Assert.assertEquals(message, expected.getDepartmentName(), actual.getDepartmentName());
        Assert.assertEquals(message, expected.getMaintenanceMonth(), actual.getMaintenanceMonth());
        Assert.assertEquals(message, expected.getAssignedTechnician(), actual.getAssignedTechnician());
    }

    private Department getDepartmentByIdForTestVerification(int id) {

        Department department = null;
        String sql = "SELECT * FROM department WHERE department_id = ?";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);

        if (results.next()) {
            department = mapValuesToDepartment(results.getInt("department_id"), results.getString("department_name"), results.getInt("base_maintenance_month"), results.getInt("assigned_technician"));
        }
        return department;
    }
}

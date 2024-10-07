package com.techelevator.dao;


import com.techelevator.model.Model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;



import static org.junit.Assert.assertNull;
public class JdbcModelDaoTests extends BaseDaoTests {


    private static final Model MODEL_1 = new Model(1, "Model 1", "Nokia", "biannual", "description1");
    private static final Model MODEL_2 = new Model(2, "Model 2", "Palm", "quarterly", "description2");
    private static final Model MODEL_3 = new Model(3, "Model 3", "verykool", "quarterly", "description3");
    private static final Model MODEL_4 = new Model(4, "Model 4", "Sony", "monthly", "description4");
    private JdbcModelDao dao;

    @Before
    public void setup() {
        dao = new JdbcModelDao(dataSource);
    }

    @Test
    public void getModelById_with_valid_id_returns_correct_model() {

        Model model = dao.getModelById(MODEL_1.getId());

        Assert.assertNotNull("getModelById(" + MODEL_1.getId() + ") returned null", model);
        assertModelsMatch("getModelById(" + MODEL_1.getId() + ") returned wrong or partial data", MODEL_1, model);
    }

    @Test
    public void getDepartmentById_with_invalid_id_returns_null_department() {

        Model model = dao.getModelById(90000000);
        assertNull("getModelById with invalid id returned a model rather than null.", model);
    }


    @Test
    public void createModel_creates_model() {

        Model newModel = new Model();
        newModel.setModelName("Wii");
        newModel.setManufacturerName("Nintendo");
        newModel.setMaintenanceSchedule("annual");
        newModel.setModelDescription("description5");

        Model createdModel = dao.createModel(newModel);

        Assert.assertNotNull("createModel returned a null model.", createdModel);
        Assert.assertTrue("createModel did not return a model with id set.", createdModel.getId() > 0);
        Assert.assertEquals("createModel did not return a model with the correct model name.", "Wii", newModel.getModelName());
        Assert.assertEquals("createModel did not return a model with the correct manufacturer name.", "Nintendo", newModel.getManufacturerName());
        Assert.assertEquals("createModel did not return a model with the correct maintenance schedule.", "annual", newModel.getMaintenanceSchedule());
        Assert.assertEquals("createModel did not return a model with the correct model description.", "description5", newModel.getModelDescription());

        Model retrievedModel = getModelByIdForTestVerification(createdModel.getId());
        Assert.assertNotNull("createModel does not appear to have correctly persisted the newly created model. It could not be found by id.", retrievedModel);
        assertModelsMatch("createModel does does not appear to have fully persisted the newly created model. The retrieved model is incorrect/incomplete.", createdModel, retrievedModel);
    }

    @Test
    public void updateModel_updates_model() {

        Model existingModel = new Model();
        existingModel.setId(MODEL_2.getId());
        existingModel.setModelName("iPod");
        existingModel.setManufacturerName("Apple");
        existingModel.setMaintenanceSchedule("annual");
        existingModel.setModelDescription("description20");

        Model updatedModel = dao.updateModel(existingModel);

        Assert.assertNotNull("updateModel returned a null model.", updatedModel);
        assertModelsMatch("updateModel returned an incorrect/incomplete model.", updatedModel, existingModel);

        Model retrievedModel = getModelByIdForTestVerification(MODEL_2.getId());
        assertModelsMatch("updateModel does not appear to have fully persisted the updated model. The retrieved model is incorrect/incomplete.", updatedModel, retrievedModel);
    }

    @Test
    public void deleteModelById_deletes_model() {

        int recordsAffected = dao.deleteModelById(MODEL_3.getId());
        Assert.assertEquals("deleteModelById did not return the correct number of rows affected.", 1, recordsAffected);
        Model retrievedModel = getModelByIdForTestVerification(MODEL_3.getId());
        Assert.assertNull("deleteModelById did not remove model from database.", retrievedModel);

    }

    private static Model mapValuesToModel(int modelId, String modelName, String manufacturerName, String maintenanceSchedule, String modelDescription) {

        Model model = new Model();
        model.setId(modelId);
        model.setModelName(modelName);
        model.setManufacturerName(manufacturerName);
        model.setMaintenanceSchedule(maintenanceSchedule);
        model.setModelDescription(modelDescription);

        return model;

    }

    private void assertModelsMatch(String message, Model expected, Model actual) {
        Assert.assertEquals(message, expected.getId(), actual.getId());
        Assert.assertEquals(message, expected.getModelName(), actual.getModelName());
        Assert.assertEquals(message, expected.getManufacturerName(), actual.getManufacturerName());
        Assert.assertEquals(message, expected.getMaintenanceSchedule(), actual.getMaintenanceSchedule());
    }

    private Model getModelByIdForTestVerification(int id) {

        Model model = null;
        String sql = "SELECT * FROM model WHERE model_id = ?";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);

        if (results.next()) {
            model = mapValuesToModel(results.getInt("model_id"), results.getString("model_name"), results.getString("manufacturer_name"), results.getString("maintenance_schedule"), results.getString("model_description"));
        }
        return model;
    }
}

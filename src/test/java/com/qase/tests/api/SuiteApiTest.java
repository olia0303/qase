package com.qase.tests.api;

import com.qase.model.Project;
import com.qase.model.Suite;
import org.testng.annotations.Test;

import static com.qase.model.ProjectFactory.getProject;
import static com.qase.model.SuiteFactory.getSuite;
import static org.testng.Assert.assertEquals;

public class SuiteApiTest extends BaseApiTest {
    @Test(description = "Check the added new suite via API")
    public void suiteShouldBeCreatedViaApi() {
        Project project = getProject();
        Suite suite = getSuite();
        mainAdapter.createProjectViaApi(project);
        mainAdapter.createSuiteViaApi(suite, project.getCode());
        int suiteId = mainAdapter.getSpecificTestSuite(project.getCode(), suite.getSuiteId());
        assertEquals(suiteId, suite.getSuiteId());
        mainAdapter.deleteProjectViaApi(project.getCode());
    }

    @Test(description = "Check the deletion of the existing suite via API")
    public void suiteShouldBeDeletedViaApi() {
        Project project = getProject();
        Suite suite = getSuite();
        mainAdapter.createProjectViaApi(project);
        mainAdapter.createSuiteViaApi(suite, project.getCode());
        int suiteId = mainAdapter.getSpecificTestSuite(project.getCode(), suite.getSuiteId());
        mainAdapter.deleteSuiteViaApi(project.getCode(), suiteId);
        String message = mainAdapter.getSuiteErrorMessageById(project.getCode(), suiteId);
        assertEquals(message, "Suite not found");
        mainAdapter.deleteProjectViaApi(project.getCode());
    }

    @Test(description = "Check the updated existing suite via API")
    public void suiteShouldBeUpdatedViaApi() {
        Project project = getProject();
        Suite suite = getSuite();
        Suite updateToSuite = getSuite();
        mainAdapter.createProjectViaApi(project);
        mainAdapter.createSuiteViaApi(suite, project.getCode());
        int suiteId = mainAdapter.getSpecificTestSuite(project.getCode(), suite.getSuiteId());
        Suite updateDSuite = mainAdapter.updatedSuiteViaApi(project.getCode(), suiteId, updateToSuite);
        assertEquals(suiteId, updateDSuite.getSuiteId());
        assertEquals(updateDSuite.getSuiteName(), updateToSuite.getSuiteName());
        assertEquals(updateDSuite.getDescription(), updateToSuite.getDescription());
        assertEquals(updateDSuite.getPreconditions(), updateDSuite.getPreconditions());
        mainAdapter.deleteProjectViaApi(project.getCode());
    }
}

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
        projectApiAdapter.createProjectViaApi(project);
        suiteApiAdapter.createSuiteViaApi(suite, project.getCode());
        int suiteId = suiteApiAdapter.getSpecificTestSuite(project.getCode(), suite.getSuiteId());
        assertEquals(suiteId, suite.getSuiteId());
        projectApiAdapter.deleteProjectViaApi(project.getCode());
    }

    @Test(description = "Check the deletion of the existing suite via API")
    public void suiteShouldBeDeletedViaApi() {
        Project project = getProject();
        Suite suite = getSuite();
        projectApiAdapter.createProjectViaApi(project);
        suiteApiAdapter.createSuiteViaApi(suite, project.getCode());
        int suiteId = suiteApiAdapter.getSpecificTestSuite(project.getCode(), suite.getSuiteId());
        suiteApiAdapter.deleteSuiteViaApi(project.getCode(), suiteId);
        String message = suiteApiAdapter.getSuiteErrorMessageById(project.getCode(), suiteId);
        assertEquals(message, "Suite not found");
        projectApiAdapter.deleteProjectViaApi(project.getCode());
    }

    @Test(description = "Check the updated existing suite via API")
    public void suiteShouldBeUpdatedViaApi() {
        Project project = getProject();
        Suite suite = getSuite();
        Suite updateToSuite = getSuite();
        projectApiAdapter.createProjectViaApi(project);
        suiteApiAdapter.createSuiteViaApi(suite, project.getCode());
        int suiteId = suiteApiAdapter.getSpecificTestSuite(project.getCode(), suite.getSuiteId());
        Suite updateDSuite = suiteApiAdapter.updatedSuiteViaApi(project.getCode(), suiteId, updateToSuite);
        assertEquals(suiteId, updateDSuite.getSuiteId());
        assertEquals(updateDSuite.getSuiteName(), updateToSuite.getSuiteName());
        assertEquals(updateDSuite.getDescription(), updateToSuite.getDescription());
        assertEquals(updateDSuite.getPreconditions(), updateDSuite.getPreconditions());
        projectApiAdapter.deleteProjectViaApi(project.getCode());
    }
}

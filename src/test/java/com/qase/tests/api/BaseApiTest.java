package com.qase.tests.api;

import com.qase.api.APIUtilsExtended;
import com.qase.api.MainAdapter;
import com.qase.api.RestApiUtils;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

@Log4j2
public class BaseApiTest {
    public APIUtilsExtended apiUtilsExtended;
    public MainAdapter mainAdapter;

    @BeforeSuite
    public void clean() {
        log.info("Clean old test data");
        apiUtilsExtended = new APIUtilsExtended(new RestApiUtils());
        mainAdapter = new MainAdapter(apiUtilsExtended);
        mainAdapter.deleteAllProjects();
    }

    @BeforeMethod(description = "Set up test data")
    public void start() {
        apiUtilsExtended = new APIUtilsExtended(new RestApiUtils());
        mainAdapter = new MainAdapter(apiUtilsExtended);
    }
}
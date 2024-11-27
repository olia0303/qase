package com.qase.tests.api;

import com.qase.api.APIUtilsExtended;
import com.qase.api.MainAdapter;
import com.qase.api.RestApiUtils;
import org.testng.annotations.BeforeMethod;

public class BaseApiTest {
    public APIUtilsExtended apiUtilsExtended;
    public MainAdapter mainAdapter;

    @BeforeMethod(description = "Set up test data")
    public void start() {
        apiUtilsExtended = new APIUtilsExtended(new RestApiUtils());
        mainAdapter = new MainAdapter(apiUtilsExtended);
    }
}
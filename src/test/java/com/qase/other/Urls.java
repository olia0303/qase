package com.qase.other;

import utils.PropertyManager;

public class Urls {
    public static final String BASE_URL = new PropertyManager().get("application.url.qase");
    public static final String BASE_API_URL = new PropertyManager().get("application.api.url.qase");
}

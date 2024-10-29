package com.qase.other;

import utils.PropertyManager;

public class Urls {
    public static final String LOGIN_PAGE = new PropertyManager().get("application.url.qase");
    public static final String PROJECTS_PAGE = new PropertyManager().get("projects.url");
}

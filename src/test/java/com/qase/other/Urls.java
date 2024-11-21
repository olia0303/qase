package com.qase.other;

import utils.PropertyManager;

public class Urls {
    public static final String LOGIN_PAGE = new PropertyManager().get("application.url.qase");
    public static final String PROJECTS_PAGE_URL = new PropertyManager().get("projects.url");
    public static final String PROJECT_REPOSITORY_URL = new PropertyManager().get("project.repo.url");
    public static final String PROJECT_API_URL = new PropertyManager().get("api.project.url.qase");
}

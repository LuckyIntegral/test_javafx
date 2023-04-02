package com.example.testfx.config;

public enum LoaderPage {

    LAYOUT("layout", "root-layout.fxml"),
    DEPARTMENT("departments", "departments-view.fxml"),
    EMPLOYEE("employee", "employees-view.fxml");

    private final String page;
    private final String view;

    LoaderPage(String view, String page) {
        this.view = view;
        this.page = page;
    }

    public String getPage() {
        return page;
    }

    public String getView() {
        return view;
    }
}

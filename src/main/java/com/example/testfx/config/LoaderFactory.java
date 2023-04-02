package com.example.testfx.config;

import javafx.fxml.FXMLLoader;

import java.util.HashMap;
import java.util.Map;

public class LoaderFactory {
    private final Map<String, FXMLLoader> pageMap = new HashMap<>();

    public LoaderFactory(Class<?> mainClass) {
        final Map<String, String> loaderMap = Map.ofEntries(
                Map.entry(LoaderPage.LAYOUT.getView(), LoaderPage.LAYOUT.getPage()),
                Map.entry(LoaderPage.DEPARTMENT.getView(), LoaderPage.DEPARTMENT.getPage()),
                Map.entry(LoaderPage.EMPLOYEE.getView(), LoaderPage.EMPLOYEE.getPage())
        );
        loaderMap.forEach((k, v) -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(mainClass.getResource(v));
            pageMap.put(k, loader);
        });
    }

    public Map<String, FXMLLoader> getPageMap() {
        return pageMap;
    }
}

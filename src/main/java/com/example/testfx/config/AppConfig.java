package com.example.testfx.config;

import com.example.testfx.persistance.jdbc.JdbcService;
import com.example.testfx.resources.ResourcesUtil;
import javafx.stage.Stage;

public class AppConfig {
    public static void init(Stage stage, Class<?> mainClass) {
        JdbcService.getInstance().initDBState(ResourcesUtil.getResources(mainClass.getClassLoader()));
        LoaderFactory loaderFactory = new LoaderFactory(mainClass);
        PageConfig pageConfig = new PageConfig(stage, loaderFactory);
    }
}

package com.example.testfx;

import com.example.testfx.config.AppConfig;
import javafx.application.Application;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {
        AppConfig.init(stage, this.getClass());
    }

    public static void main(String[] args) {
        launch();
    }
}
package com.example.testfx.controller;

import com.example.testfx.config.LoaderPage;
import com.example.testfx.reactive.PubSubRX;

public class RootLayoutController {
    public void showDepartments() {
        PubSubRX.getInstance().publish(LoaderPage.DEPARTMENT);
    }

    public void showEmployee() {
        PubSubRX.getInstance().publish(LoaderPage.EMPLOYEE);
    }
}

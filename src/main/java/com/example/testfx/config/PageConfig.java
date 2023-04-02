package com.example.testfx.config;

import com.example.testfx.reactive.PubSubRX;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class PageConfig {
    private final Stage stage;
    private final LoaderFactory loaderFactory;
    private BorderPane rootLayout;
    private AnchorPane departmentView;
    private AnchorPane employeeView;

    public PageConfig(Stage stage, LoaderFactory loaderFactory) {
        this.stage = stage;
        this.loaderFactory = loaderFactory;
        this.stage.setTitle("JavaFX application");
        initRootLayout(this.loaderFactory.getPageMap().get(LoaderPage.LAYOUT.getView()));

        PubSubRX.getInstance().subscribe(loaderPageObserver());
    }

    private void initRootLayout(FXMLLoader loader) {
        try {
            this.rootLayout = loader.load();

            Scene scene = new Scene(this.rootLayout);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void showDepartmentsView(FXMLLoader loader) {
        try {
            if (this.departmentView == null) {
                this.departmentView = loader.load();
            }
            rootLayout.setCenter(departmentView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showEmployeesView(FXMLLoader loader) {
        try {
            if (this.employeeView == null) {
                this.employeeView = loader.load();
            }
            rootLayout.setCenter(employeeView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void switchPage(LoaderPage loaderPage) {
        switch (loaderPage) {
            case DEPARTMENT -> showDepartmentsView(this.loaderFactory.getPageMap().get(LoaderPage.DEPARTMENT.getView()));
            case EMPLOYEE -> showEmployeesView(this.loaderFactory.getPageMap().get(LoaderPage.EMPLOYEE.getView()));
        }
    }

    private Observer<LoaderPage> loaderPageObserver() {
        return new Observer<>(){
            @Override
            public void onSubscribe(@NonNull Disposable disposable) {

            }

            @Override
            public void onNext(@NonNull LoaderPage loaderPage) {
                switchPage(loaderPage);
            }

            @Override
            public void onError(@NonNull Throwable throwable) {

            }

            @Override
            public void onComplete() {

            }
        };
    }
}

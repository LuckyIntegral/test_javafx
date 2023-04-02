module com.example.testfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires rxjava2.extensions;
    requires io.reactivex.rxjava2;
    requires org.reactivestreams;
    requires java.sql;

    opens com.example.testfx to javafx.fxml;
    exports com.example.testfx;
    exports com.example.testfx.controller;
    exports com.example.testfx.persistance.entity;
    exports com.example.testfx.persistance.dto;
    exports com.example.testfx.persistance.type;

    opens com.example.testfx.controller to javafx.fxml;
}
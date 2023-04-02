package com.example.testfx.controller;

import com.example.testfx.persistance.dto.DepartmentDto;
import com.example.testfx.persistance.entity.Department;
import com.example.testfx.persistance.type.DepartmentType;
import com.example.testfx.service.DepartmentService;
import com.example.testfx.service.impl.DepartmentServiceImpl;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.BehaviorSubject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

public class DepartmentController implements Initializable {
    @FXML
    private TextField depIdText;
    @FXML
    private TextField depTypeText;
    @FXML
    private TableView<DepartmentDto> departmentTable;
    @FXML
    private TableColumn<DepartmentDto, Long> idColumn;
    @FXML
    private TableColumn<DepartmentDto, DepartmentType> typeColumn;
    @FXML
    private TableColumn<DepartmentDto, Integer> employeeNumberColumn;

    private final DepartmentService departmentService = new DepartmentServiceImpl();
    private ObservableList<DepartmentDto> dtoObservableList = FXCollections.observableArrayList();
    private final BehaviorSubject<Boolean> publisher = BehaviorSubject.createDefault(true);
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dtoObservableList.addAll(findAll());
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("departmentType"));
        employeeNumberColumn.setCellValueFactory(new PropertyValueFactory<>("employeeCount"));
        departmentTable.setItems(dtoObservableList);

        departmentTable.setRowFactory(rv -> {
            TableRow<DepartmentDto> row = new TableRow<>();
            row.setOnMouseClicked(mouseEvent -> {
                DepartmentDto dto = row.getItem();
                depIdText.setText(dto.getId().toString());
                depTypeText.setText(dto.getDepartmentType().toString());
            });
            return row;
        });

        publisher.subscribe(observerCDUDepartment());
    }

    public void insertButton() {
        Department department = new Department();
        department.setDepartmentType(DepartmentType.valueOf(depTypeText.getText()));
        departmentService.create(department);
        publisher.onNext(true);
    }
    public void updateButton() {
        Department department = new Department();
        department.setId(Long.valueOf(depIdText.getText()));
        department.setDepartmentType(DepartmentType.valueOf(depTypeText.getText()));
        departmentService.update(department);
        publisher.onNext(true);
    }
    public void deleteButton() {
        departmentService.delete(Long.valueOf(depIdText.getText()));
        publisher.onNext(true);
    }

    private Observer<Boolean> observerCDUDepartment() {
        return new Observer<>() {
            @Override
            public void onSubscribe(@NonNull Disposable disposable) {

            }

            @Override
            public void onNext(@NonNull Boolean aBoolean) {
                if (aBoolean) {
                    dtoObservableList = FXCollections.observableArrayList();
                    dtoObservableList.addAll(findAll());
                    departmentTable.setItems(dtoObservableList);
                }
            }

            @Override
            public void onError(@NonNull Throwable throwable) {

            }

            @Override
            public void onComplete() {

            }
        };
    }

    private Collection<DepartmentDto> findAll() {
        return departmentService.findEmployeeCountByDepartmentType();
    }
}

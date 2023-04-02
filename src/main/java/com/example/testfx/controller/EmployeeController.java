package com.example.testfx.controller;

import com.example.testfx.persistance.entity.Employee;
import com.example.testfx.service.EmployeeService;
import com.example.testfx.service.impl.EmployeeServiceImpl;

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

public class EmployeeController implements Initializable {
    @FXML
    private TextField idEmpText;
    @FXML
    private TextField firstNameText;
    @FXML
    private TextField lastNameText;
    @FXML
    private TextField ageText;
    @FXML
    private TableView<Employee> employeeTable;
    @FXML
    private TableColumn<Employee, Long> idColumn;
    @FXML
    private TableColumn<Employee, String> firstNameColumn;
    @FXML
    private TableColumn<Employee, String> lastNameColumn;
    @FXML
    private TableColumn<Employee, Integer> ageColumn;

    private ObservableList<Employee> employees = FXCollections.observableArrayList();
    private final EmployeeService employeeService = new EmployeeServiceImpl();
    private final BehaviorSubject<Boolean> publisher = BehaviorSubject.createDefault(true);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        employees.addAll(findAll());
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        employeeTable.setItems(employees);

        employeeTable.setRowFactory(tv -> {
            TableRow<Employee> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                Employee rowData = row.getItem();
                idEmpText.setText(rowData.getId().toString());
                firstNameText.setText(rowData.getFirstName());
                lastNameText.setText(rowData.getLastName());
                ageText.setText(String.valueOf(rowData.getAge()));
            });
            return row;
        });

        publisher.subscribe(observerCDUEmployee());
    }

    public void insertButton() {
        Employee employee = new Employee();
        employee.setFirstName(firstNameText.getText());
        employee.setLastName(lastNameText.getText());
        employee.setAge(Integer.parseInt(ageText.getText()));
        employeeService.create(employee);
        publisher.onNext(true);
    }

    public void updateButton() {
        Employee employee = new Employee();
        employee.setId(Long.parseLong(idEmpText.getText()));
        employee.setFirstName(firstNameText.getText());
        employee.setLastName(lastNameText.getText());
        employee.setAge(Integer.parseInt(ageText.getText()));
        employeeService.update(employee);
        publisher.onNext(true);
    }

    public void deleteButton() {
        employeeService.delete(Long.parseLong(idEmpText.getText()));
        publisher.onNext(true);
    }

    private Observer<Boolean> observerCDUEmployee() {
        return new Observer<>() {
            @Override
            public void onSubscribe(@NonNull Disposable disposable) {

            }

            @Override
            public void onNext(@NonNull Boolean aBoolean) {
                if (aBoolean) {
                    employees = FXCollections.observableArrayList();
                    employees.addAll(findAll());
                    employeeTable.setItems(employees);
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

    private Collection<Employee> findAll() {
        return employeeService.findAll();
    }
}

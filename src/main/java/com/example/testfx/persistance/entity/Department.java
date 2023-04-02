package com.example.testfx.persistance.entity;

import com.example.testfx.persistance.type.DepartmentType;

import java.util.Set;

public class Department extends BaseEntity {
    private DepartmentType departmentType;
    private Set<Employee> employees;

    public Department() {
        super();
    }

    public DepartmentType getDepartmentType() {
        return departmentType;
    }

    public void setDepartmentType(DepartmentType departmentType) {
        this.departmentType = departmentType;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id='" + getId() + '\'' +
                "departmentType=" + departmentType +
                "} ";
    }
}

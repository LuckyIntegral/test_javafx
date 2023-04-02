package com.example.testfx.persistance.dto;

import com.example.testfx.persistance.type.DepartmentType;

public class DepartmentDto {
    private Long id;
    private DepartmentType departmentType;
    private int employeeCount;

    public DepartmentDto(Long id, DepartmentType departmentType, int employeeCount) {
        this.id = id;
        this.departmentType = departmentType;
        this.employeeCount = employeeCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DepartmentType getDepartmentType() {
        return departmentType;
    }

    public void setDepartmentType(DepartmentType departmentType) {
        this.departmentType = departmentType;
    }

    public int getEmployeeCount() {
        return employeeCount;
    }

    public void setEmployeeCount(int employeeCount) {
        this.employeeCount = employeeCount;
    }
}

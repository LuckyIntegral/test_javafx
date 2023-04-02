package com.example.testfx.service;

import com.example.testfx.persistance.dto.DepartmentDto;
import com.example.testfx.persistance.entity.Department;
import com.example.testfx.persistance.entity.Employee;

import java.util.Collection;

public interface DepartmentService extends BaseService<Department> {
    Collection<DepartmentDto> findEmployeeCountByDepartmentType();
    void attachEmployeesToDepartment(Department department, Employee employee);
    void deAttachEmployeesToDepartment(Department department, Employee employee);
}

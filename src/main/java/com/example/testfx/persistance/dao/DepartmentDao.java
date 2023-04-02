package com.example.testfx.persistance.dao;

import com.example.testfx.persistance.dto.DepartmentDto;
import com.example.testfx.persistance.entity.Department;
import com.example.testfx.persistance.entity.Employee;

import java.util.Collection;

public interface DepartmentDao extends BaseEntityDao<Department> {
    Collection<DepartmentDto> findEmployeeCountByDepartmentType();
    void attachEmployeesToDepartment(Department department, Employee employee);
    void deAttachEmployeesToDepartment(Department department, Employee employee);
}

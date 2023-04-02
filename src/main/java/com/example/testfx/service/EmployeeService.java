package com.example.testfx.service;

import com.example.testfx.persistance.entity.Employee;

import java.util.Collection;

public interface EmployeeService extends BaseService<Employee> {
    Collection<Employee> findAllByDepartmentId(Long id);
}

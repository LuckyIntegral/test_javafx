package com.example.testfx.persistance.dao;

import com.example.testfx.persistance.entity.Employee;

import java.util.Collection;

public interface EmployeeDao extends BaseEntityDao<Employee> {
    Collection<Employee> findAllByDepartmentId(Long id);
}

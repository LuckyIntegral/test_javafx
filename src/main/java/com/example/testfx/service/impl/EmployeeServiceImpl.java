package com.example.testfx.service.impl;

import com.example.testfx.persistance.dao.EmployeeDao;
import com.example.testfx.persistance.dao.impl.EmployeeDaoImpl;
import com.example.testfx.persistance.entity.Employee;
import com.example.testfx.service.EmployeeService;

import java.util.Collection;

public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeDao employeeDao = new EmployeeDaoImpl();
    @Override
    public void create(Employee entity) {
        employeeDao.create(entity);
    }

    @Override
    public void update(Employee entity) {
        employeeDao.update(entity);
    }

    @Override
    public void delete(Long id) {
        employeeDao.delete(id);
    }

    @Override
    public Employee findById(Long id) {
        return employeeDao.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public Collection<Employee> findAll() {
        return employeeDao.findAll();
    }

    @Override
    public Collection<Employee> findAllByDepartmentId(Long id) {
        return employeeDao.findAllByDepartmentId(id);
    }
}

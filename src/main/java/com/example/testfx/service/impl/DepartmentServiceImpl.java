package com.example.testfx.service.impl;

import com.example.testfx.persistance.dao.DepartmentDao;
import com.example.testfx.persistance.dao.impl.DepartmentDaoImpl;
import com.example.testfx.persistance.dto.DepartmentDto;
import com.example.testfx.persistance.entity.Department;
import com.example.testfx.persistance.entity.Employee;
import com.example.testfx.service.DepartmentService;

import java.util.Collection;

public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentDao departmentDao = new DepartmentDaoImpl();

    @Override
    public void create(Department entity) {
        departmentDao.create(entity);
    }

    @Override
    public void update(Department entity) {
        departmentDao.update(entity);
    }

    @Override
    public void delete(Long id) {
        departmentDao.delete(id);
    }

    @Override
    public Department findById(Long id) {
        return departmentDao.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public Collection<Department> findAll() {
        return departmentDao.findAll();
    }

    @Override
    public Collection<DepartmentDto> findEmployeeCountByDepartmentType() {
        return departmentDao.findEmployeeCountByDepartmentType();
    }

    @Override
    public void attachEmployeesToDepartment(Department department, Employee employee) {
        departmentDao.attachEmployeesToDepartment(department, employee);
    }

    @Override
    public void deAttachEmployeesToDepartment(Department department, Employee employee) {
        departmentDao.deAttachEmployeesToDepartment(department,employee);
    }
}

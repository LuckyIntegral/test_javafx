package com.example.testfx.persistance.dao.impl;

import com.example.testfx.persistance.dao.DepartmentDao;
import com.example.testfx.persistance.dto.DepartmentDto;
import com.example.testfx.persistance.entity.Department;
import com.example.testfx.persistance.entity.Employee;
import com.example.testfx.persistance.jdbc.JdbcService;
import com.example.testfx.persistance.type.DepartmentType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class DepartmentDaoImpl implements DepartmentDao {
    private final JdbcService jdbcService = JdbcService.getInstance();

    private static final String CREATE_DEPARTMENT = "insert into departments values (default, ?)";
    private static final String UPDATE_DEPARTMENT = "update departments set department_type = ? where id = ?";
    private static final String DELETE_DEPARTMENT = "delete from departments where id = ?";
    private static final String FIND_DEPARTMENT_BY_ID = "select * from departments where id = ";
    private static final String FIND_ALL_DEPARTMENTS = "select * from departments";
    private static final String ATTACH_EMPLOYEE_TO_DEPARTMENT = "insert into emp_dep values (?, ?)";
    private static final String REMOVE_EMPLOYEE_TO_DEPARTMENT = "delete from emp_dep where dep_id = ? and emp_id = ?";
    private static final String FIND_EMPLOYEE_COUNT_BY_DEPARTMENT_TYPE = "select d.id, d.department_type, count(dep_id)" +
            " as number from departments as d left join emp_dep as e on d.id = e.dep_id group by d.id";

    @Override
    public void create(Department entity) {
        try (PreparedStatement preparedStatement = jdbcService.getConnection().prepareStatement(CREATE_DEPARTMENT)) {
            preparedStatement.setString(1, entity.getDepartmentType().name());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Department entity) {
        try (PreparedStatement preparedStatement = jdbcService.getConnection().prepareStatement(UPDATE_DEPARTMENT)) {
            preparedStatement.setString(1, entity.toString());
            preparedStatement.setLong(2, entity.getId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement preparedStatement = jdbcService.getConnection().prepareStatement(DELETE_DEPARTMENT)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Department> findById(Long id) {
        try (ResultSet resultSet = jdbcService.getStatement().executeQuery(FIND_DEPARTMENT_BY_ID + id)) {
            if (resultSet.next()) {
                return Optional.of(generateDepartmentByResultSet(resultSet));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Collection<Department> findAll() {
        Collection<Department> departments = new ArrayList<>();
        try (ResultSet resultSet = jdbcService.getStatement().executeQuery(FIND_ALL_DEPARTMENTS)) {
            while (resultSet.next()) {
                departments.add(generateDepartmentByResultSet(resultSet));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return departments;
    }

    @Override
    public Collection<DepartmentDto> findEmployeeCountByDepartmentType() {
        Collection<DepartmentDto> departments = new ArrayList<>();
        try (ResultSet resultSet = jdbcService.getStatement().executeQuery(FIND_EMPLOYEE_COUNT_BY_DEPARTMENT_TYPE)) {
            while (resultSet.next()) {
                departments.add(generateDepartmentDtoByResultSet(resultSet));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return departments;
    }

    @Override
    public void attachEmployeesToDepartment(Department department, Employee employee) {
        try (PreparedStatement preparedStatement = jdbcService.getConnection().prepareStatement(ATTACH_EMPLOYEE_TO_DEPARTMENT)) {
            preparedStatement.setLong(1, department.getId());
            preparedStatement.setLong(2, employee.getId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deAttachEmployeesToDepartment(Department department, Employee employee) {
        try (PreparedStatement preparedStatement = jdbcService.getConnection().prepareStatement(REMOVE_EMPLOYEE_TO_DEPARTMENT)) {
            preparedStatement.setLong(1, department.getId());
            preparedStatement.setLong(2, employee.getId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Department generateDepartmentByResultSet(ResultSet resultSet) throws SQLException {
        Department department = new Department();
        department.setId(resultSet.getLong("id"));
        department.setDepartmentType(DepartmentType.valueOf(resultSet.getString("department_type")));
        return department;
    }

    private DepartmentDto generateDepartmentDtoByResultSet(ResultSet resultSet) throws SQLException {
        Department department = new Department();
        department.setId(resultSet.getLong("id"));
        department.setDepartmentType(DepartmentType.valueOf(resultSet.getString("department_type")));
        int number = resultSet.getInt("number");
        return new DepartmentDto(department.getId(), department.getDepartmentType(), number);
    }
}

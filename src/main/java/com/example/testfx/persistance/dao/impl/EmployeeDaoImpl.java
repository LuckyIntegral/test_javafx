package com.example.testfx.persistance.dao.impl;

import com.example.testfx.persistance.dao.EmployeeDao;
import com.example.testfx.persistance.entity.Employee;
import com.example.testfx.persistance.jdbc.JdbcService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class EmployeeDaoImpl implements EmployeeDao {
    private final JdbcService jdbcService = JdbcService.getInstance();

    private static final String CREATE_EMPLOYEE = "insert into employees values (default, ?, ?, ?)";
    private static final String UPDATE_EMPLOYEE = "update employees set first_name = ?, last_name = ?, age = ? where id = ?";
    private static final String DELETE_EMPLOYEE = "delete from employees where id = ?";
    private static final String FIND_BY_ID = "select * from employees where id = ";
    private static final String FIND_ALL = "select * from employees";
    private static final String FIND_ALL_BY_DEPARTMENT_ID = "select e.id ,e.first_name, e.last_name, e.age " +
            "from employees as e left join emp_dep as ed on e.id = ed.emp_id where dep_id = ";


    @Override
    public void create(Employee entity) {
        try (PreparedStatement preparedStatement = jdbcService.getConnection().prepareStatement(CREATE_EMPLOYEE)) {
            preparedStatement.setString(1, entity.getFirstName());
            preparedStatement.setString(2, entity.getLastName());
            preparedStatement.setInt(3, entity.getAge());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Employee entity) {
        try (PreparedStatement preparedStatement = jdbcService.getConnection().prepareStatement(UPDATE_EMPLOYEE)) {
            preparedStatement.setString(1, entity.getFirstName());
            preparedStatement.setString(2, entity.getLastName());
            preparedStatement.setInt(3, entity.getAge());
            preparedStatement.setLong(4, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement preparedStatement = jdbcService.getConnection().prepareStatement(DELETE_EMPLOYEE)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Employee> findById(Long id) {
        try (ResultSet resultSet = jdbcService.getStatement().executeQuery(FIND_BY_ID + id)) {
            if (resultSet.next()) {
                return Optional.of(generateEmployeeFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Collection<Employee> findAll() {
        Collection<Employee> employees = new ArrayList<>();
        try (ResultSet resultSet = jdbcService.getStatement().executeQuery(FIND_ALL)) {
            while (resultSet.next()) {
                employees.add(generateEmployeeFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    @Override
    public Collection<Employee> findAllByDepartmentId(Long id) {
        Collection<Employee> employees = new ArrayList<>();
        try (ResultSet resultSet = jdbcService.getStatement().executeQuery(FIND_ALL_BY_DEPARTMENT_ID + id)) {
            while (resultSet.next()) {
                employees.add(generateEmployeeFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    private Employee generateEmployeeFromResultSet(ResultSet resultSet) throws SQLException {
        Employee employee = new Employee();
        employee.setId(resultSet.getLong("id"));
        employee.setFirstName(resultSet.getString("first_name"));
        employee.setLastName(resultSet.getString("last_name"));
        employee.setAge(resultSet.getInt("age"));
        return employee;
    }
}

package com.employee.service;

import java.sql.SQLException;
import java.util.List;

import com.employee.exception.UserNotFoundException;
import com.employee.model.Employee;


public interface IEmployeeService {
	
	int createEmployee(Employee user) throws SQLException;
	Employee viewEmployee(Employee employee) throws UserNotFoundException, SQLException;
	int deleteEmployee(Employee emp);
	List<Employee> viewAllEmployees();
	int updateEmployee(Employee employee) throws UserNotFoundException, SQLException;
	public int emailValid(String email) throws SQLException;
	public int login(String email, String password) throws SQLException;

}

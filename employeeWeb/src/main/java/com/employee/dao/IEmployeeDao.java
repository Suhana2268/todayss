package com.employee.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.employee.exception.UserNotFoundException;
import com.employee.model.Employee;

public interface IEmployeeDao {
	
	int createEmployee(Employee user) throws SQLException;
	Employee viewEmployee(Employee employee) throws UserNotFoundException, SQLException;
	int deleteEmployee(Employee emp);
	List<Employee> viewAllEmployees();
	int updateEmployee(Employee employee) throws UserNotFoundException, SQLException;
	public int emailValid(String email) throws SQLException;
	public int login(String email, String password) throws SQLException;
}





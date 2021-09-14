package com.employee.service;

import java.sql.SQLException;
import java.util.List;

import com.employee.dao.IEmployeeDao;
import com.employee.exception.UserNotFoundException;
import com.employee.model.Employee;
import com.employee.utility.DAOFactory;

public class EmployeeService implements IEmployeeService{
	
	IEmployeeDao iDao;
	

	public EmployeeService() {
		
		iDao = DAOFactory.createDaoObject();
		}
		
		public int createEmployee(Employee user) throws SQLException {
			return iDao.createEmployee(user);
		}
		
		public int deleteEmployee(Employee user) {
			return iDao.deleteEmployee(user);
		}
		public Employee viewEmployee(Employee user) throws UserNotFoundException, SQLException{
			Employee employee = null;
			try {
				
			employee = iDao.viewEmployee(user);
			}
			catch (UserNotFoundException e) {
				throw e;
			}
			return employee;
		}
		public List<Employee> viewAllEmployees() {
			return iDao.viewAllEmployees();
		}
		public int updateEmployee(Employee user) throws UserNotFoundException, SQLException {
			System.out.println(user.getEmail()+"Serviceee");
			int i = 0;
			Employee em = null;
			try {
			i = iDao.updateEmployee(user);
			if(i > 0) {
				em = user;
			}
			}
			catch (UserNotFoundException e) {
				// TODO: handle exception
				throw e;
			}
			return i;
			
		}

		public int emailValid(String email) throws SQLException {
			// TODO Auto-generated method stub
			return iDao.emailValid(email);
		}

		public int login(String email, String password) throws SQLException {
			// TODO Auto-generated method stub
			return iDao.login(email, password);
		}

}

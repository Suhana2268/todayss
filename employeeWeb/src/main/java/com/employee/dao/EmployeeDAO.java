package com.employee.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.employee.exception.UserNotFoundException;
import com.employee.model.Employee;



public class EmployeeDAO implements IEmployeeDao{
	
	private static Employee users[];
	static List<Employee> empList = null;
	
	
	Connection con;
	PreparedStatement ps;


	
	static {
		users = new Employee[10];
		empList = new ArrayList();
	}

	public EmployeeDAO() {
		
		con = DatabaseUtil.con;
		
	}
	
	
	
	public int createEmployee(Employee user) throws SQLException{
		
		
		int status = 0;
		
		
		/*for(int i = 0; i < users.length; i++) {
			if(users[i] == null) {
				users[i] = user;
				status = 1;
				break;
			}
		}*/
		
		int empId = user.getEmpId();
		String name = user.getName();
		String email = user.getEmail();
		String password = user.getPassword();
		String department = user.getDepartment();
		double salary = user.getSalary();
		
		
		String query = "insert into employeetable1 values(?,?,?,?,?,?)";
		
		ps = con.prepareStatement(query);
		ps.setInt(1, empId);
		ps.setString(2, name);
		ps.setString(3, password);
		ps.setString(4, email);
		ps.setString(5, department);
		ps.setDouble(6, salary);
		
		int i = ps.executeUpdate();
		if(i == 1) status = 1;
		
		return status;
		
		/*
		int j = empList.size();
		System.out.println(j);
		empList.add(user);
		if(j > 0) {
		status = 1;
		}
		
		
		
		System.out.println("Your profile is created ");
		return status;*/
	}
	
	public Employee viewEmployee(Employee user) throws UserNotFoundException, SQLException{
		
		Employee employee = new Employee();
		boolean status = false;
		
		String query = "select * from employeetable1 where email=?";
	
		ps = con.prepareStatement(query);
		ps.setString(1, user.getEmail());
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			int empId = rs.getInt(1);
			String name = rs.getString(2);
			String password = rs.getString(3);
			String email = rs.getString(4);
			String department = rs.getString(5);
			double salary = rs.getDouble(6);
			
			employee.setEmpId(empId);
			employee.setPassword(password);
			employee.setName(name);
			employee.setEmail(email);
			employee.setDepartment(department);
			employee.setSalary(salary);
			
			
			status = true;
		}
		
		/*for(Employee u: empList) {
			if(u != null) {
			
			if(u.getEmail().equals(user.getEmail())) {
				status = true;
				return u;
			}
			}
		}*/
		
		
		if(status == false) {
			throw new UserNotFoundException("User not found");
		}
		
		return employee;
	}
	
	public List<Employee> viewAllEmployees()
	{
		/*for(Employee u: empList) {
			if(u != null) {
			return u;
		}
		}
		return null;
		return empList;*/
		
		List<Employee> employeeList=new ArrayList<Employee>();
		try {
			PreparedStatement ps=con.prepareStatement("select * from employeetable1");
			
			
			ResultSet res=ps.executeQuery();
			while(res.next()) {
				Employee emp=new Employee();
				emp.setEmpId(res.getInt(1));
				emp.setName(res.getString(2));
				emp.setPassword(res.getString(3));
				emp.setEmail(res.getString(4));
				emp.setDepartment(res.getString(5));
				emp.setSalary(res.getDouble(6));
				
				employeeList.add(emp);
			}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		return employeeList;
	}


	/*public Employee updateEmployee(Employee user) throws UserNotFoundException{
		boolean status = false;
		Employee emp = null;
		for(Employee u: empList) {
			if(u != null) {
			if(u.getEmail().equals(user.getEmail())) {
				Scanner sc = new Scanner(System.in);
				System.out.println("Enter your id");
				Integer empId = sc.nextInt();
				System.out.println("Enter your name");
				String name = sc.next();
				System.out.println("Enter your password");
				String password = sc.next();
				System.out.println("Enter your department");
				String department = sc.next();
				System.out.println("Enter your salary");
				double salary = sc.nextDouble();
		
				u.setEmpId(empId);
				u.setName(name);
				u.setPassword(password);
				u.setEmail(user.getEmail());
				u.setDepartment(department);
				u.setSalary(salary);
				emp = u;
				status = true;
				
			}
			
		}
		}
		if(status == false) {
			throw new UserNotFoundException("User not found");
		}
		return emp;
	
	}
	*/
	public int deleteEmployee(Employee user) {
		int j = 0, i = 0;
		for(Employee u : empList) {
			if(u != null) {
				if(u.getEmail().equals(user.getEmail())) {
					//users[i] = null;
					empList.remove(empList.indexOf(u));
					j = 1;
					break;
				}
			}
		}
		return j;
	}
	
	public int emailValid(String email) throws SQLException {
		String query = "select * from employeetable1 where email=?";
		
		int i = 0;
		ps = con.prepareStatement(query);
		ps.setString(1, email);
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			i = 1;
			
		}
		else {
			i = 0;
		}
		return i;
	}
	
	public int login(String email, String password) throws SQLException {
		String query = "select * from employeetable1 where email=? and password=?";
		
		int i = 0;
		ps = con.prepareStatement(query);
		ps.setString(1, email);
		ps.setString(2, password);
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			i = 1;
			
		}
		else {
			i = 0;
		}
		return i;
		
	}
	
	public int updateEmployee(Employee e) throws SQLException {

		String query = "update employeetable1 set name =?,password = ?, department =?, salary=? where email =?";
		
		ps = con.prepareStatement(query);
		ps.setString(1, e.getName());
		ps.setString(2, e.getPassword());
		ps.setString(3, e.getDepartment());
		ps.setDouble(4, e.getSalary());
		ps.setString(5, e.getEmail());
		
		
		
		int i = 0;
			i=	ps.executeUpdate();
		
		
		return i;
		
	}

}

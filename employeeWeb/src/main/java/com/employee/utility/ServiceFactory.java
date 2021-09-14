package com.employee.utility;


import com.employee.service.EmployeeService;
import com.employee.service.IEmployeeService;

public class ServiceFactory {
	
	private ServiceFactory() {
		
	}

	public static IEmployeeService createServiceObject() {
		return new EmployeeService();
			
			
		}
}

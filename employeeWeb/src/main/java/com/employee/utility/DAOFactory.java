package com.employee.utility;

import com.employee.dao.EmployeeDAO;
import com.employee.dao.IEmployeeDao;

public class DAOFactory {
	
	private DAOFactory() {
		// TODO Auto-generated constructor stub
	}

	public static IEmployeeDao createDaoObject() {
		return new EmployeeDAO();
	}

}

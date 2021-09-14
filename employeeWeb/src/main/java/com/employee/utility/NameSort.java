package com.employee.utility;

import java.util.Comparator;

import com.employee.model.Employee;

public class NameSort implements Comparator<Employee>{

	public int compare(Employee o1, Employee o2) {
		// TODO Auto-generated method stub
		return o1.getName().compareTo(o2.getName());
		
	}
	
}


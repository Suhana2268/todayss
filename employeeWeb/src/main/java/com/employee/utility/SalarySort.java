package com.employee.utility;

import java.util.Comparator;

import com.employee.model.Employee;

public class SalarySort implements Comparator<Employee>{

	public int compare(Employee o1, Employee o2) {
		// TODO Auto-generated method stub
		return (int) (o1.getSalary()-o2.getSalary());
	}

	

}

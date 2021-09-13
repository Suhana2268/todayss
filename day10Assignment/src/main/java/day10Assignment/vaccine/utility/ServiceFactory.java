package day10Assignment.vaccine.utility;

import day10Assignment.vaccine.service.IVaccineService;
import day10Assignment.vaccine.service.VaccineService;

public class ServiceFactory {

	public ServiceFactory() {
		// TODO Auto-generated constructor stub
	}

	public static IVaccineService createServiceObject() {
		// TODO Auto-generated method stub
		return new VaccineService();
	}
	
	
	
}

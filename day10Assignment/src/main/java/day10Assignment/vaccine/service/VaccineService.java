package day10Assignment.vaccine.service;

import org.json.simple.JSONObject;

public class VaccineService implements IVaccineService{

	public JSONObject printJsonData(String name) {
		// TODO Auto-generated method stub
		JSONObject jsonObj = new JSONObject();
		System.out.println(name+"Inside service");
		if(name.equals("Covishield")) {
			jsonObj.put("name", "Covishield");
			jsonObj.put("secondDose", "84 days");
			jsonObj.put("sideEffects", "fever, headache");
			jsonObj.put("precautions", "Paracetamol for fever, consult doctor");
			
		}
		System.out.println(name+"Inside service");
		if(name.equals("Covaxin")) {
			System.out.println(name+"Inside service");
			jsonObj.put("name", "Covaxin");
			jsonObj.put("secondDose", "24 days");
			jsonObj.put("sideEffects", "fever, headache");
			jsonObj.put("precautions", "Paracetamol for fever, consult doctor");
		}
		
		return jsonObj;
	}

}

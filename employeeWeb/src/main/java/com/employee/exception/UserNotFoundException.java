package com.employee.exception;

/**
 * @author suhan
 *
 */
public class UserNotFoundException extends Exception{
	
	
	private String excMsg = "";

	public UserNotFoundException(String excMsg) {
		super();
		this.excMsg = excMsg;
	}

	@Override
	public String toString() {
		return "UserNotFoundException [excMsg=" + excMsg + "]";
	}
	
	
	

}

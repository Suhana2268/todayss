package com.employee.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.employee.model.Employee;
import com.employee.service.IEmployeeService;
import com.employee.utility.ServiceFactory;

/**
 * Servlet implementation class ControllerServlet
 */
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("eid");
		String name=request.getParameter("nm");
		String password=request.getParameter("pass");
		String email=request.getParameter("em");
		String department=request.getParameter("dep");
		String salay = request.getParameter("sal");
		
		IEmployeeService employeeService;
		
		employeeService = ServiceFactory.createServiceObject();
		
		double salary = Double.parseDouble(salay);
		int empId = Integer.parseInt(id);
		Employee user = new Employee();
		user.setName(name);
		user.setPassword(password);
		user.setEmail(email);
		user.setDepartment(department);
		user.setSalary(salary);
		user.setEmpId(empId);
		
		int i = 0;
		try {
			i = employeeService.createEmployee(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		out.println("<html><body>");
		if(i>0) {
			out.println("profile created");
			out.println(user);
		}
		else {
			out.println("could not create profile");
		}
		
		
		out.println("</body></html>");

	}
			
}

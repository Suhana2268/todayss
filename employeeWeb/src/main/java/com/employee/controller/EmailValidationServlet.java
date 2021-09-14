package com.employee.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.employee.service.IEmployeeService;
import com.employee.utility.ServiceFactory;

/**
 * Servlet implementation class EmailValidationSErvlet
 */
public class EmailValidationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmailValidationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String em = request.getParameter("v2");
		
		IEmployeeService employeeService = ServiceFactory.createServiceObject();
		int i =0;
		try {
			i = employeeService.emailValid(em);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		out.println("<html><body>");
		if(i > 0) {
			out.println("<font color=red>email already exist on server</font>");
		}
		else {
			out.println("<font color=green>valid email for "+em+"</font>");
		}
		
		out.println("</body></html>");

	}

}

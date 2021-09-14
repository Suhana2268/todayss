package com.employee.controller;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.employee.exception.UserNotFoundException;
import com.employee.model.Employee;
import com.employee.service.IEmployeeService;
import com.employee.utility.ServiceFactory;
import com.google.gson.Gson;

/**
 * Servlet implementation class GlobalServlet
 */
public class GlobalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	 IEmployeeService employeeService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GlobalServlet() {
        super();
        // TODO Auto-generated constructor stub
       
		
		employeeService = ServiceFactory.createServiceObject();
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String a = request.getParameter("ac");
		PrintWriter out=response.getWriter();
		
		if(a.equals("register")) {
			String id = request.getParameter("eid");
			String name=request.getParameter("nm");
			String password=request.getParameter("pass");
			String email=request.getParameter("em");
			String department=request.getParameter("dep");
			String salay = request.getParameter("sal");
			
		
			
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
			
			
			out.println("<html><body>");
			if(i>0) {
				request.setAttribute("message", "profile created <a href=login.jsp>Sign In</a>");
				RequestDispatcher rd=getServletContext().getRequestDispatcher("/Register.jsp");
				rd.forward(request, response);
			}
			else {
				out.println("could not create profile");
			}

		}
		
		if(a.equals("login")) {
			String email=request.getParameter("em");
			String password=request.getParameter("ps");
			
			int i = 0;
			
			try {
				i = employeeService.login(email, password);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.setContentType("text/html");
			
			
			if(i>0) {
				HttpSession ss=request.getSession(true);
				ss.setAttribute("id", email);
				
				RequestDispatcher rd=getServletContext().getRequestDispatcher("/welcome.jsp");
				rd.forward(request, response);
			}
			else {
				out.println("Wrong credentials");
				RequestDispatcher rd=getServletContext().getRequestDispatcher("/login.jsp");
				rd.include(request, response);

			}
			

		}
		
		if(a.equals("viewEmployee")) {
			
			HttpSession ss = request.getSession(true);
			String email = ss.getAttribute("id").toString();
			Employee emp = new Employee();
			emp.setEmail(email);

			Employee emp1 = null;
			try {
				emp1 = employeeService.viewEmployee(emp);
			} catch (UserNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(emp1 != null) {
				request.setAttribute("asd", emp1);
				
				RequestDispatcher rd=getServletContext().getRequestDispatcher("/welcome.jsp");
				rd.forward(request, response);

			}
			else {
				request.setAttribute("asd", "profile not found");
				RequestDispatcher rd=getServletContext().getRequestDispatcher("/welcome.jsp");
				rd.include(request, response);
			}
		}
		
		
		if(a.equals("editEmployee")) {
			String email=request.getParameter("uid");
			
			Employee empUser=new Employee();
			
			empUser.setEmail(email);
			
			
			Employee empU = null;
			try {
				empU = employeeService.viewEmployee(empUser);
			} catch (UserNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
			Gson g=new Gson();
			String ss=g.toJson(empU);
			
			System.out.println("myjson "+ss);
			
			if(empU!=null) {
				out.println(ss);
				out.println("<form action=GlobalServlet?ac=usereditprofile method=post>");
				out.println("Id <input type=text name=empI id = eid value="+empU.getEmpId()+">");
					out.println("Name <input type=text name=nm value="+empU.getName()+">");
					out.println("Password <input type=text name=pass value="+empU.getPassword()+">");
					out.println("Email <input type=text name=em value="+empU.getEmail()+" disabled>");
					out.println("<input type=hidden name=em1 value="+empU.getEmail()+" >");
					out.println("Department <input type=text name=depart value="+empU.getDepartment()+">");
					out.println("Salary <input type=text name=sal value="+empU.getSalary()+">");
					out.println(" <input type=submit value=edit>");
				out.println("</form>");
				
				
			}
			else {
				out.println("record not found");
			}
		}
		
		if(a.equals("usereditprofile")) {
			
			System.out.println("in");
				String empid = request.getParameter("empI");
				String name=request.getParameter("nm");
				String password=request.getParameter("pass");
				String email=request.getParameter("em1");
				String department=request.getParameter("depart");
				String salay = request.getParameter("sal");
				
			
				
				double salary = Double.parseDouble(salay);
				int empId = Integer.parseInt(empid);
				
				System.out.println(name+password+email+department+salay);
				Employee user = new Employee();
				user.setName(name);
				user.setPassword(password);
				user.setEmail(email);
				user.setDepartment(department);
				user.setSalary(salary);
				user.setEmpId(empId);
			
					
			
			int i = 0;
			try {
				i = employeeService.updateEmployee(user);
			} catch (UserNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(i);
			
			if(i>0) {
				
				out.println("profile edited");
			}
			

			
		}
		
		if(a.equals("displayAllProfile")) {
			List<Employee> empList = employeeService.viewAllEmployees();
			
			System.out.println(empList.size());
			if(empList.size() > 0) {
				request.setAttribute("data", empList);
				
				RequestDispatcher rd=getServletContext().getRequestDispatcher("/timeline.jsp");
				rd.forward(request, response);
			}
			out.println("</body></html>");	
		}
		
		
		}

}

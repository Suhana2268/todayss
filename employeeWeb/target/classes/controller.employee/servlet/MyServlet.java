package employee.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.employee.model.Employee;

/**
 * Servlet implementation class MyServlet
 */
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = request.getParameter("id");
		String name=request.getParameter("nm");
		String password=request.getParameter("pass");
		String email=request.getParameter("em");
		String department=request.getParameter("address");
		double salary = request.getParameter("dep");
		
		
		
		
		Employee user = new Employee();
		user.setName(name);
		user.setPassword(password);
		user.setEmail(email);
		user.setDepartment(department);
		user.setSalary(salary);
		user.setEmpId(empId);
		
		int i = employeeService.createEmployee(user);
		
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		out.println("<html><body>");
		if(i>0) {
			out.println("profile created");
		}
		else {
			out.println("could not create profile");
		}
		
		//	out.println("your detail is below-->");
			//out.println(name+"  "+password+"  "+email+"  "+address);
		out.println("</body></html>");
	}


}

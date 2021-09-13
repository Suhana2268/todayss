package day10Assignment.vaccine.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import day10Assignment.vaccine.service.IVaccineService;
import day10Assignment.vaccine.service.VaccineService;
import day10Assignment.vaccine.utility.ServiceFactory;

/**
 * Servlet implementation class GlobalServlet
 */
public class GlobalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	VaccineService vaccineService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GlobalServlet() {
        super();
        // TODO Auto-generated constructor stub
        vaccineService = new VaccineService();
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String v1 = request.getParameter("ac");
		JSONObject jsonObj = vaccineService.printJsonData(v1);
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		out.println(jsonObj);
		
		
		
	}

}

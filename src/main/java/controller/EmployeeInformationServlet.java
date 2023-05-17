package controller;

import java.io.IOException;
import java.util.ArrayList;
import dao.EmployeeDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Employee;

/**
 * Servlet implementation class EmployeeServlet
 */
@WebServlet("/information")
public class EmployeeInformationServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	EmployeeDao employeeDao = new EmployeeDao();

    /**
     * Default constructor. 
     */
    public EmployeeInformationServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/employeeinformation.jsp");
		
        String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		ArrayList<Employee> employees = null;
		
		try {
			employees = employeeDao.listEmployees();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for(Employee employee : employees) {
			if ((username.equals(employee.getUsername())) && (password.equals(employee.getPassword()))) {
				request.setAttribute("username", employee.getUsername());
				request.setAttribute("password", employee.getPassword());
				request.setAttribute("contact", employee.getContact());
				request.setAttribute("address", employee.getAddress());
				request.setAttribute("firstname", employee.getFirstName());
				request.setAttribute("lastname", employee.getLastName());
			}
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/employeeinformation.jsp");
		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        
    }

}

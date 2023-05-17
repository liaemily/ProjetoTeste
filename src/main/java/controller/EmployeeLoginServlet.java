package controller;

import java.io.IOException;

import dao.EmployeeDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EmployeeServlet
 */
@WebServlet("/login")
public class EmployeeLoginServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	EmployeeDao employeeDao = new EmployeeDao();

    /**
     * Default constructor. 
     */
    public EmployeeLoginServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/employeelogin.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        boolean login = false;
         
        try {
            login = employeeDao.loginEmployee(username, password);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        if (login) {
     
        	request.setAttribute("username", username);
			request.setAttribute("password", password);
        	
        	EmployeeInformationServlet information = new EmployeeInformationServlet();
        	information.doGet(request, response);
        }
  
        
    }

}

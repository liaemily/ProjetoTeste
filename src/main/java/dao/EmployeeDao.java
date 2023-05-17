package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Employee;


public class EmployeeDao {
	
	 public int registerEmployee(Employee employee) throws ClassNotFoundException {
	        String INSERT_USERS_SQL = "INSERT INTO employee" +
	            "  (first_name, last_name, username, password, address, contact) VALUES " +
	            " (?, ?, ?, ?,?,?);";

	        int result = 0;

	        Class.forName("com.mysql.jdbc.Driver");

	        try (Connection connection = DriverManager
	            .getConnection("jdbc:mysql://127.0.0.1:3306/employees", "root", "12345lia");

	            // Step 2:Create a statement using connection object
	            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
	            //preparedStatement.setInt(1, 1);
	            preparedStatement.setString(1, employee.getFirstName());
	            preparedStatement.setString(2, employee.getLastName());
	            preparedStatement.setString(3, employee.getUsername());
	            preparedStatement.setString(4, employee.getPassword());
	            preparedStatement.setString(5, employee.getAddress());
	            preparedStatement.setString(6, employee.getContact());

	            System.out.println(preparedStatement);
	            // Step 3: Execute the query or update query
	            result = preparedStatement.executeUpdate();

	        } catch (SQLException e) {
	            // process sql exception
	            e.printStackTrace();
	        }
	        return result;
	    }
	 
	 public boolean loginEmployee(String username, String password) throws ClassNotFoundException{
		 
		 boolean login = false;
		 
		 String SELECT_USER_SQL = "SELECT first_name, last_name, username, password, "
		 		+ "address, contact FROM employee WHERE username = ? and password = ?";
	
		 ResultSet rset = null;
		 
	        Class.forName("com.mysql.jdbc.Driver");

	        try (Connection connection = DriverManager
	            .getConnection("jdbc:mysql://127.0.0.1:3306/employees", "root", "12345lia");
	        
	        	PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_SQL)){
	            
	            preparedStatement.setString(1, username);
	            preparedStatement.setString(2, password);
	            
	            // Step 3: Execute the query or update query
	            rset = preparedStatement.executeQuery();
	            
	            if(rset.next()) {
	            	login = true;
	            }
            	
	        } catch (SQLException e) {
	            // process sql exception
	            e.printStackTrace();
	        }
		 
		return login;
	 }
	 
	 public ArrayList<Employee> listEmployees() throws ClassNotFoundException{
		 		 
		 String SELECT_USERS_SQL = "SELECT * FROM employee";
		 
		 Class.forName("com.mysql.jdbc.Driver");
		 
		 ArrayList<Employee> employees = new ArrayList<>();
		 
		 ResultSet rset = null;
		
	        try (Connection connection = DriverManager
	            .getConnection("jdbc:mysql://127.0.0.1:3306/employees", "root", "12345lia");
	        
	        	PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USERS_SQL)){
	            System.out.println(preparedStatement);
	            // Step 3: Execute the query or update query
	            rset = preparedStatement.executeQuery();
	            
	            while (rset.next()) {
	            	Employee employee = new Employee();
	            	
	            	employee.setFirstName(rset.getString("first_name"));
	            	employee.setLastName(rset.getString("last_name"));
	            	employee.setUsername(rset.getString("username"));
	            	employee.setPassword(rset.getString("password"));
	            	employee.setAddress(rset.getString("address"));
	            	employee.setContact(rset.getString("contact"));
	            	
	            	employees.add(employee);
	            }

	        } catch (SQLException e) {
	            // process sql exception
	            e.printStackTrace();
	        }
		 
		 return employees;
	 }

}

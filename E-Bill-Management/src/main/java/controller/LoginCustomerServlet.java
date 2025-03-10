package controller;

import dao.CustomerDAO;
import dao.DBConnection;
import model.Customer;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;

@WebServlet("/LoginCustomer")
public class LoginCustomerServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private CustomerDAO customerDAO;

    @Override
    public void init() throws ServletException {
        try {
            Connection conn = DBConnection.getConnection(); 
            customerDAO = new CustomerDAO(conn);
        } catch (Exception e) {
            throw new ServletException("Database connection error", e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");

        try {
            Customer customer = customerDAO.validateUser(userId, password);
            
            if (customer != null) {
                if ("active".equalsIgnoreCase(customer.getCustomerStatus())) {
                    // Start session and redirect to home page
                    HttpSession session = request.getSession();
                    session.setAttribute("customer", customer);
                    if ("Admin".equalsIgnoreCase(customer.getUserType())) {
                        response.sendRedirect("adminDashboard.jsp");
                    } else { 
                    	response.sendRedirect("home.jsp");
                    }
                    
                } else {
                    response.sendRedirect("login.jsp?message=Your account is inactive. Please contact support.");
                }
            } else {
                response.sendRedirect("login.jsp?message=Invalid User ID or Password.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("login.jsp?message=Something went wrong! Please try again.");
        }
    }
}

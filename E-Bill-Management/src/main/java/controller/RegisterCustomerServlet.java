package controller;

import dao.CustomerDAO;
import model.Customer;
import dao.DBConnection; 
import java.io.IOException;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/registerCustomer")
public class RegisterCustomerServlet extends HttpServlet {
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
        try {
            // Get form data
            //String billNumber = request.getParameter("billNumber");
            String title = request.getParameter("title");
            String customerName = request.getParameter("customerName");
            String email = request.getParameter("email");
            String mobileNumber = request.getParameter("mobileNumber");
            String userId = request.getParameter("userId");
            String password = request.getParameter("password");
            String confirmPassword = request.getParameter("confirmPassword");

            // Validate password match
            if (!password.equals(confirmPassword)) {
                request.setAttribute("error", "Passwords do not match!");
                request.getRequestDispatcher("register.jsp").forward(request, response);
                return;
            }

            // Check if email or user ID already exists
            if (customerDAO.isEmailOrUserIdExists(email, userId)) {
                request.setAttribute("error", "Email or User ID already exists!");
                request.getRequestDispatcher("register.jsp").forward(request, response);
                return;
            }

            // Generate unique customer ID
            long customerId = CustomerDAO.generateCustomerId();

            // Create Customer object
            Customer customer = new Customer(customerId, title, customerName, email, mobileNumber, userId, password);

            // Register customer
            boolean isRegistered = customerDAO.registerCustomer(customer);

            if (isRegistered) {
                // Pass customer details to the acknowledgment page
                request.setAttribute("registeredCustomer", customer);
                request.getRequestDispatcher("registerAcknowledgement.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Registration failed! Try again.");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "An error occurred during registration!");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}

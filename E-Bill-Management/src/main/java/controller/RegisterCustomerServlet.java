package controller;

import dao.CustomerDAO;
import model.Customer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/registerCustomer")
public class RegisterCustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int billNumber = Integer.parseInt(request.getParameter("billNumber"));
            String title = request.getParameter("title");
            String customerName = request.getParameter("customerName");
            String email = request.getParameter("email");
            String mobileNumber = request.getParameter("mobileNumber");
            String userId = request.getParameter("userId");
            String password = request.getParameter("password");
            String confirmPassword = request.getParameter("confirmPassword");
            
            // Generate a random Consumer ID
            int consumerId = (int) (1000000000000L + Math.random() * 9000000000000L);
            // Check password confirmation
            if (!password.equals(confirmPassword)) {
                request.setAttribute("error", "Passwords do not match!");
                request.getRequestDispatcher("register.jsp").forward(request, response);
                return;
            }

            // Create Customer object
            Customer customer = new Customer(consumerId, billNumber, title, customerName, email, mobileNumber, userId, password, confirmPassword,"Active");

            // Save customer in DB
            CustomerDAO customerDAO = new CustomerDAO();
            boolean isRegistered = customerDAO.registerCustomer(customer);

            if (isRegistered) {
                // Pass customer details to the JSP page
                request.setAttribute("registeredCustomer", customer);
                request.getRequestDispatcher("registerAcknowledgement.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Registration failed! Try again.");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Invalid input!");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }

}



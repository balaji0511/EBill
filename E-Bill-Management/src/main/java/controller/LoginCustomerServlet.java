package controller;

import dao.CustomerDAO;
import model.Customer;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/LoginCustomerServlet")
public class LoginCustomerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");

        CustomerDAO dao = new CustomerDAO();
        Customer customer = dao.validateUser(userId, password);

        if (customer != null) {
            if ("active".equalsIgnoreCase(customer.getCustomerStatus())) {
                // Start session and redirect to home page
                HttpSession session = request.getSession();
                session.setAttribute("customer", customer);
                response.sendRedirect("home.jsp");
            } else {
                response.sendRedirect("login.jsp?message=Your account is inactive. Please contact support.");
            }
        } else {
            response.sendRedirect("login.jsp?message=Invalid User ID or Password.");
        }
    }
}
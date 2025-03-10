package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DBConnection;
import model.Customer;

@WebServlet("/UpdateCustomerServlet")
public class UpdateCustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("customer") == null) {
            response.sendRedirect("login.jsp?message=Please login first.");
            return;
        }

        // Retrieve customer session
        Customer customer = (Customer) session.getAttribute("customer");
        String userId = customer.getUserId();
        

        // Get updated values from form
        String title = request.getParameter("title");
        String customerName = request.getParameter("customerName");
        String mobileNumber = request.getParameter("mobileNumber");
        String email = request.getParameter("email");

        try (Connection conn = DBConnection.getConnection()) {
            String sql = "UPDATE Customer SET title = ?, customerName = ?, mobileNumber = ?, email = ? WHERE userId = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, title);
                stmt.setString(2, customerName);
                stmt.setString(3, mobileNumber);
                stmt.setString(4, email);
                stmt.setString(5, userId);

                int rowsUpdated = stmt.executeUpdate();

                if (rowsUpdated > 0) {
                    // Update session data with new values
                    customer.setTitle(title);
                    customer.setCustomerName(customerName);
                    customer.setMobileNumber(mobileNumber);
                    session.setAttribute("customer", customer);

                    response.sendRedirect("ViewCustomerServlet?success=Profile updated successfully.");
                } else {
                    response.sendRedirect("updateCustomer.jsp?error=Update failed.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("updateCustomer.jsp?error=Database error.");
        }
    }
}

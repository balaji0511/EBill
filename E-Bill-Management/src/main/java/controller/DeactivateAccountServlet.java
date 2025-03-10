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

@WebServlet("/DeactivateAccountServlet")
public class DeactivateAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("customer") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        Customer customer = (Customer) session.getAttribute("customer");
        String userId = customer.getUserId();
        long consumerNumber = customer.getConsumerNumber(); // Assuming getter exists

        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false); // Start transaction

            // Update customer table
            String updateCustomerSQL = "UPDATE customer SET customerStatus = 'Inactive' WHERE consumerNumber = ?";
            PreparedStatement customerStmt = conn.prepareStatement(updateCustomerSQL);
            customerStmt.setLong(1, consumerNumber);
            int customerRows = customerStmt.executeUpdate();
            customerStmt.close();

            // Update login table
            String updateLoginSQL = "UPDATE login SET userStatus = 'Inactive' WHERE consumerNumber = ?";
            PreparedStatement loginStmt = conn.prepareStatement(updateLoginSQL);
            loginStmt.setLong(1, consumerNumber);
            int loginRows = loginStmt.executeUpdate();
            loginStmt.close();

            if (customerRows > 0 && loginRows > 0) {
                conn.commit(); // Commit transaction
                session.invalidate(); // Logout user after deactivation
                response.sendRedirect("login.jsp?message=Your account has been deactivated.");
            } else {
                conn.rollback(); // Rollback if update fails
                response.sendRedirect("viewCustomer.jsp?error=Failed to deactivate account.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("viewCustomer.jsp?error=Database error occurred.");
        }
    }
}

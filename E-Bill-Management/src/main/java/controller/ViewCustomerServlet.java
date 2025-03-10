package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DBConnection;
import model.Customer;

@WebServlet("/ViewCustomerServlet")
public class ViewCustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("customer") == null) {
            response.sendRedirect("login.jsp?message=Please login first.");
            return;
        }

        Customer customer = (Customer) session.getAttribute("customer");
        String userId = customer.getUserId();

        // Retrieve success message if available
        String successMessage = request.getParameter("message");

        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT title, customerName, mobileNumber, email, customerStatus FROM Customer WHERE userId = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, userId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                request.setAttribute("title", rs.getString("title"));
                request.setAttribute("customerName", rs.getString("customerName"));
                request.setAttribute("email", rs.getString("email"));
                request.setAttribute("mobileNumber", rs.getString("mobileNumber"));
                request.setAttribute("userId", userId);
                request.setAttribute("customerStatus", rs.getString("customerStatus"));

                if (successMessage != null) {
                    request.setAttribute("message", successMessage);
                }

                request.getRequestDispatcher("viewCustomer.jsp").forward(request, response);
            } else {
                response.sendRedirect("dashboard.jsp?error=No customer found");
            }

            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("dashboard.jsp?error=Database error");
        }
    }
}

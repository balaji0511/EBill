package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.DBConnection;

@WebServlet("/RestoreAccountServlet")
public class RestoreAccountServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userId = request.getParameter("userId");

        try (Connection conn = DBConnection.getConnection()) {
            String sql = "UPDATE Login SET userStatus = 'Active' WHERE userId = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, userId);
            int rows = stmt.executeUpdate();
            stmt.close();

            if (rows > 0) {
                response.sendRedirect("login.jsp?message=Account restored. Please log in.");
            } else {
                response.sendRedirect("login.jsp?message=Error restoring account.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("login.jsp?message=Database error.");
        }
    }
}

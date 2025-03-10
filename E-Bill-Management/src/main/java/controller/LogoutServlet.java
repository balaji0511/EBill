package controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CustomerDAO;
import dao.DBConnection;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
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
        HttpSession session = request.getSession(false); // Get the current session, do not create a new one
        if (session != null) {
            session.invalidate(); // Destroy session
        }
        response.sendRedirect("login.jsp"); // Redirect to login page
    }
}

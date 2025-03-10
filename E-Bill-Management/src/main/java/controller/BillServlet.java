package controller;

import dao.BillDAO;
import dao.DBConnection;
import model.Bill;
import model.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/PayBill")
public class BillServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BillDAO billDAO;

    @Override
    public void init() throws ServletException {
        try {
            Connection conn = DBConnection.getConnection();
            billDAO = new BillDAO(conn);
        } catch (Exception e) {
            throw new ServletException("Database connection error", e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// Prevent caching
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); 
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        
        // Retrieve session and check if user is logged in
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("customer") == null) {
            response.sendRedirect("login.jsp?message=Please login first.");
            return;
        }

        Customer customer = (Customer) session.getAttribute("customer");
        // Fetch bills for the logged-in customer
        List<Bill> bills = billDAO.getBillsByConsumerNumber(customer.getConsumerNumber());
       
        request.setAttribute("bills", bills);
        request.getRequestDispatcher("pay_bill.jsp").forward(request, response);
    }
}

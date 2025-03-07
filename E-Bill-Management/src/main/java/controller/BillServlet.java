package controller;

import dao.BillDAO;
import model.Bill;
import model.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/BillServlet")
public class BillServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        if (customer == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Fetch bills for the logged-in customer
        List<Bill> bills = BillDAO.getBillsByConsumerId(customer.getConsumerId());

        request.setAttribute("bills", bills);
        
        // Forward to JSP
        request.getRequestDispatcher("pay_bill.jsp").forward(request, response);
    }
}

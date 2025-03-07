package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BillDAO;
import model.Bill;
import model.Customer;

@WebServlet("/BillPaymentServlet")
public class BillPaymentServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        if (customer == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        List<Bill> selectedBills = BillDAO.getUnpaidBills(customer.getConsumerId()); 
        double totalAmount = BillDAO.calculateTotal(selectedBills);

        request.setAttribute("selectedBills", selectedBills);
        request.setAttribute("totalAmount", totalAmount);
        request.getRequestDispatcher("bill_payment.jsp").forward(request, response);
    }
}

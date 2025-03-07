package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BillDAO;
import dao.DBConnection;
import model.Bill;

@WebServlet("/PaymentServlet")
public class PaymentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] selectedBillIds = request.getParameterValues("selectedBills");
        String totalAmount = request.getParameter("totalAmount");

        if (selectedBillIds == null || selectedBillIds.length == 0) {
            request.setAttribute("errorMessage", "Please select at least one bill to proceed.");
            request.getRequestDispatcher("pay_bill.jsp").forward(request, response);
            return;
        }

        double total = Double.parseDouble(totalAmount);
        List<Bill> bills = BillDAO.getBillsByIds(selectedBillIds);

        boolean isUpdated = BillDAO.updateBillStatusToPaid(selectedBillIds);

        if (isUpdated) {
            request.setAttribute("selectedBills", bills);
            request.setAttribute("totalAmount", total);
            request.setAttribute("successMessage", "Payment successful for ₹" + totalAmount);
            request.getRequestDispatcher("bill_payment.jsp").forward(request, response);
        } else {
            request.setAttribute("errorMessage", "Error processing payment. Some bills may already be paid.");
            request.getRequestDispatcher("pay_bill.jsp").forward(request, response);
        }
    }
}

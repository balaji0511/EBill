package controller;

import dao.BillDAO;
import dao.DBConnection;
import model.Bill;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/PaymentServlet")
public class PaymentServlet extends HttpServlet {
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] selectedBillIds = request.getParameterValues("selectedBills");
        String totalAmountStr = request.getParameter("totalAmount");

        if (selectedBillIds == null || selectedBillIds.length == 0) {
            request.setAttribute("errorMessage", "Please select at least one bill to proceed.");
            request.getRequestDispatcher("pay_bill.jsp").forward(request, response);
            return;
        }

        double totalAmount = 0;
        try {
            totalAmount = Double.parseDouble(totalAmountStr);
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid total amount.");
            request.getRequestDispatcher("pay_bill.jsp").forward(request, response);
            return;
        }

        // Retrieve selected bill details
        List<Bill> selectedBills = new ArrayList<>();
        for (String idStr : selectedBillIds) {
            try {
                int billId = Integer.parseInt(idStr);
                Bill bill = billDAO.getBillById(billId);
                if (bill != null) {
                    selectedBills.add(bill);
                }
            } catch (NumberFormatException e) {
                // Skip invalid bill id
            }
        }

        // Update the status of selected bills to 'Paid'
        boolean allUpdated = true;
        for (String idStr : selectedBillIds) {
            try {
                int billId = Integer.parseInt(idStr);
                boolean updated = billDAO.markBillAsPaid(billId);
                if (!updated) {
                    allUpdated = false;
                    break;
                }
            } catch (NumberFormatException e) {
                allUpdated = false;
                break;
            }
        }

        if (allUpdated) {
            request.setAttribute("selectedBills", selectedBills);
            request.setAttribute("totalAmount", totalAmount);
            request.setAttribute("successMessage", "Payment successful for ₹" + totalAmountStr);
            request.getRequestDispatcher("bill_payment.jsp").forward(request, response);
        } else {
            request.setAttribute("errorMessage", "Error processing payment. Some bills may already be paid.");
            request.getRequestDispatcher("pay_bill.jsp").forward(request, response);
        }
    }
}

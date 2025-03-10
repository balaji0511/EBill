package controller;

import dao.DBConnection;
import dao.PaymentDAO;
import dao.BillDAO;
import model.Customer;
import model.Payment;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;

@WebServlet("/CardPayment")
public class CardPaymentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private PaymentDAO paymentDAO;
    private BillDAO billDAO;

    @Override
    public void init() throws ServletException {
        try {
            Connection conn = DBConnection.getConnection();
            paymentDAO = new PaymentDAO(conn);
            billDAO = new BillDAO(conn);
        } catch (Exception e) {
            throw new ServletException("Database connection error", e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Prevent caching
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        // Retrieve card details from request
        String cardNumber = request.getParameter("cardNumber");
        String cardHolder = request.getParameter("cardHolder");
        String expiryMonth = request.getParameter("expiryDateMonth");
        String expiryYear = request.getParameter("expiryDateYear");
        String cvv = request.getParameter("cvv");
        String paymentMode = request.getParameter("paymentMode");
        String totalPayableStr = request.getParameter("totalPayable");

        double totalPayable = 0;
        try {
            totalPayable = Double.parseDouble(totalPayableStr);
        } catch(NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid payment amount.");
            request.getRequestDispatcher("card_payment.jsp").forward(request, response);
            return;
        }

        // Ensure the customer is logged in
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("customer") == null) {
            response.sendRedirect("login.jsp?message=Please log in first.");
            return;
        }
        Customer customer = (Customer) session.getAttribute("customer");

        // Simulate payment authorization (replace with real integration if available)
        boolean paymentAuthorized = simulatePaymentAuthorization(cardNumber, totalPayable);

        if (!paymentAuthorized) {
            request.setAttribute("errorMessage", "Payment authorization failed. Please try again.");
            request.getRequestDispatcher("card_payment.jsp").forward(request, response);
            return;
        }

        // Create Payment object and record payment details in the database
        Payment payment = new Payment();
        payment.setConsumerNumber(customer.getConsumerNumber());
        payment.setCardNumber(cardNumber);
        payment.setCardHolder(cardHolder);
        payment.setExpiryMonth(expiryMonth);
        payment.setExpiryYear(expiryYear);
        payment.setCvv(cvv);
        payment.setTotalAmount(totalPayable);
        payment.setPaymentMode(paymentMode);
        payment.setTransactionDate(LocalDateTime.now());

        try {
            boolean paymentSaved = paymentDAO.addPayment(payment);
            if (paymentSaved) {
                // Now update the selected bills to "Paid"
                String[] selectedBillIds = request.getParameterValues("selectedBills");
                boolean allBillsUpdated = true;
                if (selectedBillIds != null) {
                    for (String idStr : selectedBillIds) {
                        try {
                            int billId = Integer.parseInt(idStr);
                            boolean updated = billDAO.markBillAsPaid(billId);
                            if (!updated) {
                                allBillsUpdated = false;
                                break;
                            }
                        } catch (NumberFormatException e) {
                            allBillsUpdated = false;
                            break;
                        }
                    }
                }
                if (allBillsUpdated) {
                    request.setAttribute("payment", payment);
                    request.getRequestDispatcher("receipt.jsp").forward(request, response);
                    return;
                }
            }
            request.setAttribute("errorMessage", "Payment processed but failed to update bill status.");
            request.getRequestDispatcher("card_payment.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Database error during payment processing.");
            request.getRequestDispatcher("card_payment.jsp").forward(request, response);
        }
    }

    // Simulated payment authorization (replace with real integration)
    private boolean simulatePaymentAuthorization(String cardNumber, double amount) {
        // For demo, authorize if card number starts with "4" (Visa) or "5" (Mastercard)
        return cardNumber != null && (cardNumber.startsWith("4") || cardNumber.startsWith("5"));
    }
}

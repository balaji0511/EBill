package dao;

import model.Payment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PaymentDAO {
    private Connection conn;

    public PaymentDAO(Connection conn) {
        this.conn = conn;
    }

    public boolean addPayment(Payment payment) throws SQLException {
        String query = "INSERT INTO payment (consumerNumber, cardNumber, cardHolder, expiryMonth, expiryYear, cvv, totalAmount, paymentMode, transactionDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setLong(1, payment.getConsumerNumber());
            ps.setString(2, payment.getCardNumber());
            ps.setString(3, payment.getCardHolder());
            ps.setString(4, payment.getExpiryMonth());
            ps.setString(5, payment.getExpiryYear());
            ps.setString(6, payment.getCvv());
            ps.setDouble(7, payment.getTotalAmount());
            ps.setString(8, payment.getPaymentMode());
            ps.setTimestamp(9, java.sql.Timestamp.valueOf(payment.getTransactionDate()));
            int rows = ps.executeUpdate();
            return rows > 0;
        }
    }
}

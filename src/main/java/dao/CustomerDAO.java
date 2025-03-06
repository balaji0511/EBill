package dao;

import model.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class CustomerDAO {
    public boolean registerCustomer(Customer customer) {
        String query = "INSERT INTO Customer (consumerId, billNumber, customerName, email, mobileNumber, userId, password, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, customer.getConsumerId());
            pstmt.setInt(2, customer.getBillNumber());
            pstmt.setString(3, customer.getCustomerName());
            pstmt.setString(4, customer.getEmail());
            pstmt.setString(5, customer.getMobileNumber());
            pstmt.setString(6, customer.getUserId());
            pstmt.setString(7, customer.getPassword());
            pstmt.setBoolean(8, true); // Active status

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public Customer validateUser(String userId, String password) {
        Customer customer = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM customers WHERE user_id=? AND password=?")) {

            stmt.setString(1, userId);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                customer = new Customer();
                customer.setConsumerId(rs.getInt("consumer_id"));
                customer.setBillNumber(rs.getInt("bill_number"));
                customer.setTitle(rs.getString("title"));
                customer.setCustomerName(rs.getString("customer_name"));
                customer.setEmail(rs.getString("email"));
                customer.setMobileNumber(rs.getString("mobile_number"));
                customer.setUserId(rs.getString("user_id"));
                customer.setCustomerStatus(rs.getString("customer_status"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return customer;
    }
}

package dao;

import model.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class CustomerDAO {
    public boolean registerCustomer(Customer customer) {
        String query = "INSERT INTO Customer (consumerId, billNumber,title, customerName, email, mobileNumber, userId, password, confirmPassword, customerStatus) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        if (isCustomerExists(customer.getEmail())) {
            System.out.println("Customer already exists!");
            return false;
        }
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, customer.getConsumerId());
            pstmt.setInt(2,customer.getBillNumber());
            pstmt.setString(3, customer.getTitle());
            pstmt.setString(4, customer.getCustomerName());
            pstmt.setString(5, customer.getEmail());
            pstmt.setString(6, customer.getMobileNumber());
            pstmt.setString(7, customer.getUserId());
            pstmt.setString(8, customer.getPassword());
            pstmt.setString(9, customer.getConfirmPassword());
            pstmt.setString(10, customer.getCustomerStatus());

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
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM customer WHERE userId=? AND password=?")) {

            stmt.setString(1, userId);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                customer = new Customer();
                customer.setConsumerId(rs.getInt("consumerId"));
                customer.setBillNumber(rs.getInt("billNumber"));
                customer.setTitle(rs.getString("title"));
                customer.setCustomerName(rs.getString("customerName"));
                customer.setEmail(rs.getString("email"));
                customer.setMobileNumber(rs.getString("mobileNumber"));
                customer.setUserId(rs.getString("userId"));
                customer.setCustomerStatus(rs.getString("customerStatus"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return customer;
    }
    
    public boolean isCustomerExists(String email) {
        Connection conn = DBConnection.getConnection();
        String query = "SELECT COUNT(*) FROM CUSTOMER WHERE email = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                return true; 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}



package dao;

import java.security.SecureRandom;
import java.sql.*;
import java.util.*;
import model.Customer;

public class CustomerDAO {
    private Connection conn;

    public CustomerDAO() {
    	
    }
    public CustomerDAO(Connection conn) {
        this.conn = conn;
    }

    // Check if email or user ID already exists
    public boolean isEmailOrUserIdExists(String email, String userId) throws SQLException {
        String query = "SELECT 1 FROM customer WHERE email = ? OR userId = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, email);
            ps.setString(2, userId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    // Generate a unique 13-digit customer ID
    public static long generateCustomerId() {
        SecureRandom random = new SecureRandom();
        return 1000000000000L + (Math.abs(random.nextLong()) % 9000000000000L);
    }

    
    public boolean registerCustomer(Customer customer) throws SQLException {
        String customerQuery = "INSERT INTO customer (customerId, title, customerName, email, mobileNumber, userId, password) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String loginQuery = "INSERT INTO login (consumerNumber, email, userId, password, userType, userStatus) VALUES (?, ?, ?, ?, ?, ?)";

        try (
            PreparedStatement psCustomer = conn.prepareStatement(customerQuery, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement psLogin = conn.prepareStatement(loginQuery)
        ) {
            conn.setAutoCommit(false); // Start transaction

            // Insert into customer table
            psCustomer.setLong(1, customer.getCustomerId());
            psCustomer.setString(2, customer.getTitle());
            psCustomer.setString(3, customer.getCustomerName());
            psCustomer.setString(4, customer.getEmail());
            psCustomer.setString(5, customer.getMobileNumber());
            psCustomer.setString(6, customer.getUserId());
            psCustomer.setString(7, customer.getPassword());

            int customerInserted = psCustomer.executeUpdate();

            // Retrieve generated consumerNumber
            ResultSet rs = psCustomer.getGeneratedKeys();
            long consumerNumber = -1;
            if (rs.next()) {
                consumerNumber = rs.getLong(1);
            } else {
                conn.rollback(); // Rollback if consumerNumber is not generated
                return false;
            }

            // Insert into login table using the generated consumerNumber
            psLogin.setLong(1, consumerNumber);
            psLogin.setString(2, customer.getEmail());
            psLogin.setString(3, customer.getUserId());
            psLogin.setString(4, customer.getPassword());
            psLogin.setString(5, "Customer"); // Default userType
            psLogin.setString(6, "Active");   // Default userStatus

            int loginInserted = psLogin.executeUpdate();

            // Commit transaction if both inserts succeed
            if (customerInserted > 0 && loginInserted > 0) {
                conn.commit();
                System.out.println("Registration successful! Customer and Login details saved.");
                return true;
            } else {
                conn.rollback(); // Rollback if any insert fails
                System.out.println("Registration failed! Rolling back transaction.");
                return false;
            }
        } catch (SQLException e) {
            conn.rollback(); // Rollback on exception
            throw e;
        } finally {
            conn.setAutoCommit(true); // Restore auto-commit mode
        }
    }

    public Customer validateUser(String userId, String password) throws SQLException {
        Customer customer = null;
        String query = "SELECT consumerNumber FROM login WHERE userId = ? AND password = ?";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, userId);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    long consumerNumber = rs.getLong("consumerNumber");

                    // Fetch full customer details from customer table
                    
                    customer = getCustomerByConsumerNumber(consumerNumber);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customer;
    }
    
 // New method to fetch full customer details
    public Customer getCustomerByConsumerNumber(long consumerNumber) throws SQLException {
        Customer customer = null;
        String query = "SELECT * FROM customer WHERE consumerNumber = ?";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setLong(1, consumerNumber);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    customer = new Customer();
                    customer.setConsumerNumber(rs.getLong("consumerNumber"));
                    customer.setCustomerId(rs.getLong("customerId"));
                    customer.setTitle(rs.getString("title"));
                    customer.setCustomerName(rs.getString("customerName"));
                    customer.setEmail(rs.getString("email"));
                    customer.setMobileNumber(rs.getString("mobileNumber"));
                    customer.setUserId(rs.getString("userId"));
                    customer.setPassword(rs.getString("password"));
                    customer.setCustomerStatus(rs.getString("customerStatus"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customer;
    }

    // Get customer by user ID
    public Customer getCustomerByUserId(String userId) throws SQLException {
        String query = "SELECT * FROM customer WHERE userId = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Customer(rs);
                }
            }
        }
        return null;
    }

    // Update customer details
    public boolean updateCustomerDetails(Customer customer) throws SQLException {
        String query = "UPDATE customer SET title = ?, customerName = ?, email = ?, mobileNumber = ? WHERE customerId = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, customer.getTitle());
            ps.setString(2, customer.getCustomerName());
            ps.setString(3, customer.getEmail());
            ps.setString(4, customer.getMobileNumber());
            ps.setLong(5, customer.getCustomerId());
            return ps.executeUpdate() > 0;
        }
    }

    // Update password
    public boolean updatePassword(long customerId, String newPassword) throws SQLException {
        String query = "UPDATE customer SET password = ? WHERE customerId = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, newPassword);
            ps.setLong(2, customerId);
            return ps.executeUpdate() > 0;
        }
    }

    // Delete customer
    public boolean deleteCustomer(long customerId) throws SQLException {
        String query = "DELETE FROM customer WHERE customerId = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setLong(1, customerId);
            return ps.executeUpdate() > 0;
        }
    }

    // Get all customers
    public List<Customer> getAllCustomers() throws SQLException {
        List<Customer> customers = new ArrayList<>();
        String query = "SELECT * FROM customer";
        try (PreparedStatement ps = conn.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                customers.add(new Customer(rs));
            }
        }
        return customers;
    }
    
	
}

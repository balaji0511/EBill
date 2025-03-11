package dao;

import model.Bills;
import model.Complaint;
import model.Customer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO {

    private static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String URL = "jdbc:derby:C:\\Users\\2783490\\MyDB;create=true";

    public static List<Customer> getAllConsumerNumbers() {
        List<Customer> customers = new ArrayList<>();
        String query = "SELECT * FROM customer";
        try {
            Class.forName(DRIVER);
            try (Connection conn = DriverManager.getConnection(URL);
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    customers.add(new Customer(
                        rs.getLong("ConsumerNumber"),
                        rs.getString("Title"),
                        rs.getString("CustomerName"),
                        rs.getString("Email"),
                        rs.getLong("MobileNumber"),
                        rs.getString("UserId"),
                        rs.getString("Password"),
                        rs.getString("Password")  // Using password as confirmPassword
                    ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customers;
    }

    public static List<Complaint> getAllComplaints() {
        List<Complaint> complaintList = new ArrayList<>();
        String query = "SELECT ComplaintID, ConsumerNumber, ComplaintType, ComplaintCategory, ComplaintName, ProblemDescription, MobileNumber,Status FROM complaint";
        try {
            Class.forName(DRIVER);
            try (Connection conn = DriverManager.getConnection(URL);
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    Complaint complaint = new Complaint();
                    complaint.setComplaintID(rs.getString("ComplaintID"));
                    complaint.setConsumerNumber(rs.getLong("ConsumerNumber"));
                    complaint.setComplaintType(rs.getString("ComplaintType"));
                    complaint.setComplaintCategory(rs.getString("ComplaintCategory"));
                    complaint.setContactPerson(rs.getString("ComplaintName"));
                    complaint.setProblemDescription(rs.getString("ProblemDescription"));
                    complaint.setMobileNumber(rs.getLong("MobileNumber"));
                    complaint.setStatus(rs.getString("Status"));
                    complaintList.add(complaint);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return complaintList;
    }

    public static boolean addBill(Bills bill) {
        String query = "INSERT INTO Bill (billNumber, consumerNumber, billUploadDate, dueAmount, payableAmount, paymentId, PaymentDate, payment_Status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        int rowsAffected = 0;
        try {
            Class.forName(DRIVER);
            try (Connection conn = DriverManager.getConnection(URL);
                 PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setInt(1, bill.getBillNumber());
                pstmt.setLong(2, bill.getConsumerNumber());
                pstmt.setDate(3, new java.sql.Date(System.currentTimeMillis())); 
                pstmt.setDouble(4, bill.getDueAmount());
                pstmt.setDouble(5, bill.getPayableAmount());
                pstmt.setString(6, null);  // paymentId
                pstmt.setDate(7, null);    // paymentDate
                pstmt.setString(8, bill.getPaymentStatus());
                rowsAffected = pstmt.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
            return false;
        }
    }

    public static boolean updateComplaintStatus(String complaintID, String newStatus) {
        String query = "UPDATE complaint SET Status = ? WHERE ComplaintID = ?";
        try {
            Class.forName(DRIVER);
            try (Connection conn = DriverManager.getConnection(URL);
                 PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, newStatus);
                stmt.setString(2, complaintID);
                int rowsUpdated = stmt.executeUpdate();
                return rowsUpdated > 0;
            }
        } catch (Exception e) {
        	System.out.print(e.getMessage());
        }
        return false;
    }

    public static boolean checkBillNumberExists(int billNumber) {
        String query = "SELECT COUNT(*) FROM Bill WHERE billNumber = ?";
        try {
            Class.forName(DRIVER);
            try (Connection conn = DriverManager.getConnection(URL);
                 PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setInt(1, billNumber);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        return rs.getInt(1) > 0;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}

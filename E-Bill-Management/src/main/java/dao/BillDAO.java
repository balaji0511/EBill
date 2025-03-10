package dao;

import model.Bill;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BillDAO {
    private Connection conn;

    // Constructor to initialize the database connection
    public BillDAO(Connection conn) {
        this.conn = conn;
    }

//    Fetch all bills for a specific consumer number
    public List<Bill> getBillsByConsumerNumber(long consumerNumber) {
        List<Bill> bills = new ArrayList<>();
        String query = "SELECT * FROM bill WHERE consumerNumber = ? AND billStatus = 'Unpaid'";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setLong(1, consumerNumber);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Bill bill = new Bill();
                bill.setBillId(rs.getInt("billId"));
                bill.setConsumerNumber(rs.getLong("consumerNumber"));
                bill.setBillNumber(rs.getInt("billNumber"));
                bill.setDueAmount(rs.getDouble("dueAmount"));
                bill.setPayableAmount(rs.getDouble("payableAmount"));
                bill.setTotalAmount(rs.getDouble("totalAmount"));
                bill.setBillStatus(rs.getString("billStatus"));
                bills.add(bill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bills;
    }

  
    //Fetch a single bill by its ID
    public Bill getBillById(int billId) {
        Bill bill = null;
        String query = "SELECT * FROM bill WHERE billId = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, billId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                bill = new Bill();
                bill.setBillId(rs.getInt("billId"));
                bill.setConsumerNumber(rs.getLong("consumerNumber"));
                bill.setBillNumber(rs.getInt("billNumber"));
                bill.setDueAmount(rs.getDouble("dueAmount"));
                bill.setPayableAmount(rs.getDouble("payableAmount"));
                bill.setTotalAmount(rs.getDouble("totalAmount"));
                bill.setBillStatus(rs.getString("billStatus"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bill;
    }

    /**
     * Add a new bill to the database
     */
    public boolean addBill(Bill bill) {
        String query = "INSERT INTO bill (consumerNumber, billNumber, dueAmount, payableAmount, totalAmount, billStatus) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setLong(1, bill.getConsumerNumber());
            pstmt.setInt(2, bill.getBillNumber());
            pstmt.setDouble(3, bill.getDueAmount());
            pstmt.setDouble(4, bill.getPayableAmount());
            pstmt.setDouble(5, bill.getTotalAmount());
            pstmt.setString(6, bill.getBillStatus());

            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Update an existing bill
     */
    public boolean updateBill(Bill bill) {
        String query = "UPDATE bill SET dueAmount = ?, payableAmount = ?, totalAmount = ?, billStatus = ? WHERE billId = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setDouble(1, bill.getDueAmount());
            pstmt.setDouble(2, bill.getPayableAmount());
            pstmt.setDouble(3, bill.getTotalAmount());
            pstmt.setString(4, bill.getBillStatus());
            pstmt.setInt(5, bill.getBillId());

            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    
    public boolean deleteBill(int billId) {
        String query = "DELETE FROM bill WHERE billId = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, billId);
            int rowsDeleted = pstmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    
    public boolean markBillAsPaid(int billId) {
        String query = "UPDATE bill SET payableAmount = 0, billStatus = 'Paid' WHERE billId = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, billId);
            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    
}

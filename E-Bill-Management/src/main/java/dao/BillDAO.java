
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Bill;
import dao.DBConnection;

public class BillDAO {
    public static List<Bill> getUnpaidBills(int consumerNumber) {
        List<Bill> bills = new ArrayList<>();
        String query = "SELECT * FROM bill WHERE consumerNumber = ? AND billStatus = 'UNPAID'";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
             
            pstmt.setInt(1, consumerNumber);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Bill bill = new Bill();
                bill.setBillId(rs.getInt("billId"));
                bill.setConsumerNumber(rs.getInt("consumerNumber"));
                bill.setDueAmount(rs.getDouble("dueAmount"));
                bill.setPayableAmount(rs.getDouble("payableAmount"));
                bill.setBillStatus(rs.getString("billStatus"));
                bills.add(bill);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bills;
    }
    
    public static double calculateTotal(List<Bill> bills) {
        double total = 0;
        for (Bill bill : bills) {
            total += bill.getPayableAmount();
        }
        return total;
    }

    public static List<Bill> getBillsByConsumerId(int consumerId) {
        List<Bill> bills = new ArrayList<>();
        String sql = "SELECT * FROM bill WHERE consumerNumber = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, consumerId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Bill bill = new Bill();
                bill.setBillId(rs.getInt("billId"));
                bill.setBillNumber(rs.getInt("billNumber"));
                bill.setConsumerNumber(rs.getInt("consumerNumber"));
                bill.setDueAmount(rs.getDouble("dueAmount"));
                bill.setPayableAmount(rs.getDouble("payableAmount"));
                bill.setTotalAmount(rs.getDouble("totalAmount"));
                bill.setBillStatus(rs.getString("billStatus"));

                bills.add(bill);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bills;
    }
    
    public static List<Bill> getBillsByIds(String[] billIds) {
        List<Bill> selectedBills = new ArrayList<>();
        String query = "SELECT billId, billNumber, consumerNumber, dueAmount, payableAmount, totalAmount, billStatus FROM bill WHERE billId = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
             
            for (String billId : billIds) {
                pstmt.setInt(1, Integer.parseInt(billId));
                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        Bill bill = new Bill();
                        bill.setBillId(rs.getInt("billId"));
                        bill.setBillNumber(rs.getInt("billNumber"));
                        bill.setConsumerNumber(rs.getInt("consumerNumber"));
                        bill.setDueAmount(rs.getDouble("dueAmount"));
                        bill.setPayableAmount(rs.getDouble("payableAmount"));
                        bill.setTotalAmount(rs.getDouble("totalAmount"));
                        bill.setBillStatus(rs.getString("billStatus"));
                        selectedBills.add(bill);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return selectedBills;
    }
    
    public static boolean updateBillStatusToPaid(String[] billIds) {
        String updateQuery = "UPDATE bill SET billStatus = 'PAID' WHERE billId = ? AND billStatus != 'PAID'";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(updateQuery)) {
             
            for (String billId : billIds) {
                pstmt.setInt(1, Integer.parseInt(billId));
                pstmt.addBatch();  // Add to batch for execution
            }
            int[] result = pstmt.executeBatch();  // Execute batch update
            return result.length > 0;  // Returns true if at least one row is updated
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
   
}

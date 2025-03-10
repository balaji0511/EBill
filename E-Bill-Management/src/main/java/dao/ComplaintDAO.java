package dao;

import java.sql.*;
import model.Complaint;

public class ComplaintDAO {

    public static int registerComplaint(Complaint c, long consumerNumber) {		 
         int status = 0;
         try {					
             Connection conn = DBConnection.getConnection();
             
             // Check if consumer number exists in the customer table
             PreparedStatement pstmt1 = conn.prepareStatement("SELECT consumerNumber FROM customer WHERE consumerNumber = ?");
             pstmt1.setLong(1, consumerNumber);
             ResultSet rs = pstmt1.executeQuery();
             
             if(rs.next()) {
                 // Insert the complaint record and retrieve the auto-generated complaintId
                 PreparedStatement pstmt = conn.prepareStatement(
                     "INSERT INTO complaint (consumerNumber, complaintType, category, customerName, landmark, problemDescription, mobileNumber, address, complaintStatus) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
                     PreparedStatement.RETURN_GENERATED_KEYS);
                    
                 pstmt.setLong(1, c.getConsumerNumber());
                 pstmt.setString(2, c.getComplaintType());
                 pstmt.setString(3, c.getCategory());
                 pstmt.setString(4, c.getCustomerName());
                 pstmt.setString(5, c.getLandmark());
                 pstmt.setString(6, c.getProblem());
                 pstmt.setString(7, c.getMobileNumber());
                 pstmt.setString(8, c.getAddress());
                 pstmt.setString(9, c.getComplaintStatus());
                 
                 status = pstmt.executeUpdate();	
                 
                 if (status > 0) {
                     try (ResultSet res = pstmt.getGeneratedKeys()) {
                         if (res.next()) {
                             c.setComplaintId(res.getInt(1));
                         }
                     }
                 } else {
                     System.out.println("Insertion failed.");
                 }
             } else {
                 System.out.println("You entered the wrong Consumer Number. No such Consumer Number is found.");
             }
             conn.close();
         } catch(SQLException e) {
             e.printStackTrace();
         }
         return status;
    }
    
    public static String displayComplaint(String complaintId){ 
        String res = "";
        try {
            Connection conn = DBConnection.getConnection();
            int compId;
            try {
                compId = Integer.parseInt(complaintId);
            } catch (NumberFormatException e) {
                compId = 0;
            }
            PreparedStatement pstmt = conn.prepareStatement("SELECT complaintStatus FROM complaint WHERE complaintId = ?");
            pstmt.setInt(1, compId);
            ResultSet rs = pstmt.executeQuery();
            
            if(rs.next()) {
                res = rs.getString(1);
            } else {
                res = "ComplaintID Not Found!";
            }
            conn.close();
        } catch(SQLException e) {
            System.out.println(e);
        }
        return res;
    }
}

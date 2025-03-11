package controller;

import dao.AdminDAO;
import model.Bills;
import model.Complaint;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
    public void init() {
        System.out.println("AdminServlet initialized");
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            response.sendRedirect(request.getContextPath() + "/adminHome.jsp"); // Updated path
            return;
        }
        
        if ("checkBillNumber".equals(action)) {
            int billNumber = Integer.parseInt(request.getParameter("billNumber"));
            boolean exists = AdminDAO.checkBillNumberExists(billNumber);
            response.setContentType("application/json");
            response.getWriter().write("{\"exists\": " + exists + "}");
            return;
        }
        
        if ("updateComplaintStatus".equals(action)) {
            String complaintID = request.getParameter("complaintID");
            String newStatus = request.getParameter("newStatus");
            boolean updateSuccess = AdminDAO.updateComplaintStatus(complaintID, newStatus);
            response.sendRedirect(request.getContextPath() + "/manageComplaints.jsp?updateSuccess=" + updateSuccess);
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("addBill".equals(action)) {
            try {
                int billNumber = Integer.parseInt(request.getParameter("billNumber"));
                long consumerNumber = Long.parseLong(request.getParameter("consumerNumber"));
                double dueAmount = Double.parseDouble(request.getParameter("dueAmount"));
                double payableAmount = Double.parseDouble(request.getParameter("payableAmount"));
                String paymentStatus = request.getParameter("paymentStatus");
 
        Bills bill = new Bills(
            billNumber, 
            consumerNumber, 
            new java.sql.Date(System.currentTimeMillis()),
            dueAmount,
            payableAmount,
            null,
            null,
            paymentStatus
        );
                
                boolean rowsAffected = AdminDAO.addBill(bill);
                System.out.println("In Servlet Test:"+rowsAffected);
                HttpSession session = request.getSession();
                if(rowsAffected == true) {
                    session.setAttribute("message", "Bill added successfully!");
                    response.sendRedirect(request.getContextPath() + "/manageBills.jsp");
                } else {
                    session.setAttribute("error", "Failed! Bill Not Added");
                    response.sendRedirect(request.getContextPath() + "/manageBills.jsp");
                }
            } catch (Exception e) {
                
                HttpSession session = request.getSession();
                session.setAttribute("error", "Error adding bill: " + e.getMessage());
                response.sendRedirect(request.getContextPath() + "/manageBills.jsp");
            }
        }
    }
}

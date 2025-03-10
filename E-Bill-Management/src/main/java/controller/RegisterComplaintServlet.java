package controller;

import java.io.IOException;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import dao.ComplaintDAO;
import model.Complaint;
import model.Customer;

@WebServlet("/RegisterComplaint")
public class RegisterComplaintServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        // Retrieve the customer from the session
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer == null) {
            req.setAttribute("errorMessage", "Session expired. Please log in again.");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
            return;
        }
        
        // Retrieve consumer number from the customer object
        Long consumerNumber = customer.getConsumerNumber();
        // No need for a separate "consumerNumber" session attribute now.

        // Generate a random complaint ID (or you could let the database generate it)
        int complaintId = new Random().nextInt(4000);
        req.setAttribute("complaintId", complaintId);

        // Get form parameters
        String complaintType = req.getParameter("complaintType");
        String category = req.getParameter("category");
        String landmark = req.getParameter("landmark");
        String problemDes = req.getParameter("problemDes");
        String mobileNumber = req.getParameter("mobileNumber");
        String address = req.getParameter("address");
        String contactPerson = req.getParameter("contactPerson");
        String status = "Open";

        // Create a Complaint object
        Complaint complaint = new Complaint(complaintId, complaintType, category, contactPerson, landmark, consumerNumber, problemDes, mobileNumber, address, status);

        // Register the complaint
        int result = ComplaintDAO.registerComplaint(complaint, consumerNumber);
        System.out.println("Complaint created");
        if (result > 0) {
            session.setAttribute("complaintId", complaint.getComplaintId());
            req.getRequestDispatcher("complaint_success.jsp").forward(req, resp);
        } else {
            req.setAttribute("errorMessage", "Sorry! Unable to save the complaint. Please try again.");
            req.getRequestDispatcher("register_complaint.jsp").forward(req, resp);
        }
    }
}

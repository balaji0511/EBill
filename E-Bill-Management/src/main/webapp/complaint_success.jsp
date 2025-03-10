<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Complaint Registered</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <div class="navbar">
        <div class="navbar-list">
            <a class="navbar-items" href="home.jsp">HOME</a>
            <a class="navbar-items" href="PayBill">PAY BILL</a>
            <a class="navbar-items" href="register_complaint.jsp">REGISTER COMPLAINT</a>
            <a class="navbar-items" href="complaint_status.jsp">COMPLAINT STATUS</a>
        </div>
        <div class="navbar-user">
            <p>Welcome, <%= session.getAttribute("username") != null ? session.getAttribute("username") : "Guest" %>!</p>
            <form action="LogoutServlet" method="post">
                <button type="submit">Logout</button>
            </form>
        </div>
    </div>
    <div class="container">
        <div class="sub-header">
            <h2>Complaint Registered Successfully!</h2>
        </div>
        <p><strong>Complaint ID:</strong> 
	        <% 
                // Retrieve the complaintId as an Integer and convert to String if needed
                Integer complaintId = (Integer) session.getAttribute("complaintId");
                out.println(complaintId != null ? complaintId.toString() : "N/A");
            %>
        </p>
        <p>Thank you for reaching out. Our team will get back to you soon.</p>
        <button onclick="goHome()">Back to Home</button>
    </div>
    <script>
        function goHome() {
            window.location.href = "home.jsp";
        }
    </script>
</body>
</html>

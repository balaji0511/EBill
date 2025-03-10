<%@ page import="model.Customer" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    Customer customer = (Customer) session.getAttribute("customer");
    if (customer == null) {
        response.sendRedirect("login.jsp?message=Please login first.");
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home</title>
    <link rel="stylesheet" href="styles.css">
    <style>
	    .navbar-user {
	        position: relative;
	        display: inline-block;
	        cursor: pointer;
	    }
	    
	    .username {
	        padding: 10px;
	        font-weight: bold;
	        color: white;
	        background: blue;
	        border-radius: 5px;
	        display: inline-block;
	    }
	    
	    .dropdown-content {
	        display: none;
	        position: absolute;
	        background-color: white;
	        box-shadow: 0px 4px 8px rgba(0,0,0,0.2);
	        border-radius: 5px;
	        min-width: 150px;
	        z-index: 10;
	    }
	    
	    .dropdown-content a, .dropdown-content button {
	        display: block;
	        width: 100%;
	        padding: 10px;
	        text-align: left;
	        border: none;
	        background: none;
	        cursor: pointer;
	        color: black;
	        text-decoration: none;
	    }
	    
	    .dropdown-content a:hover, .dropdown-content button:hover {
	        background: lightgray;
	    }
	</style>
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
            <p onclick="toggleDropdown()" class="username">Welcome, <%= customer.getCustomerName() %> ▼</p>
            <!-- Drop down Menu -->
		    <div id="userDropdown" class="dropdown-content">
		        <a href="ViewCustomerServlet">View Profile</a>
		        <form action="LogoutServlet" method="post">
		            <button type="submit">Logout</button>
		        </form>
		    </div>
        </div>
    </div>
    
    <div class="container">
        <h2>Customer Details</h2>
        <p><strong>Customer ID:</strong> <%= customer.getCustomerId() %></p>
        <p><strong>Name:</strong> <%= customer.getCustomerName() %></p>
        <p><strong>Email:</strong> <%= customer.getEmail() %></p>
        <p><strong>Mobile Number:</strong> <%= customer.getMobileNumber() %></p>
    </div>
    <script>
	    function toggleDropdown() {
	        var dropdown = document.getElementById("userDropdown");
	        dropdown.style.display = dropdown.style.display === "block" ? "none" : "block";
	    }
	    
	    // Close dropdown if clicked outside
	    window.onclick = function(event) {
	        if (!event.target.matches('.username')) {
	            var dropdown = document.getElementById("userDropdown");
	            if (dropdown.style.display === "block") {
	                dropdown.style.display = "none";
	            }
	        }
	    };
	</script>
</body>
</html>


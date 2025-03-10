<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Complaint Status - Consumer Portal</title>
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
            <p>Welcome, <span id="username"></span>!</p>
            <button onclick="logout()">Logout</button>
        </div>
    </div>

    <div class="container">
        <div class="header">
            <p>Check Complaint Status</p>
        </div>
         <form action="ComplaintStatusServlet" method="get">
        	<label for="complaintID">Enter Complaint ID:</label>
	        <input type="text" id="complaintID" placeholder="Enter your Complaint ID" required name="compId">
	        <div class="submit-box">
	            <button type="reset" class="reset" onclick="resetForm()">Cancel</button>
	            <button class="register">Check Status</button>
	        </div>
        </form>
        
        
        <div id="statusResult" class="status-box">
        	
	        <% 
	        	String complaintId =(String) session.getAttribute("status"); 
	        	
	        	if (complaintId != null) {
	        	%>
	        		<p><strong>Status:</strong> 
	        	<% 
	                out.println(complaintId);
	            } 
	        %>
         </p> 
        </div>
    </div>
    <script>
        document.addEventListener("DOMContentLoaded", function () {
        let username = localStorage.getItem("username");
        if (document.getElementById("username")) {
            document.getElementById("username").innerText = username;
        }
        });

        function resetForm() {
        document.getElementById("complaintForm").reset();
        }
        
        function logout() {
            alert("Logging out...");
            window.location.href = "login.jsp";
        }
    </script>
</body>
</html>
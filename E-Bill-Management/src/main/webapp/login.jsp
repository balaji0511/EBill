<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="java.io.PrintWriter" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Consumer Login</title>
    <link rel="stylesheet" href="styles.css">
    <style>
    .navbar {
	    background: #0056b3;
	    padding: 15px;
	    text-align: center;
	    font-size: 22px;
	    font-weight: bold;
	}
	.navbar a {
	    color: white;
	    text-decoration: none;
	}
	
	 .popup-container {
	    display: none;
	    position: fixed;
	    top: 0; left: 0; width: 100%; height: 100%;
	    background: rgba(0, 0, 0, 0.5);
	    justify-content: center;
	    align-items: center;
	    z-index: 1000; /* Ensures it stays on top */
	}
	
	.popup-box {
	    background: white;
	    padding: 25px;
	    border-radius: 10px;
	    text-align: center;
	    width: 400px;
	    box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.3);
	    position: relative;
	    animation: fadeIn 0.3s ease-in-out;
	}
	
	/* Animation for smooth appearance */
	@keyframes fadeIn {
	    from { opacity: 0; transform: scale(0.9); }
	    to { opacity: 1; transform: scale(1); }
	}
	
	.popup-box h2 {
	    font-size: 20px;
	    color: #337cc1;
	    margin-bottom: 15px;
	}
	
	.popup-box p {
	    font-size: 16px;
	    color: #444;
	    margin-bottom: 20px;
	}
	
	/* Button Styles */
	.popup-box button {
	    padding: 12px;
	    border: none;
	    cursor: pointer;
	    border-radius: 5px;
	    font-size: 16px;
	    margin: 10px;
	    width: 140px;
	}
	
	.restore-btn { background: #28a745; color: white; }
	.restore-btn:hover { background: #218838; }
	
	.cancel-btn { background: #dc3545; color: white; }
	.cancel-btn:hover { background: #c82333; }
    </style>
</head>
<body>
<div class="navbar">
	<a href="index.jsp" class="navbar-title">E-Bill Management</a>
</div>
<div class="container">
    <div class="header">
        <p>Consumer Login</p>
    </div>

    <form action="LoginCustomer" method="post">
        <div class="fields-box">
            <div class="details-box">
                <label>User ID:</label>
                <input type="text" name="userId" required maxlength="20" value="<%= request.getParameter("userId") != null ? request.getParameter("userId") : "" %>">
            </div>
            <div class="details-box">
                <label>Password:</label>
                <input type="password" name="password" required maxlength="30">
            </div>
        </div>
        <div class="submit-box">
            <button type="submit">Login</button>
            <a href="register.jsp" class="reset">Not Registered Yet?</a>
        </div>

        <%-- Display error message if login fails --%>
        <% String message = request.getParameter("message");
    		if (message != null) { 
		%>
    <p style="color: red;"><%= message %></p>
<% } %>
    </form>
</div>

<!-- Inactive Account Pop up -->
<div id="inactive-popup" class="popup-container">
    <div class="popup-box">
        <h2>Restore Account</h2>
        <p>Your account is inactive. Do you want to restore it?</p>
        <form action="RestoreAccountServlet" method="post">
            <input type="hidden" name="userId" value="<%= request.getParameter("userId") %>">
            <button type="submit" class="restore-btn">Restore Account</button>
        </form>
        <button class="cancel-btn" onclick="closePopup()">Go Back</button>
    </div>
</div>

<!-- Auto-show Pop up if User is Inactive -->
<% if ("true".equals(request.getParameter("inactive"))) { %>
    <script> showInactivePopup(); </script>
<% } %>

	<script>
        function showInactivePopup() {
            document.getElementById("inactive-popup").style.display = "flex";
        }
        
        function closePopup() {
            document.getElementById("inactive-popup").style.display = "none";
        }
    </script>
</body>
</html>

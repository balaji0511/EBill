<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Consumer Registration</title>
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
		
    </style>
</head>
<body>
<div class="navbar">
   	<a href="index.jsp" class="navbar-title">E-Bill Management</a>
</div>
<div class="container">
    <div class="header">
        <p>Register New User</p>
    </div>

    <% String error = (String) request.getAttribute("error"); 
       if (error != null) { %>
       <p style="color: red;"><%= error %></p>
    <% } %>

    <form action="registerCustomer" method="post">
        <div class="register-title">
            <p>Consumer Details</p>
        </div>
        <div class="fields-box">
            <div class="details-box">
                <label for="consumerId">Consumer ID:</label>
                <input type="text" id="consumerId" name="consumerId" value="Auto Generated" disabled>
            </div>
        </div>
        <div class="register-title">
            <p>User Details</p>
        </div>
        <div class="fields-box">
            <div class="details-box">
                <label for="title">Title:</label>
                <select id="title" name="title">
                    <option value="Mr.">Mr.</option>
                    <option value="Ms.">Ms.</option>
                    <option value="Mrs.">Mrs.</option>
                    <option value="Dr.">Dr.</option>
                </select>
            </div>
            <div class="details-box">
                <label for="customerName">Name:</label>
                <input type="text" id="customerName" name="customerName" pattern="[A-Za-z ]{3,}" required 
                    title="Customer Name must contain only letters and be at least 3 characters long.">
            </div>    
        </div>
        <div class="fields-box">
            <div class="details-box">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required>
            </div>
            <div class="details-box">
                <label for="mobileNumber">Mobile Number:</label>
                <select id="countryCode" aria-placeholder="91 - India">
                    <option value="+91">+91 (India)</option>
                    <option value="+1">+1 (USA)</option>
                    <option value="+44">+44 (UK)</option>
                </select>	
                <input type="text" id="mobileNumber" name="mobileNumber" pattern="\d{10}" maxlength="10" required
                    title="Mobile Number must be exactly 10 digits.">
            </div>
        </div>
        <div class="register-title">
            <p>Login Details</p>
        </div>
        <div class="fields-box">
            <div class="details-box">
                <label for="userId">User ID:</label>
                <input type="text" id="userId" name="userId" minlength="5" required
                    title="User ID must be at least 5 characters long.">
            </div>
            <div class="details-box">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required
                    pattern="(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,16}"
                    title="Password must be 8-16 characters, include at least one uppercase letter, one number, and one special character.">
            </div>
            <div class="details-box">
                <label for="confirmPassword">Confirm Password:</label>
                <input type="password" id="confirmPassword" name="confirmPassword" required
                    title="Passwords must match.">
            </div>
        </div>
        <div class="submit-box">
            <button type="reset" class="reset">Reset</button>
            <button type="submit" class="register">Register</button>
        </div>
    </form>
</div>

</body>
</html>

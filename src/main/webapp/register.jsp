<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Consumer Registration</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>

<div class="container">
    <div class="header">
        <p>Register New User</p>
    </div>

    <% String error = (String) request.getAttribute("error"); 
       if (error != null) { %>
       <p style="color: red;"><%= error %></p>
    <% } %>

    <form action="customerRegister" method="post" >
        <div class="register-title">
            <p>Consumer Details</p>
        </div>
        <div class="fields-box">
            <div class="details-box">
                <label for="consumerId">Consumer ID:</label>
                <input type="text" id="consumerId" name="consumerId" value="Auto Generated" disabled>
            </div>
            <div class="details-box">
                <label for="billNumber">Bill Number:</label>
                <input type="number" id="billNumber" name="billNumber" maxlength="5" pattern="[0-9]{5}" required>
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
                <input type="text" id="customerName" name="customerName" required>
            </div>    
        </div>
        <div class="fields-box">
            <div class="details-box">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required>
            </div>
            <div class="details-box">
                <label for="mobileNumber">Mobile Number:</label>
                <input type="text" id="mobileNumber" name="mobileNumber" required>
            </div>
        </div>
        <div class="register-title">
            <p>Login Details</p>
        </div>
        <div class="fields-box">
            <div class="details-box">
                <label for="userId">User ID:</label>
                <input type="text" id="userId" name="userId" required>
            </div>
            <div class="details-box">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required>
            </div>
            <div class="details-box">
                <label for="confirmPassword">Confirm Password:</label>
                <input type="password" id="confirmPassword" name="confirmPassword" required>
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

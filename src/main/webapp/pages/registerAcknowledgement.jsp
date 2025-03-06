<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Customer" %>
<%
    Customer customer = (Customer) request.getAttribute("registeredCustomer");

    if (customer == null) {
        response.sendRedirect("register.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration Successful</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <div class="container">
        <div class="header">
            <p>Registered Successfully</p>
        </div>
        <h3 style="color: #337cc1;">Thank you for registering with EBill Application</h3>
        <div class="success-content">
            <p><b>Customer ID:</b> <span><%= customer.getConsumerId() %></span></p>
            <p><b>Customer Name:</b> <span><%= customer.getCustomerName() %></span></p>
            <p><b>Mobile Number:</b> <span><%= customer.getMobileNumber() %></span></p>
        </div>
        <div class="login-button">
            <a href="login.jsp">Back to Login</a>
        </div>
    </div>
</body>
</html>

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
</head>
<body>

<div class="container">
    <div class="header">
        <p>Consumer Login</p>
    </div>
    <form action="ConsumerLoginServlet" method="post">
        <div class="fields-box">
            <div class="details-box">
                <label>User ID:</label>
                <input type="text" name="userId" required maxlength="20">
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
        <%
            String message = request.getParameter("message");
            if (message != null) {
        %>
            <p style="color: red;"><%= message %></p>
        <%
            }
        %>
    </form>
</div>

</body>
</html>

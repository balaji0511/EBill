<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Welcome to E-Bill Management</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .navbar {
            background-color: #007BFF;
            padding: 15px;
            text-align: center;
            font-size: 24px;
            color: white;
            font-weight: bold;
        }
        .container {
            background: white;
            width: 50%;
            margin: 100px auto;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0px 0px 10px gray;
            text-align: center;
        }
        h1 {
            color: #333;
        }
        .btn {
            display: inline-block;
            padding: 10px 20px;
            margin: 20px;
            text-decoration: none;
            font-size: 18px;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .btn-login {
            background-color: #007BFF;
        }
        .btn-register {
            background-color: #28A745;
        }
        .btn:hover {
            opacity: 0.8;
        }
    </style>
</head>
<body>
    <div class="navbar">E-Bill Management</div>
    <div class="container">
        <h1>Welcome to E-Bill Management</h1>
        <p>Manage your bills, payments, and complaints efficiently.</p>
        <a href="login.jsp" class="btn btn-login">Login</a>
        <a href="register.jsp" class="btn btn-register">Register</a>
    </div>
</body>
</html>

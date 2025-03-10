<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Customer Profile</title>
    <link rel="stylesheet" href="styles.css">

    <style>
            .container {
            width: 50%;
            margin: 40px auto;
            background: #fff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
        }

        /* Heading */
        .container h2 {
            text-align: center;
            color: #333;
            font-size: 24px;
            border-bottom: 2px solid #337cc1;
            padding-bottom: 10px;
            margin-bottom: 20px;
        }

        /* Form layout */
        .form-group {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 15px;
        }

        .form-group label {
            font-weight: bold;
            font-size: 16px;
            color: #444;
            width: 30%;
        }

        .form-group input {
            width: 65%;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 5px;
            background: #f8f9fa;
            font-size: 16px;
        }

        /* Buttons container */
        .button-group {
            text-align: center;
            margin-top: 20px;
        }

        .button-group button {
            padding: 10px 20px;
            border: none;
            cursor: pointer;
            border-radius: 5px;
            font-size: 16px;
            margin: 10px;
            width: 180px;
        }

        .update-btn {
            background: #337cc1;
            color: white;
        }

        .update-btn:hover {
            background: #2868a6;
        }

        .deactivate-btn {
            background: #dc3545;
            color: white;
        }

        .deactivate-btn:hover {
            background: #c82333;
        }
        
        /* Full-screen overlay (hidden by default) */
        .popup-container {
            display: none; /* Hidden until activated */
            position: fixed;
            top: 0; left: 0; width: 100%; height: 100%;
            background: rgba(0, 0, 0, 0.5);
            z-index: 1000;
            justify-content: center;
            align-items: center;
        }

        /* Centered popup box */
        .popup-box {
            background: white;
            padding: 25px;
            border-radius: 10px;
            text-align: center;
            width: 400px;
            box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.3);
            animation: fadeIn 0.3s ease-in-out;
        }

        /* Fade-in animation */
        @keyframes fadeIn {
            from { opacity: 0; transform: scale(0.9); }
            to { opacity: 1; transform: scale(1); }
        }

        /* Popup Header */
        .popup-box h2 {
            font-size: 22px;
            color: #dc3545;
            margin-bottom: 15px;
        }

        /* Popup Message */
        .popup-box p {
            font-size: 16px;
            color: #444;
            margin-bottom: 20px;
        }

        /* Buttons Styling */
        .popup-box button {
            padding: 12px;
            border: none;
            cursor: pointer;
            border-radius: 5px;
            font-size: 16px;
            margin: 10px;
            width: 140px;
        }

        /* Deactivate Button */
        .deactivate-btn {
            background: #dc3545;
            color: white;
        }

        .deactivate-btn:hover {
            background: #c82333;
        }

        /* Cancel Button */
        .cancel-btn {
            background: #6c757d;
            color: white;
        }

        .cancel-btn:hover {
            background: #5a6268;
        }
    </style>

    <script>
        function showDeactivatePopup() {
            document.getElementById("deactivate-popup").style.display = "flex";
        }

        function closeDeactivatePopup() {
            document.getElementById("deactivate-popup").style.display = "none";
        }
    </script>

</head>
<body>

<% String message = (String) request.getAttribute("message");
   if (message != null) { %>
    <div class="message-box">
        <%= message %>
    </div>
<% } %>

<div class="container">
    <h2>Customer Profile</h2>
    <form>
        <div class="form-group">
            <label>Title:</label>
            <input type="text" value="${requestScope.title}" readonly>
        </div>
        <div class="form-group">
            <label>Customer Name:</label>
            <input type="text" value="${requestScope.customerName}" readonly>
        </div>
        <div class="form-group">
            <label>Email:</label>
            <input type="email" value="${requestScope.email}" readonly>
        </div>
        <div class="form-group">
            <label>Mobile Number:</label>
            <input type="text" value="${requestScope.mobileNumber}" readonly>
        </div>
        <div class="form-group">
            <label>User ID:</label>
            <input type="text" value="${requestScope.userId}" readonly>
        </div>
        <div class="form-group">
            <label>Status:</label>
            <input type="text" value="${requestScope.customerStatus}" readonly>
        </div>

        <button type="button" class="update-btn" onclick="window.location.href='updateCustomer.jsp'">Update Details</button>
        <button type="button" class="deactivate-btn" onclick="showDeactivatePopup()">Deactivate Account</button>
    </form>
</div>

<!-- Deactivate Account pop up (Hidden by default) -->
<div id="deactivate-popup" class="popup-container">
    <div class="popup-box">
        <h2>Deactivate Account</h2>
        <p>Are you sure you want to deactivate your account? You can restore it later.</p>
        <form action="DeactivateAccountServlet" method="post">
            <button type="submit" class="deactivate-btn">Confirm</button>
        </form>
        <button class="cancel-btn" onclick="closeDeactivatePopup()">Cancel</button>
    </div>
</div>

</body>
</html>

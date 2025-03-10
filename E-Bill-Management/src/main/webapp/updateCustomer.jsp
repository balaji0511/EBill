<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Update Customer Details</title>
    <script>
        function goBack() {
            window.history.back(); // Go to the previous page
        }
    </script>

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
        
        .form-group select {
    width: 68%;
    padding: 8px;
    border: 1px solid #ccc;
    border-radius: 5px;
    background: #f8f9fa;
    font-size: 16px;
    appearance: none;
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

        .save-btn {
            background: #337cc1;
            color: white;
        }

        .save-btn:hover {
            background: #2868a6;
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
</head>
<body>

<div class="container">
    <h2>Update Customer Details</h2>
    <form action="UpdateCustomerServlet" method="post">
        <div class="form-group">
            <label>User ID:</label>
            <input type="text" value="${sessionScope.customer.userId}" readonly>
        </div>
<div class="form-group">
    <label>Title:</label>
    <select name="title" required>
        <option value="" disabled selected>Select a title</option>
        <option value="Mr." ${requestScope.title == 'Mr.' ? 'selected' : ''}>Mr.</option>
        <option value="Ms." ${requestScope.title == 'Ms.' ? 'selected' : ''}>Ms.</option>
        <option value="Mrs." ${requestScope.title == 'Mrs.' ? 'selected' : ''}>Mrs.</option>
        <option value="Dr." ${requestScope.title == 'Dr.' ? 'selected' : ''}>Dr.</option>
        <option value="Prof." ${requestScope.title == 'Prof.' ? 'selected' : ''}>Prof.</option>
    </select>
</div>
        <div class="form-group">
            <label>Customer Name:</label>
            <input type="text" name="customerName" value="${requestScope.customerName}" required>
        </div>
        <div class="form-group">
            <label>Email:</label>
            <input type="email" name="email" value="${requestScope.email}" required>
        </div>
        <div class="form-group">
            <label>Mobile Number:</label>
            <input type="text" name="mobileNumber" value="${requestScope.mobileNumber}" required>
        </div>

        <div class="button-group">
            <button type="submit" class="save-btn">Save Changes</button>
            <button type="button" class="cancel-btn" onclick="goBack()">Go Back</button>
        </div>
    </form>
</div>

</body>
</html>

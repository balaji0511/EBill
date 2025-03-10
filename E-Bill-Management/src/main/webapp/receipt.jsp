<%@ page import="model.Payment, model.Customer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<%
    // Retrieve logged-in customer and payment details
    Customer customer = (Customer) session.getAttribute("customer");
    if (customer == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    Payment payment = (Payment) request.getAttribute("payment");
    if (payment == null) {
        response.sendRedirect("PayBill");
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Payment Receipt</title>
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
            <p>Welcome, <%= customer.getCustomerName() %>!</p>
            <form action="LogoutServlet" method="post">
                <button type="submit">Logout</button>
            </form>
        </div>
    </div>
    <div class="container">
        <div class="header">
            <h2>Payment Receipt</h2>
        </div>
        <table border="1" cellpadding="10" cellspacing="0" style="width:50%; margin:auto; text-align:left;">
            <tr>
                <th>Field</th>
                <th>Details</th>
            </tr>
            <tr>
                <td>Card Holder Name</td>
                <td><%= payment.getCardHolder() %></td>
            </tr>
            <tr>
                <td>Card Number</td>
                <td>**** **** **** <%= payment.getCardNumber().substring(12) %></td>
            </tr>
            <tr>
                <td>Payment Mode</td>
                <td><%= payment.getPaymentMode() %></td>
            </tr>
            <tr>
                <td>Total Amount Paid</td>
                <td>₹<%= payment.getTotalAmount() %></td>
            </tr>
            <tr>
                <td>Transaction Date</td>
                <td><%= payment.getTransactionDate() %></td>
            </tr>
        </table>
        <br>
        <div class="submit-box" style="text-align:center;">
            <button onclick="goHome()">Back to Home</button>
            <button onclick="downloadReceipt()">Download Receipt</button>
        </div>
    </div>
    <script>
        function goHome() {
            window.location.href = "home.jsp";
        }
        function downloadReceipt() {
            // Build an HTML document with table formatting for the receipt
            let receiptHTML = `
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Payment Receipt</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        h2 { text-align: center; }
        table { border-collapse: collapse; width: 80%; margin: 0 auto; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
    </style>
</head>
<body>
    <h2>Payment Receipt</h2>
    <table>
        <tr>
            <th>Field</th>
            <th>Details</th>
        </tr>
        <tr>
            <td>Card Holder Name</td>
            <td><%= payment.getCardHolder() %></td>
        </tr>
        <tr>
            <td>Card Number</td>
            <td>**** **** **** <%= payment.getCardNumber().substring(12) %></td>
        </tr>
        <tr>
            <td>Payment Mode</td>
            <td><%= payment.getPaymentMode() %></td>
        </tr>
        <tr>
            <td>Total Amount Paid</td>
            <td>₹<%= payment.getTotalAmount() %></td>
        </tr>
        <tr>
            <td>Transaction Date</td>
            <td><%= payment.getTransactionDate() %></td>
        </tr>
    </table>
</body>
</html>
            `;
            let blob = new Blob([receiptHTML], { type: "text/html" });
            let link = document.createElement("a");
            link.href = URL.createObjectURL(blob);
            link.download = "Payment_Receipt.html";
            link.click();
        }
    </script>
</body>
</html>

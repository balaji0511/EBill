<%@ page import="model.Customer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<%
    // Retrieve logged-in customer
    Customer customer = (Customer) session.getAttribute("customer");
    if (customer == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    // totalPayable is forwarded as a request attribute from receipt.jsp (or bill_payment.jsp)
    Double totalPayable = (Double) request.getAttribute("totalPayable");
    if (totalPayable == null) {
        totalPayable = 0.0;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Card Payment</title>
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
            <p>Card Payment</p>
        </div>
        <form action="CardPayment" method="post">
            <div class="fields-box">
                <div class="payment-box">
                    <label for="cardNumber">Credit Card Number</label>
                    <input type="text" name="cardNumber" id="cardNumber" minlength="16" maxlength="16" pattern="[0-9]{16}" required>
                </div>
                <div class="payment-box">
                    <label for="cardHolder">Card Holder Name</label>
                    <input type="text" name="cardHolder" id="cardHolder" minlength="10" required>
                </div>
            </div>
            <div class="fields-box">
                <div class="payment-box">
                    <label for="expiryDateMonth">Expiry Date (MM/YYYY)</label>
                    <input type="text" name="expiryDateMonth" id="expiryDateMonth" maxlength="2" placeholder="MM" pattern="(0[1-9]|1[0-2])" required>
                    <input type="text" name="expiryDateYear" id="expiryDateYear" maxlength="4" placeholder="YYYY" pattern="[0-9]{4}" required>
                </div>
                <div class="payment-box">
                    <label for="cvv">CVV/CVC</label>
                    <input type="text" name="cvv" id="cvv" maxlength="3" pattern="[0-9]{3}" required>
                </div>
            </div>
            <!-- Hidden field for total payable amount -->
            <input type="hidden" name="totalPayable" value="<%= totalPayable %>">
            <div class="fields-box">
                <label for="paymentMode">Mode of Payment:</label>
                <select id="paymentMode" name="paymentMode">
                    <option value="debit">Debit Card</option>
                    <option value="credit">Credit Card</option>
                </select>
            </div>
            <div class="submit-box">
                <button type="submit">Make Payment</button>
            </div>
        </form>
    </div>
</body>
</html>

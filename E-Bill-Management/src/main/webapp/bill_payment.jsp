<%@ page import="java.util.List, model.Bill, model.Customer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<%
    Customer customer = (Customer) session.getAttribute("customer");
    if (customer == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    List<Bill> selectedBills = (List<Bill>) request.getAttribute("selectedBills");
    Double totalAmount = (Double) request.getAttribute("totalAmount");
    double pgCharge = 50.0;
    double totalPayable = totalAmount != null ? totalAmount + pgCharge : pgCharge;
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bill Payment Summary</title>
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
            <button onclick="logout()">Logout</button>
        </div>
    </div>
    <div class="container">
        <div class="header">
            <p>Bill Payment Summary</p>
        </div>
        <table class="bill-table">
            <thead>
                <tr>
                    <th>Bill Details</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>Bill Amount:</td>
                    <td>₹<span id="billAmount"><%= totalAmount != null ? totalAmount : 0 %></span></td>
                </tr>
                <tr>
                    <td>PG Charge:</td>
                    <td>₹<span id="pgCharge"><%= pgCharge %></span></td>
                </tr>
                <tr>
                    <td>Total Payable Amount:</td>
                    <td>₹<span id="totalPayable"><%= totalPayable %></span></td>
                </tr>
            </tbody>
        </table>
        <br><br>
        <div class="bill-summary">
            <table class="bill-summary-table">
                <thead>
                    <tr>
                        <th>Bill Number</th>
                        <th>Payable Amount</th>
                    </tr>
                </thead>
                <tbody>
                    <% if (selectedBills != null && !selectedBills.isEmpty()) { 
                        for (Bill bill : selectedBills) { %>
                            <tr>
                                <td><%= bill.getBillNumber() %></td>
                                <td>₹<%= bill.getPayableAmount() %></td>
                            </tr>
                        <% } 
                    } else { %>
                        <tr><td colspan="2">No selected bills</td></tr>
                    <% } %>
                </tbody>
            </table>
        </div>
        <br>
        <form action="CardPayment" method="post">
            <label for="paymentMode">Mode of Payment:</label>
            <select id="paymentMode" name="paymentMode">
                <option value="debit">Debit Card</option>
                <option value="credit">Credit Card</option>
            </select>
            <input type="hidden" name="totalPayable" value="<%= totalPayable %>">
            <div class="submit-box">
                <button type="submit">Pay Now</button>
                <button type="button" onclick="goHome()">Back to Home</button>
            </div>
        </form>
    </div>    

    <script>
    function goHome() {
        window.location.href = "home.jsp";
    }
    </script>
</body>
</html>

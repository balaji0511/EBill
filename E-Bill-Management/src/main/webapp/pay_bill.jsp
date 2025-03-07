<%@ page import="java.util.List, model.Bill, model.Customer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<%
	Customer customer = (Customer) session.getAttribute("customer");
	if (customer == null) {
	    response.sendRedirect("login.jsp"); 
	    return;
	}
	List<Bill> bills = (List<Bill>) request.getAttribute("bills");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pay Bill</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <div class="navbar">
        <div class="navbar-list">
            <a class="navbar-items" href="home.jsp">HOME</a>
            <a class="navbar-items" href="">PAY BILL</a>
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
            <p>Select Bills to Pay</p>
        </div>

        <form action="PaymentServlet" method="POST">
            <table class="bill-table">
                <thead>
                    <tr>
                        <th>Consumer Number</th>
                        <th>Select</th>
                        <th>Due Amount (₹)</th>
                        <th>Payable (₹)</th>
                    </tr>
                </thead>
                <tbody>
                    <% if (bills != null && !bills.isEmpty()) { %>
                        <% for (Bill bill : bills) { %>
                            <tr>
                                <td><%= bill.getConsumerNumber() %></td>
                                <td>
                                    <input type="checkbox" name="selectedBills" value="<%= bill.getBillId() %>" class="bill" data-amount="<%= bill.getPayableAmount() %>">
                                </td>
                                <td>₹<%= bill.getDueAmount() %></td>
                                <td>₹<span class="payable"><%= bill.getPayableAmount() %></span></td>
                            </tr>
                        <% } %>
                    <% } else { %>
                        <tr>
                            <td colspan="4">No unpaid bills found for your account.</td>
                        </tr>
                    <% } %>
                </tbody>
            </table>

            <p class="total">Total Amount: ₹<span id="totalAmount">0</span></p>
            <input type="hidden" name="totalAmount" id="totalAmountInput">
            <button type="submit">Proceed to Pay</button>
        </form>
    </div>

    <script>
        document.querySelectorAll('.bill').forEach(checkbox => {
            checkbox.addEventListener('change', updateTotal);
        });

        function updateTotal() {
            let total = 0;
            document.querySelectorAll('.bill:checked').forEach(checkbox => {
                total += parseFloat(checkbox.dataset.amount);
            });
            document.getElementById('totalAmount').textContent = total;
            document.getElementById('totalAmountInput').value = total;
        }
    </script>
</body>
</html>

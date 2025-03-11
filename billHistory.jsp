<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="dao.Operations" %>
<%@ page import="model.Bills" %>
<%@ page import="model.Customer" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Bill History</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);
        }
        
        .bill-history-container {
            max-width: 90%;
            margin: 120px auto 20px;
            padding: 20px;
            background: white;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }
        
        .card-header {
            background-color: #3498db !important;
            color: white;
            border-radius: 8px 8px 0 0;
        }
        
        .table {
            margin-top: 20px;
        }
        
        .table th {
            background-color: #578fca;
            color: white;
            font-weight: 500;
        }
        
        .table td {
            vertical-align: middle;
        }
        
        .table tr:hover {
            background-color: #f8f9fa;
        }
        
        .status-paid {
            color: #28a745;
            font-weight: 500;
        }
        
        .status-pending {
            color: #ffc107;
            font-weight: 500;
        }
        
        .no-records {
            text-align: center;
            padding: 20px;
            color: #dc3545;
            font-weight: 500;
        }
    </style>
</head>
<body>
    <%
    // Session check
    Customer customer = (Customer) session.getAttribute("customer");
    if (customer == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    %>

    <jsp:include page="header.jsp" />

    <div class="bill-history-container">
        <div class="card">
            <div class="card-header">
                <h3><i class="bi bi-clock-history"></i> Bill History</h3>
            </div>
            <div class="card-body">
                <% 
                List<Bills> bills = Operations.viewBill(customer.getConsumerID());
                if (bills != null && !bills.isEmpty()) {
                %>
                    <div class="table-responsive">
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>Bill Number</th>
                                    <th>Bill Date</th>
                                    <th>Due Amount (₹)</th>
                                    <th>Payable Amount (₹)</th>
                                    <th>Payment Status</th>
                                    <th>Payment ID</th>
                                    <th>Payment Date</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% for (Bills bill : bills) { %>
                                <tr>
                                    <td><%= bill.getBillNumber() %></td>
                                    <td><%= bill.getBillUploadDate() != null ? bill.getBillUploadDate() : "-" %></td>
                                    <td><%= String.format("%.2f", bill.getDueAmount()) %></td>
                                    <td><%= String.format("%.2f", bill.getPayableAmount()) %></td>
                                    <td class="<%= "Paid".equalsIgnoreCase(bill.getPaymentStatus()) ? "status-paid" : "status-pending" %>">
                                        <%= bill.getPaymentStatus() %>
                                    </td>
                                    <td><%= bill.getPaymentId() != null ? bill.getPaymentId() : "-" %></td>
                                    <td><%= bill.getPaymentDate() != null ? bill.getPaymentDate() : "-" %></td>
                                </tr>
                                <% } %>
                            </tbody>
                        </table>
                    </div>
                <% } else { %>
                    <div class="no-records">
                        <i class="bi bi-info-circle"></i> No bill history found.
                    </div>
                <% } %>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

<%@ page import="java.util.List, dao.AdminDAO, model.Customer" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Manage Bills</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f8f9fa;
        }
        .container {
            width: 80%;
            margin: 40px auto;
            background: white;
            padding: 20pxs;
            border-radius: 10px;
            box-shadow: 0px 4px 12px rgba(0, 0, 0, 0.1);
            position: relative;
        }
        .home-btn {
            position: absolute;
            top: 10px;
            right: 10px;
            background-color: #e67e22;
            color: white;
            padding: 8px 14px;
            border: none;
            cursor: pointer;
            border-radius: 5px;
            text-decoration: none;
            font-size: 14px;
        }
        .home-btn:hover {
            background-color: #d35400;
        }
        h2 {
            text-align: center;
            color: #2c3e50;
            font-size: 24px;
            margin-bottom: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            background: #ffffff;
            border-radius: 8px;
            overflow: hidden;
        }
        th, td {
            padding: 12px;
            text-align: left;
        }
        th {
            background-color: #2980b9;
            color: white;
        }
        td {
            border-bottom: 1px solid #ddd;
            color: #333;
        }
        tr:hover {
            background-color: #f1f1f1;
        }
        .add-btn {
            background-color: #27ae60;
            color: white;
            padding: 6px 12px;
            border: none;
            cursor: pointer;
            border-radius: 5px;
            text-decoration: none;
            font-size: 14px;
        }
        .add-btn:hover {
            background-color: #219150;
        }
        .no-data {
            text-align: center;
            padding: 15px;
            color: #555;
        }
         .modal {
            position: fixed;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            width: 300px;
            padding: 20px;
            background: white;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
            text-align: center;
            font-size: 16px;
            opacity: 0;
            visibility: hidden;
            transition: opacity 0.5s ease-in-out, transform 0.5s ease-in-out;
        }

        .modal.show {
            opacity: 1;
            visibility: visible;
            transform: translate(-50%, -50%) scale(1.1);
        }

        .overlay {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background: rgba(0, 0, 0, 0.5);
            opacity: 0;
            visibility: hidden;
            transition: opacity 0.5s ease-in-out;
        }

        .overlay.show {
            opacity: 1;
            visibility: visible;
        }

        .modal.success {
            border-left: 5px solid #2ecc71;
            color: #2ecc71;
        }

        .modal.error {
            border-left: 5px solid #e74c3c;
            color: #e74c3c;
        }

        .close-btn {
            margin-top: 10px;
            background-color: #444;
            color: white;
            border: none;
            padding: 5px 10px;
            cursor: pointer;
            border-radius: 5px;
            font-size: 14px;
        }

        .close-btn:hover {
            background-color: #222;
        }
    </style>
</head>
<body>
    <jsp:include page="adminHeader.jsp" />
    
    <div class="container">
        <h2>Manage Bills</h2>

        <table>
            <tr>
                <th>Consumer Number</th>
                <th>Customer Name</th>
                <th>Phone Number</th>
                <th>Action</th>
            </tr>
            <%
                List<Customer> customers = AdminDAO.getAllConsumerNumbers();
                if (customers != null && !customers.isEmpty()) {
                    for (Customer customer : customers) {
            %>
            <tr>
                <td><%= customer.getConsumerID() %></td>
                <td><%= customer.getCustomerName() %></td>
                <td><%= customer.getMobileNumber() %></td>
                <td>
                    <a href="<%= request.getContextPath()%>/addBill.jsp?consumerNumber=<%= customer.getConsumerID() %>" class="add-btn">Add Bill</a>
                </td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="2" class="no-data">No Consumers Found</td>
            </tr>
            <%
                }
            %>
        </table>
    </div>
    <div id="overlay" class="overlay"></div>
    <div id="popup" class="modal">
        <p id="popup-message"></p>
        <button class="close-btn" onclick="closePopup()">Close</button>
    </div>

    <script>
        function showPopup(message, type) {
            let popup = document.getElementById("popup");
            let overlay = document.getElementById("overlay");
            let messageText = document.getElementById("popup-message");

            if (message) {
                messageText.innerHTML = message;

                if (type === "success") {
                    popup.classList.add("success");
                    popup.classList.remove("error");
                } else if (type === "error") {
                    popup.classList.add("error");
                    popup.classList.remove("success");
                }

                popup.classList.add("show");
                overlay.classList.add("show");
            }
        }

        function closePopup() {
            let popup = document.getElementById("popup");
            let overlay = document.getElementById("overlay");
            popup.classList.remove("show");
            overlay.classList.remove("show");
        }
        <% 
            String successMessage = (String) session.getAttribute("message");
            String errorMessage = (String) session.getAttribute("error");

            if (successMessage != null) { 
        %>
            showPopup("<%= successMessage %>", "success");
            <% session.removeAttribute("message"); %>
        <% 
            } else if (errorMessage != null) { 
        %>
            showPopup("<%= errorMessage %>", "error");
            <% session.removeAttribute("error"); %>
        <% } %>
    </script>
    

</body>
</html>

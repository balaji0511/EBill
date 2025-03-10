<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.Customer" %>
<%
    // Get the logged-in customer from the session
    Customer customer = (Customer) session.getAttribute("customer");
    if (customer == null) {
        response.sendRedirect("login.jsp?message=Please log in first.");
        return;
    }
    // The consumer number can be stored in the customer object
    Long consumerNumber = customer.getConsumerNumber();
    
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Register Complaint</title>
    <link rel="stylesheet" href="styles.css"/>
</head>
<body>
    <div class="navbar">
        <div class="navbar-list">
            <a class="navbar-items" href="home.jsp">HOME</a>
            <a class="navbar-items" href="PayBill">PAY BILL</a>
            <a class="navbar-items" href="WEB-INF/pages/register_complaint.jsp">REGISTER COMPLAINT</a>
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
            <p>Register New Complaint</p>
        </div>
        <form id="complaintForm" action="RegisterComplaint" method="post">
            <div class="fields-box">
                <div class="details-box">
                    <label>Complaint Type:</label>
                    <select id="complaintType" name="complaintType" onchange="updateCategories()">
                        <option value="">--Select Type--</option>
                        <option value="Billing">Billing Related</option>
                        <option value="Voltage">Voltage Related</option>
                        <option value="Disruption">Frequent Disruption</option>
                        <option value="Street Light">Street Light Related</option>
                        <option value="Pole">Pole Related</option>
                    </select>
                </div>
                <div class="details-box">
                    <label>Category:</label>
                    <select id="category" name="category">
                        <option value="">--Select Category--</option>
                    </select>
                </div>
            </div>
            <div class="fields-box">
                <div class="details-box">
                    <label>Contact Person:</label>
                    <input type="text" id="contactPerson" placeholder="Contact Person" required name="contactPerson">
                </div>
                <div class="details-box">
                    <label>Landmark:</label>
                    <input type="text" id="landmark" placeholder="Landmark" required name="landmark">
                </div>
            </div>
            <div class="fields-box">
                <!-- Consumer Number is auto-fetched from session (read-only) -->
                <div class="details-box">
                    <label>Consumer Number:</label>
                    <input type="text" id="consumerNumber" name="consumerNumber" value="<%= consumerNumber %>" readonly>
                </div>
                <div class="details-box">
                    <label>Problem Description:</label>
                    <textarea id="problemDescription" placeholder="Description" required name="problemDes"></textarea>
                </div>
            </div>
            <div class="fields-box">
                <div class="details-box">
                    <label>Mobile Number:</label>
                    <input type="text" id="mobileNumber" placeholder="Mobile Number" maxlength="10" pattern="\d{10}" required name="mobileNumber">
                </div>
                <div class="details-box">
                    <label>Address:</label>
                    <textarea id="address" placeholder="Address" required name="address"></textarea>
                </div>  
            </div>
            <div class="submit-box">
                <button type="reset" class="reset" onclick="resetForm()">Cancel</button>
                <button type="submit" class="register">Submit Complaint</button>
            </div>
        </form>
        <% String errorMessage = (String) request.getAttribute("errorMessage"); %>
        <% if (errorMessage != null) { %>
            <div class="error-message">
                <%= errorMessage %>
            </div>
        <% } %> 
    </div>
    <script>
        function resetForm() {
            document.getElementById("complaintForm").reset();
        }
        function updateCategories() {
            const categories = {
                Billing: ["Wrong Bill", "Meter Reading Issue", "Extra Charges"],
                Voltage: ["Low Voltage", "Voltage Fluctuations"],
                Disruption: ["Power Cut", "Transformer Issue"],
                "Street Light": ["Light Not Working", "Damaged Pole"],
                Pole: ["Broken Pole", "Leaning Pole"]
            };
            let complaintType = document.getElementById("complaintType").value;
            let categorySelect = document.getElementById("category");
            categorySelect.innerHTML = '<option value="">--Select Category--</option>';
            if (categories[complaintType]) {
                categories[complaintType].forEach(cat => {
                    let option = document.createElement("option");
                    option.value = cat;
                    option.innerText = cat;
                    categorySelect.appendChild(option);
                });
            }
        }
        function logout() {
            alert("Logging out...");
            window.location.href = "login.jsp";
        }
    </script>
</body>
</html>

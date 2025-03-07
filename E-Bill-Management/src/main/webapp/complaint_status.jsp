<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Complaint Status - Consumer Portal</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <div class="navbar">
        <div class="navbar-list">
            <a class="navbar-items" href="home.jsp">HOME</a>
            <a class="navbar-items" href="pay_bill.jsp">PAY BILL</a>
            <a class="navbar-items" href="register_complaint.jsp">REGISTER COMPLAINT</a>
            <a class="navbar-items" href="complaint_status.jsp">COMPLAINT STATUS</a>
        </div>
        <div class="navbar-user">
            <p>Welcome, <span id="username"></span>!</p>
            <button onclick="logout()">Logout</button>
        </div>
    </div>

    <div class="container">
        <div class="header">
            <p>Check Complaint Status</p>
        </div>
        <label for="complaintID">Enter Complaint ID:</label>
        <input type="text" id="complaintID" placeholder="Enter your Complaint ID" required>
        <div class="submit-box">
            <button type="reset" class="reset" onclick="resetForm()">Cancel</button>
            <button class="register" onclick="checkComplaintStatus()">Check Status</button>
        </div>
        
        <div id="statusResult" class="status-box"></div>
    </div>
    <script>
        document.addEventListener("DOMContentLoaded", function () {
        let username = localStorage.getItem("username");
        if (document.getElementById("username")) {
            document.getElementById("username").innerText = username;
        }
        });

        function resetForm() {
        document.getElementById("complaintForm").reset();
        }
        function checkComplaintStatus() {
        let complaintID = document.getElementById("complaintID").value;
        let statusResult = document.getElementById("statusResult");

        if (complaintID.trim() === "") {
            statusResult.innerHTML = "<p class='error'>Please enter a valid Complaint ID.</p>";
            return;
        }
        let sampleStatuses = {
            "C123": "In Progress",
            "C124": "Resolved",
            "C125": "Pending",
            "C126": "Rejected"
        };

        let status = sampleStatuses[complaintID] || "Complaint ID Not Found!";
        statusResult.innerHTML = `<p><strong>Status:</strong> ${status}</p>`;
        }
        function logout() {
            alert("Logging out...");
            window.location.href = "login.html";
        }
    </script>
</body>
</html>

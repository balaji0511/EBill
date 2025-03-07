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
            <a class="navbar-items" href="home.html">HOME</a>
            <a class="navbar-items" href="pay_bill.html">PAY BILL</a>
            <a class="navbar-items" href="register_complaint.html">REGISTER COMPLAINT</a>
            <a class="navbar-items" href="complaint_status.html">COMPLAINT STATUS</a>
        </div>
        <div class="navbar-user">
            <p>Welcome, <span id="username"></span>!</p>
            <button onclick="logout()">Logout</button>
        </div>
    </div>
    <div class="container">
        <div class="header">
            <p>Payment Done Successfully</p>
        </div>
        <h3>Total Amount paid: ₹<span id="paidAmount"></span></h3>
        <div class="sub-header">
            <p>Transaction Details</p>
        </div>
        <p><strong>Card Holder Name:</strong> <span id="cardHolder"></span></p>
        <p><strong>Total Amount paid: </strong> ₹<span id="paidAmount1"></span></h3>
        <p><strong>Transaction Date:</strong> <span id="transactionDate"></span></p>
        <p>Thank you for your payment!</p>
        <br>
        <div class="submit-box">
            <button onclick="goHome()">Back to Home</button>
            <button onclick="downloadReceipt()">Download Receipt</button>
        </div>
        
    </div>
    <script>
        document.addEventListener("DOMContentLoaded", function () {
            let username = localStorage.getItem("username");
            if (document.getElementById("username")) {
                document.getElementById("username").innerText = username;
            }

            let totalPayable = localStorage.getItem("totalPayable");

            document.getElementById("paidAmount").innerText = totalPayable;
            document.getElementById("paidAmount1").innerText = totalPayable;
            document.getElementById("cardHolder").innerText = localStorage.getItem("cardHolder");
            document.getElementById("transactionDate").innerText = localStorage.getItem("transactionDate");
        });
        function downloadReceipt() {
            let receiptContent = 
                `Payment Receipt\n\n` +
                `Card Holder Name: ${localStorage.getItem("cardHolder")}\n` +
                `Amount Paid: ₹${localStorage.getItem("totalPayable")}\n` +
                `Transaction Date: ${localStorage.getItem("transactionDate")}\n` +
                `Status: Transaction Successful`;

            let blob = new Blob([receiptContent], { type: "text/plain" });
            let link = document.createElement("a");
            link.href = URL.createObjectURL(blob);
            link.download = "Payment_Receipt.txt";
            link.click();
        }

        function logout() {
            alert("Logging out..");
            window.location.href = "login.html";
        }
    </script>
</body>
</html>

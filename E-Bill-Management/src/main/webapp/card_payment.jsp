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
            <p>Payment Screen</p>
        </div>
        <div class="fields-box">
            <div class="payment-box">
                <span>Credit Card Number</span>
                <input type="text" id="cardNumber" minlength="16" maxlength="16" pattern="[0-9]{16}" required>
            </div>
            <div class="payment-box">
                <span>Card Holder Name</span>
                <input type="text" id="cardHolder" minlength="10" required>
            </div>
        </div>
        <div class="fields-box">
            <div class="payment-box">
                <span>Expiry Date</span>
                <div class="expiry-date">
                    <input type="text" id="expiryDateMonth" width="10px" maxlength="2" placeholder="MM" pattern="(0[1-9]|1[0-2])" required>
                    <input type="text" id="expiryDateYear" width="10px" maxlength="4" placeholder="YYYY" pattern="[0-9]{4}" required>
                </div>
            </div>
            <div class="payment-box">
                <span>CVV/CVC</span>
                <input type="text" id="cvv" min="100" max="999" maxlength="3" required>
            </div>
        </div>
        <div class="fields-box">
            
        </div>
        <button onclick="makePayment()">Make Payment</button>
    </div>
<script>
    document.addEventListener("DOMContentLoaded", function () {
    let username = localStorage.getItem("username");
    if (document.getElementById("username")) {
        document.getElementById("username").innerText = username;
    }
    });
    
    function makePayment() {
        let cardNumber = document.getElementById("cardNumber").value;
        let cardHolder = document.getElementById("cardHolder").value;
        let expiryMonth = document.getElementById("expiryDateMonth").value;
        let expiryYear = document.getElementById("expiryDateYear").value;
        let cvv = document.getElementById("cvv").value;
        let amount = localStorage.getItem("totalPayable");

        if (cardNumber.length !== 16 || cardHolder.length < 10 || expiryMonth.length !== 2 || expiryYear.length !== 4 || cvv.length !== 3 || amount <= 0) {
                alert("Invalid Payment Details!");
                return;
        }
        localStorage.setItem("cardHolder", cardHolder);
        localStorage.setItem("transactionDate", new Date().toLocaleString());
        
        alert("Payment Successful!");
        window.location.href = "receipt.html";
    }
    function goHome() {
    window.location.href = "home.html";
    }
    function logout() {
        alert("Logging out..");
        window.location.href = "login.html";
    }   
</script>
</body>
</html>

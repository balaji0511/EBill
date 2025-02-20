//registration
document.addEventListener("DOMContentLoaded", function() {
    
    const registrationForm = document.getElementById("registrationForm");

    if (registrationForm) {
        registrationForm.addEventListener("submit", function (event) {
            event.preventDefault();
            
            let password = document.getElementById("password").value;
            let confirmPassword = document.getElementById("confirmPassword").value;
            let customerName = document.getElementById("customerName").value;
            let mobileNumber = document.getElementById("mobileNumber").value;
            let userId = document.getElementById("userId").value;
            console.log(userId+" "+password);
            if (password !== confirmPassword) {
                alert("Passwords do not match!");
                return;
            }

            let consumerId = Math.floor(1000000000000 + Math.random() * 9000000000000);
            document.getElementById("consumerId").value = consumerId;

            localStorage.setItem("consumerId", consumerId);
            localStorage.setItem("customerName", customerName);
            localStorage.setItem("mobileNumber", mobileNumber);
            localStorage.setItem("userId", userId);
            localStorage.setItem("password", password);

            window.location.href = "registerAcknowledgement.html";
        });
    }
    
});

if (window.location.pathname.includes("registerAcknowledgement.html")) {
    document.getElementById("displayConsumerId").innerText = localStorage.getItem("consumerId");
    document.getElementById("displayCustomerName").innerText = localStorage.getItem("customerName");
    document.getElementById("displayMobile").innerText = localStorage.getItem("mobileNumber");
}


// login 
document.addEventListener("DOMContentLoaded",function(){
    const loginForm=document.getElementById("loginForm");

    if(loginForm){
        loginForm.addEventListener("submit", function (event) {
            event.preventDefault();
            let enteredUserId = document.getElementById("userId").value;
            let enteredPassword = document.getElementById("password").value;
        
            let storedUserId = localStorage.getItem("userId");
            let storedPassword = localStorage.getItem("password");
        
            if (enteredUserId === storedUserId && enteredPassword === storedPassword) {
                localStorage.setItem("username", enteredUserId);
                window.location.href = "home.html"; 
            } else {
                alert("Invalid User ID or Password");
            }
        });
    }
});

document.addEventListener("DOMContentLoaded", function () {
    let username = localStorage.getItem("username");
    if (document.getElementById("username")) {
        document.getElementById("username").innerText = username;
    }
});

document.addEventListener("DOMContentLoaded", function () {
    let billAmount = parseFloat(localStorage.getItem("totalAmount")) || 0;
    let pgCharge = (billAmount * 0.02).toFixed(2);
    let totalPayable = (billAmount + parseFloat(pgCharge)).toFixed(2);

    document.getElementById("billAmount").innerText = billAmount;
    document.getElementById("pgCharge").innerText = pgCharge;
    document.getElementById("totalPayable").innerText = totalPayable;

    localStorage.setItem("totalPayable", totalPayable);
});

function proceedToCardPayment() {
    window.location.href = "card_payment.html";
}

function goHome() {
    window.location.href = "home.html";
}

function logout() {
    localStorage.clear();
    window.location.href = "login.html";
}




function proceedToPayment() {
    window.location.href = "payment.html";
}

function makePayment() {
    alert("Payment Successful!");
    window.location.href = "payment_success.html";
}

function submitComplaint() {
    alert("Complaint Registered Successfully!");
    window.location.href = "home.html";
}


function logout() {
    sessionStorage.clear();
    window.location.href = "index.html";
}

function resetForm() {
    document.getElementById("complaintForm").reset();
}

document.getElementById("complaintForm")?.addEventListener("submit", function (event) {
    event.preventDefault();

    let complaintId = "CMP" + Math.floor(100000 + Math.random() * 900000);  // Generate Unique ID
    sessionStorage.setItem("complaintId", complaintId);

    alert("Complaint Registered Successfully! Your Complaint ID: " + complaintId);
    window.location.href = "complaint_success.html";
});

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

    categorySelect.innerHTML = '<option value="">--Select Category--</option>'; // Reset Categories

    if (categories[complaintType]) {
        categories[complaintType].forEach(category => {
            let option = document.createElement("option");
            option.value = category;
            option.innerText = category;
            categorySelect.appendChild(option);
        });
    }
}


function checkComplaintStatus() {
    let complaintID = document.getElementById("complaintID").value;
    let statusResult = document.getElementById("statusResult");

    if (complaintID.trim() === "") {
        statusResult.innerHTML = "<p class='error'>Please enter a valid Complaint ID.</p>";
        return;
    }

    // Simulating a complaint status check (Replace this with actual backend API integration)
    let sampleStatuses = {
        "C123": "In Progress",
        "C124": "Resolved",
        "C125": "Pending",
        "C126": "Rejected"
    };

    let status = sampleStatuses[complaintID] || "Complaint ID Not Found!";
    statusResult.innerHTML = `<p><strong>Status:</strong> ${status}</p>`;
}


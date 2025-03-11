<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Add Bill</title>
    <style>
      body {
        font-family: "Arial", sans-serif;
        margin: 0;
        padding: 0;
        background-color: #f8f9fa;
      }

      .form-container {
        max-width: 600px;
        margin: 40px auto;
        padding: 30px;
        background: white;
        border-radius: 8px;
        box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
      }

      h2 {
        color: #2c3e50;
        text-align: center;
        margin-bottom: 20px;
      }

      .form-group {
        margin-bottom: 20px;
      }

      label {
        display: block;
        margin-bottom: 8px;
        color: #333;
        font-weight: 500;
      }

      input[type="text"],
      input[type="number"],
      select {
        width: 100%;
        padding: 10px;
        border: 1px solid #ddd;
        border-radius: 4px;
        font-size: 14px;
      }

      .submit-btn {
        background-color: #27ae60;
        color: white;
        padding: 12px 20px;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        width: 100%;
        font-size: 16px;
        transition: background-color 0.3s;
      }

      .submit-btn:hover {
        background-color: #219150;
      }

      .back-btn {
        display: block;
        text-align: center;
        margin-top: 20px;
        color: #3498db;
        text-decoration: none;
      }

      .back-btn:hover {
        text-decoration: underline;
      }

      .modal {
        display: none;
        position: fixed;
        z-index: 1;
        left: 0;
        top: 0;
        width: 100%;
        height: 100%;
        background-color: rgba(0, 0, 0, 0.4);
      }

      .modal-content {
        background-color: #fefefe;
        margin: 15% auto;
        padding: 20px;
        border: 1px solid #888;
        width: 300px;
        text-align: center;
        border-radius: 8px;
      }

      .modal-button {
        padding: 8px 16px;
        background-color: #e74c3c;
        color: white;
        border: none;
        border-radius: 4px;
        cursor: pointer;
      }
    </style>
  </head>
  <body>
    <jsp:include page="adminHeader.jsp" />

    <div class="form-container">
      <h2>Add New Bill</h2>
      <form action="<%= request.getContextPath() %>/AdminServlet" method="post">
        <input type="hidden" name="action" value="addBill" />
        <div class="form-group">
          <label for="billNumber">Bill Number</label>
          <input
            type="number"
            id="billNumber"
            name="billNumber"
            pattern="\d{5}"
            placeholder="Bill number must be exactly 5 digits"
            min="10000"
            max="99999"
            required
          />
        </div>
        <div class="form-group">
          <label for="consumerNumber">Consumer Number</label>
          <input type="number" id="consumerNumber" name="consumerNumber"
          value="<%= request.getParameter("consumerNumber") %>" readonly>
        </div>
        <div class="form-group">
          <label for="dueAmount">Due Amount</label>
          <input
            type="number"
            id="dueAmount"
            name="dueAmount"
            min="10"
            step="0.01"
            required
          />
        </div>
        <div class="form-group">
          <label for="payableAmount">Payable Amount</label>
          <input
            type="number"
            id="payableAmount"
            name="payableAmount"
            min="10"
            step="0.01"
            required
          />
        </div>
        <div class="form-group">
          <label for="paymentStatus">Payment Status</label>
          <select id="paymentStatus" name="paymentStatus" required>
            <option value="Unpaid">Unpaid</option>
            <option value="Paid">Paid</option>
          </select>
        </div>
        <button type="submit" class="submit-btn">Add Bill</button>
        <a
          href="<%= request.getContextPath() %>/manageBills.jsp"
          class="back-btn"
          >Back to Manage Bills</a
        >
      </form>
    </div>

    <div id="errorModal" class="modal">
      <div class="modal-content">
        <p id="errorMessage"></p>
        <button class="modal-button" onclick="closeErrorModal()">Close</button>
      </div>
    </div>
  </body>
</html>

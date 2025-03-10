package model;

import java.time.LocalDateTime;

public class Payment {
    private long consumerNumber;
    private String cardNumber;
    private String cardHolder;
    private String expiryMonth;
    private String expiryYear;
    private String cvv;
    private double totalAmount;
    private String paymentMode;
    private LocalDateTime transactionDate;

    // Getters and setters

    public long getConsumerNumber() {
        return consumerNumber;
    }
    public void setConsumerNumber(long consumerNumber) {
        this.consumerNumber = consumerNumber;
    }
    public String getCardNumber() {
        return cardNumber;
    }
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    public String getCardHolder() {
        return cardHolder;
    }
    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }
    public String getExpiryMonth() {
        return expiryMonth;
    }
    public void setExpiryMonth(String expiryMonth) {
        this.expiryMonth = expiryMonth;
    }
    public String getExpiryYear() {
        return expiryYear;
    }
    public void setExpiryYear(String expiryYear) {
        this.expiryYear = expiryYear;
    }
    public String getCvv() {
        return cvv;
    }
    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
    public double getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
    public String getPaymentMode() {
        return paymentMode;
    }
    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }
    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }
    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }
}

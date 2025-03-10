package model;

public class Bill {
	private int billId;
    private int billNumber;
    private long consumerNumber;
    private double dueAmount;
    private double payableAmount;
    private double totalAmount;
    private String billStatus;
    
    public Bill() {
    	
    }

	public int getBillId() {
		return billId;
	}

	public void setBillId(int billId) {
		this.billId = billId;
	}

	public int getBillNumber() {
		return billNumber;
	}

	public void setBillNumber(int billNumber) {
		this.billNumber = billNumber;
	}

	public long getConsumerNumber() {
		return consumerNumber;
	}

	public void setConsumerNumber(long consumerNumber) {
		this.consumerNumber = consumerNumber;
	}

	public double getDueAmount() {
		return dueAmount;
	}

	public void setDueAmount(double dueAmount) {
		this.dueAmount = dueAmount;
	}

	public double getPayableAmount() {
		return payableAmount;
	}

	public void setPayableAmount(double payableAmount) {
		this.payableAmount = payableAmount;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getBillStatus() {
		return billStatus;
	}

	public void setBillStatus(String billStatus) {
		this.billStatus = billStatus;
	}

	public Bill(int billId, int billNumber, long consumerNumber, double dueAmount, double payableAmount,
			double totalAmount, String billStatus) {
		super();
		this.billId = billId;
		this.billNumber = billNumber;
		this.consumerNumber = consumerNumber;
		this.dueAmount = dueAmount;
		this.payableAmount = payableAmount;
		this.totalAmount = totalAmount;
		this.billStatus = billStatus;
	}
}

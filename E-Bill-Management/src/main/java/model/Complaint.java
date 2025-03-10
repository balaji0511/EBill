package model;

public class Complaint {
	private int complaintId;
    private String complaintType;
    private String category;
    private String customerName;
    private String landmark;
    private long consumerNumber;
    private String problem;
    private String mobileNumber;
    private String address;
    private String complaintStatus;
    
    public Complaint() {
    	
    }
	public int getComplaintId() {
		return complaintId;
	}
	public void setComplaintId(int complaintId) {
		this.complaintId = complaintId;
	}
	public String getComplaintType() {
		return complaintType;
	}
	public void setComplaintType(String complaintType) {
		this.complaintType = complaintType;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getLandmark() {
		return landmark;
	}
	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}
	public long getConsumerNumber() {
		return consumerNumber;
	}
	public void setConsumerNumber(long consumerNumber) {
		this.consumerNumber = consumerNumber;
	}
	public String getProblem() {
		return problem;
	}
	public void setProblem(String problem) {
		this.problem = problem;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getComplaintStatus() {
		return complaintStatus;
	}
	public void setComplaintStatus(String complaintStatus) {
		this.complaintStatus = complaintStatus;
	}
	public Complaint(int complaintId, String complaintType, String category, String customerName, String landmark,
			long consumerNumber, String problem, String mobileNumber, String address,
			String complaintStatus) {
		super();
		this.complaintId = complaintId;
		this.complaintType = complaintType;
		this.category = category;
		this.customerName = customerName;
		this.landmark = landmark;
		this.consumerNumber = consumerNumber;
		this.problem = problem;
		this.mobileNumber = mobileNumber;
		this.address = address;
		this.complaintStatus = complaintStatus;
	}
	    
}

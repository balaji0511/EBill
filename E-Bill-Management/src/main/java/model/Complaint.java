package model;

public class Complaint {
	private int complaintId;
    private String complaintType;
    private String category;
    private String customerName;
    private String landmark;
    private int consumerNumber;
    private String problemDescription;
    private String mobileNumber;
    private String address;
    
	public Complaint(int complaintId, String complaintType, String category, String customerName, String landmark,
			int consumerNumber, String problemDescription, String mobileNumber, String address) {
		super();
		this.complaintId = complaintId;
		this.complaintType = complaintType;
		this.category = category;
		this.customerName = customerName;
		this.landmark = landmark;
		this.consumerNumber = consumerNumber;
		this.problemDescription = problemDescription;
		this.mobileNumber = mobileNumber;
		this.address = address;
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

	public int getConsumerNumber() {
		return consumerNumber;
	}

	public void setConsumerNumber(int consumerNumber) {
		this.consumerNumber = consumerNumber;
	}

	public String getProblemDescription() {
		return problemDescription;
	}

	public void setProblemDescription(String problemDescription) {
		this.problemDescription = problemDescription;
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
    
	
    
}

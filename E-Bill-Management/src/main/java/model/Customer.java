package model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Customer {
	private long customerId;
	private long consumerNumber;
    private String title;
    private String customerName;
    private String email;
    private String mobileNumber;
    private String userId;
    private String password;
    private String confirmPassword;
    private String customerStatus;
    private String userType;
    
    public Customer() {
    	
    }

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public long getConsumerNumber() {
		return consumerNumber;
	}

	public void setConsumerNumber(long consumerNumber) {
		this.consumerNumber = consumerNumber;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getCustomerStatus() {
		return customerStatus;
	}

	public void setCustomerStatus(String customerStatus) {
		this.customerStatus = customerStatus;
	}
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Customer(long customerId, long consumerNumber, String title, String customerName, String email,
			String mobileNumber, String userId, String password, String confirmPassword, String customerStatus) {
		super();
		this.customerId = customerId;
		this.consumerNumber = consumerNumber;
		this.title = title;
		this.customerName = customerName;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.userId = userId;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.customerStatus = customerStatus;
	}
	
	public Customer(long customerId2, String title2, String customerName2, String email2, String mobileNumber2,
			String userId2, String password2) {
		super();
		this.customerId = customerId2;
		this.title = title2;
		this.customerName = customerName2;
		this.email = email2;
		this.mobileNumber = mobileNumber2;
		this.userId = userId2;
		this.password = password2;
	}

	public Customer(ResultSet rs) throws SQLException {
	    this.consumerNumber = rs.getLong("consumerNumber");
	    this.email = rs.getString("email");
	    this.userId = rs.getString("userId");
	    this.password = rs.getString("password");
	    this.userType = rs.getString("userType");
	    this.customerStatus = rs.getString("userStatus");
	}

	
}

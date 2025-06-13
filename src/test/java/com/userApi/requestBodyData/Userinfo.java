package com.userApi.requestBodyData;

public class Userinfo {
	
	private String userFirstName,userLastName,userContactNumber,userEmailId;
    UserAddressInfo userAddress;
	
	public Userinfo(String userFirstName, String userLastName, String userContactNumber, String userEmailId, UserAddressInfo userAddress) {
	    this.userFirstName = userFirstName;
	    this.userLastName = userLastName;
	    this.userContactNumber = userContactNumber;
	    this.userEmailId = userEmailId;
	    this.userAddress = userAddress;
	}
	
	public String getUserFirstName() {
		return userFirstName;
	}
	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}
	public String getUserLastName() {
		return userLastName;
	}
	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}
	public String getUserContactNumber() {
		return userContactNumber;
	}
	public void setUserContactNumber(String userContactNumber) {
		this.userContactNumber = userContactNumber;
	}
	public String getUserEmailId() {
		return userEmailId;
	}
	public void setUserEmailId(String userEmailId) {
		this.userEmailId = userEmailId;
	}
	public UserAddressInfo getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(UserAddressInfo userAddress) {
		this.userAddress = userAddress;
	}

}





















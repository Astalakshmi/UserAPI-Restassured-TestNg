package com.userApi.requestBodyData;

public class UserAddressInfo {
	
private String plotNumber,street,state,country,zipCode;
	
public UserAddressInfo(String plotNumber, String street, String state, String country, String zipCode) {
		
		this.plotNumber = plotNumber;
		this.street = street;
		this.state = state;
		this.country = country;
		this.zipCode = zipCode;
	}



public String getPlotNumber() {
	return plotNumber;
}

public void setPlotNumber(String plotNumber) {
	this.plotNumber = plotNumber;
}

public String getStreet() {
	return street;
}

public void setStreet(String street) {
	this.street = street;
}

public String getState() {
	return state;
}

public void setState(String state) {
	this.state = state;
}

public String getCountry() {
	return country;
}

public void setCountry(String country) {
	this.country = country;
}

public String getZipCode() {
	return zipCode;
}

public void setZipCode(String zipCode) {
	this.zipCode = zipCode;
}
	
}







//{
//	  "userId": 0,
//	  "userFirstName": "string",
//	  "userLastName": "string",
//	  "userContactNumber": 0,
//	  "userEmailId": "string",
//	  "creationTime": "2025-06-09T19:13:56.381Z",
//	  "lastModTime": "2025-06-09T19:13:56.381Z",
//	  "userAddress": {
//	    "addressId": 0,
//	    "plotNumber": "string",
//	    "street": "string",
//	    "state": "string",
//	    "country": "string",
//	    "zipCode": 0
//	  }
//	}
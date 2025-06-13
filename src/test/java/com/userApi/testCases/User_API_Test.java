package com.userApi.testCases;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.userApi.utilities.Dataprovider;
import com.userApi.utilities.LoggerLoad;
import static io.restassured.RestAssured.*;
import com.userApi.requestBodyData.UserAddressInfo;
import com.userApi.requestBodyData.Userinfo;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class User_API_Test extends BaseTest{

	int UsrID;String Name;
	
	@Test(priority = 1, dataProvider = "GetUser", dataProviderClass = Dataprovider.class)
	public void GetAllUsersTest(Map<String, String> rowData) {
	
		int code = Double.valueOf(rowData.get("Code")).intValue();
		
		String LineMsg =rowData.get("Linemsg");
		String Scenario = rowData.get("Scenario");
		
		LoggerLoad.info("GET REQUEST: "+Scenario);
		if ("Valid".equals(Scenario)) {
			given().auth().basic(Username,Password)
			.when().get(BaseURL+SearchAllEp)
			.then().statusCode(code).statusLine(LineMsg).header("Content-Type", Json);
			
		}
		else if("NoAuth".equals(Scenario)) {
				given().auth().basic(eUserName,ePassword)
				.when().get(BaseURL+SearchAllEp)
				.then().statusCode(code).statusLine(LineMsg).header("Content-Type", Json);
				
		}
		else if("EmptyUsr".equals(Scenario)) {	
				given().auth().basic(eUserName,Password)
				.when().get(BaseURL+SearchAllEp)
				.then().statusCode(code).statusLine(LineMsg).header("Content-Type", Json);	
				
		}
		else if("EmptyPswd".equals(Scenario)) {
				given().auth().basic(Username,ePassword)
				.when().get(BaseURL+SearchAllEp)
				.then().statusCode(code).statusLine(LineMsg).header("Content-Type", Json);	
				
		}
		else if("IncorrectEndPoint".equals(Scenario)) {
			given().auth().basic(Username,Password)
			.when().get(BaseURL+SearchAllEp+"invalid")
			.then().statusCode(code).statusLine(LineMsg).header("Content-Type", Json);
			
		}
		else if("IncorrectBaseURL".equals(Scenario)) {
			given().auth().basic(Username,Password)
			.when().get(InBaseURL+SearchAllEp)
			.then().statusCode(code).statusLine(LineMsg);
			
		}	

	}

@Test(priority = 2, dataProvider = "PostUser", dataProviderClass = Dataprovider.class)
public void CreateUser(Map<String, String> rowData) {
	
	int code = Double.valueOf(rowData.get("Code")).intValue();
	String LineMsg =rowData.get("Linemsg");
	String Scenario = rowData.get("Scenario");
	String FName = rowData.get("FirstName");
	String LName = rowData.get("LastName");
	String Phone = rowData.get("Phno");			
	String Mail = rowData.get("Email");
	String Plot = rowData.get("Plot");
	String Street = rowData.get("Street");
	String State = rowData.get("State");
	String Country = rowData.get("Country");
	String Zip = rowData.get("Zip");
	
	LoggerLoad.info("POST REQUEST: "+Scenario);
	UserAddressInfo address = new UserAddressInfo(Plot,Street,State,Country,Zip);
	Userinfo data = new Userinfo(FName,LName,Phone,Mail,address);

	if("Valid".equals(Scenario)){	
		Response response =
		given().auth().basic(Username,Password).header("Content-Type", Json).body(data).log().all()
		.when().post(BaseURL+CreateEP)
		.then().assertThat().statusCode(code).statusLine(LineMsg).header("Content-Type", Json).extract().response();

		JsonPath jsonPath = response.jsonPath();
		Assert.assertEquals(jsonPath.getString("userFirstName"), FName);
		Assert.assertEquals(jsonPath.getString("userLastName"), LName);
		Assert.assertEquals(jsonPath.getString("userContactNumber"), Phone);
		Assert.assertEquals(jsonPath.getString("userEmailId"), Mail);
		Assert.assertEquals(jsonPath.getString("userAddress.plotNumber"), Plot);
		Assert.assertEquals(jsonPath.getString("userAddress.street"), Street);
		Assert.assertEquals(jsonPath.getString("userAddress.state"), State);
		Assert.assertEquals(jsonPath.getString("userAddress.country"), Country);
		Assert.assertEquals(jsonPath.getString("userAddress.zipCode"), Zip);
		UsrID = jsonPath.get("userId");
		//Name = jsonPath.get("userFirstName");
		
	}
					
	else if("ValidName".equals(Scenario)){	
		Name=
		given().auth().basic(Username,Password).header("Content-Type", Json).body(data).log().all()
		.when().post(BaseURL+CreateEP)
		.then().assertThat().statusCode(code).statusLine(LineMsg).header("Content-Type", Json).extract().path("userFirstName");
		
	}
	else {
		given().auth().basic(Username,Password).header("Content-Type", Json).body(data).log().all()
		.when().post(BaseURL+CreateEP)
		.then().statusCode(code).statusLine(LineMsg).header("Content-Type", Json);
	}
}

@Test(priority = 3, dataProvider = "GetUser", dataProviderClass = Dataprovider.class)
public void GetUserByIdTest(Map<String, String> rowData) {
	int code = Double.valueOf(rowData.get("Code")).intValue();
	String LineMsg =rowData.get("Linemsg");
	String Scenario = rowData.get("Scenario");
	
	LoggerLoad.info("GET REQUEST By ID: "+Scenario);

	if ("Valid".equals(Scenario)) {
		given().auth().basic(Username,Password).pathParam("id", UsrID).log().all()
		.when().get(BaseURL+SearchByIdEP)
		.then().assertThat().statusCode(code).statusLine(LineMsg).header("Content-Type", Json);
		
	}
	else if("NoAuth".equals(Scenario)) {
			given().auth().basic(eUserName,ePassword).pathParam("id", UsrID).log().all()
			.when().get(BaseURL+SearchByIdEP)
			.then().statusCode(code).statusLine(LineMsg).header("Content-Type", Json);
	}
	else if("EmptyUsr".equals(Scenario)) {
			given().auth().basic(eUserName,Password).pathParam("id", UsrID).log().all()
			.when().get(BaseURL+SearchByIdEP)
			.then().statusCode(code).statusLine(LineMsg).header("Content-Type", Json);
	}
	else if("EmptyPswd".equals(Scenario)) {
			given().auth().basic(Username,ePassword).pathParam("id", UsrID).log().all()
			.when().get(BaseURL+SearchByIdEP)
			.then().statusCode(code).statusLine(LineMsg).header("Content-Type", Json);
	}
	else if("IncorrectEndPoint".equals(Scenario)) {
		given().auth().basic(Username,Password).pathParam("id", UsrID).log().all()
		.when().get(BaseURL+SearchByIdEP+"00001")
		.then().statusCode(code).statusLine(LineMsg).header("Content-Type", Json);
	}
	else if("IncorrectBaseURL".equals(Scenario)) {
		given().auth().basic(Username,Password).pathParam("id", UsrID).log().all()
		.when().get(InBaseURL+SearchByIdEP)
		.then().statusCode(code).statusLine(LineMsg);
	}	
	else if("NonExistingUser".equals(Scenario)) {
		given().auth().basic(Username,Password).pathParam("id", UsrID+1000).log().all()
		.when().get(BaseURL+SearchByIdEP)
		.then().statusCode(code).statusLine(LineMsg).header("Content-Type", Json);
	}	
}

@Test(priority = 4, dataProvider = "GetUser", dataProviderClass = Dataprovider.class)
public void GetUserByFNameTest(Map<String, String> rowData) {
	int code = Double.valueOf(rowData.get("Code")).intValue();
	String LineMsg =rowData.get("Linemsg");
	String Scenario = rowData.get("Scenario");
	
	LoggerLoad.info("GET REQUEST By Name: "+Scenario);

	if ("Valid".equals(Scenario)) {
		given().auth().basic(Username,Password).pathParam("fname", Name).log().all()
		.when().get(BaseURL+SearchByNameEP)
		.then().assertThat().statusCode(code).statusLine(LineMsg).header("Content-Type", Json);
	}
	else if("NoAuth".equals(Scenario)) {
			given().auth().basic(eUserName,ePassword).pathParam("fname", Name).log().all()
			.when().get(BaseURL+SearchByNameEP)
			.then().statusCode(code).statusLine(LineMsg).header("Content-Type", Json);
	}
	else if("EmptyUsr".equals(Scenario)) {
			given().auth().basic(eUserName,Password).pathParam("fname", Name).log().all()
			.when().get(BaseURL+SearchByNameEP)
			.then().statusCode(code).statusLine(LineMsg).header("Content-Type", Json);
	}
	else if("EmptyPswd".equals(Scenario)) {
			given().auth().basic(Username,ePassword).pathParam("fname", Name).log().all()
			.when().get(BaseURL+SearchByNameEP)
			.then().statusCode(code).statusLine(LineMsg).header("Content-Type", Json);
	}
	else if("IncorrectEndPoint".equals(Scenario)) {
		given().auth().basic(Username,Password).pathParam("fname", Name).log().all()
		.when().get(BaseURL+SearchByNameEP+"$invalid")
		.then().statusCode(code).statusLine(LineMsg).header("Content-Type", Json);
	}
	else if("IncorrectBaseURL".equals(Scenario)) {
		given().auth().basic(Username,Password).pathParam("fname", Name).log().all()
		.when().get(InBaseURL+SearchByNameEP)
		.then().statusCode(code).statusLine(LineMsg);
	}	
	else if("NonExistingUser".equals(Scenario)) {
		given().auth().basic(Username,Password).pathParam("fname", Name+"API").log().all()
		.when().get(BaseURL+SearchByNameEP)
		.then().statusCode(code).statusLine(LineMsg).header("Content-Type", Json);
	}	
}


@Test(priority = 5, dataProvider = "UpdateUser", dataProviderClass = Dataprovider.class)
public void UpdateUser(Map<String, String> rowData) {
	
	int code = Double.valueOf(rowData.get("Code")).intValue();
	String LineMsg =rowData.get("Linemsg");
	String Scenario = rowData.get("Scenario");
	String FName = rowData.get("FirstName");
	String LName = rowData.get("LastName");
	String Phone = rowData.get("Phno");			
	String Mail = rowData.get("Email");
	String Plot = rowData.get("Plot");
	String Street = rowData.get("Street");
	String State = rowData.get("State");
	String Country = rowData.get("Country");
	String Zip = rowData.get("Zip");
	
	LoggerLoad.info("PUT REQUEST: "+Scenario);
	UserAddressInfo address = new UserAddressInfo(Plot,Street,State,Country,Zip);
	Userinfo data = new Userinfo(FName,LName,Phone,Mail,address);
	
	if("Valid".equals(Scenario)){	
		
		given().auth().basic(Username,Password).pathParam("userId", UsrID).header("Content-Type", Json).body(data).log().all()
		.when().put(BaseURL+UpdateEP)
		.then().assertThat().statusCode(code).statusLine(LineMsg).header("Content-Type", Json).extract().response();
	}
	else {
		given().auth().basic(Username,Password).pathParam("userId", UsrID).header("Content-Type", Json).body(data).log().all()
		.when().put(BaseURL+UpdateEP)
		.then().statusCode(code).statusLine(LineMsg).header("Content-Type", Json);
	}
}

@Test(priority = 6, dataProvider = "PatchUser", dataProviderClass = Dataprovider.class)
public void PatchUser(Map<String, String> rowData) {

	int code = Double.valueOf(rowData.get("Code")).intValue();
    String lineMsg = rowData.get("Linemsg");
    String scenario = rowData.get("Scenario");

    LoggerLoad.info("PATCH REQUEST for Scenario: " + scenario);

    //  patch data
    Map<String, Object> patchData = new HashMap<>();

    if (rowData.get("FirstName") != null && !rowData.get("FirstName").isEmpty())
        patchData.put("userFirstName", rowData.get("FirstName"));

    if (rowData.get("LastName") != null && !rowData.get("LastName").isEmpty())
        patchData.put("userLastName", rowData.get("LastName"));

    if (rowData.get("Phno") != null && !rowData.get("Phno").isEmpty())
        patchData.put("userContactNumber", rowData.get("Phno"));

    if (rowData.get("Email") != null && !rowData.get("Email").isEmpty())
        patchData.put("userEmailId", rowData.get("Email"));

    // Address
    Map<String, String> address = new HashMap<>();
    if (rowData.get("Plot") != null && !rowData.get("Plot").isEmpty())
        address.put("plotNumber", rowData.get("Plot"));

    if (rowData.get("Street") != null && !rowData.get("Street").isEmpty())
        address.put("street", rowData.get("Street"));

    if (rowData.get("State") != null && !rowData.get("State").isEmpty())
        address.put("state", rowData.get("State"));

    if (rowData.get("Country") != null && !rowData.get("Country").isEmpty())
        address.put("country", rowData.get("Country"));

    if (rowData.get("Zip") != null && !rowData.get("Zip").isEmpty())
        address.put("zipCode", rowData.get("Zip"));

    if (!address.isEmpty()) {
        patchData.put("userAddress", address);
    }

    
    if ("Valid".equalsIgnoreCase(scenario)) {
        try {
            Response response = given()
                    .auth().basic(Username, Password).pathParam("id", UsrID).header("Content-Type", Json).body(patchData).log().all()
                    .when().patch(BaseURL + PatchEP)
                    .then().assertThat().statusCode(code).statusLine(lineMsg).header("Content-Type", Json).extract().response();

            LoggerLoad.info("PATCH Response: " + response.asString());

        } catch (Exception e) {
            LoggerLoad.error("PATCH failed: " + e.getMessage());
            throw e;
        }

    } 
    
    else {
		given().auth().basic(Username,Password).pathParam("id", UsrID).header("Content-Type", Json).body(patchData).log().all()
		.when().patch(BaseURL+PatchEP)
		.then().statusCode(code).statusLine(lineMsg).header("Content-Type", Json);
	}
}



@Test(priority = 7, dataProvider = "DeleteUser", dataProviderClass = Dataprovider.class)
public void DeleteUserByIdTest(Map<String, String> rowData) {
	int code = Double.valueOf(rowData.get("Code")).intValue();
	String LineMsg =rowData.get("Linemsg");
	String Scenario = rowData.get("Scenario");
	LoggerLoad.info("DELETE REQUEST By ID: "+Scenario);

	if ("Valid".equals(Scenario)) {
		given().auth().basic(Username,Password).pathParam("id", UsrID).log().all()
		.when().delete(BaseURL+DeleteByIdEP)
		.then().assertThat().statusCode(code).statusLine(LineMsg).header("Content-Type", Json);
	}
	else if("NoAuth".equals(Scenario)) {
		given().auth().basic(eUserName,ePassword).pathParam("id", UsrID).log().all()
		.when().delete(BaseURL+DeleteByIdEP)
		.then().statusCode(code).statusLine(LineMsg).header("Content-Type", Json);
	}
	else if("EmptyUsr".equals(Scenario)) {
			given().auth().basic(eUserName,Password).pathParam("id", UsrID).log().all()
			.when().delete(BaseURL+DeleteByIdEP)
			.then().statusCode(code).statusLine(LineMsg).header("Content-Type", Json);
	}
	else if("EmptyPswd".equals(Scenario)) {
			given().auth().basic(Username,ePassword).pathParam("id", UsrID).log().all()
			.when().delete(BaseURL+DeleteByIdEP)
			.then().statusCode(code).statusLine(LineMsg).header("Content-Type", Json);
	}
	else if("IncorrectEndPoint".equals(Scenario)) {
		given().auth().basic(Username,Password).pathParam("id", UsrID).log().all()
		.when().delete(BaseURL+DeleteByIdEP+"101010")
		.then().statusCode(code).statusLine(LineMsg).header("Content-Type", Json);
	}
	else if("IncorrectBaseURL".equals(Scenario)) {
		given().auth().basic(Username,Password).pathParam("id", UsrID).log().all()
		.when().delete(InBaseURL+DeleteByIdEP)
		.then().statusCode(code).statusLine(LineMsg);
	}	
	else if("NonExistingUser".equals(Scenario)) {
		given().auth().basic(Username,Password).pathParam("id", UsrID+1000).log().all()
		.when().delete(BaseURL+DeleteByIdEP)
		.then().statusCode(code).statusLine(LineMsg).header("Content-Type", Json);
	}	
	
}

@Test(priority = 8, dataProvider = "DeleteUser", dataProviderClass = Dataprovider.class)
public void DeleteUserByFNameTest(Map<String, String> rowData) {
	int code = Double.valueOf(rowData.get("Code")).intValue();
	String LineMsg =rowData.get("Linemsg");
	String Scenario = rowData.get("Scenario");
	LoggerLoad.info("DELETE REQUEST By Name: "+Scenario);

	if ("Valid".equals(Scenario)) {
		given().auth().basic(Username,Password).pathParam("fname", Name).log().all()
		.when().delete(BaseURL+DeleteByNameEP)
		.then().assertThat().statusCode(code).statusLine(LineMsg).header("Content-Type", Json);
	}
	else if("NoAuth".equals(Scenario)) {
		given().auth().basic(eUserName,ePassword).pathParam("fname", Name).log().all()
		.when().delete(BaseURL+DeleteByNameEP)
		.then().statusCode(code).statusLine(LineMsg).header("Content-Type", Json);
	}
	else if("EmptyUsr".equals(Scenario)) {
			given().auth().basic(eUserName,Password).pathParam("fname", Name).log().all()
			.when().delete(BaseURL+DeleteByNameEP)
			.then().statusCode(code).statusLine(LineMsg).header("Content-Type", Json);
	}
	else if("EmptyPswd".equals(Scenario)) {
			given().auth().basic(Username,ePassword).pathParam("fname", Name).log().all()
			.when().delete(BaseURL+DeleteByNameEP)
			.then().statusCode(code).statusLine(LineMsg).header("Content-Type", Json);
	}
	else if("IncorrectEndPoint".equals(Scenario)) {
		given().auth().basic(Username,Password).pathParam("fname", Name).log().all()
		.when().delete(BaseURL+DeleteByNameEP+"$invalid")
		.then().statusCode(code).statusLine(LineMsg).header("Content-Type", Json);
	}
	else if("IncorrectBaseURL".equals(Scenario)) {
		given().auth().basic(Username,Password).pathParam("fname", Name).log().all()
		.when().delete(InBaseURL+DeleteByNameEP)
		.then().statusCode(code).statusLine(LineMsg);
	}	
	else if("NonExistingUser".equals(Scenario)) {
		given().auth().basic(Username,Password).pathParam("fname", Name+"API").log().all()
		.when().delete(BaseURL+DeleteByNameEP)
		.then().statusCode(code).statusLine(LineMsg).header("Content-Type", Json);
	}	
	
}


}
		
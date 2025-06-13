package com.userApi.utilities;


import java.util.List;
import java.util.Map;
import org.testng.annotations.DataProvider;

import com.userApi.testCases.*;

public class Dataprovider extends BaseTest{

	
    @DataProvider(name = "GetUser")
    public Object[][] getDataSearch() {
        return convertToDataProviderFormat(Get);
    }

    @DataProvider(name = "PostUser")
    public Object[][] getDataCreate() {
        return convertToDataProviderFormat(Post);
    }
    
    @DataProvider(name = "UpdateUser")
    public Object[][] getDataUpdate() {
        return convertToDataProviderFormat(Put);
    }
    
    @DataProvider(name = "PatchUser")
    public Object[][] getDataPatch() {
        return convertToDataProviderFormat(Patch);
    }
    
    @DataProvider(name = "DeleteUser")
    public Object[][] getDataDelete() {
        return convertToDataProviderFormat(Delete);
    }
  
    private Object[][] convertToDataProviderFormat(List<Map<String, String>> data) {
        Object[][] dataProvider = new Object[data.size()][1];

        for (int i = 0; i < data.size(); i++) {
            dataProvider[i][0] = data.get(i);          }
		return dataProvider;
	}
	
}
	
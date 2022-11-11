package com.johndeere.utilities;

import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

public class DataUtils {
	//will start at 11:45 AM IST
	@DataProvider
	public Object[][] commonDataProvider(Method mtd) throws IOException
	{
		//@Test method name is the sheetname 
		String testMethodName=mtd.getName();
		Object[][] arr= ExcelUtils.getSheetIntoTwoDimArray("test-data/orange_data.xlsx", testMethodName);
		return arr;
	}
	
	
	@DataProvider
	public Object[][] invalidLoginData()
	{
		Object[][] arr=new Object[2][3];
		//i->number of test case
		//j->number of arguments/parameters
		
		arr[0][0]="saul";
		arr[0][1]="saul123";
		arr[0][2]="Invalid credentials";
		
		arr[1][0]="Kim";
		arr[1][1]="Kim123";
		arr[1][2]="Invalid credentials";
		
		return arr;
	}

}

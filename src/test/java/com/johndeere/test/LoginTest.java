package com.johndeere.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.johndeere.base.AutomationWrapper;
import com.johndeere.pages.LoginPage;
import com.johndeere.pages.MainPage;
import com.johndeere.utilities.DataUtils;

public class LoginTest extends AutomationWrapper {

	@Test(dataProvider = "commonDataProvider", dataProviderClass = DataUtils.class, groups = { "login", "smoke" })
	public void validLoginTest(String username, String password, String expectedUrl) {

		LoginPage loginPage = new LoginPage(driver);

		loginPage.enterUsername(username);
		loginPage.enterPassword(password);
		loginPage.clickOnLogin();

		MainPage mainPage=new MainPage(driver);
		
		mainPage.waitForPresenceOfPIMMenu();
		String actualUrl = mainPage.getMainPageUrl();
		
		Assert.assertEquals(actualUrl, expectedUrl);
	}

	@Test(dataProvider = "commonDataProvider", dataProviderClass = DataUtils.class, groups = { "login", "invalid" })
	public void invalidLoginTest(String username, String password, String expectedError) {

		LoginPage loginPage = new LoginPage(driver);

		loginPage.enterUsername(username);
		loginPage.enterPassword(password);
		loginPage.clickOnLogin();

		String actualError = loginPage.getInvalidErrorMessage();
		Assert.assertEquals(actualError, expectedError);
	}
}

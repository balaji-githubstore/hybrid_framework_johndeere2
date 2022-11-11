package com.johndeere.test;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.johndeere.base.AutomationWrapper;
import com.johndeere.utilities.DataUtils;

public class LoginTest extends AutomationWrapper {

	@Test(dataProvider = "commonDataProvider",dataProviderClass = DataUtils.class,groups = {"login","smoke"})
	public void validLoginTest(String username,String password,String expectedUrl) {
		driver.findElement(By.name("username")).sendKeys(username);
		driver.findElement(By.cssSelector("input[name='password']")).sendKeys(password);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		// wait for page load to complete
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl);
	}

	@Test(dataProvider = "commonDataProvider",dataProviderClass = DataUtils.class,groups = {"login","invalid"})
	public void invalidLoginTest(String username, String password, String expectedError) {
		driver.findElement(By.name("username")).sendKeys(username);
		driver.findElement(By.cssSelector("input[name='password']")).sendKeys(password);
		driver.findElement(By.xpath("//button[@type='submit']")).click();

		String actualError = driver.findElement(By.xpath("//p[contains(@class,'oxd-alert-content')]")).getText();
		Assert.assertEquals(actualError, expectedError);
	}
}

package com.Hemanth.H_ReusableCode;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PageObject.PageObject;
import PageObject.excelDataConfig;

public class DataDrivenFramework extends PageObject{

	excelDataConfig config;
	int counter=0;

	@BeforeMethod
	public void setup() throws Exception {
		getBrowser();
		driver.get(getPropertyValue("wordpress"));
	}

	//its takes the data from data provider and feeds to this method
	@Test(dataProvider="excelConfigData")
	public void showData(String username, String password) throws InterruptedException, IOException {
		//		System.out.println(counter);
		driver.findElement(By.id("user_login")).sendKeys(username);
		driver.findElement(By.id("user_pass")).sendKeys(password);
		driver.findElement(By.id("wp-submit")).click();
		Thread.sleep(5000);
		if (verifyTitle()==true) {
			config.writeData(System.getProperty("user.dir")+"//ExcelFiles//demo1.xlsx", counter, "User succesfully logged in");
			System.out.println("************"+"User succesfully logged in");
		} else {
			config.writeData(System.getProperty("user.dir")+"//ExcelFiles//demo1.xlsx", counter, getErrorText());
			System.out.println("************"+getErrorText());
		}
		counter++;
	}

	@AfterMethod
	public void tearDown( ) {
		driver.close();
	}

	@DataProvider(name="excelConfigData")
	public Object[][] getData() throws IOException {

		config = new excelDataConfig(System.getProperty("user.dir")+"//ExcelFiles//demo1.xlsx");

		int row = config.getRowCount(0);

		Object[][] data = new Object[row][2];

		for (int i = 0; i < row; i++) {
			data[i][0] = config.getDataFromExcel(0, i, 0);
			data[i][1] = config.getDataFromExcel(0, i, 1);
		}
		return data;
	}

	public Boolean verifyTitle() {
		String getTitle = driver.getTitle();
		//		System.out.println(getTitle);
		if (getTitle.contains("Dashboard")) {
			return true;
		} else {
			return false;
		}
	}

	public String getErrorText() {
		return driver.findElement(By.id("login_error")).getText();
	}
}

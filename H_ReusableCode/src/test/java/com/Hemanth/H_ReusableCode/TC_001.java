package com.Hemanth.H_ReusableCode;

import java.io.IOException;
import org.testng.annotations.Test;

import PageObject.PageObject;

public class TC_001 extends PageObject {
	@Test
	public void setUp() throws IOException {	
		getBrowser();
		driver.get(getPropertyValue("url"));
	}
}

package appiumMarch;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;

import util.ReadExcel;

public class AddContact {



	AndroidDriver<WebElement> driver;



	@BeforeMethod
	public void setCapability() throws MalformedURLException{
		// TODO Auto-generated method stub


		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("appPackage", "com.google.android.contacts");
		capabilities.setCapability("appActivity", "com.android.contacts.activities.PeopleActivity"); 
		capabilities.setCapability("deviceName","0123456789ABCDEF");

		driver = new AndroidDriver<WebElement>(new URL("http://0.0.0.0:4723/wd/hub"),capabilities);

	}

	@Test(dataProvider ="DSPH_DSPHI021")
	public void contactNumber(String firstname,String number) throws MalformedURLException, InterruptedException {
		driver.findElementById("com.android.contacts:id/floating_action_button").click();
		driver.findElementByXPath("//android.widget.EditText[@text='First name']").sendKeys(firstname);
		//driver.findElementByXPath("//android.widget.EditText[@text='Last name']").sendKeys(lastname);
		driver.hideKeyboard();
		driver.findElementByXPath("//android.widget.EditText[@text='Phone']").sendKeys(number);
		driver.findElementById("com.android.contacts:id/editor_menu_save_button").click();
		String contactName= driver.findElementById("com.android.contacts:id/photo_touch_intercept_overlay").getAttribute("name");
		if (contactName.equalsIgnoreCase(firstname)) {
			System.out.println(firstname+" "+number+" "+"Contact added successfully");

		} else {
			System.out.println(firstname+" "+number+" "+"Contact could not added");
		}


	}
	@AfterMethod
	public void endtest(){
		driver.quit();
	}




	@DataProvider(name = "DSPH_DSPHI021")
	public Object[][] fetchData() throws IOException, InvalidFormatException {

		ReadExcel dd = new ReadExcel();

		return dd.readData();

	}}
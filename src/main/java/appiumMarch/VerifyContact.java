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

public class VerifyContact {
	
	
	
	AndroidDriver<WebElement> driver;
	
	

	@BeforeMethod
	public void setCapability() throws MalformedURLException{
		// TODO Auto-generated method stub
		

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("appPackage", "com.google.android.googlequicksearchbox");
		capabilities.setCapability("appActivity", "com.android.contacts"); 
		capabilities.setCapability("deviceName","5489f632");

		driver = new AndroidDriver<WebElement>(new URL("http://0.0.0.0:4723/wd/hub"),capabilities);
		
	}

	@Test(dataProvider ="DSPH_DSPHI021")
	public void contactNumber(String firstname,String lastname,String num) throws MalformedURLException, InterruptedException {
		driver.findElementById("android.widget.EditText").sendKeys(firstname+" "+lastname);
		driver.findElementById("com.android.contacts:id/cliv_name_textview");
		driver.findElementById("com.android.contacts:id/cliv_name_textview").click();
		String txt=driver.findElementById("com.android.contacts:id/data").getText();
		if(txt.equalsIgnoreCase(num)){
			System.out.println("the contact is matching");
		}else {
			
		}
		

		/*driver.findElementByXPath("//android.widget.EditText[@text='Name']").clear();
		driver.findElementByXPath("//android.widget.EditText[@text='Last name']").sendKeys("Appium");*/

		driver.findElementById("com.android.contacts:id/editor_menu_save_button").click();


		String name= driver.findElementById("com.android.contacts:id/photo_touch_intercept_overlay").getAttribute("name");

		if (name.equals("")) {
			System.out.println("Contact updated successfully");

		} else {
			System.out.println("Contact could not updated");
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
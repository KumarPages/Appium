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

public class EditContact {

	AndroidDriver<WebElement> driver;
	@BeforeMethod
	public void setCapability() throws MalformedURLException{
		// TODO Auto-generated method stub


		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("appPackage", "com.google.android.contacts");
		capabilities.setCapability("appActivity", "com.android.contacts.activities.PeopleActivity"); 
		capabilities.setCapability("deviceName","ZX1G224ZNM");

		driver = new AndroidDriver<WebElement>(new URL("http://0.0.0.0:4723/wd/hub"),capabilities);

	}

	@Test(dataProvider="DSPH_DSPHI021")
	public void editContactNumber(String firstname,String lastname) throws MalformedURLException, InterruptedException {
		driver.findElementById("com.android.contacts:id/menu_search").click();
		driver.findElementById("com.android.contacts:id/search_view").sendKeys(firstname+" "+lastname);

		String txt = driver.findElementById("com.android.dialer:id/name").getText();
		
		if (txt.equalsIgnoreCase(firstname+" "+lastname)) {
			
			System.out.println("contect is present with the name: "+firstname+" "+lastname);
			
		}else {
			System.out.println("Contact could not be matched");
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
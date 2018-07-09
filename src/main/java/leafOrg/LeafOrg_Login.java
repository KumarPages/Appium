package leafOrg;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;

public class LeafOrg_Login {

	public static void main(String[] args) throws MalformedURLException, InterruptedException {

		AndroidDriver<WebElement> driver;
		String emailId = "sakthivel@testleaf.com";
		String defPassword = "Leaf@123";
		
		final String USERNAME = "jayasaravanan";
		final String ACCESS_KEY = "d306cc0a-e958-401c-8876-928cb9713447"; 
		final String URL = "http://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:80/wd/hub";
		/*DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability("appPackage", "com.testleaf.leaforg");
		dc.setCapability("appActivity", "com.testleaf.leaforg.MainActivity");
		dc.setCapability("deviceName", "Samsung Galaxy S4 Emulator");
		dc.setCapability("testobject_api_key", "C7CE7B47AF0C4850B38C7407C166117F");
		dc.setCapability("testobject_device", "Samsung Galaxy S4 Emulator");*/
		
		DesiredCapabilities caps = DesiredCapabilities.android();
		caps.setCapability("appiumVersion", "1.6.3");
		caps.setCapability("deviceName","Android Emulator");
		caps.setCapability("deviceOrientation", "portrait");
		caps.setCapability("browserName", "Browser");
		//caps.setCapability("testobject_api_key", "C7CE7B47AF0C4850B38C7407C166117F");
		caps.setCapability("platformVersion", "4.4");
		caps.setCapability("platformName","Android");
		
		driver = new AndroidDriver<WebElement>(new URL("http://0.0.0.0:4723/wd/hub"), caps);
		WebDriverWait wait= new WebDriverWait(driver, 60);
		Thread.sleep(5000);
		
		driver.get("http://google.com");
		Thread.sleep(20000);

		Set<String> contextNames = driver.getContextHandles();

		for (String contextName : contextNames) {

			if (contextName.contains("WEBVIEW"))
				driver.context(contextName);				

		}

		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'Create an Account')]")));
		driver.findElementByXPath("(//input[@formcontrolname='email'])").sendKeys(emailId);
		driver.findElementByXPath("(//input[@formcontrolname='password'])").sendKeys(defPassword);
		driver.findElementByXPath("//span[contains(text(),'Login')]").click();


		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//Span[contains(text(),'Settings')]")));
		driver.findElementByXPath("//Span[contains(text(),'Settings')]").click();
		driver.findElementByXPath("//span[contains(text(),'LOGOUT')]").click();
		driver.findElementByXPath("//span[contains(text(),'Yes, logout')]").click();
		Thread.sleep(5000);
		driver.quit();

		
	}

}

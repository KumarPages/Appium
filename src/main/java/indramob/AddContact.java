package indramob;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import util.ReadExcel;

public class AddContact {

		
		
		
		AndroidDriver<WebElement> driver;
		
		

		@BeforeMethod
		public void setCapability() throws MalformedURLException{
			// TODO Auto-generated method stub
			

			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("appPackage", "com.android.contacts");
			capabilities.setCapability("appActivity", "com.android.contacts.activities.PeopleActivity"); 
			capabilities.setCapability("deviceName","0123456789ABCDEF");

			driver = new AndroidDriver<WebElement>(new URL("http://0.0.0.0:4723/wd/hub"),capabilities);
			
				
		}
		@Test(dataProvider ="DSPH_DSPHI021")
		public void contactNumber(String firstname,String lastname,String number) throws MalformedURLException, InterruptedException {
			driver.findElementById("com.android.contacts:id/floating_action_button").click();
			driver.findElementById("android:id/text2").click();
			driver.findElementById("com.android.contacts:id/expansion_view_container").click();
			driver.findElementByXPath("//android.widget.EditText[@text='Name prefix']").sendKeys(firstname);
			driver.findElementByXPath("//android.widget.EditText[@text='Given name']").sendKeys(lastname);
			driver.hideKeyboard();
			Dimension size = driver.manage().window().getSize();
			int starty = (int) (size.getHeight()*0.2);
			int endy = (int) (size.getHeight()*0.9);
			int startx =(int) (size.getHeight()*0.5);
		//	driver.swipe(startx, endy, startx, starty, 3000);
			driver.findElementByXPath("//android.widget.EditText[@text='Phone']").sendKeys(number);
			driver.findElementByXPath("//android.widget.ImageButton[@content-desc='Navigate up']").click();
			String contactName= driver.findElementById("com.android.contacts:id/header").getAttribute("text");
			if (contactName.contains(number)) {
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
		
	}
	// iOS scroll by object
    public boolean swipeToDirection_iOS_XCTest(MobileElement el, String direction) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            HashMap<String, String> swipeObject = new HashMap<String, String>();
            if (direction.equals("d")) {
                swipeObject.put("direction", "down");
            } else if (direction.equals("u")) {
                swipeObject.put("direction", "up");
            } else if (direction.equals("l")) {
                swipeObject.put("direction", "left");
            } else if (direction.equals("r")) {
                swipeObject.put("direction", "right");
            }
            swipeObject.put("element", el.getId());
            js.executeScript("mobile:swipe", swipeObject);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean scrollToDirection_iOS_XCTest(MobileElement el, String direction) {
        // The main difference from swipe call with the same argument is that scroll will try to move
        // the current viewport exactly to the next/previous page (the term "page" means the content,
        // which fits into a single device screen)
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            HashMap<String, String> scrollObject = new HashMap<String, String>();
            if (direction.equals("d")) {
                scrollObject.put("direction", "down");
            } else if (direction.equals("u")) {
                scrollObject.put("direction", "up");
            } else if (direction.equals("l")) {
                scrollObject.put("direction", "left");
            } else if (direction.equals("r")) {
                scrollObject.put("direction", "right");
            }
            scrollObject.put("element", el.getId());
            scrollObject.put("toVisible", "true"); // optional but needed sometimes
            js.executeScript("mobile:scroll", scrollObject);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

// Android scroll to some text in scroll View

   /* public boolean tapByText(String text) {
        List<WebElement> elementList = driver.findElements(MobileBy.AndroidUIAutomator(
                "new UiScrollable(new UiSelector().resourceIdMatches(\".*id/ADD_YOUR_SCOLLER_VIEW_ID\")).setMaxSearchSwipes(5).scrollIntoView("
                + "new UiSelector().text(\"" + text + "\"))"));
        if (elementList.isEmpty())
            return false;
       return tapElement(elementList.get(0)); // tapElement is just custom tap method. use any you know instead.
    }*/


}


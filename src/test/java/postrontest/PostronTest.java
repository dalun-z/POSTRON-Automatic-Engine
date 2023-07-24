package postrontest;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;


public class PostronTest {
	
	public static AndroidDriver driver;
	
	public static WebElement Numbtn(char num) {
		return driver.findElement(By.id("com.postron.smartPOS:id/btn_num"+num));
	}

	public static void main(String[] args) {
		try {
			RunPOSTRON();
			CashierLogin();
			EnterCashBalance();
		} catch (Exception e) {
			System.out.println(e.getCause());
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("deprecation")
	public static void RunPOSTRON() throws Exception {
		 
		DesiredCapabilities cap = new DesiredCapabilities();
		
		// Device info
		cap.setCapability("deviceName", "D4-503");
		cap.setCapability("udid", "192.168.1.116:5555");	// device id # or it could be the device IP address
		cap.setCapability("platformName", "Android");
		cap.setCapability("platformVersion", "11");
		cap.setCapability("automationName", "UIAutomator2");
		
		// APK info
		cap.setCapability("appPackage", "com.postron.smartPOS");
		cap.setCapability("appActivity", "tech.bigbug.postron.mobile.view.activity.StartupActivity");
		
		URL url = new URL("http://127.0.0.1:4723");		// server IP address
		driver = new AndroidDriver(url, cap);
		
		System.out.println("Application Started Successfully...");
		
		// click all the permission buttons
		driver.findElement(By.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button")).click();
		driver.findElement(By.id("com.android.permissioncontroller:id/permission_allow_button")).click();
		driver.findElement(By.id("com.android.permissioncontroller:id/permission_allow_button")).click();
		
		// important to wait 1-2 second for DOM response to capture button from the app
		TimeUnit.SECONDS.sleep(2);
		
		WebElement confirmButton = driver.findElement(By.id("android:id/button1"));
		confirmButton.click();
		
		System.out.println("Launch App Process Finished ...");
	}
	
	public static void CashierLogin() {
		try {
			TimeUnit.SECONDS.sleep(25);
			
			WebElement btn1 = driver.findElement(By.xpath("//android.widget.TextView[@text='Cashier-In']"));
			btn1.click();
			
			WebElement pin1 = driver.findElement(By.id("com.postron.smartPOS:id/btn_num1"));
			WebElement pin2 = driver.findElement(By.id("com.postron.smartPOS:id/btn_num2"));
			WebElement pin3 = driver.findElement(By.id("com.postron.smartPOS:id/btn_num3"));
			WebElement pin4 = driver.findElement(By.id("com.postron.smartPOS:id/btn_num4"));
			pin1.click();
			pin2.click();
			pin3.click();
			pin4.click();
			
			WebElement loginbtn = driver.findElement(By.id("com.postron.smartPOS:id/btn_login"));
			loginbtn.click();
			
			System.out.println("Cashier Logged In using PIN : 1234");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void EnterCashBalance() {
		try {
			Numbtn('2').click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

package postrontest;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;


import io.appium.java_client.android.AndroidDriver;


public class PostronTest {
	
	public static AndroidDriver driver;
	
	public static void main(String[] args) {
		try {
			RunPOSTRON();
			CashierLogin();
//			EnterCashBalance();
			DineIn();
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
		cap.setCapability("new CommandTimeout", 300);
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
		Sleep(2);
		
		WebElement confirmButton = driver.findElement(By.id("android:id/button1"));
		confirmButton.click();
		
		for(int i = 0; i < 2; i++) {
			System.out.println("Main Screen Loading...");
			Sleep(56);
			System.out.print("...");
			driver.getStatus();
			System.out.println(driver.currentActivity());	// important for keeping the driver time out
		}
		
		System.out.println("Launch App Process Finished ...");
	}
	
	public static void CashierLogin() {
		
		if (driver.findElement(By.id("com.postron.smartPOS:id/txt_cashier")).getText().equals("No cashier")) {
			try {
				Sleep(1);
				
				WebElement btn1 = driver.findElement(By.xpath("//android.widget.TextView[@text='Cashier-In']"));
				btn1.click();
				
				// 1234
				AdminPIN();
				
				WebElement loginbtn = driver.findElement(By.id("com.postron.smartPOS:id/btn_login"));
				loginbtn.click();
				
				System.out.println("Cashier Logged In using PIN : 1234");
				
				Sleep(1);
				EnterCashBalance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Cashier already logged in!");
		}
	}
	
	public static void DineIn() throws InterruptedException {
		
		try {
			Sleep(1);
			if (driver.findElement(By.id("com.postron.smartPOS:id/txt_loginUser")).getText().equals("No login")) {
				Sleep(1);
				driver.findElement(By.xpath("//android.widget.TextView[@text='Dine In']")).click();
				Sleep(2);
				AdminPIN();
				driver.findElement(By.id("com.postron.smartPOS:id/btn_login")).click();
				Sleep(2);
				System.out.println("User Logged In!");
			} else {
				Sleep(1);
				driver.findElement(By.xpath("//android.widget.TextView[@text='Dine In']")).click();
				Sleep(1);
				System.out.println("User Already Logged In!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void EnterCashBalance() {
		try {
			Sleep(2);
			Numbtn('2').click();
			Numbtn('0').click();
			Numbtn('0').click();
			Numbtn('0').click();
			Numbtn('0').click();
			driver.findElement(By.id("com.postron.smartPOS:id/btn_submit")).click();
			
			System.out.println("Cash Balance Entered : $200.00");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static WebElement Numbtn(char num) {
		return driver.findElement(By.id("com.postron.smartPOS:id/btn_num"+num));
	}

	public static void AdminPIN() {
		Numbtn('1').click();
		Numbtn('2').click();
		Numbtn('3').click();
		Numbtn('4').click();
	}
	
	public static void EmployeePIN() {
		Numbtn('2').click();
		Numbtn('2').click();
		Numbtn('2').click();
		Numbtn('2').click();
	}
	
	public static void Sleep(int n) throws InterruptedException {
		TimeUnit.SECONDS.sleep(n);
	}
	
}

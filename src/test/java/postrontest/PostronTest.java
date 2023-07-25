package postrontest;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;


public class PostronTest {
	
	public static AndroidDriver driver;
	
	public static void main(String[] args) {
		try {
			RunPOSTRON();
			CashierLogin();
//			EnterCashBalance();
			DineIn();
			SelectTable();
			MenuTest();
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
		
		// click all the permission buttons
		driver.findElement(By.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button")).click();
		driver.findElement(By.id("com.android.permissioncontroller:id/permission_allow_button")).click();
		driver.findElement(By.id("com.android.permissioncontroller:id/permission_allow_button")).click();
		
		// important to wait 1-2 second for DOM response to capture button from the app
		Sleep(2);
		
		// Cancel
		CancelSync();
		
		// Confirm
//		ConfirmSync();
		
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
	
	public static void MenuTest() {
		try {
			Sleep(1);
			SelectCategory("Dim Sum");
			
			Sleep(1);
			SelectDishes();
			
			Sleep(1);
			TagTab();
			
			Sleep(1);
			DiscountTab();
			
			Sleep(1);
			ComboTab();
			
			Sleep(1);
			SearchTab();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void SearchTab() throws InterruptedException {
		System.out.println("Start Search Feature Testing...");
		driver.findElement(By.id("com.postron.smartPOS:id/btn_tabSearch")).click();
		Sleep(1);
		
		driver.pressKey(new KeyEvent(AndroidKey.DIGIT_9));
		driver.pressKey(new KeyEvent(AndroidKey.DIGIT_5));
		
		driver.findElement(By.id("com.postron.smartPOS:id/btn_search")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@text='95 Cara EggTart']")).click();
		driver.findElement(By.id("com.postron.smartPOS:id/btn_confirm")).click();
		Sleep(2);
		
		System.out.println("Search Test Passed!");
	}
	
	public static void ComboTab() throws InterruptedException {
		driver.findElement(By.id("com.postron.smartPOS:id/btn_tabCombo")).click();
		Sleep(1);
		
		driver.findElement(By.xpath("//android.widget.TextView[@text='Test On Click /w modifier and spec pop']")).click();
		Sleep(1);
		
		// Garlic Cucumber Modifier
		// price
		Numbtn('1').click();
		Numbtn('2').click();
		Numbtn('4').click();
		Numbtn('9').click();
		driver.findElement(By.id("android:id/button1")).click();
		Sleep(2);
		
		driver.findElement(By.id("android:id/button1")).click();
		Sleep(1);
		
		// 牛肉 Modifier
		driver.findElement(By.xpath("//android.widget.TextView[@text='牛肉']")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@text='Add']")).click();
		driver.findElement(By.id("com.postron.smartPOS:id/btn_confirm")).click();
		Sleep(2);
		driver.findElement(By.id("android:id/button1")).click();
		Sleep(1);
		
		
		// 醉翁蝦 Modifier
		// price
		Numbtn('2').click();
		Numbtn('2').click();
		Numbtn('9').click();
		Numbtn('9').click();
		driver.findElement(By.id("android:id/button1")).click();
		Sleep(1);
		// lbs
		Numbtn('1').click();
		Numbtn('0').click();
		Numbtn('0').click();
		driver.findElement(By.id("android:id/button1")).click();
		Sleep(2);
		
		driver.findElement(By.xpath("//android.widget.TextView[@text='Garlic Steamed']")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@text='醉翁蝦']")).click();
		driver.findElement(By.id("com.postron.smartPOS:id/btn_confirm")).click();
		Sleep(2);
		
		// Island Fish Modifier
		driver.findElement(By.id("android:id/button1")).click();
		Sleep(1);
		// price
		Numbtn('9').click();
		Numbtn('9').click();
		Numbtn('9').click();
		driver.findElement(By.id("android:id/button1")).click();
		Sleep(1);
		// lbs
		Numbtn('1').click();
		Numbtn('5').click();
		Numbtn('0').click();
		driver.findElement(By.id("android:id/button1")).click();
		Sleep(1);
		
		driver.findElement(By.id("com.postron.smartPOS:id/btn_confirm")).click();
		Sleep(1);
		
		System.out.println("Combo Tab Test Passed.");
	}
	
	public static void DiscountTab() {
		driver.findElement(By.id("com.postron.smartPOS:id/btn_tabDiscount")).click();
		System.out.println("Discount Tab Test Passed.");
	}
	
	public static void TagTab() throws InterruptedException {
		driver.findElement(By.id("com.postron.smartPOS:id/btn_tabTag")).click();
		Sleep(1);
		SelectTab("普通飲料");
		Sleep(1);
		driver.findElement(By.xpath("//android.widget.TextView[@text='ICE TEA']")).click();
		System.out.println("Tag Tab Testing Passed.");
	}
	
	public static void SelectTab(String tab) {
		driver.findElement(By.xpath("//android.widget.TextView[@text='" + tab +"']")).click();
	}
	
	public static void SelectCategory(String category) {
		driver.findElement(By.xpath("//android.widget.TextView[@text='" + category + "']")).click();
		System.out.println("Selected '" + category + "' Category.");
	}
	
	public static void SelectDishes() {
		driver.findElement(By.xpath("//android.widget.TextView[@text='91 Sf/Gin dumpling']")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@text='BBQ Pk Puff']")).click();
		System.out.println("Selected 2 dishes.");
	}
	
	// Selecting A5 table for testing
	public static void SelectTable() {
		try {
			Sleep(2);
			driver.findElement(By.xpath("//android.widget.TextView[@text='A 5']")).click();
			Sleep(2);
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
	
	public static void CancelSync() throws InterruptedException {
		driver.findElement(By.id("android:id/button2")).click();
		System.out.println("Application Started Successfully...");
		Sleep(5);
	}
	
	public static void ConfirmSync() throws InterruptedException {
		WebElement confirmButton = driver.findElement(By.id("android:id/button1"));
		confirmButton.click();
		
		System.out.println("Application Started Successfully...");
		
		for(int i = 0; i < 2; i++) {
			System.out.println("Main Screen Loading...");
			Sleep(56);
			System.out.print("...");
			driver.getStatus();
			System.out.println(driver.currentActivity());	// important for keeping the driver time out
		}
	}
	
}

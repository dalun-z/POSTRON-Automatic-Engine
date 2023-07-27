package postrontest;

import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class SingletonAndroidDriver {
	private static AndroidDriver driver = null;
	
	private SingletonAndroidDriver() {	
	}
	
	public static AndroidDriver getDriverInstance() {
		if (driver == null) {
			createDriverInstance();
		}
		return driver;
	}
	
	private static void createDriverInstance() {
		try {
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
			TestScenarios.Sleep(2);
			
			// Cancel
			TestScenarios.CancelSync();
			
			// Confirm
//			TestScenarios.ConfirmSync();
			
			System.out.println("Launch App Process Finished ...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

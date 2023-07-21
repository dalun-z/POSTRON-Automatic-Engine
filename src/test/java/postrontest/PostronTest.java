package postrontest;

import java.net.URL;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.android.AndroidDriver;


public class PostronTest {
	
	static AndroidDriver driver;

	public static void main(String[] args) {
		try {
			RunPOSTRON();
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
		cap.setCapability("udid", "192.168.1.116:5555");
		cap.setCapability("platformName", "Android");
		cap.setCapability("platformVersion", "11");
		cap.setCapability("automationName", "UIAutomator2");
		
		// APK info
		cap.setCapability("appPackage", "com.postron.smartPOS");
		cap.setCapability("appActivity", "tech.bigbug.postron.mobile.view.activity.StartupActivity");
		
		URL url = new URL("http://127.0.0.1:4723");
		driver = new AndroidDriver(url, cap);
		
		
		System.out.println("Application Started Successfully...");
	}

}

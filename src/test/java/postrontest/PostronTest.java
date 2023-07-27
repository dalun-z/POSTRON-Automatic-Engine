package postrontest;

import io.appium.java_client.android.AndroidDriver;

public class PostronTest {	
	public static void main(String[] args) {
		try {
			AndroidDriver driver = AndroidDriverFactory.getDriverInstance();
			
		} catch (Exception e) {
			System.out.println(e.getCause());
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}

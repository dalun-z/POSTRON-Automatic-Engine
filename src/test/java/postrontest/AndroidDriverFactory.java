package postrontest;

import io.appium.java_client.android.AndroidDriver;

public class AndroidDriverFactory {
	private static AndroidDriver driverInstance = null;
	
	public static AndroidDriver getDriverInstance() {
		if (driverInstance == null) {
			driverInstance = SingletonAndroidDriver.getDriverInstance();
		}
		return driverInstance;
	}
}

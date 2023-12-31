package postrontest;

import io.appium.java_client.android.AndroidDriver;

public class PostronTest {	

	public static void main(String[] args) {
		try {
			@SuppressWarnings("unused")
			AndroidDriver driver = AndroidDriverFactory.getDriverInstance();
			
			TestScenarios.CashierLogin();
			TestScenarios.DineIn();
			TestScenarios.SelectTable();
			TestScenarios.SelectTestDishes();
//			TestScenarios.MenuTest();
			TestScenarios.FeatureTest();
		} catch (Exception e) {
			System.out.println(e.getCause());
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}

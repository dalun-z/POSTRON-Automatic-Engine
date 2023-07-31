package postrontest;

import io.appium.java_client.android.AndroidDriver;

public class PostronTest {	

	public static void main(String[] args) {
		try {
			@SuppressWarnings("unused")
			AndroidDriver driver = AndroidDriverFactory.getDriverInstance();
			
			TestScenarios.CashierLogin();
			TestScenarios.DineIn();
<<<<<<< HEAD
			TestScenarios.SelectTable();
=======
			TestScenarios.SearchTab();
>>>>>>> 346cb91271594e7503dc5f3991481e909a02cb73
			TestScenarios.MenuTest();
		} catch (Exception e) {
			System.out.println(e.getCause());
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}

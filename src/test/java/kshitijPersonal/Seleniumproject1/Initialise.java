package kshitijPersonal.Seleniumproject1;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;

public class Initialise {
	
	private static WebDriver driver;
	


	 public static WebDriver getDriver() {
		return driver;
	}



	public static void setup()
	{
		System.setProperty("webdriver.chrome.driver", "D:\\My Files\\Selenium pre-reqs\\chromedriver.exe");
		driver = new ChromeDriver();	
		driver.manage().window().maximize();
	}

	public void getURL(String URL)
	{
		getDriver().get(URL);
	}

	
	
}

package kshitijPersonal.Seleniumproject1;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class Initialise {
	
	
	@Test
	public void setup()
	{
		System.setProperty("webdriver.chrome.driver", "D:\\My Files\\Selenium pre-reqs\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.spicejet.com/");
		driver.manage().window().maximize();
	}
	
}

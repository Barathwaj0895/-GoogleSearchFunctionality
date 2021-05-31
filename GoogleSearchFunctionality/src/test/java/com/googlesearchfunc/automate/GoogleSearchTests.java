package com.googlesearchfunc.automate;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GoogleSearchTests {

	static WebDriver driver;

	@BeforeMethod(alwaysRun = true)
	public void setup() {
		driver = new ChromeDriver();
		System.setProperty("webdriver.chrome.driver", "C:/Users/ravisanb/eclipse-workspace/GoogleSearchFunctionality/driver/chromedriver.exe");
		System.out.println("Starting the test");
		System.out.println("Started Logger");
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		this.driver = new ChromeDriver();
		System.setProperty("webdriver.chrome.driver", "C:/Users/ravisanb/eclipse-workspace/GoogleSearchFunctionality/driver/chromedriver.exe");
		System.out.println("Completed the Test");
		System.out.println("Closing the tab/window");
		driver.close();
	}

	@Test
	public void launchGoogleWebsite() {
		String google = "Google";
		System.setProperty("webdriver.chrome.driver", "C:/Users/ravisanb/eclipse-workspace/GoogleSearchFunctionality/driver/chromedriver.exe");
		driver.manage().window().maximize();
		driver.get("http://www.google.com");
		assertTrue(driver.findElement(By.xpath("//img[@alt='Google']")).getText().contains(google));
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	@Test
	public void testSearchBoxacceptingKeywords() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:/Users/ravisanb/eclipse-workspace/GoogleSearchFunctionality/driver/chromedriver.exe");
		driver.get("http://www.google.com");
		driver.manage().window().maximize();
		driver.findElement(By.name("q")).sendKeys("Selenium");
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
		String searchResults = driver.findElement(By.xpath("//h2[@class='qrShPb kno-ecr-pt PZPZlf mfMhoc']")).getText();
		System.out.println(searchResults);
		assertTrue(searchResults.contains("Selenium"));
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testSearchSuggestions() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:/Users/ravisanb/eclipse-workspace/GoogleSearchFunctionality/driver/chromedriver.exe");
		driver.get("http://www.google.com");
		driver.findElement(By.name("q")).sendKeys("Selenium");
		List<WebElement> list = (List<WebElement>) driver.findElement(By.xpath("//ul[@role='listbox']//li/descendant::div[@class='wM6W7d']"));
		System.out.println(list.size());
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		for(int i=0; i<list.size(); i++) {
			String listItem = list.get(i).getText();
			System.out.println(listItem);
			if(listItem.contains("Selenium webdriver")) {
				list.get(i).click();
				break;
			}
		}
	}
}

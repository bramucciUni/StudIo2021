package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestSeleniumLogin {
	
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\adria\\Downloads\\Chromedriver87/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://localhost:8092/ProgettoWeb/");
		driver.findElement(By.xpath("//*[@id=\"user\"]")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//*[@id=\"emailLogin\"]")).sendKeys("mailS");
		Thread.sleep(500);
		driver.findElement(By.xpath("//*[@id=\"passwordLogin\"]")).sendKeys("1234");
		Thread.sleep(500);
		driver.findElement(By.xpath("//*[@id=\"loginBtn\"]")).click();
	}

}

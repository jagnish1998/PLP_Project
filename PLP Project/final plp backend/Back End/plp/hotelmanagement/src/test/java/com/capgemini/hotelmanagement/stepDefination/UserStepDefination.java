package com.capgemini.hotelmanagement.stepDefination;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class UserStepDefination {

	static {
		System.setProperty("webdriver.chrome.driver", "./Driver/chromedriver.exe");

	}
	WebDriver driver;

	@Given("^the user has loaded the application in the browser$")
	public void the_user_has_loaded_the_application_in_the_browser() throws Throwable {
		driver = new ChromeDriver();
		driver.get("http://localhost:4200");
		driver.manage().window().maximize();
	}

	@When("^the user clicks  on the login Button navbar$")
	public void the_user_clicks_on_the_login_Button_navbar() throws Throwable {
		driver.findElement(By.xpath("//a[text()='Login']")).click();
	}

	@When("^the user enters email in textbox$")
	public void the_user_enters_email_in_textbox() throws Throwable {
		driver.findElement(By.name("email")).sendKeys("mayuri@gmail.com");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	@When("^the user enters the password in the textbox$")
	public void the_user_enters_the_password_in_the_textbox() throws Throwable {
		driver.findElement(By.name("password")).sendKeys("Mayu@123");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	@When("^the user clicks on the login button$")
	public void the_user_clicks_on_the_login_button() throws Throwable {
		driver.findElement(By.xpath("/html/body/app-root/app-login/div/div/div[2]/form/div/button")).click();
	}

}

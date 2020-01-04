package com.capgemini.hotelmanagement.stepDefination;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class EmployeeStepDefination {

	static {
		System.setProperty("webdriver.chrome.driver", "./Driver/chromedriver.exe");

	}
	WebDriver driver;

	@Given("^the employee has load the application in the browser$")
	public void the_employee_has_load_the_application_in_the_browser() throws Throwable {
		driver = new ChromeDriver();
		driver.get("http://localhost:4200");
		driver.manage().window().maximize();
	}

	@When("^the employee clicks  on the login Button navbar$")
	public void the_employee_clicks_on_the_login_Button_navbar() throws Throwable {
		driver.findElement(By.xpath("//a[text()='Login']")).click();
	}

	@When("^the employee enters email in textbox$")
	public void the_employee_enters_email_in_textbox() throws Throwable {
		driver.findElement(By.name("email")).sendKeys("shital@gmail.com");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	@When("^the employee enters the password in the textbox$")
	public void the_employee_enters_the_password_in_the_textbox() throws Throwable {
		driver.findElement(By.name("password")).sendKeys("Sagar@123");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	@When("^the employee clicks on the login button$")
	public void the_employee_clicks_on_the_login_button() throws Throwable {
		driver.findElement(By.xpath("/html/body/app-root/app-login/div/div/div[2]/form/div/button")).click();
	}

}

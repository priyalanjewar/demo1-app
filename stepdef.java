package com.qa.automation.stepdefs;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class stepdef {
	
	WebDriver driver;
	String baseUrl = "https://www.google.co.in/";
	int implictly_Wait_TimeOut_in_Sec = 20;
	
	
	 @Given("user opened browser")
	    public void user_opened_browser() {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\Hp\\eclipse-workspace\\chromedriver-win64 (3)\\chromedriver-win64\\chromedriver.exe");
		 ChromeOptions opt = new ChromeOptions();
		opt.setBinary("C:\\Users\\Hp\\eclipse-workspace\\chrome-win64\\chrome-win64\\chrome.exe");
			
		    driver = new ChromeDriver(opt);
			
		driver.manage().window().maximize();
		 driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(implictly_Wait_TimeOut_in_Sec, TimeUnit.SECONDS);
		 
	    }
	    
		@Given("user navigated to the home application url")
	    public void user_navigated_to_the_home_application_url() {
	    	driver.get("https://www.amazon.in/");
	    	String expectedTitle = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
	    	String actualTitle = driver.getTitle();
	    	Assert.assertEquals("The title of home page for the application is not matched",expectedTitle,actualTitle);
	   
	    }
	    
	    @When("user search for product {string}")
	    public void user_search_for_product(String productname) {
	    	
	    	WebElement searchBoxEle = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
	         WebDriverWait wait = new WebDriverWait(driver, 15);
	         wait.until(ExpectedConditions.elementToBeClickable(searchBoxEle));
	         
	         searchBoxEle.sendKeys(productname);
	         
	         WebElement searchBtnEle = driver.findElement(By.xpath("//input[@id='nav-search-submit-button']"));
	         searchBtnEle.click();
	         
	        
	    }
	    
	    
		@Then("search result page is displayed with page title keyword contains{string}")
	    public void search_result_page_is_displayed_with_page_title_keyword_contains(String titleProdNameKeyword) {
	    	
	    	String expectedtitle="Amazon.in : "+ titleProdNameKeyword;
	    	
	    	WebDriverWait wait = new WebDriverWait(driver, 15);
	         wait.until(ExpectedConditions.titleContains(titleProdNameKeyword));
	        
	         Assert.assertEquals(expectedtitle, driver.getTitle());
	         
	         driver.quit();
	    }


}

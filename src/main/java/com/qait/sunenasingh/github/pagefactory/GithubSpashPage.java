package com.qait.sunenasingh.github.pagefactory;


import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
public class GithubSpashPage extends BaseClass{
	private WebDriver driver;
	
	//@FindBy(xpath="//title")
	//WebElement title;
	
	//@FindBy(xpath="//a[text()='Sign in']")
	@FindBy(linkText="Sign in")
	private WebElement signInLink;
	
	public GithubSpashPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public GithubSignInPage clickSignIn(){
		signInLink.click();
		return new GithubSignInPage(driver);
	}
}
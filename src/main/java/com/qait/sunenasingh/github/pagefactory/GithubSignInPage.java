package com.qait.sunenasingh.github.pagefactory;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

public class GithubSignInPage extends BaseClass {
	private WebDriver driver;
	
	//@FindBy(tagName="title")
	//WebElement title;
	
	@FindBy(id="login_field")
	private WebElement inputUserNameorEmail;
	
	@FindBy(id="password")
	private WebElement inputPassword;

	@FindBy(xpath="//div[@id='login']//input[@value='Sign in'and@type='submit']")
	private WebElement btnSignIn;
	
	public GithubSignInPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	private void setUserNameorEmail(String userNameorEmail) {
		inputUserNameorEmail.sendKeys(userNameorEmail);
	}
	
	private void setPassword(String password) {
		inputPassword.sendKeys(password);
	}
	
	private void clickSignIn() {
		btnSignIn.click();
	}
	
	public GithubHomePage login(String userNameorEmail, String password) {
		this.setUserNameorEmail(userNameorEmail);
		this.setPassword(password);
		this.clickSignIn();
		return new GithubHomePage(driver);
	}
}
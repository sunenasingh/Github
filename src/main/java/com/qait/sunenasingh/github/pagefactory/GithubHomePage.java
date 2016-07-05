package com.qait.sunenasingh.github.pagefactory;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GithubHomePage extends BaseClass {
	private WebDriver driver;
	
	@FindBy(partialLinkText = "New repository")
	private WebElement linkNewRepository;
	
	public GithubHomePage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public GithubCreateNewRepositoryPage clickNewRepository(){
		linkNewRepository.click();
		return new GithubCreateNewRepositoryPage(driver);
	}
}
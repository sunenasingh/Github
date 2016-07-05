package com.qait.sunenasingh.github.pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GithubLatestCommitPage extends BaseClass{
	private WebDriver driver;
	
	@FindBy(xpath = "//div[@id='js-repo-pjax-container']//div[contains(@class,'commit-meta')]//span[contains(@class,'user-select-contain')]")
	private WebElement spanLatestCommit;
	
	public GithubLatestCommitPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public String getLatestCommit() {
		return spanLatestCommit.getText();
	}
	
}

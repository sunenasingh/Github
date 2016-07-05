package com.qait.sunenasingh.github.pagefactory;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GithubNewRepositoryPage extends BaseClass {
	private WebDriver driver;
	
	@FindBy(xpath="//div[@class='url-box']/span/input")
	private WebElement githubRemoteHttpsLink;
	
	public GithubNewRepositoryPage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean isGithubRemoteLinkDisplayed() {
		if(githubRemoteHttpsLink.isDisplayed())
			return true;
		else
			return false;
	}
	
	public String getRemoteGithibLink(){
		return githubRemoteHttpsLink.getAttribute("value");
	}
	
	public GithubRepositoryPage newRepositoryPageRefresh() throws InterruptedException{
		driver.navigate().refresh();
		Thread.sleep(2000);
		return new GithubRepositoryPage(driver);
	}
}
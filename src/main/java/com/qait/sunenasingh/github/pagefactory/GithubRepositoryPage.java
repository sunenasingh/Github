package com.qait.sunenasingh.github.pagefactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qait.gauravjain.utility.Utility;

public class GithubRepositoryPage extends BaseClass {

	private WebDriver driver;
	
	private WebElement fileName;
	
	@FindBy(xpath="//div[@id='js-repo-pjax-container']//a[contains(@href,'commit') and contains(@class,'commit-tease-sha')]")
	private WebElement latestCommitLink;
	
	public GithubRepositoryPage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
		 fileName = driver.findElement(By.xpath("//div[@class='file-wrap']//a[text()='"+Utility.getYamlValues("filename")+"']"));
	}
	
	public String getFileName(){
		return fileName.getText();
	}
	
	public GithubLatestCommitPage clickLatestCommitLink(){
		latestCommitLink.click();
		return new GithubLatestCommitPage(driver);
	}
}

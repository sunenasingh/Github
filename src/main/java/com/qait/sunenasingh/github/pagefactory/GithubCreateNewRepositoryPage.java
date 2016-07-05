package com.qait.sunenasingh.github.pagefactory;


	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.support.FindBy;
	import org.openqa.selenium.support.PageFactory;

	public class GithubCreateNewRepositoryPage extends BaseClass {
		private WebDriver driver;
		
		@FindBy(id="repository_name")
		private WebElement inputRepositoryName;
		
		@FindBy(id="repository_public_true")
		private WebElement radioButtonPublic;
		
		@FindBy(xpath="//button[contains(text(),'Create repository')]")
		private WebElement buttonCreateRepository;
		
		public GithubCreateNewRepositoryPage(WebDriver driver){
			this.driver = driver;
			PageFactory.initElements(driver, this);
		}
		
		private void setRepositoryName(String repositoryName){
			inputRepositoryName.clear();
			inputRepositoryName.sendKeys(repositoryName);
		}
		
		private void clickRadioButtonPublic(){
			radioButtonPublic.click();
		}
		
		private void clickCreateRepositoryButton(){
			buttonCreateRepository.click();
		}
		
		public GithubNewRepositoryPage createValidRepository(){
			this.setRepositoryName("test");
			this.clickRadioButtonPublic();
			this.clickCreateRepositoryButton();
			return new GithubNewRepositoryPage(driver);
		}
	}
	


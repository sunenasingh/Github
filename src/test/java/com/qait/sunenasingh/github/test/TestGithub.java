package com.qait.gauravjain.github.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.*;
import org.testng.*;
import org.testng.annotations.*;

import com.qait.gauravjain.unix.*;
import com.qait.gauravjain.utility.*;
import com.qait.sunenasingh.github.pagefactory.*;

public class TestGithub {
	WebDriver driver;
	GithubSpashPage githubSpashPage;
	GithubSignInPage githubSignInPage;
	GithubHomePage githubHomePage;
	GithubCreateNewRepositoryPage githubCreateNewRepositoryPage;
	GithubNewRepositoryPage githubNewRepositoryPage;
	GithubRepositoryPage githubRepositoryPage;
	GithubLatestCommitPage githubLatestCommitPage;
	
	String gitUrl;
	
	@BeforeClass
	public void setup(){
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://github.com");
	}
	
	@Test(priority=1, enabled=true)
	public void testCreateRepository(){
		githubSpashPage = new GithubSpashPage(driver);
		Assert.assertEquals(driver.getTitle(), "How people build software · GitHub");
		githubSignInPage = githubSpashPage.clickSignIn();
		Assert.assertEquals(githubSignInPage.getTitle(driver), "Sign in to GitHub · GitHub");
		githubHomePage = githubSignInPage.login(Utility.getYamlValues("username"),Utility.getYamlValues("password"));
		Assert.assertEquals(githubHomePage.getTitle(driver), "GitHub");
		githubCreateNewRepositoryPage = githubHomePage.clickNewRepository();
		Assert.assertEquals(githubCreateNewRepositoryPage.getTitle(driver), "Create a New Repository");
		githubNewRepositoryPage = githubCreateNewRepositoryPage.createValidRepository();
		Assert.assertEquals(githubNewRepositoryPage.getTitle(driver), Utility.getYamlValues("username")+"/"+Utility.getYamlValues("repositoryname"));
		Assert.assertEquals(githubNewRepositoryPage.isGithubRemoteLinkDisplayed(), true);
	}
	

	@Test(priority=2)
	public void testCommandExec(){
		System.out.println((new java.util.Date()).getTime());
		List<String> commands = new ArrayList<String>();
		commands.add("mkdir "+Utility.getYamlValues("repositoryname"));
		commands.add("cd "+Utility.getYamlValues("repositoryname"));
	    commands.add("touch "+Utility.getYamlValues("filename"));
	    commands.add("git init");		
	    commands.add("git remote add origin 'https://"+Utility.getYamlValues("username")+":"+Utility.getYamlValues("password")+"@github.com/"+Utility.getYamlValues("username")+"/"+Utility.getYamlValues("repositoryname")+".git'");
	    commands.add("git add .");
	    commands.add("git commit -m \""+(new java.util.Date()).getTime()+"\"");
	    commands.add("git push "+"'https://"+Utility.getYamlValues("username")+":"+Utility.getYamlValues("password")+"@github.com/"+Utility.getYamlValues("username")+"/"+Utility.getYamlValues("repositoryname")+".git'"+" master");
		Utility.writeToFile("src/main/resources/gitCommands.sh",commands);
		CommandExecutor gitCommands = new CommandExecutor();
		String[] str_arr = {"sh","src/main/resources/gitCommands.sh"};
		System.out.println(gitCommands.execCommand(str_arr));
	}
	
	@Test(dependsOnMethods = "testCommandExec")
	public void validatePushedFile() throws InterruptedException{
		githubRepositoryPage = githubNewRepositoryPage.newRepositoryPageRefresh();
		Assert.assertEquals(githubRepositoryPage.getTitle(driver), Utility.getYamlValues("username")+"/"+Utility.getYamlValues("repositoryname"));
		Assert.assertEquals(Utility.getYamlValues("filename"), githubRepositoryPage.getFileName());
	}
	
	@Test(dependsOnMethods = "validatePushedFile")
	public void validateLatestCommit(){
		CommandExecutor gitCommands = new CommandExecutor();
		String[] str_arr = {"git","log"};
		String output = gitCommands.execCommand(str_arr, Utility.getYamlValues("repositoryname")+"/");
		String line[] = output.split("\n");
		String word[]=line[0].split(" ");
		System.out.println(word[1]);
		githubLatestCommitPage = githubRepositoryPage.clickLatestCommitLink();
		Assert.assertEquals(githubLatestCommitPage.getLatestCommit(), word[1]);
	}
}
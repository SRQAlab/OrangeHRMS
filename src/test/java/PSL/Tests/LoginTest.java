package PSL.Tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import PSL.PageObjects.LandingPage;
import PSL.TestComponents.BaseTest;



public class LoginTest extends BaseTest{
	
	

	@Test(groups= {"ErrorHandling"})
	public void invalidLogin() throws IOException, InterruptedException 
	{
		
		LandingPage landingpage = new LandingPage(driver);
		landingpage.loginApplication("Admin", "abc123");
		Assert.assertEquals("Invalid credentials", landingpage.getErrorMessage());
	}
	
	
	@Test
	public void validLogin() throws IOException, InterruptedException 
	{
		// TODO Auto-generated method stub
		
		
		LandingPage landingpage = new LandingPage(driver);
		landingpage.loginApplication("Admin", "admin123");
		
   
	}
	
	
}

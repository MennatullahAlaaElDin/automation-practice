package phpTravelsUITests;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.shaft.driver.DriverFactory;
import com.shaft.tools.io.JSONFileManager;

import phpTravelsPageObjectModel.Login;

public class LoginTests {
	private WebDriver driver;
	private final JSONFileManager testData = new JSONFileManager(
			System.getProperty("testDataFolderPath") + "PhpTravels_ClientLogin_TestData.json");
	private final SoftAssert softAssert = new SoftAssert();

	@Test(description = "TC_01 Check That Login Page's items are displayed")
	public void checkLoginPageItems() {
		var login = new Login(driver).navigateToPhpTravelsLoginPage();
		softAssert.assertEquals(login.getLoginPageTitleText(), "Secure Client Login");
		softAssert.assertTrue(login.isEmailFieldDisplayed(), "Email field is displayed");
		softAssert.assertTrue(login.isPasswordFieldDisplayed(), "Password field is displayed");
		softAssert.assertTrue(login.isForgotLinkDisplayed(), "Forgot link is displayed");
		softAssert.assertFalse(login.isRememberMeCheckBoxChecked(), "Remember me is unchecked by default");
		softAssert.assertTrue(login.isLoginBtnDisplayed(), "Login button is displayed");
		softAssert.assertAll();
	}

//	@Test(description = "TC_02 Check The Login Behavior with Valid Data")
//	Captcha is not able to be automated, shall ask the dev team to disable it on test environment
	public void checkLoginWithValidCredentials() {
		var dashboard = new Login(driver).navigateToPhpTravelsLoginPage().loginWithValidLoginCredentials(
				testData.getTestData("validEmail"), testData.getTestData("validPassword"));
		softAssert.assertEquals(dashboard.getDashboardTabTitle(),
				"Welcome Back, " + testData.getTestData("validFirstName"));
		softAssert.assertEquals(dashboard.getDashboardPageTitle(), "My Dashboard");
		softAssert.assertAll();

	}

//	@Test(description = "TC_03_Case A:  Check The Login Behavior with Invalid Password")
//	Captcha is not able to be automated, shall ask the dev team to disable it on test environment
	public void checkLoginWithInvalidPassword() {
		var login = new Login(driver).navigateToPhpTravelsLoginPage().loginWithInValidLoginCredentials(
				testData.getTestData("validEmail"), testData.getTestData("inValidPassword"));
		Assert.assertEquals(login.getGeneralErrorText(), "Login Details Incorrect. Please try again.");
	}

//	@Test(description = "TC_03_Case B:  Check The Login Behavior with Invalid Email")
//	Captcha is not able to be automated, shall ask the dev team to disable it on test environment
	public void checkLoginWithInvalidEmail() {
		var login = new Login(driver).navigateToPhpTravelsLoginPage().loginWithInValidLoginCredentials(
				testData.getTestData("inValidEmail"), testData.getTestData("validPassword"));
		Assert.assertEquals(login.getGeneralErrorText(), "Login Details Incorrect. Please try again.");
	}

//	@Test(description = "TC_03_Case C:  Check The Login Behavior with Empty Credentials")
//	Captcha is not able to be automated, shall ask the dev team to disable it on test environment
	public void checkLoginWithEmptyCredentials() {
		var login = new Login(driver).navigateToPhpTravelsLoginPage().loginWithInValidLoginCredentials("","");
		Assert.assertEquals(login.getGeneralErrorText(), "Login Details Incorrect. Please try again.");
	}

	@BeforeMethod
	public void beforeMethod() {
		driver = DriverFactory.getDriver();
		Dimension dimension = new Dimension(1024, 768);
		driver.manage().window().setSize(dimension);
	}

	@AfterMethod
	public void afterMethod() {
		DriverFactory.closeAllDrivers();
	}

}

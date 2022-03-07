package phpTravelsUITests;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.shaft.driver.DriverFactory;
import com.shaft.tools.io.JSONFileManager;

import phpTravelsPageObjectModel.Login;

public class RegistrationTests {
	private WebDriver driver;
	private final JSONFileManager testData = new JSONFileManager(System.getProperty("testDataFolderPath") +"PhpTravels_Registration_TestData.json");
	

	@Test(description = "TC_01 Check That Register Button is Displayed in Login Page")
	public void checkThatRegistrationBtnISDisplayed() {
		var isRegisterBtnDisplayed = new Login(driver).navigateToPhpTravelsLoginPage().isRegisterBtnDisplayed();
		Assert.assertTrue(isRegisterBtnDisplayed);
	}

	@Test(description = "TC_02 Check the navigation of Register button")
	public void checkThatRegistrationPageOpenedSuccessfully() {
		var registrationPageObj = new Login(driver).navigateToPhpTravelsLoginPage().clickOnRegisterBtn();
		String registrationTabTitle = registrationPageObj.getRegistrationTabTitle();
		Assert.assertEquals(registrationTabTitle, "Register");
		
		String registrationFormTitle = registrationPageObj.getRegisterFormTitleText();
		Assert.assertEquals(registrationFormTitle, "Register");
	}

	@Test(description = "TC_04_Check The Behavior When Enter Invalid First Name Value")
	public void checkInvalidFirstNameErrorRegistrationMessage() {
		var firstNameErrorMessage = new Login(driver).navigateToPhpTravelsLoginPage().clickOnRegisterBtn()
				.fillMandatoryRegistrationFormFields(testData.getTestData("inValidFirstName"),
						testData.getTestData("validLastName"),
						testData.getTestData("validEmail"), 
						testData.getTestData("validStreetAddress"), 
						testData.getTestData("validCity"),
						testData.getTestData("validPhoneNumber"),
						testData.getTestData("validPassword"),
						testData.getTestData("validConfirmPassword"))
				.clickOnRegisterSubmitBtnWithInValidData()
				.getSpecificErrorText();
		Assert.assertEquals(firstNameErrorMessage, "You did not enter your first name");
	}
	
	@Test(description = "TC_05_Check The Behavior When Enter Invalid Last Name Value")
	public void checkInvalidLastNameErrorRegistrationMessage() {
		var lastNameErrorMessage = new Login(driver).navigateToPhpTravelsLoginPage().clickOnRegisterBtn()
				.fillMandatoryRegistrationFormFields(testData.getTestData("validFirstName"),
						testData.getTestData("inValidLastName"),
						testData.getTestData("validEmail"), 
						testData.getTestData("validStreetAddress"), 
						testData.getTestData("validCity"),
						testData.getTestData("validPhoneNumber"),
						testData.getTestData("validPassword"),
						testData.getTestData("validConfirmPassword"))
				.clickOnRegisterSubmitBtnWithInValidData()
				.getSpecificErrorText();
		Assert.assertEquals(lastNameErrorMessage, "You did not enter your last name");
	}
	
	@Test(description = "TC_06_Check The Behavior When Enter Invalid Phone Number Value")
	public void checkInvalidPhoneNumberRegistrationMessage() {
		var phoneNumberErrorMessage = new Login(driver).navigateToPhpTravelsLoginPage().clickOnRegisterBtn()
				.fillMandatoryRegistrationFormFields(testData.getTestData("validFirstName"),
						testData.getTestData("validLastName"),
						testData.getTestData("validEmail"), 
						testData.getTestData("validStreetAddress"), 
						testData.getTestData("validCity"),
						testData.getTestData("inValidPhoneNumber"),
						testData.getTestData("validPassword"),
						testData.getTestData("validConfirmPassword"))
				.clickOnRegisterSubmitBtnWithInValidData()
				.getSpecificErrorText();
		Assert.assertEquals(phoneNumberErrorMessage, "Your phone number is not valid");
	}
	
	@Test(description = "TC_07_Case 4: Check The Behavior When Enter Invalid Email Value")
	public void checkInvalidEmailErrorRegistrationMessage() {
		var emailErrorMessage = new Login(driver).navigateToPhpTravelsLoginPage().clickOnRegisterBtn()
				.fillMandatoryRegistrationFormFields(testData.getTestData("validFirstName"),
						testData.getTestData("validLastName"),
						testData.getTestData("inValidEmail"), 
						testData.getTestData("validStreetAddress"), 
						testData.getTestData("validCity"),
						testData.getTestData("validPhoneNumber"),
						testData.getTestData("validPassword"),
						testData.getTestData("validConfirmPassword"))
				.clickOnRegisterSubmitBtnWithInValidData()
				.getSpecificErrorText();
		Assert.assertEquals(emailErrorMessage, "The email address you entered was not valid");
	}
	
	@Test(description = "TC_07_Case 5: Check The Behavior When Enter Already Exist Email Value")
	public void checkAlreadyExistEmailErrorRegistrationMessage() {
		var emailErrorMessage = new Login(driver).navigateToPhpTravelsLoginPage().clickOnRegisterBtn()
				.fillMandatoryRegistrationFormFields(testData.getTestData("validFirstName"),
						testData.getTestData("validLastName"),
						testData.getTestData("alreadyExistEmail"), 
						testData.getTestData("validStreetAddress"), 
						testData.getTestData("validCity"),
						testData.getTestData("validPhoneNumber"),
						testData.getTestData("validPassword"),
						testData.getTestData("validConfirmPassword"))
				.clickOnRegisterSubmitBtnWithInValidData()
				.getSpecificErrorText();
		Assert.assertEquals(emailErrorMessage, "A user already exists with that email address");
	}
	
	
	@Test(description = "TC_08_Check The Behavior When Enter Invalid Value in Street Address Field")
	public void checkInvalidStreetAddressErrorRegistrationMessage() {
		var addressErrorMessage = new Login(driver).navigateToPhpTravelsLoginPage().clickOnRegisterBtn()
				.fillMandatoryRegistrationFormFields(testData.getTestData("validFirstName"),
						testData.getTestData("validLastName"),
						testData.getTestData("validEmail"), 
						testData.getTestData("inValidStreetAddress"), 
						testData.getTestData("validCity"),
						testData.getTestData("validPhoneNumber"),
						testData.getTestData("validPassword"),
						testData.getTestData("validConfirmPassword"))
				.clickOnRegisterSubmitBtnWithInValidData()
				.getSpecificErrorText();
		Assert.assertEquals(addressErrorMessage, "You did not enter your address (line 1)");
	}
	
	@Test(description = "TC_09_Check The Behavior When Enter Invalid Value in City Field")
	public void checkInvalidCityErrorRegistrationMessage() {
		var cityErrorMessage = new Login(driver).navigateToPhpTravelsLoginPage().clickOnRegisterBtn()
				.fillMandatoryRegistrationFormFields(testData.getTestData("validFirstName"),
						testData.getTestData("validLastName"),
						testData.getTestData("validEmail"), 
						testData.getTestData("validStreetAddress"), 
						testData.getTestData("inValidCity"),
						testData.getTestData("validPhoneNumber"),
						testData.getTestData("validPassword"),
						testData.getTestData("validConfirmPassword"))
				.clickOnRegisterSubmitBtnWithInValidData()
				.getSpecificErrorText();
		Assert.assertEquals(cityErrorMessage, "You did not enter your city");
	}
	
	@Test(description = "TC_10_Check The Behavior When Leave Password Field Empty")
	public void checkTheBehaviorWhenLeavePasswordFieldEmpty() {
		var passwordErrorMessage = new Login(driver).navigateToPhpTravelsLoginPage().clickOnRegisterBtn()
				.fillMandatoryRegistrationFormFields(testData.getTestData("validFirstName"),
						testData.getTestData("validLastName"),
						testData.getTestData("validEmail"), 
						testData.getTestData("validStreetAddress"), 
						testData.getTestData("validCity"),
						testData.getTestData("validPhoneNumber"),
						testData.getTestData("emptyPassword"),
						testData.getTestData("emptyConfirmPassword"))
				.clickOnRegisterSubmitBtnWithInValidData()
				.getSpecificErrorText();
		Assert.assertEquals(passwordErrorMessage, "You did not enter a password");
	}
	
	@Test(description = "TC_10_Check The Behavior When Enter Invalid Password")
	public void checkInvalidPasswordLengthErrorMessage() {
		var passwordErrorMessage = new Login(driver).navigateToPhpTravelsLoginPage().clickOnRegisterBtn()
				.fillMandatoryRegistrationFormFields(testData.getTestData("validFirstName"),
						testData.getTestData("validLastName"),
						testData.getTestData("validEmail"), 
						testData.getTestData("validStreetAddress"), 
						testData.getTestData("validCity"),
						testData.getTestData("validPhoneNumber"),
						testData.getTestData("inValidPasswordLength"),
						testData.getTestData("emptyConfirmPassword"))
				.clickOnRegisterSubmitBtnWithInValidData()
				.getSpecificErrorText();
		Assert.assertEquals(passwordErrorMessage, "The password you entered is not strong enough - please enter a more complex password");
	}
	
	@Test(description = "TC_11_Check The Behavior When Leave Confirm Password Field Empty")
	public void checkTheBehaviorWhenLeaveConfirmPasswordFieldEmpty() {
		var confirmPasswordErrorMessage = new Login(driver).navigateToPhpTravelsLoginPage().clickOnRegisterBtn()
				.fillMandatoryRegistrationFormFields(testData.getTestData("validFirstName"),
						testData.getTestData("validLastName"),
						testData.getTestData("validEmail"), 
						testData.getTestData("validStreetAddress"), 
						testData.getTestData("validCity"),
						testData.getTestData("validPhoneNumber"),
						testData.getTestData("validPassword"),
						testData.getTestData("emptyConfirmPassword"))
				.clickOnRegisterSubmitBtnWithInValidData()
				.getSpecificErrorText();
		Assert.assertEquals(confirmPasswordErrorMessage, "You did not confirm your password");
	}
	
	@Test(description = "TC_11_Check The Behavior When Passwords Fields Are Mismatched")
	public void checkTheBehaviorWhenPasswordsFieldsAreMismatched() {
		var obj = new Login(driver).navigateToPhpTravelsLoginPage().clickOnRegisterBtn()
				.fillMandatoryRegistrationFormFields(testData.getTestData("validFirstName"),
						testData.getTestData("validLastName"),
						testData.getTestData("validEmail"), 
						testData.getTestData("validStreetAddress"), 
						testData.getTestData("validCity"),
						testData.getTestData("validPhoneNumber"),
						testData.getTestData("validPassword"),
						testData.getTestData("misMatchConfirmPassword"));
		String mismatchPasswordErrorMessage = obj.getPasswordsMisMatchErrorMessageText();
		Assert.assertEquals(mismatchPasswordErrorMessage, "The passwords entered do not match");
		Assert.assertFalse(obj.isRegistrationSubmitBtnEnabled());
	}
	
	@Test(description = "TC_12_Check The Default Generated Password Length")
	public void checkTheDefaultGeneratedPasswordLength() {
		var generatedPasswordModal = new Login(driver).navigateToPhpTravelsLoginPage().clickOnRegisterBtn()
				.clickOnGeneratePasswordBtn();
		String generatedPasswordLength = generatedPasswordModal.getPasswordLengthModalFieldText();
		Assert.assertEquals(generatedPasswordLength, testData.getTestData("defaultGeneratedPasswordLength"));
		
		String generatePasswordOutputModalFieldLength = generatedPasswordModal.getLengthOfGeneratePasswordOutputModalField();
		Assert.assertEquals(generatePasswordOutputModalFieldLength, testData.getTestData("defaultGeneratedPasswordLength"));
	}
	
	@Test(description = "TC_12_Case A: Check The Copy to clipboard and Insert Functionality with Change password length to be 8 =< number <= 64 ")
	public void checkTheCopyToClipboardAndInsertFunctionality() {
		var insertPasswordLength = new Login(driver).navigateToPhpTravelsLoginPage().clickOnRegisterBtn()
				.clickOnGeneratePasswordBtn()
				.insertPasswordLength(testData.getTestData("validGeneratePasswordLength"))
				.clickOnGeneratePasswordModalBtn()
				.clickOnCopyToClipboardAndInsertModalBtn();
		String lengthOfPasswordField = insertPasswordLength.getLengthOfPasswordField();
		Assert.assertEquals(lengthOfPasswordField,testData.getTestData("validGeneratePasswordLength"));
		
		String lengthOfConfirmPasswordField = insertPasswordLength.getLengthOfConfirmPasswordField();
		Assert.assertEquals(lengthOfConfirmPasswordField, testData.getTestData("validGeneratePasswordLength"));
	}
	
	@Test(description = "TC_12_Case E: Dismiss the modal without copying any generated passwords")
	public void checkDismissingModalWithoutCopyingAnyGeneratedPasswords() {
		var registrationForm = new Login(driver).navigateToPhpTravelsLoginPage().clickOnRegisterBtn()
				.clickOnGeneratePasswordBtn()
				.dismissGeneratedPasswordModal();
		String lengthOfPasswordField = registrationForm.getLengthOfPasswordField();
		Assert.assertEquals(lengthOfPasswordField, "0");
		
		String lengthOfConfirmPasswordField = registrationForm.getLengthOfConfirmPasswordField();
		Assert.assertEquals(lengthOfConfirmPasswordField, "0");
	}

//	@Test(description = "TC_13_Check The Behavior When Enter Valid Data")
//	Captcha is not able to be automated, shall ask the dev team to disable it on test environment
	public void checkTheRegistrationWhenEnterValidData() {
		var dashboardObj = new Login(driver).navigateToPhpTravelsLoginPage().clickOnRegisterBtn()
				.fillMandatoryRegistrationFormFields(testData.getTestData("validFirstName"),
						testData.getTestData("validLastName"),
						testData.getTestData("validEmail"), 
						testData.getTestData("validStreetAddress"), 
						testData.getTestData("validCity"),
						testData.getTestData("validPhoneNumber"),
						testData.getTestData("validPassword"),
						testData.getTestData("validConfirmPassword"))	
				.clickOnRegisterSubmitBtnWithValidData();
		String currentTabTitle = dashboardObj.getDashboardTabTitle();
		Assert.assertEquals(currentTabTitle, "Welcome Back, "+ testData.getTestData("validLastName"));
		
		String dashboardPageTitle = dashboardObj.getDashboardPageTitle();
		Assert.assertEquals(dashboardPageTitle, "My Dashboard");
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

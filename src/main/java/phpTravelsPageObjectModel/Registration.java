package phpTravelsPageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.locators.RelativeLocator;

import com.shaft.gui.browser.BrowserActions;
import com.shaft.gui.element.ElementActions;

public class Registration {
	// variables
	private final WebDriver driver;

	// locators
	private final By registerFormTitle = By.className("login-title");
	private final By specificErrorMessage = By.xpath("//*[@id=\"registration\"]/div/div/ul/li[1]");

	private final By firstNameField = By.id("inputFirstName");
	private final By lastNameField = By.id("inputLastName");
	private final By emailField = By.id("inputEmail");
	private final By phoneNumberField = By.id("inputPhone");
	private final By streetAddressField = By.id("inputAddress1");
	private final By cityField = By.id("inputCity");
	private final By passwordField = By.id("inputNewPassword1");
	private final By confirmPasswordField = By.id("inputNewPassword2");
	private final By generatePasswordBtn = RelativeLocator.with(By.tagName("button"))
			.below(By.className("input-password-strenght"));
	private final By passwordLengthModalField = By.id("inputGeneratePasswordLength");
	private final By generatePasswordOutputModalField = By.id("inputGeneratePasswordOutput");
	private final By generatePasswordModalBtn = By.xpath("//button[@class='btn btn-default btn-sm']");
	private final By copyToClipboardAndInsertModalBtn = By.id("btnGeneratePasswordInsert");
	private final By passwordsMisMatchErrorMessage = By.id("nonMatchingPasswordResult");
	private final By generatePasswordModalCloseBtn = RelativeLocator.with(By.tagName("button"))
			.toLeftOf(By.id("btnGeneratePasswordInsert"));
	private final By registerSubmitBtn = By.xpath("//input[@type='submit'][@value= 'Register']");

	// constructor
	public Registration(WebDriver driver) {
		this.driver = driver;
	}

	// keywords

	public String getRegistrationTabTitle() {
		return BrowserActions.getCurrentWindowTitle(driver);
	}

	public String getSpecificErrorText() {
		return (driver.findElement(specificErrorMessage).getText());
	}

	public Registration fillMandatoryRegistrationFormFields(String firstName, String lastName, String email,
															String streetAddress, String city, String phoneNumber, String password, String confirmPassword) {
		ElementActions.type(driver, firstNameField, firstName);
		ElementActions.type(driver, lastNameField, lastName);
		ElementActions.type(driver, emailField, email);
		ElementActions.type(driver, streetAddressField, streetAddress);
		ElementActions.type(driver, cityField, city);
		ElementActions.type(driver, phoneNumberField, phoneNumber);
		ElementActions.type(driver, passwordField, password);
		ElementActions.type(driver, confirmPasswordField, confirmPassword);
		return this;
	}

	public String getRegisterFormTitleText() {
		return ElementActions.getText(driver, registerFormTitle);
	}

	public String getPasswordsMisMatchErrorMessageText() {
		return ElementActions.getText(driver, passwordsMisMatchErrorMessage);
	}

	public Registration clickOnGeneratePasswordBtn() {
		ElementActions.click(driver, generatePasswordBtn);
		return this;
	}

	public String getPasswordLengthModalFieldText() {
		return ElementActions.getText(driver, passwordLengthModalField);
	}

	public Registration insertPasswordLength(String length) {
		ElementActions.type(driver, passwordLengthModalField, length);
		return this;
	}

	public String getLengthOfGeneratePasswordOutputModalField() {
		int length = ElementActions.getText(driver, generatePasswordOutputModalField).length();
		return Integer.toString(length);
	}

	public Registration clickOnGeneratePasswordModalBtn() {
		ElementActions.click(driver, generatePasswordModalBtn);
		return this;
	}

	public Registration clickOnCopyToClipboardAndInsertModalBtn() {
		ElementActions.click(driver, copyToClipboardAndInsertModalBtn);
		return this;
	}

	public String getLengthOfPasswordField() {
		int length = ElementActions.getText(driver, passwordField).length();
		return Integer.toString(length);
	}

	public String getLengthOfConfirmPasswordField() {
		int length = ElementActions.getText(driver, confirmPasswordField).length();
		return Integer.toString(length);
	}

	public Dashboard clickOnRegisterSubmitBtnWithValidData() {
		ElementActions.click(driver, registerSubmitBtn);
		return new Dashboard(driver);
	}

	public Registration clickOnRegisterSubmitBtnWithInValidData() {
		ElementActions.click(driver, registerSubmitBtn);
		return this;
	}

	public boolean isRegistrationSubmitBtnEnabled() {
		return ElementActions.isElementClickable(driver, registerSubmitBtn);
	}

	public Registration dismissGeneratedPasswordModal() {
		ElementActions.click(driver, generatePasswordModalCloseBtn);
		return this;
	}

}

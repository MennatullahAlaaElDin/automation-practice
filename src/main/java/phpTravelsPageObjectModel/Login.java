package phpTravelsPageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.shaft.gui.browser.BrowserActions;
import com.shaft.gui.element.ElementActions;

public class Login {
	// variables
	private final WebDriver driver;
	private final String url = System.getProperty("phptravelsURL");

	// locators
	private final By registerBtn = By.xpath("//a[@class='btn btn-outline btn-default']");
	private final By loginPageTitle = By.className("login-title");
	private final By generalErrorMessage = By.className("alert-body");
	private final By emailField = By.id("inputEmail");
	private final By passwordField = By.id("inputPassword");
	private final By forgotLink = By.xpath("//a[@href=\"/password/reset\"]");
	private final By rememberMeCheckBox = By.name("rememberme");
	private final By loginBtn = By.id("login");

	// constructor
	public Login(WebDriver driver) {
		this.driver = driver;
	}

	// keywords
	public Login navigateToPhpTravelsLoginPage() {
		BrowserActions.navigateToURL(driver, url);
		return this;
	}

	public boolean isRegisterBtnDisplayed() {
		return ElementActions.isElementDisplayed(driver, registerBtn);

	}

	public Registration clickOnRegisterBtn() {
		ElementActions.click(driver, registerBtn);
		return new Registration(driver);
	}

	public String getLoginPageTitleText() {
		return ElementActions.getText(driver, loginPageTitle);
	}

	public String getGeneralErrorText() {
		return ElementActions.getText(driver, generalErrorMessage);
	}

	public boolean isEmailFieldDisplayed() {
		return ElementActions.isElementDisplayed(driver, emailField);
	}

	public boolean isPasswordFieldDisplayed() {
		return ElementActions.isElementDisplayed(driver, passwordField);
	}

	public boolean isRememberMeCheckBoxChecked() {
		return driver.findElement(rememberMeCheckBox).isSelected();
	}

	public boolean isForgotLinkDisplayed() {
		return ElementActions.isElementDisplayed(driver, forgotLink);
	}

	public boolean isLoginBtnDisplayed() {
		return ElementActions.isElementDisplayed(driver, loginBtn);
	}

	public Dashboard loginWithValidLoginCredentials(String email, String password) {
		ElementActions.type(driver, emailField, email);
		ElementActions.type(driver, passwordField, password);
		ElementActions.click(driver, loginBtn);
		return new Dashboard(driver);
	}

	public Login loginWithInValidLoginCredentials(String email, String password) {
		ElementActions.type(driver, emailField, email);
		ElementActions.type(driver, passwordField, password);
		ElementActions.click(driver, loginBtn);
		return this;
	}
}

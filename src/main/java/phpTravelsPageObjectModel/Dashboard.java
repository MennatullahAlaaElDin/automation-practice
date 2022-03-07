package phpTravelsPageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.shaft.gui.browser.BrowserActions;
import com.shaft.gui.element.ElementActions;

public class Dashboard {

	// variables
	private final WebDriver driver;

	// locators
	private final By dashboardTitle = By.className("main-header-title");

	// constructor
	public Dashboard(WebDriver driver) {
		this.driver = driver;
	}

	// keywords

	public String getDashboardTabTitle() {
		return BrowserActions.getCurrentWindowTitle(driver);

	}

	public String getDashboardPageTitle() {
		return ElementActions.getText(driver, dashboardTitle);
	}

}

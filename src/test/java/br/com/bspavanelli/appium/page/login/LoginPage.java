package br.com.bspavanelli.appium.page.login;

import br.com.bspavanelli.appium.constants.login.LoginConstants;
import br.com.bspavanelli.appium.utilities.screen_actions.ScreenActions;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class LoginPage extends ScreenActions {

	@AndroidFindBy(id = "")
	@iOSXCUITFindBy(id = "")
	private MobileElement FLD_USERNAME;

	@AndroidFindBy(id = "")
	@iOSXCUITFindBy(id = "")
	private MobileElement FLD_PASSWORD;

	@AndroidFindBy(id = "")
	@iOSXCUITFindBy(id = "")
	private MobileElement BTN_LOGIN;

	public LoginPage() {
		initElements(this);
	}

	public LoginPage writeUsername() {
		sendKeys(FLD_USERNAME, LoginConstants.USERNAME);
		return this;
	}

	public LoginPage writePassword() {
		sendKeys(FLD_PASSWORD, LoginConstants.PASSWORD);
		return this;
	}

	public LoginPage clickLogin() {
		click(BTN_LOGIN);
		return this;
	}
}

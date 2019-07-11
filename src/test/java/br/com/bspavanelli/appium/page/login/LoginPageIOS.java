package br.com.bspavanelli.appium.page.login;

import org.openqa.selenium.By;

import br.com.bspavanelli.appium.constants.login.LoginConstants;
import br.com.bspavanelli.appium.utilities.basepage.BasePageIOS;
import io.appium.java_client.MobileBy;

public class LoginPageIOS extends BasePageIOS implements LoginPage {

	private By FLD_USERNAME = MobileBy.id("");
	private By FLD_PASSWORD = MobileBy.id("");
	private By BTN_LOGIN = MobileBy.id("");

	@Override
	public LoginPage writeUsername() {
		sendKeys(FLD_USERNAME, LoginConstants.USERNAME);
		return this;
	}

	@Override
	public LoginPage writePassword() {
		sendKeys(FLD_PASSWORD, LoginConstants.PASSWORD);
		return this;
	}

	@Override
	public LoginPage clickLogin() {
		click(BTN_LOGIN);
		return this;
	}
}

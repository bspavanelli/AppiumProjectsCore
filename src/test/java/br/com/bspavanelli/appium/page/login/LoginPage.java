package br.com.bspavanelli.appium.page.login;

import org.openqa.selenium.support.FindBy;

import br.com.bspavanelli.appium.constants.login.LoginConstants;
import br.com.bspavanelli.appium.utilities.screen_actions.ScreenActions;
import io.appium.java_client.MobileElement;

public class LoginPage extends ScreenActions {

	@FindBy(id = "login_cpf")
	private MobileElement fld_cpf;

	@FindBy(id = "login_password")
	private MobileElement fld_password;

	@FindBy(id = "login_join")
	private MobileElement btn_login;

	@FindBy(id = "tutorial_join")
	private MobileElement btn_startLogin;

	public LoginPage() {
		initElements(this);
	}

	public LoginPage clickStartLogin() {
		btn_startLogin.click();
		return this;
	}

	public LoginPage writeCPF() {
		fld_cpf.sendKeys(LoginConstants.CPF);
		return this;
	}

	public LoginPage writePassword() {
		fld_password.sendKeys(LoginConstants.PASSWORD);
		return this;
	}

	public LoginPage clickLogin() {
		btn_login.click();
		return this;
	}
}

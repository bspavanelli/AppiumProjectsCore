package br.com.bspavanelli.appium.test.login;

import br.com.bspavanelli.appium.utilities.BaseTest;

public class LoginTest extends BaseTest {

	public void realizarLogin() {
		loginPage.clickStartLogin()
			.writeCPF()
			.writePassword()
			.clickLogin();

		homePage.validateHeader();
	}
}

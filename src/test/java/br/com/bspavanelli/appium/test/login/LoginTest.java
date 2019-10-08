package br.com.bspavanelli.appium.test.login;

import br.com.bspavanelli.appium.utilities.BaseTest;

public class LoginTest extends BaseTest {

	public void realizarLogin() {
		loginPage.writeUsername()
			.writePassword()
			.clickLogin();
		
		homePage.validateHeader();
	}
}

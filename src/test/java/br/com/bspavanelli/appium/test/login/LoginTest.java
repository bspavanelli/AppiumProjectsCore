package br.com.bspavanelli.appium.test.login;

import br.com.bspavanelli.appium.utilities.BaseTest;

public class LoginTest extends BaseTest {

	public void realizarLogin() {
		// A page já foi instanciada na BaseTest, então para usá-la, basta chamar a variável correspondente e os metodos desejados.
		loginPage.writeUsername()
			.writePassword()
			.clickLogin();
		
		homePage.validateHeader();
	}
}

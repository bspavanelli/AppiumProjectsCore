package br.com.bspavanelli.appium.test.login;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import br.com.bspavanelli.appium.utilities.BaseConstants;
import br.com.bspavanelli.appium.utilities.enums.OS;
import br.com.bspavanelli.appium.utilities.rules.CheckNeedToRunOnPlatform;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

public class Test_LoginAndroid extends LoginTest {

	private static OS os = OS.ANDROID;

	@ClassRule
	public static CheckNeedToRunOnPlatform checkNeedToRunOnPlatform = new CheckNeedToRunOnPlatform(os);

	@Before
	public void setUpPlatformAndPages() {
		BaseConstants.executionOS = os;
		setUpPages();
	}

	@Severity(value = SeverityLevel.NORMAL)
	@Epic("Android")
	@Feature("Login")
	@Story("Realizar Login")
	@Test
	public void realizarLogin() {
		super.realizarLogin();
	}
}

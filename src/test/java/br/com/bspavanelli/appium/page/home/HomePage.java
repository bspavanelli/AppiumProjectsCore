package br.com.bspavanelli.appium.page.home;

import org.junit.Assert;

import br.com.bspavanelli.appium.constants.home.HomeConstants;
import br.com.bspavanelli.appium.utilities.screen_actions.ScreenActions;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class HomePage extends ScreenActions {

	@AndroidFindBy(xpath = "//*[contains(@resource-id,'extract')]/android.widget.TextView")
	@iOSXCUITFindBy(id = "extract_button")
	private MobileElement extratoDePontos;

	public HomePage() {
		initElements(this);
	}

	public HomePage validateHeader() {
		Assert.assertEquals(HomeConstants.TXT_EXTRATO_DE_PONTOS, getText(extratoDePontos));
		return this;
	}
}

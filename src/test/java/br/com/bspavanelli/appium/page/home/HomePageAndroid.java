package br.com.bspavanelli.appium.page.home;

import org.junit.Assert;
import org.openqa.selenium.By;

import br.com.bspavanelli.appium.constants.home.HomeConstants;
import br.com.bspavanelli.appium.utilities.basepage.BasePageAndroid;
import io.appium.java_client.MobileBy;

public class HomePageAndroid extends BasePageAndroid implements HomePage {

	private By HEADER = MobileBy.id("");
	
	@Override
	public HomePage validateHeader() {
		Assert.assertEquals(HomeConstants.TXT_HEADER, getText(HEADER));
		return null;
	}

}

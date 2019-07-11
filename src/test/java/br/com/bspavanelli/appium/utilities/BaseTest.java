package br.com.bspavanelli.appium.utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import br.com.bspavanelli.appium.page.home.HomePage;
import br.com.bspavanelli.appium.page.home.HomePageAndroid;
import br.com.bspavanelli.appium.page.home.HomePageIOS;
import br.com.bspavanelli.appium.page.login.LoginPage;
import br.com.bspavanelli.appium.page.login.LoginPageAndroid;
import br.com.bspavanelli.appium.page.login.LoginPageIOS;

public class BaseTest {

	@Rule
	public TestName testName = new TestName();

	// Criar uma variavel usando a interface da page para cada page usada nos testes, para instanciar a respectiva page no setUp() de acordo com o OS informado na BaseConstants
	protected LoginPage loginPage;
	protected HomePage homePage;

	@Before
	public void setUp() {
		System.out.println("Iniciando os testes do método '" + testName.getMethodName() + "' na plataforma '"
				+ BaseConstants.executionOS.getValor() + "'!");
		switch (BaseConstants.executionOS) {
			case ANDROID:
				// Instanciar todas as pages referente a Android nas variaveis declaradas acima
				loginPage = new LoginPageAndroid();
				homePage = new HomePageAndroid();
				break;
			case IOS:
				// Instanciar todas as pages referente a IOS nas variaveis declaradas acima
				loginPage = new LoginPageIOS();
				homePage = new HomePageIOS();
				break;
		}
		DriverFactory.createDriver();
	}

	@After
	public void tearDown() {
		System.out.println("Finalizando os testes do método '" + testName.getMethodName() + "'!");
		gerarScreenshot();
		DriverFactory.killDriver();
	}

	public void resetApp() {
		DriverFactory.resetApp();
	}

	public void gerarScreenshot() {
		File imagem = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(imagem, new File("target/screenshots/" + testName.getMethodName() + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

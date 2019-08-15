package br.com.bspavanelli.appium.utilities.listener;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import br.com.bspavanelli.appium.utilities.BaseConstants;
import br.com.bspavanelli.appium.utilities.DriverFactory;
import io.qameta.allure.Attachment;

public class TestListener extends TestWatcher {
	
	@Override
	protected void failed(Throwable e, Description description) {
		System.out.println("Método '" + description.getMethodName() + "' falhou! Tirando screenshot da tela.");
		takeScreenshot(description.getMethodName());
	}

	@Override
	protected void finished(Description description) {
		System.out.println("Finalizando os testes do método '" + description.getMethodName() + "'!");
		DriverFactory.killDriver();
	}
	
	@Attachment(value = "Page screenshot", type = "image/png")
	public byte[] takeScreenshot(String methodName) {
		File imagem = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(imagem, new File("target/screenshots/ " + BaseConstants.executionOS.getValor() + "/" + methodName + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
	}
}

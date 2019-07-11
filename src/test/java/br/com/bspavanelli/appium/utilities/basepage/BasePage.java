package br.com.bspavanelli.appium.utilities.basepage;

import org.openqa.selenium.By;

import io.appium.java_client.MobileElement;

public interface BasePage {

	void sleep(long time);

	void sendKeys(By by, String text);

	String getText(By by);

	String getText(By by, int position);

	void click(By by);

	void click(String text);

	void click(String attribute, String text);

	void longClick(By by);

	void selectCombo(By by, String value);

	boolean isChecked(By by);

	boolean elementExists(By by);

	boolean elementExists(String text);

	boolean elementExists(String attribute, String text);

	void tap(int x, int y);

	void scroll(double inicio, double fim, double posicaoHorizontal);

	void scroll(double inicio, double fim);

	void scrollDown();

	void scroll(By by, double inicio, double fim);

	void scrollDown(By by);

	void scrollUp(By by);

	void scroll(MobileElement element, double inicio, double fim);

	void scrollDown(MobileElement element);

	void scrollUp(MobileElement element);

	void swipe(double inicio, double fim);

	void swipeLeft();

	void swipeRight();

	void swipeElement(MobileElement element, double inicio, double fim);

	void swipeElementLeft(MobileElement element);

	void swipeElementRight(MobileElement element);

	void setImplicitlyWait();

	void setImplicitlyWait(int seconds);

	void checkNeedOfScrollToShowElement(By by);

	void checkNeedOfScrollToShowElement(By by, boolean longScroll);

	void checkNeedOfScrollToShowElement(By by, int maxScrolls, boolean longScroll);
}

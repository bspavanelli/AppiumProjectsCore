package br.com.bspavanelli.appium.utilities.basepage;

import static br.com.bspavanelli.appium.utilities.BaseConstants.DEFAULT_IMPLICITLY_WAIT;
import static br.com.bspavanelli.appium.utilities.DriverFactory.getDriver;
import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.interactions.touch.TouchActions;

import br.com.bspavanelli.appium.utilities.BaseConstants;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;

@SuppressWarnings({ "rawtypes" })
public class BasePageIOS implements BasePage {

	/**
	 * Perform a sleep
	 * 
	 * @param time
	 *            = Time in miliseconds
	 */
	public void sleep(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Send keys to the element found using the By param
	 * 
	 * @param by
	 * @param text
	 */
	public void sendKeys(By by, String text) {
		getDriver().findElement(by)
			.sendKeys(text);
	}

	/**
	 * Hide the keyboard.
	 */
	public void hideKeyboard() {
		getDriver().hideKeyboard();
	}

	/**
	 * Get the text of the element found using the By param
	 * 
	 * @param by
	 * @return
	 */
	public String getText(By by) {
		String text = "";
		try {
			text = getDriver().findElement(by)
				.getText();
		} catch (StaleElementReferenceException e) {
			sleep(500);
			text = getDriver().findElement(by)
				.getText();
		}
		return text.replaceAll("\u00a0", " ")
			.replaceAll("\\s+", " ")
			.trim();
	}

	/**
	 * Get the text of one particular element found using the By param.
	 * 
	 * @param by
	 *            = By used to find the list of element
	 * @param position
	 *            = the position of the element list, -1 if the position desired is
	 *            the last position
	 * @return text of the element
	 */
	public String getText(By by, int position) {
		List<MobileElement> elements = getDriver().findElements(by);

		if (position == -1) {
			return elements.get(elements.size() - 1)
				.getText();
		} else {
			return elements.get(position)
				.getText();
		}
	}

	/**
	 * Click on an element found using the By param
	 * 
	 * @param by
	 */
	public void click(By by) {
		getDriver().findElement(by)
			.click();
		sleep(1000);
	}

	/**
	 * Click on an element found by text
	 * 
	 * @param text
	 */
	public void click(String text) {
		click(By.xpath("//*[@text='" + text + "']"));
	}

	/**
	 * Click on an element found by text
	 * 
	 * @param attribute
	 *            = the attribute to search
	 * @param text
	 */
	public void click(String attribute, String text) {
		click(By.xpath("//*[@" + attribute + "='" + text + "']"));
	}

	/**
	 * Perform a long click on the element
	 * 
	 * @param by
	 */
	public void longClick(By by) {
		TouchActions action = new TouchActions(getDriver());
		action.longPress(getDriver().findElement(by))
			.perform();
	}

	/**
	 * Click on a Combo element and select the option searching by text
	 * 
	 * @param by
	 *            = Element to click
	 * @param value
	 *            = Value to select
	 */
	public void selectCombo(By by, String value) {
		getDriver().findElement(by)
			.click();
		click(value);
	}

	/**
	 * Verify if the attribute checked of the element is true
	 * 
	 * @param by
	 * @return
	 */
	public boolean isChecked(By by) {
		return getDriver().findElement(by)
			.getAttribute("checked")
			.equals("true");
	}

	/**
	 * Check if the element exists, searching by the given By param.
	 * 
	 * @param by
	 * @return = true if found, false if not
	 */
	public boolean elementExists(By by) {
		List<MobileElement> elementos = getDriver().findElements(by);
		return elementos.size() > 0;
	}

	/**
	 * Check if the element exists, searching by the given text.
	 * 
	 * @param text
	 * @return = true if found, false if not
	 */
	public boolean elementExists(String text) {
		return elementExists(MobileBy.xpath("//*[@text=\"" + text + "\"]"));
	}

	/**
	 * Check if the element exists, searching by the given text.
	 * 
	 * @param attribute
	 *            = the attribute to search
	 * @param text
	 *            = the value of the attribute
	 * @return = true if found, false if not
	 */
	public boolean elementExists(String attribute, String text) {
		return elementExists(MobileBy.xpath("//*[@" + attribute + "=\"" + text + "\"]"));
	}

	/**
	 * Make a tap in the position defined by the params
	 * 
	 * @param x
	 * @param y
	 */
	public void tap(int x, int y) {
		new TouchAction(getDriver()).tap(point(x, y))
			.waitAction(waitOptions(Duration.ofMillis(250)))
			.perform();
	}

	/**
	 * Make a defined by the params
	 * 
	 * @param inicio
	 *            = posição inicial do scroll, número de 0 a 1, sendo 0 a posição
	 *            vertical mais acima e 1 a posição vertical mais abaixo
	 * @param fim
	 *            = posição inicial do scroll, número de 0 a 1, sendo 0 a posição
	 *            vertical mais acima e 1 a posição vertical mais abaixo
	 * @param posicaoHorizontal
	 *            = número de 0 a 1, sendo 0 a posição horizontal mais a esquerda e
	 *            1 a posição horizontal mais a direita
	 */
	public void scroll(double inicio, double fim, double posicaoHorizontal) {
		Dimension size = getDriver().manage()
			.window()
			.getSize();

		int x = (int) (size.width * posicaoHorizontal);

		int yInicial = (int) (size.height * inicio);
		int yFinal = (int) (size.height * fim);

		TouchAction action = new TouchAction(getDriver());
		action.press(point(x, yInicial))
			.waitAction(waitOptions(Duration.ofMillis(500)))
			.moveTo(point(x, yFinal))
			.release()
			.perform();
		sleep(2000);
	}

	/**
	 * Make a defined by the params
	 * 
	 * @param inicio
	 * @param fim
	 */
	public void scroll(double inicio, double fim) {
		scroll(inicio, fim, 0.5);
	}

	/**
	 * Make a long scroll from the bottom to the top
	 * 
	 * @param element
	 */
	public void scrollDown() {
		scroll(0.9, 0.1);
	}

	/**
	 * Make a long scroll from the top to the bottom
	 * 
	 * @param element
	 */
	public void scrollUp() {
		scroll(0.1, 0.9);
	}

	/**
	 * Make a defined by the params
	 * 
	 * @param by
	 * @param inicio
	 * @param fim
	 */
	public void scroll(By by, double inicio, double fim) {
		scroll(getDriver().findElement(by), inicio, fim);
	}

	/**
	 * Make a long scroll from the bottom to the top
	 * 
	 * @param element
	 */
	public void scrollDown(By by) {
		scroll(getDriver().findElement(by), 0.9, 0.1);
	}

	/**
	 * Make a long scroll from the top to the bottom
	 * 
	 * @param element
	 */
	public void scrollUp(By by) {
		scroll(getDriver().findElement(by), 0.1, 0.9);
	}

	/**
	 * Make a scroll defined by the params
	 * 
	 * @param element
	 * @param inicio
	 * @param fim
	 */
	public void scroll(MobileElement element, double inicio, double fim) {
		Dimension size = element.getSize();

		int x = size.width / 2;

		int yInicial = (int) (size.height * inicio);
		int yFinal = (int) (size.height * fim);

		TouchAction action = new TouchAction(getDriver());
		action.press(point(x, yInicial))
			.waitAction(waitOptions(Duration.ofMillis(500)))
			.moveTo(point(x, yFinal))
			.release()
			.perform();
	}

	/**
	 * Make a long scroll from the bottom to the top
	 * 
	 * @param element
	 */
	public void scrollDown(MobileElement element) {
		scroll(element, 0.9, 0.1);
	}

	/**
	 * Make a long scroll from the top to the bottom
	 * 
	 * @param element
	 */
	public void scrollUp(MobileElement element) {
		scroll(element, 0.1, 0.9);
	}

	/**
	 * Make a swipe defined by the params
	 * 
	 * @param inicio
	 * @param fim
	 */
	public void swipe(double inicio, double fim) {
		Dimension size = getDriver().manage()
			.window()
			.getSize();

		int xInicial = (int) (size.width * inicio);
		int xFinal = (int) (size.width * fim);

		int y = size.height / 2;

		new TouchAction(getDriver()).press(point(xInicial, y))
			.waitAction(waitOptions(Duration.ofMillis(500)))
			.moveTo(point(xFinal, y))
			.release()
			.perform();
		sleep(1000);
	}

	/**
	 * Make a long swipe from the left to the right
	 * 
	 * @param element
	 */
	public void swipeLeft() {
		swipe(0.1, 0.9);
	}

	/**
	 * Make a long swipe from the right to the left
	 * 
	 * @param element
	 */
	public void swipeRight() {
		swipe(0.9, 0.1);
	}

	/**
	 * Make a swipe defined by the params
	 * 
	 * @param element
	 * @param inicio
	 * @param fim
	 */
	public void swipeElement(MobileElement element, double inicio, double fim) {
		Dimension size = element.getSize();

		int xInicial = (int) (size.width * inicio);
		int xFinal = (int) (size.width * fim);

		int y = element.getLocation().y + (size.getHeight() / 2);

		new TouchAction(getDriver()).press(point(xInicial, y))
			.waitAction(waitOptions(Duration.ofMillis(500)))
			.moveTo(point(xFinal, y))
			.release()
			.perform();
		sleep(1000);
	}

	/**
	 * Make a long swipe from the left to the right
	 * 
	 * @param element
	 */
	public void swipeElementLeft(MobileElement element) {
		swipeElement(element, 0.1, 0.9);
	}

	/**
	 * Make a long swipe from the right to the left
	 * 
	 * @param element
	 */
	public void swipeElementRight(MobileElement element) {
		swipeElement(element, 0.9, 0.1);
	}

	/**
	 * Set the implicitly wait of the driver to its default value, defined in
	 * DEFAULD_IMPLICITLY_WAIT constant in BaseConstants
	 * 
	 * @param seconds
	 */
	public void setImplicitlyWait() {
		setImplicitlyWait(DEFAULT_IMPLICITLY_WAIT);
	}

	/**
	 * Set the implicitly wait of the driver
	 * 
	 * @param seconds
	 */
	public void setImplicitlyWait(int seconds) {
		getDriver().manage()
			.timeouts()
			.implicitlyWait(seconds, TimeUnit.SECONDS);
	}

	/**
	 * Check if the element exists in the page, if not, make a short scroll to find
	 * the element a number of times equals to the
	 * DEFAULT_MAX_SCROLLS_TO_FIND_ELEMENT constant in BaseConstants
	 * 
	 * @param by
	 *            = The element
	 */
	public void checkNeedOfScrollToShowElement(By by) {
		checkNeedOfScrollToShowElement(by, BaseConstants.DEFAULT_MAX_SCROLLS_TO_FIND_ELEMENT, false);
	}

	/**
	 * Check if the element exists in the page, if not, make a scroll to find the
	 * element a number of times equals to the DEFAULT_MAX_SCROLLS_TO_FIND_ELEMENT
	 * constant in BaseConstants
	 * 
	 * @param by
	 *            = The element
	 * @param longScroll
	 *            = true for a long scroll (0.9 to 0.2) or false for a short scroll
	 *            (0.6 to 0.4)
	 */
	public void checkNeedOfScrollToShowElement(By by, boolean longScroll) {
		checkNeedOfScrollToShowElement(by, BaseConstants.DEFAULT_MAX_SCROLLS_TO_FIND_ELEMENT, longScroll);
	}

	/**
	 * Check if the element exists in the page, if not, make a scroll to find the
	 * element
	 * 
	 * @param by
	 *            = The element
	 * @param maxScrolls
	 *            = Max scrolls before stop scrolling
	 * @param longScroll
	 *            = true for a long scroll (0.8 to 0.2) or false for a short scroll
	 *            (0.6 to 0.4)
	 */
	public void checkNeedOfScrollToShowElement(By by, int maxScrolls, boolean longScroll) {
		setImplicitlyWait(3);
		for (int i = 1; i <= maxScrolls; i++) {
			if (!elementExists(by)) {
				if (longScroll) {
					scroll(0.8, 0.2);
				} else {
					scroll(0.6, 0.4);
				}
			} else {
				break;
			}
		}
		setImplicitlyWait(DEFAULT_IMPLICITLY_WAIT);
	}
}

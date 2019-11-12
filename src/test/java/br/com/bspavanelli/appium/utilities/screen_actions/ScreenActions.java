package br.com.bspavanelli.appium.utilities.screen_actions;

import static br.com.bspavanelli.appium.utilities.DriverFactory.getDriver;
import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.PageFactory;

import br.com.bspavanelli.appium.utilities.BaseConstants;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

@SuppressWarnings({ "rawtypes" })
public class ScreenActions {
	/**
	 * Perform a sleep
	 * 
	 * @param time
	 *            = Time in miliseconds
	 */
	protected void sleep(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Send keys to the element
	 * 
	 * @param element
	 * @param text
	 */
	protected void sendKeys(MobileElement element, String text) {
		element.sendKeys(text);
	}

	/**
	 * Get the attribute value of the element
	 * 
	 * @param element
	 * @param attribute
	 * @return value of the element attribute
	 */
	protected String getAttribute(MobileElement element, String attribute) {
		String text = "";
		try {
			text = element.getAttribute(attribute);
		} catch (StaleElementReferenceException e) {
			sleep(500);
			text = element.getAttribute(attribute);
		}
		return removeOtherSpaces(text);
	}

	/**
	 * Get the text of the element
	 * 
	 * @param element
	 * @return text of the element
	 * 
	 */
	protected String getText(MobileElement element) {
		String text = "";
		try {
			text = element.getText();
		} catch (StaleElementReferenceException e) {
			sleep(500);
			text = element.getText();
		}
		return removeOtherSpaces(text);
	}

	/**
	 * Click on an element
	 * 
	 * @param element
	 */
	protected void click(MobileElement element) {
		element.click();
		sleep(1000);
	}

	/**
	 * Click on an element found in a list with the following value of an attribute
	 * 
	 * @param elements
	 * @param attribute
	 * @param value
	 */
	protected void click(List<MobileElement> elements, String attribute, String value) {
		for (MobileElement element : elements) {
			if (removeOtherSpaces(element.getAttribute(attribute)).equals(value)) {
				element.click();
				return;
			}
		}
		throw new RuntimeException(
				"Erro! Elemento com atributo '" + attribute + "' de valor '" + value + "' não encontrado.");
	}

	/**
	 * Click on an element found by text
	 * 
	 * @param text
	 */
	protected void click(String text) {
		click(getDriver().findElement(By.xpath("//*[@text='" + text + "' or @value='" + text + "']")));
	}

	/**
	 * Click on an element found by attribute value
	 * 
	 * @param attribute
	 *            = the attribute to search
	 * @param text
	 */
	protected void click(String attribute, String value) {
		click(getDriver().findElement(By.xpath("//*[@" + attribute + "='" + value + "']")));
	}

	/**
	 * Perform a long click on the element found with By param
	 * 
	 * @param element
	 */
	protected void longClick(MobileElement element) {
		TouchActions action = new TouchActions(getDriver());
		action.longPress(element)
			.perform();
	}

	/**
	 * Click on a Combo element and select the option searching by value
	 * 
	 * @param element
	 *            = Element to click
	 * @param value
	 *            = Value to select
	 */
	protected void selectCombo(MobileElement element, String value) {
		element.click();
		click(value);
	}

	/**
	 * Verify if the attribute checked of the element is true
	 * 
	 * @param element
	 * @return true if checked, false if not
	 */
	protected boolean isChecked(MobileElement element) {
		return element.getAttribute("checked")
			.equals("true");
	}

	/**
	 * Check if the element exists
	 * 
	 * @param element
	 * @return = true if found, false if not
	 */
	protected boolean elementExists(MobileElement element) {
		try {
			element.isDisplayed();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * Check if the element exists
	 * 
	 * @param elements
	 * @return = true if found, false if not
	 */
	protected boolean elementExists(List<MobileElement> elements) {
		return elements.size() > 0;
	}

	/**
	 * Check if the element exists
	 * 
	 * @param elements
	 * @param timeout
	 *            = the implicitly wait to be set before the check
	 * @return = true if found, false if not
	 */
	protected boolean elementExists(List<MobileElement> elements, int timeout) {
		return elements.size() > 0;
	}

	/**
	 * Check if the element exists, searching by the given text.
	 * 
	 * @param text
	 * @return = true if found, false if not
	 */
	protected boolean elementExists(String text) {
		return elementExists(
				getDriver().findElements(MobileBy.xpath("//*[@text='" + text + "' or @value='" + text + "']")));
	}

	/**
	 * Check if the element exists, searching by the given text.
	 * 
	 * @param text
	 * @param timeout
	 *            = the implicitly wait to be set before the check
	 * @return = true if found, false if not
	 */
	protected boolean elementExists(String text, int timeout) {
		return elementExists(
				getDriver().findElements(MobileBy.xpath("//*[@text='" + text + "' or @value='" + text + "']")),
				timeout);
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
	protected boolean elementExists(String attribute, String text) {
		return elementExists(getDriver().findElements(MobileBy.xpath("//*[@" + attribute + "='" + text + "']")));
	}

	/**
	 * Check if the element exists, searching by the given text.
	 * 
	 * @param attribute
	 *            = the attribute to search
	 * @param text
	 *            = the value of the attribute
	 * @param timeout
	 *            = the implicitly wait to be set before the check
	 * @return = true if found, false if not
	 */
	protected boolean elementExists(String attribute, String text, int timeout) {
		return elementExists(getDriver().findElements(MobileBy.xpath("//*[@" + attribute + "='" + text + "']")),
				timeout);
	}

	/**
	 * Make a tap in the position defined by the params
	 * 
	 * @param x
	 * @param y
	 */
	protected void tap(int x, int y) {
		new TouchAction(getDriver()).tap(point(x, y))
			.waitAction(waitOptions(Duration.ofMillis(250)))
			.perform();
	}

	/**
	 * Make a scroll defined by the params
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
	protected void scroll(double inicio, double fim, double posicaoHorizontal) {
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
	 * Make a scroll defined by the params
	 * 
	 * @param inicio
	 * @param fim
	 */
	protected void scroll(double inicio, double fim) {
		scroll(inicio, fim, 0.5);
	}

	/**
	 * Make a long scroll from the bottom to the top
	 * 
	 */
	protected void scrollDown() {
		scroll(0.9, 0.1);
	}

	/**
	 * Make a long scroll from the top to the bottom
	 * 
	 */
	protected void scrollUp() {
		scroll(0.1, 0.9);
	}

	/**
	 * Make a scroll inside the mobile element defined by the params
	 * 
	 * @param element
	 * @param inicio
	 * @param fim
	 */
	protected void scroll(MobileElement element, double inicio, double fim) {
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
	 * Make a long scroll inside the mobile element from the bottom to the top
	 * 
	 * @param element
	 */
	protected void scrollDown(MobileElement element) {
		scroll(element, 0.9, 0.1);
	}

	/**
	 * Make a long scroll inside the mobile element from the top to the bottom
	 * 
	 * @param element
	 */
	protected void scrollUp(MobileElement element) {
		scroll(element, 0.1, 0.9);
	}

	/**
	 * Make a swipe defined by the params
	 * 
	 * @param inicio
	 * @param fim
	 */
	protected void swipe(double inicio, double fim) {
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
	 */
	protected void swipeLeft() {
		swipe(0.1, 0.9);
	}

	/**
	 * Make a long swipe from the right to the left
	 * 
	 */
	protected void swipeRight() {
		swipe(0.9, 0.1);
	}

	/**
	 * Make a swipe inside the mobile element defined by the params
	 * 
	 * @param element
	 * @param inicio
	 * @param fim
	 */
	protected void swipeElement(MobileElement element, double inicio, double fim) {
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
	protected void swipeElementLeft(MobileElement element) {
		swipeElement(element, 0.1, 0.9);
	}

	/**
	 * Make a long swipe from the right to the left
	 * 
	 * @param element
	 */
	protected void swipeElementRight(MobileElement element) {
		swipeElement(element, 0.9, 0.1);
	}

	/**
	 * Check if the element exists in the page, if not, make a short scroll to find
	 * the element a number of times equals to the
	 * DEFAULT_MAX_SCROLLS_TO_FIND_ELEMENT constant in BaseConstants
	 * 
	 * @param element
	 *            = The element
	 */
	protected void checkNeedOfScrollToShowElement(MobileElement element) {
		checkNeedOfScrollToShowElement(element, BaseConstants.DEFAULT_MAX_SCROLLS_TO_FIND_ELEMENT, false);
	}

	/**
	 * Check if the element exists in the page, if not, make a scroll to find the
	 * element a number of times equals to the DEFAULT_MAX_SCROLLS_TO_FIND_ELEMENT
	 * constant in BaseConstants
	 * 
	 * @param element
	 *            = The element
	 * @param longScroll
	 *            = true for a long scroll (0.9 to 0.2) or false for a short scroll
	 *            (0.6 to 0.4)
	 */
	protected void checkNeedOfScrollToShowElement(MobileElement element, boolean longScroll) {
		checkNeedOfScrollToShowElement(element, BaseConstants.DEFAULT_MAX_SCROLLS_TO_FIND_ELEMENT, longScroll);
	}

	/**
	 * Check if the element exists in the page, if not, make a scroll to find the
	 * element
	 * 
	 * @param element
	 *            = The element
	 * @param maxScrolls
	 *            = Max scrolls before stop scrolling
	 * @param longScroll
	 *            = true for a long scroll (0.9 to 0.2) or false for a short scroll
	 *            (0.6 to 0.4)
	 */
	protected void checkNeedOfScrollToShowElement(MobileElement element, int maxScrolls, boolean longScroll) {
		for (int i = 1; i <= maxScrolls; i++) {
			if (!elementExists(element)) {
				if (longScroll) {
					scroll(0.8, 0.2);
				} else {
					scroll(0.6, 0.4);
				}
			} else {
				break;
			}
		}
	}

	/**
	 * Check if the element exists in the page, if not, make a short scroll to find
	 * the element a number of times equals to the
	 * DEFAULT_MAX_SCROLLS_TO_FIND_ELEMENT constant in BaseConstants
	 * 
	 * @param attribute
	 *            = The attribute to search
	 * @param value
	 *            = The attribute value
	 */
	protected void checkNeedOfScrollToShowElement(String attribute, String value) {
		checkNeedOfScrollToShowElement(getDriver().findElement(By.xpath("//*[@" + attribute + "=\"" + value + "\"]")));
	}

	/**
	 * Check if the element exists in the page, if not, make a scroll to find the
	 * element a number of times equals to the DEFAULT_MAX_SCROLLS_TO_FIND_ELEMENT
	 * constant in BaseConstants
	 * 
	 * @param attribute
	 *            = The attribute to search
	 * @param value
	 *            = The attribute value
	 * @param longScroll
	 *            = true for a long scroll (0.9 to 0.2) or false for a short scroll
	 *            (0.6 to 0.4)
	 */
	protected void checkNeedOfScrollToShowElement(String attribute, String value, boolean longScroll) {
		checkNeedOfScrollToShowElement(getDriver().findElement(By.xpath("//*[@" + attribute + "=\"" + value + "\"]")),
				longScroll);
	}

	/**
	 * Check if the element exists in the page, if not, make a scroll to find the
	 * element
	 * 
	 * @param attribute
	 *            = The attribute to search
	 * @param value
	 *            = The attribute value
	 * @param maxScrolls
	 *            = Max scrolls before stop scrolling
	 * @param longScroll
	 *            = true for a long scroll (0.9 to 0.2) or false for a short scroll
	 *            (0.6 to 0.4)
	 */
	protected void checkNeedOfScrollToShowElement(String attribute, String value, int maxScrolls, boolean longScroll) {
		checkNeedOfScrollToShowElement(getDriver().findElement(By.xpath("//*[@" + attribute + "=\"" + value + "\"]")),
				maxScrolls, longScroll);
	}

	/**
	 * Remove all double space, line break and other types of space except simple
	 * space of the string
	 * 
	 * @param string
	 * @return string with only simple spaces
	 *
	 */
	protected String removeOtherSpaces(String string) {
		return string.replaceAll("\u00a0", " ")
			.replaceAll("\\s+", " ")
			.trim();
	}

	/**
	 * Execute the initElements of the Page Factory with a field decorator timeout
	 * equals to the param seconds
	 * 
	 * @param page
	 * @param seconds
	 */
	protected void initElements(Object page, Duration seconds) {
		PageFactory.initElements(new AppiumFieldDecorator(getDriver(), seconds), page);
	}

	/**
	 * Execute the initElements of the Page Factory with a field decorator timeout
	 * equals to the default in BaseConstants
	 * 
	 * @param page
	 */
	protected void initElements(Object page) {
		initElements(page, BaseConstants.DEFAULT_FACTORY_DURATION);
	}

}

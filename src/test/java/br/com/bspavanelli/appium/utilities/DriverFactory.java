package br.com.bspavanelli.appium.utilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

public class DriverFactory {

	public static AppiumDriver<MobileElement> driver;

	public static AppiumDriver<MobileElement> getDriver() {
		if (driver == null) {
			createDriver();
		}
		return driver;
	}

	public static void createDriver() {
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		switch (BaseConstants.executionOS) {
			case ANDROID:
				desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "android");
				desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
				desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
				desiredCapabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
				desiredCapabilities.setCapability(MobileCapabilityType.APP, BaseConstants.ANDROID_APP);
				// desiredCapabilities.setCapability("appPackage", BaseConstants.BASE_PACKAGE);
				desiredCapabilities.setCapability("appActivity", BaseConstants.BASE_ACTIVITY);

				try {
					driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"),
							desiredCapabilities);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}

				driver.manage()
					.timeouts()
					.implicitlyWait(BaseConstants.DEFAULT_IMPLICITLY_WAIT, TimeUnit.SECONDS);

				break;
			case IOS:
				desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "IphoneXUiTest");
				desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
				desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "IOS");
				desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "12.2");
				desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60);
				desiredCapabilities.setCapability(MobileCapabilityType.FULL_RESET, false);
				desiredCapabilities.setCapability(MobileCapabilityType.CLEAR_SYSTEM_FILES, true);
				desiredCapabilities.setCapability(IOSMobileCapabilityType.AUTO_ACCEPT_ALERTS, true);
				desiredCapabilities.setCapability(IOSMobileCapabilityType.USE_NEW_WDA, true);
				desiredCapabilities.setCapability(MobileCapabilityType.APP, BaseConstants.IOS_APP);
				desiredCapabilities.setCapability("unicodeKeyboard", true);
				desiredCapabilities.setCapability("resetKeyboard", true);
				desiredCapabilities.setCapability("connectHardwareKeyboard", true);
				desiredCapabilities.setCapability("udid", BaseConstants.UDID);
				try {
					driver = new IOSDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
				break;
			default:
				break;
		}
		driver.manage()
			.timeouts()
			.implicitlyWait(BaseConstants.DEFAULT_IMPLICITLY_WAIT, TimeUnit.SECONDS);
	}

	public static void killDriver() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}

	public static void resetApp() {
		if (driver != null) {
			driver.resetApp();
		}
	}
}

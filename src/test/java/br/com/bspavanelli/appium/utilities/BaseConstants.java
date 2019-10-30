package br.com.bspavanelli.appium.utilities;

import java.time.Duration;

import br.com.bspavanelli.appium.utilities.enums.OS;

public class BaseConstants {

	public static OS executionOS = OS.getOS(System.getProperty("executionOS"));

	public static final String BASE_PACKAGE = "br.com.app";
	public static final String BASE_ACTIVITY = "br.com.app.splash.SplashActivity";

	public static final int DEFAULT_TIMEOUT = 30;
	public static final int DEFAULT_IMPLICITLY_WAIT = 15;
	public static final int DEFAULT_MAX_SCROLLS_TO_FIND_ELEMENT = 5;

	public static final Duration DEFAULT_FACTORY_DURATION = Duration.ofSeconds(DEFAULT_IMPLICITLY_WAIT);

	public static final String APP_DIR = System.getProperty("user.dir") + "/src/test/resources/";

	public static final String ANDROID_APP = APP_DIR + "livelo-remoteUatDebug.apk";
	public static final String IOS_APP = APP_DIR + "Livelo.app";

	public static final String UDID = "BE50D29B-96AB-458A-9E45-BB27F3270CD0";
//	public static final String UDID = "5E324623-793C-44E3-9258-56A742135748"; // Jenkins

}

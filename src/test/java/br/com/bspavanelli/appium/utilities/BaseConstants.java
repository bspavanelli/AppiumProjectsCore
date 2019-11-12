package br.com.bspavanelli.appium.utilities;

import java.time.Duration;

import br.com.bspavanelli.appium.utilities.enums.OS;

public class BaseConstants {

	public static OS executionOS;

	public static final String BASE_PACKAGE = "br.com.app";
	public static final String BASE_ACTIVITY = "br.com.app.splash.SplashActivity";

	public static final int DEFAULT_IMPLICITLY_WAIT = 15;
	public static final int DEFAULT_MAX_SCROLLS_TO_FIND_ELEMENT = 5;

	public static final Duration DEFAULT_FACTORY_DURATION = Duration.ofSeconds(DEFAULT_IMPLICITLY_WAIT);

	public static final String APP_DIR = System.getProperty("user.dir") + "/src/test/resources/";

	public static final String ANDROID_APP = APP_DIR + "nome-do-app.apk";
	public static final String IOS_APP = APP_DIR + "nome-do-app.app";

	public static final String UDID = "87BB0B65-6A30-4A14-82BA-344985519F0C";
//	public static final String UDID = "5E324623-793C-44E3-9258-56A742135748"; // Jenkins

}

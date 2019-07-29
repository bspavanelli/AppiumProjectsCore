package br.com.bspavanelli.appium.utilities;

import br.com.bspavanelli.appium.utilities.enums.OS;

public class BaseConstants {

	//	public static OS executionOS = OS.ANDROID;
	public static OS executionOS = OS.getOS(System.getProperty("executionOS"));

	public static final String BASE_PACKAGE = "br.com.app";
	public static final String BASE_ACTIVITY = "br.com.app.splash.SplashActivity";

	public static final int DEFAULT_TIMEOUT = 30;
	public static final int DEFAULT_IMPLICITLY_WAIT = 15;
	public static final int DEFAULT_MAX_SCROLLS_TO_FIND_ELEMENT = 5;

}

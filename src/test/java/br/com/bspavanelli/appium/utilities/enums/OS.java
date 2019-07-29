package br.com.bspavanelli.appium.utilities.enums;

public enum OS {
	ANDROID("Android"), IOS("IOS");

	private String valor;

	OS(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}

	public static OS getOS(String valor) {
		for (OS os : OS.values()) {
			if (os.valor.equals(valor))
				return os;
		}
		throw new IllegalArgumentException(
				"OS Not found! Possible options: 'IOS' and 'Android'. Option sent: '" + valor + "'");
	}

	public static OS valueOfIgnoreCase(String value) {
		return valueOf(value.toUpperCase());
	}
}
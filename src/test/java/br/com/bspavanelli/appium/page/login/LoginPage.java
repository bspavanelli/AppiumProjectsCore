package br.com.bspavanelli.appium.page.login;

public interface LoginPage {

	// Note que geralmente as implementações dos metodos da interface nas classes específicas da plataforma normalmente serão as mesmas, 
	// mas podem haver diferenças por conta da plataforma, nese caso a arquitetura já está pronta caso precise de algum tratamente especifico para ios ou android
	// Uma coisa que diferencia com certeza são as variáveis By, que mudam do IOS pro Android

	// Retorna própria instancia para realizar chamadas em cascata nos testes
	LoginPage writeUsername();

	LoginPage writePassword();

	LoginPage clickLogin();
}

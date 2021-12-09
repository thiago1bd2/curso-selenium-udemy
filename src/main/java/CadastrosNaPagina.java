import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class CadastrosNaPagina {
	
	static final String DRIVER_PATH = "/home/monitora/Documents/webdrivers/chromium/chromedriver";
	static final String CAMPO_TREINAMENTO_HTML = "file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html";

	@Test
	public void deveCadastrarUmaPessoaEValidarDados() {
		System.setProperty("webdriver.chrome.driver", DRIVER_PATH);
		WebDriver webDriver = new ChromeDriver();
		webDriver.get(CAMPO_TREINAMENTO_HTML);
		
		String nome = "Thiago";
		String sobrenome = "Ribeiro";
		
		WebElement inputTextName = webDriver.findElement(By.id("elementosForm:nome"));
		WebElement inputTextSurname = webDriver.findElement(By.id("elementosForm:sobrenome"));
		WebElement radioButtonMan = webDriver.findElement(By.id("elementosForm:sexo:0"));
		WebElement checkBoxFavoriteFood = webDriver.findElement(By.id("elementosForm:comidaFavorita:2"));
		
		WebElement comboBoxEducationLevel = webDriver.findElement(By.id("elementosForm:escolaridade"));
		Select comboEducation = new Select(comboBoxEducationLevel);
		
		WebElement multiComboSports = webDriver.findElement(By.id("elementosForm:esportes"));
		Select comboSport = new Select(multiComboSports);
		
		WebElement buttonRegister = webDriver.findElement(By.id("elementosForm:cadastrar"));
		
		
		inputTextName.sendKeys(nome);
		inputTextSurname.sendKeys(sobrenome);
		radioButtonMan.click();
		checkBoxFavoriteFood.click();
		comboEducation.selectByVisibleText("Superior");
		comboSport.selectByVisibleText("Corrida");
		buttonRegister.click();
		
		WebElement textRegistered = webDriver.findElement(By.id("resultado"));
		WebElement textName = webDriver.findElement(By.id("descNome"));
		WebElement textSurname = webDriver.findElement(By.id("descSobrenome"));
		WebElement textSex = webDriver.findElement(By.id("descSexo"));
		WebElement textFavoriteFoods = webDriver.findElement(By.id("descComida"));
		WebElement textEducationLevel = webDriver.findElement(By.id("descEscolaridade"));
		WebElement textSports = webDriver.findElement(By.id("descEsportes"));
		
		assertTrue(textRegistered.getText().startsWith("Cadastrado!"));
		assertTrue(textName.getText().endsWith(nome));
		assertTrue(textSurname.getText().endsWith(sobrenome));
		assertTrue(textSex.getText().endsWith("Masculino"));
		assertTrue(textFavoriteFoods.getText().endsWith("Pizza"));
		assertTrue(textEducationLevel.getText().endsWith("superior"));
		assertTrue(textSports.getText().endsWith("Corrida"));
		
		webDriver.quit();
		
	}

}

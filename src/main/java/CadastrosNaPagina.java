import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class CadastrosNaPagina {

	final String CAMPO_TREINAMENTO_HTML = "file:///" + System.getProperty("user.dir")
			+ "/src/main/resources/componentes.html";

	private WebDriver driver;

	@Before
	public void init() {
		driver = new ChromeDriver();
		driver.get(CAMPO_TREINAMENTO_HTML);
	}

	@After
	public void finalize() {
		driver.quit();
	}

	@Test
	public void deveCadastrarUmaPessoaEValidarDados() {
		String nome = "Thiago";
		String sobrenome = "Ribeiro";

		WebElement inputTextName = driver.findElement(By.id("elementosForm:nome"));
		WebElement inputTextSurname = driver.findElement(By.id("elementosForm:sobrenome"));
		WebElement radioButtonMan = driver.findElement(By.id("elementosForm:sexo:0"));
		WebElement checkBoxFavoriteFood = driver.findElement(By.id("elementosForm:comidaFavorita:2"));

		WebElement comboBoxEducationLevel = driver.findElement(By.id("elementosForm:escolaridade"));
		Select comboEducation = new Select(comboBoxEducationLevel);

		WebElement multiComboSports = driver.findElement(By.id("elementosForm:esportes"));
		Select comboSport = new Select(multiComboSports);

		WebElement buttonRegister = driver.findElement(By.id("elementosForm:cadastrar"));

		inputTextName.sendKeys(nome);
		inputTextSurname.sendKeys(sobrenome);
		radioButtonMan.click();
		checkBoxFavoriteFood.click();
		comboEducation.selectByVisibleText("Superior");
		comboSport.selectByVisibleText("Corrida");
		buttonRegister.click();

		WebElement textRegistered = driver.findElement(By.id("resultado"));
		WebElement textName = driver.findElement(By.id("descNome"));
		WebElement textSurname = driver.findElement(By.id("descSobrenome"));
		WebElement textSex = driver.findElement(By.id("descSexo"));
		WebElement textFavoriteFoods = driver.findElement(By.id("descComida"));
		WebElement textEducationLevel = driver.findElement(By.id("descEscolaridade"));
		WebElement textSports = driver.findElement(By.id("descEsportes"));

		assertTrue(textRegistered.getText().startsWith("Cadastrado!"));
		assertTrue(textName.getText().endsWith(nome));
		assertTrue(textSurname.getText().endsWith(sobrenome));
		assertTrue(textSex.getText().endsWith("Masculino"));
		assertTrue(textFavoriteFoods.getText().endsWith("Pizza"));
		assertTrue(textEducationLevel.getText().endsWith("superior"));
		assertTrue(textSports.getText().endsWith("Corrida"));
	}

}

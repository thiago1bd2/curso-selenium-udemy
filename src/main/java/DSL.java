import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DSL {

	private WebDriver driver;

	public DSL(WebDriver driver) {
		this.driver = driver;
	}

	public void escreverTexto(String id, String texto) {
		escreverTexto(By.id(id), texto);
	}

	public void escreverTexto(By by, String texto) {
		driver.findElement(by).clear();
		driver.findElement(by).sendKeys(texto);
	}

	public String obterValorElemento(String id) {
		return driver.findElement(By.id(id)).getAttribute("value");
	}

	public String obterTextoElemento(String id) {
		return obterTextoElemento(By.id(id));
	}

	public String obterTextoElemento(By by) {
		return driver.findElement(by).getText();
	}

	public void clicarElemento(String id) {
		clicarElemento(By.id(id));
	}

	public void clicarElemento(By by) {
		driver.findElement(by).click();
	}

	public boolean isElementoSelecionado(String id) {
		return driver.findElement(By.id(id)).isSelected();
	}

	public void selecionarTextoVisivelCombo(String id, String valor) {

		new Select(driver.findElement(By.id(id))).selectByVisibleText(valor);
	}

	public void selecionaValorIndex(String id, int index) {
		new Select(driver.findElement(By.id(id))).selectByIndex(index);
	}

	public String obterValorSelecionadoCombo(String id) {
		return new Select(driver.findElement(By.id(id))).getFirstSelectedOption().getText();
	}

	public boolean isValorDisponivelCombo(String id, String valor) {
		List<WebElement> options = new Select(driver.findElement(By.id(id))).getOptions();

		for (WebElement option : options) {
			if (option.getText().equals(valor)) {
				return true;

			}
		}

		return false;
	}

	public boolean isValorSelecionado(String id, String valor) {
		List<String> options = obterValoresSelecionadosCombo(id);

		for (String option : options) {
			if (option.equals(valor)) {
				return true;

			}
		}

		return false;
	}

	public int quantidadeValoresSelecionadosCombo(String id) {
		return new Select(driver.findElement(By.id(id))).getAllSelectedOptions().size();
	}

	public int quantidadeValoresDisponiveis(String id) {
		return new Select(driver.findElement(By.id(id))).getOptions().size();
	}

	public List<String> obterValoresDisponiveisCombo(String id) {
		List<String> opcoes = new ArrayList<String>();
		Select options = new Select(driver.findElement(By.id(id)));

		for (WebElement option : options.getOptions()) {
			opcoes.add(option.getText());
		}

		return opcoes;

	}

	public List<String> obterValoresSelecionadosCombo(String id) {
		List<String> opcoes = new ArrayList<String>();
		Select options = new Select(driver.findElement(By.id(id)));

		for (WebElement option : options.getAllSelectedOptions()) {
			opcoes.add(option.getText());
		}

		return opcoes;

	}

	public Alert mudarFocoAlerta() {
		return driver.switchTo().alert();
	}

	public void mudarFocoJanela(String title) {
		driver.switchTo().window(title);
	}

	public void confirmarAlerta(Alert alert) {
		alert.accept();
	}

	public void cancelarAlerta(Alert alert) {
		alert.dismiss();
	}

	public void escreverPrompAlert(Alert alert, String valor) {
		alert.sendKeys(valor);
	}

	public String obterTextoAlerta(Alert alert) {
		return alert.getText();
	}

	public void mudarFocoFrame(String id) {
		driver.switchTo().frame(id);
	}

	public void sairFocoFrame() {
		driver.switchTo().defaultContent();
	}

	public void fechar() {
		driver.close();
	}

	public Object executarJS(String cmd, Object... objects) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript(cmd, objects);
	}

	public void clicarBotaoTabela(String coluna, String valor, String colunaBotao, String idTabela) {
		WebElement tabela = driver.findElement(By.xpath("//table[@id='"+idTabela+"']"));
		int idColuna = indexOfColuna(coluna, tabela);		
		int idLinha = indexOfLinha(valor, tabela, idColuna);
		int idColunaBotao = indexOfColuna(colunaBotao, tabela);
		WebElement botao = tabela.findElement(By.xpath("./tbody/tr["+idLinha+"]/td["+idColunaBotao+"]/input"));
		botao.click();
	}

	private int indexOfColuna(String nomeColuna, WebElement tabela) {
		List<WebElement> colunas = tabela.findElements(By.xpath(".//th"));

		for (WebElement coluna : colunas) {
			if (coluna.getText().equals(nomeColuna))
				return colunas.indexOf(coluna) + 1;
		}

		return -1;
	}
	
	private int indexOfLinha(String valor, WebElement tabela, int indexColuna) {
		List<WebElement> linhas = tabela.findElements(By.xpath("./tbody/tr/td["+indexColuna+"]"));

		for (WebElement linha : linhas) {
			if (linha.getText().equals(valor))
				return linhas.indexOf(linha) + 1;
		}
		return -1;
	}

}

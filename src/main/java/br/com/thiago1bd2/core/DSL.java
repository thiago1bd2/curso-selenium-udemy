package br.com.thiago1bd2.core;

import static br.com.thiago1bd2.core.DriverFactory.getDriver;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DSL {

	public void escreverTexto(String id, String texto) {
		escreverTexto(By.id(id), texto);
	}

	public void escreverTexto(By by, String texto) {
		getDriver().findElement(by).clear();
		getDriver().findElement(by).sendKeys(texto);
	}

	public String obterValorElemento(String id) {
		return getDriver().findElement(By.id(id)).getAttribute("value");
	}

	public String obterTextoElemento(String id) {
		return obterTextoElemento(By.id(id));
	}

	public String obterTextoElemento(By by) {
		return getDriver().findElement(by).getText();
	}

	public void clicarElemento(String id) {
		clicarElemento(By.id(id));
	}

	public void clicarElemento(By by) {
		getDriver().findElement(by).click();
	}

	public boolean isElementoSelecionado(String id) {
		return isElementoSelecionado(By.id(id));
	}

	public boolean isElementoSelecionado(By by) {
		return getDriver().findElement(by).isSelected();
	}

	public void selecionarTextoVisivelCombo(String id, String valor) {
		selecionarTextoVisivelCombo(By.id(id), valor);
	}

	public void selecionarTextoVisivelCombo(By by, String valor) {
		new Select(getDriver().findElement(by)).selectByVisibleText(valor);
	}

	public void selecionaValorIndex(String id, int index) {
		new Select(getDriver().findElement(By.id(id))).selectByIndex(index);
	}

	public String obterValorSelecionadoCombo(String id) {
		return new Select(getDriver().findElement(By.id(id))).getFirstSelectedOption().getText();
	}

	public boolean isValorDisponivelCombo(String id, String valor) {
		List<WebElement> options = new Select(getDriver().findElement(By.id(id))).getOptions();

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
		return new Select(getDriver().findElement(By.id(id))).getAllSelectedOptions().size();
	}

	public int quantidadeValoresDisponiveis(String id) {
		return new Select(getDriver().findElement(By.id(id))).getOptions().size();
	}

	public List<String> obterValoresDisponiveisCombo(String id) {
		List<String> opcoes = new ArrayList<String>();
		Select options = new Select(getDriver().findElement(By.id(id)));

		for (WebElement option : options.getOptions()) {
			opcoes.add(option.getText());
		}

		return opcoes;

	}

	public List<String> obterValoresSelecionadosCombo(String id) {
		List<String> opcoes = new ArrayList<String>();
		Select options = new Select(getDriver().findElement(By.id(id)));

		for (WebElement option : options.getAllSelectedOptions()) {
			opcoes.add(option.getText());
		}

		return opcoes;

	}

	public void mudarFocoJanela(String title) {
		getDriver().switchTo().window(title);
	}

	public void escreverPrompAlert(String valor) {
		Alert alert = getDriver().switchTo().alert();
		alert.sendKeys(valor);
	}

	public String obterTextoAlertaEConfirma() {
		Alert alert = getDriver().switchTo().alert();
		String text = alert.getText();
		alert.accept();
		return text;
	}

	public String obterTextoAlertaECancela() {
		Alert alert = getDriver().switchTo().alert();
		String text = alert.getText();
		alert.dismiss();
		return text;
	}

	public void mudarFocoFrame(String id) {
		getDriver().switchTo().frame(id);
	}

	public void sairFocoFrame() {
		getDriver().switchTo().defaultContent();
	}

	public Object executarJS(String cmd, Object... objects) {
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		return js.executeScript(cmd, objects);
	}

	public void clicarBotaoTabela(String coluna, String valor, String colunaBotao, String idTabela) {
		WebElement tabela = getDriver().findElement(By.xpath("//table[@id='" + idTabela + "']"));
		int idColuna = indexOfColuna(coluna, tabela);
		int idLinha = indexOfLinha(valor, tabela, idColuna);
		int idColunaBotao = indexOfColuna(colunaBotao, tabela);
		WebElement botao = tabela.findElement(By.xpath("./tbody/tr[" + idLinha + "]/td[" + idColunaBotao + "]/input"));
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
		List<WebElement> linhas = tabela.findElements(By.xpath("./tbody/tr/td[" + indexColuna + "]"));

		for (WebElement linha : linhas) {
			if (linha.getText().equals(valor))
				return linhas.indexOf(linha) + 1;
		}
		return -1;
	}

}

import org.openqa.selenium.By;

import br.com.thiago1bd2.core.DSL;

public class CampoTreinamentoPage {

	private DSL dsl;

	public CampoTreinamentoPage() {
		dsl = new DSL();
	}

	public void setNome(String nome) {
		dsl.escreverTexto("elementosForm:nome", nome);
	}

	public void setSobrenome(String sobrenome) {
		dsl.escreverTexto("elementosForm:sobrenome", sobrenome);
	}

	public void setSexoMasculino() {
		dsl.clicarElemento("elementosForm:sexo:0");
	}

	public void setSexoFeminino() {
		dsl.clicarElemento("elementosForm:sexo:1");
	}

	public void setComidaFavoritaCarne() {
		dsl.clicarElemento("elementosForm:comidaFavorita:0");
	}

	public void setComidaFavoritaFrango() {
		dsl.clicarElemento("elementosForm:comidaFavorita:1");
	}

	public void setComidaFavoritaPizza() {
		dsl.clicarElemento("elementosForm:comidaFavorita:2");
	}

	public void setComidaFavoritaVegetariano() {
		dsl.clicarElemento("elementosForm:comidaFavorita:3");
	}
	
	public void setEscolaridade(String valor) {
		dsl.selecionarTextoVisivelCombo("elementosForm:escolaridade", valor);
	}
	
	public void setEsporte(String...valores) {
		for (String valor : valores)
			dsl.selecionarTextoVisivelCombo("elementosForm:esportes", valor);
	}
	
	public void cadastrar() {
		dsl.clicarElemento("elementosForm:cadastrar");
	}
	
	public String getResultadoCadastro() {
		return dsl.obterTextoElemento(By.xpath("//*[@id='resultado']/span"));
	}
	
	public String getNomeCadastro() {
		return dsl.obterTextoElemento(By.xpath("//*[@id='descNome']/span"));
	}
	
	public String getSobrenomeCadastro() {
		return dsl.obterTextoElemento(By.xpath("//*[@id='descSobrenome']/span"));
	}
	
	public String getSexoCadastro() {
		return dsl.obterTextoElemento(By.xpath("//*[@id='descSexo']/span"));
	}
	
	public String getComidaFavoritaCadastro() {
		return dsl.obterTextoElemento(By.xpath("//*[@id='descComida']/span"));
	}
	
	public String getEscolaridadeCadastro() {
		return dsl.obterTextoElemento(By.xpath("//*[@id='descEscolaridade']/span"));
	}
	
	public String getEsporteCadastro() {
		return dsl.obterTextoElemento(By.xpath("//*[@id='descEsportes']/span"));
	}
	
	public String getNome() {
		return dsl.obterValorElemento("elementosForm:nome");
	}
	
	public String getSobrenome() {
		return dsl.obterValorElemento("elementosForm:sobrenome");
	}
	
	public boolean isCarneMarcada() {
		return dsl.isElementoSelecionado("elementosForm:comidaFavorita:0");
	}
	
	public boolean isFrangoMarcado() {
		return dsl.isElementoSelecionado("elementosForm:comidaFavorita:1");
	}
	
	public boolean isPizzaMarcado() {
		return dsl.isElementoSelecionado("elementosForm:comidaFavorita:2");
	}
	
	public boolean isVegetarianoMarcado() {
		return dsl.isElementoSelecionado("elementosForm:comidaFavorita:3");
	}
	
	public boolean isMasculinoMarcado() {
		return dsl.isElementoSelecionado("elementosForm:sexo:0");
	}
	
	public boolean isFemininoMarcado() {
		return dsl.isElementoSelecionado("elementosForm:sexo:1");
	}
	
	public boolean isEsporteSelecionado(String valor) {
		return dsl.isValorSelecionado("elementosForm:esportes", valor);
	}
	
	public int quantidadeOpcoesEsporte() {
		return dsl.quantidadeValoresDisponiveis("elementosForm:esportes");
	}
	
	public int quantidadeOpcoesEsporteSelecionados() {
		return dsl.quantidadeValoresSelecionadosCombo("elementosForm:esportes");
	}

}

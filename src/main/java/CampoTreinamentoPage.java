import org.openqa.selenium.WebDriver;

public class CampoTreinamentoPage {

	private DSL dsl;

	public CampoTreinamentoPage(WebDriver driver) {
		dsl = new DSL(driver);
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
		return dsl.obterTextoElemento("resultado");
	}
	
	public String getNomeCadastro() {
		return dsl.obterTextoElemento("descNome");
	}
	
	public String getSobrenomeCadastro() {
		return dsl.obterTextoElemento("descSobrenome");
	}
	
	public String getSexoCadastro() {
		return dsl.obterTextoElemento("descSexo");
	}
	
	public String getComidaFavoritaCadastro() {
		return dsl.obterTextoElemento("descComida");
	}
	
	public String getEscolaridadeCadastro() {
		return dsl.obterTextoElemento("descEscolaridade");
	}
	
	public String getEsporteCadastro() {
		return dsl.obterTextoElemento("descEsportes");
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

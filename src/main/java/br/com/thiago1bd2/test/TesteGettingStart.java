package br.com.thiago1bd2.test;

import static br.com.thiago1bd2.core.DriverFactory.getDriver;
import static br.com.thiago1bd2.core.DriverFactory.killDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TesteGettingStart {

	@Before
	public void init() {
		getDriver().get("https://www.google.com.br");
	}

	@After
	public void finalize() {
		killDriver();
	}

	@Test
	public void testeSimples() {

		String pageTitle = getDriver().getTitle();
		String expectedTitle = "Google";
		Assert.assertEquals(expectedTitle, pageTitle);
	}
}
package br.com.thiago1bd2.core;

import static br.com.thiago1bd2.core.DriverFactory.killDriver;

import org.junit.After;

public class BaseTest {
	
	@After
	public void finalize() {
		if (Properties.CLOSE_BROWSER) {
			killDriver();
		}		
	}
}

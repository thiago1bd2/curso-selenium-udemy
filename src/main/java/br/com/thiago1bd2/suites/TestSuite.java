package br.com.thiago1bd2.suites;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.com.thiago1bd2.core.DriverFactory;
import br.com.thiago1bd2.test.TesteCadastroDDT;
import br.com.thiago1bd2.test.TesteResultadoCadastro;
import br.com.thiago1bd2.test.TesteValidacaoCadastro;

@RunWith(Suite.class)
@SuiteClasses({ TesteCadastroDDT.class, TesteValidacaoCadastro.class, TesteResultadoCadastro.class })
public class TestSuite {

	@AfterClass
	public static void finalizeTudo() {
		DriverFactory.killDriver();
	}

}

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	TesteCadastroDDT.class,
	TesteManipulacaoDeAlertas.class,
	TesteManipulacaoDePopups.class,
	TesteResultadoCadastro.class,
	TesteValidacaoCadastro.class
})
public class TestSuite {

}

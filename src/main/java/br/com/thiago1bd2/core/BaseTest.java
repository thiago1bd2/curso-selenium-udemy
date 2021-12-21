package br.com.thiago1bd2.core;

import static br.com.thiago1bd2.core.DriverFactory.getDriver;
import static br.com.thiago1bd2.core.DriverFactory.killDriver;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class BaseTest {

	@Rule
	public TestName testName = new TestName();

	@After
	public void finalize() throws IOException {
		TakesScreenshot ss = (TakesScreenshot) getDriver();
		File file = ss.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file,
				new File("target" + File.separator + "screenshot" + File.separator + testName.getMethodName() + ".jpg"));

		if (Properties.CLOSE_BROWSER) {
			killDriver();
		}
	}
}

package com.lti.base;

import com.lti.capabilities.CapabilityManager;
import com.lti.dataProvider.DataProviderClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeClass;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseDriver extends DataProviderClass {

	private CapabilityManager capabilityManager;

	public BaseDriver() {

		try {
			capabilityManager = CapabilityManager.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public WebDriver driver;

	@BeforeClass
	public void baseDriver() throws MalformedURLException {

		/*
		 * JSONObject android=capabilityManager.getCapabilityFromJsonKey("chrome");
		 * System.out.println("Capability::" + android); String firstKey = (String)
		 * android.keySet().toArray()[0]; String valueForFirstKey = (String)
		 * android.get(firstKey); System.setProperty(firstKey,valueForFirstKey);
		 */
		
		//System.setProperty("webdriver.chrome.driver",".\\drivers\\chromedriver.exe");
		DesiredCapabilities capability=new DesiredCapabilities();
		capability.setPlatform(org.openqa.selenium.Platform.WINDOWS);
		capability.setBrowserName(DesiredCapabilities.chrome().getBrowserName());
		driver = new RemoteWebDriver(new URL("http://192.168.1.103:4444/wd/hub"), capability);
		
		/*System.setProperty("webdriver.chrome.driver",".\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(90, TimeUnit.MILLISECONDS);*/
	}

	public void getUrl(String url) {
		driver.get(url);
		driver.manage().window().maximize();
	}

}

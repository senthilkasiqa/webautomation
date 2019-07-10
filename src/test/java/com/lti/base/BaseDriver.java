package com.lti.base;

import com.lti.capabilities.CapabilityManager;
import com.lti.dataProvider.DataProviderClass;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

public class BaseDriver extends DataProviderClass {

    private CapabilityManager capabilityManager;

    public BaseDriver(){

        try {
            capabilityManager = CapabilityManager.getInstance();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public WebDriver driver;

    @BeforeClass
    public void baseDriver(){

        JSONObject android=capabilityManager.getCapabilityFromJsonKey("chrome");
        System.out.println("Capability::" + android);
        String firstKey = (String) android.keySet().toArray()[0];
        String valueForFirstKey = (String) android.get(firstKey);
        System.setProperty(firstKey,valueForFirstKey);
        //System.setProperty("webdriver.chrome.driver","C:\\Users\\Aarish Senthilkumar\\IdeaProjects\\webautomation\\src\\test\\resource\\chromedriver.exe");
         driver=new ChromeDriver();
         driver.manage().timeouts().implicitlyWait(90, TimeUnit.MILLISECONDS);
    }

    public void getUrl(String url){
        driver.get(url);
        driver.manage().window().maximize();
    }

}

package com.lti.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class NseHomePageObjects {

	@FindBy(xpath = "//a[text()='Live Market']")
	public WebElement liveMarket;
	@FindBy(xpath = "//*[@id='dataTable']/tbody/tr/td[1]/a)")
	public List<WebElement> getStockNames;
}

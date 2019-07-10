package com.lti.pagefunction;

import com.lti.base.BaseDriver;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class NseHomePage extends BaseDriver {


    public static void main(String[] arg) throws IOException, InvalidFormatException {

    }

    public String getTodayDate(){
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        String date = simpleDateFormat.format(new Date());
        return date;
    }
    private WebElement getStockvalues(String stockName, int value){
        return driver.findElement(By.xpath("//a[text()='"+stockName+"']//ancestor::td/following-sibling::td["+value+"]"));
    }

    private List<WebElement> getStocks(){
        return driver.findElements(By.xpath("//*[@id='dataTable']/tbody/tr/td[1]/a"));
    }

    private List<String> getStocknames(){
        List<String> getStockNameValues=new ArrayList<>();

        for(WebElement element:getStocks()){
            getStockNameValues.add(element.getText().trim());
        }
        return getStockNameValues;
    }

    public HashMap<String,List<String>> getStockValueDetails(){
        HashMap<String,List<String>> getStockWithKeyValue=new HashMap<>();

        for(String value:getStocknames()){
            List<String> getValues=new ArrayList<>();
            String ltpPrice = null;
            for(int i=3;i<7;i++){
                if(i==6){
                    ltpPrice=getStockvalues(value,i).getText().trim();
                    break;
                }
                getValues.add(getStockvalues(value,i).getText().trim());
            }
            try{
                driver.findElement(By.xpath("//a[text()='"+value+"']")).click();
            }
            catch (Exception e){
                scroll(driver.findElement(By.xpath("//a[text()='"+value+"']")));
                driver.findElement(By.xpath("//a[text()='"+value+"']")).click();
            }
            String closePrice=driver.findElement(By.id("closePrice")).getText().trim();
            if(closePrice.equalsIgnoreCase("-")) {
                getValues.add(ltpPrice);
            }
            else{
                getValues.add(closePrice);
            }
            driver.navigate().back();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            getStockWithKeyValue.put(value,getValues);
        }
        return getStockWithKeyValue;
    }

    public void scroll(WebElement element){
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("arguments[0].scrollIntoView(true);",element);
    }

    public void writeToExcel(String file, String sheetName, List<String> values) throws IOException, InvalidFormatException {

        FileInputStream fileInputStream=new FileInputStream(new File(file));
        Workbook wb=new WorkbookFactory().create(fileInputStream);
        if(wb.getSheet(sheetName)==null){
            wb.createSheet(sheetName);
        }
        Sheet sheet = wb.getSheet(sheetName);
        int rowCount = sheet.getLastRowNum();
        int columnCount = 0;
        Row row = sheet.createRow(++rowCount);
        Cell cell = row.createCell(columnCount);
        cell.setCellValue(rowCount);
        values.add(0,getTodayDate());
        for (Object field : values) {
            cell = row.createCell(columnCount++);
                if (field instanceof String) {
                    cell.setCellValue((String) field);
                } else if (field instanceof Integer) {
                    cell.setCellValue((Integer) field);
                }
            }
        fileInputStream.close();
        FileOutputStream outputStream = new FileOutputStream(file);
        wb.write(outputStream);
        wb.close();
        outputStream.close();
    }
}

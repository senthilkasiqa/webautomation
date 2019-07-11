package com.lti.apptesting;
import com.lti.pagefunction.NseHomePage;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.*;

public class LoginTest extends NseHomePage {

    /*@Test
    @ExcelDataProvider(fileName = "\\src\\test\\resource\\Movie_Id_Title_details.xlsx",sheetName = "Movie_Id_Titles",hasHeaderRow = true)
    protected void verfiyLoginPage(Method m) throws IOException {
        Object[][] getData= getExcelData(m);
        System.out.println(getData.length + "data "+getData);
        System.out.println("Length of row 1: " + getData[1].length);
        System.out.println("Length of row 2: " + getData[2].length);
        getUrl("https://login.globalglaze.in/");
        List<List<String>> listData = new ArrayList<List<String>>();
        try {
            for (int i = 1; i < getData.length; i++) {
                String[] strArray = (String[]) getData[i];
                if (!Arrays.asList(strArray).stream().filter(s -> s.equalsIgnoreCase("<columnName>")).findFirst().isPresent()) {
                    listData.add(Arrays.asList(strArray));
                    System.out.println(Arrays.asList(strArray));
                }
            }
            //Remove empty element
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        for(List<String> s:listData) {
            driver.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_usernm']")).click();
            driver.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_usernm']")).clear();
            driver.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_usernm']")).sendKeys(s.get(0));
            driver.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_adminpassword']")).clear();
            driver.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_adminpassword']")).sendKeys(s.get(1));
            System.out.println("UserName::\t"+s.get(0)+"\tPassword::\t"+s.get(1));
        }
        }*/

    @Test
    public void verifyNseDetails() throws IOException, InvalidFormatException {
            getUrl("https://www.nseindia.com/live_market/dynaContent/live_watch/equities_stock_watch.htm");
            Map<String, List<String>> treeMap=new TreeMap<>(getStockValueDetails());
            System.out.println("getStockValueDetails\n"+treeMap);
            String filePath=System.getProperty("user.dir").split("target")[0]+"\\src\\test\\resource\\testfile1.xlsx";
            TreeSet<String> stockNames=new TreeSet<>();
            Set<String> keys = treeMap.keySet();
            List<List<String>> total=new ArrayList<>();
            for(String key: keys){
                writeToExcel(filePath,key,treeMap.get(key));
            }
            driver.quit();
        }
    
    @Test
    public void verifyNseDetails3() throws IOException, InvalidFormatException {
            getUrl("https://www.nseindia.com/live_market/dynaContent/live_watch/equities_stock_watch.htm");
            Map<String, List<String>> treeMap=new TreeMap<>(getStockValueDetails());
            System.out.println("getStockValueDetails\n"+treeMap);
            String filePath=System.getProperty("user.dir").split("target")[0]+"\\src\\test\\resource\\testfile1.xlsx";
            TreeSet<String> stockNames=new TreeSet<>();
            Set<String> keys = treeMap.keySet();
            List<List<String>> total=new ArrayList<>();
            for(String key: keys){
                writeToExcel(filePath,key,treeMap.get(key));
            }
            driver.quit();
        }
    
    @Test
    public void verifyNseDetails1() throws IOException, InvalidFormatException {
            getUrl("https://www.nseindia.com/live_market/dynaContent/live_watch/equities_stock_watch.htm");
            Map<String, List<String>> treeMap=new TreeMap<>(getStockValueDetails());
            System.out.println("getStockValueDetails\n"+treeMap);
            String filePath=System.getProperty("user.dir").split("target")[0]+"\\src\\test\\resource\\testfile1.xlsx";
            TreeSet<String> stockNames=new TreeSet<>();
            Set<String> keys = treeMap.keySet();
            List<List<String>> total=new ArrayList<>();
            for(String key: keys){
                writeToExcel(filePath,key,treeMap.get(key));
            }
            driver.quit();
        }
    
    @Test
    public void verifyNseDetails2() throws IOException, InvalidFormatException {
            getUrl("https://www.nseindia.com/live_market/dynaContent/live_watch/equities_stock_watch.htm");
            Map<String, List<String>> treeMap=new TreeMap<>(getStockValueDetails());
            System.out.println("getStockValueDetails\n"+treeMap);
            String filePath=System.getProperty("user.dir").split("target")[0]+"\\src\\test\\resource\\testfile1.xlsx";
            TreeSet<String> stockNames=new TreeSet<>();
            Set<String> keys = treeMap.keySet();
            List<List<String>> total=new ArrayList<>();
            for(String key: keys){
                writeToExcel(filePath,key,treeMap.get(key));
            }
            driver.quit();
        }
}

package com.lti.dataProvider;

import com.lti.utilities.ExcelDataProvider;
import com.lti.utilities.ExcelUtility;
import com.lti.utilities.StringUtility;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.lang.reflect.Method;

public class DataProviderClass {

    public DataProviderClass(){

    }

    @DataProvider(
            name = "excel_data_provider"
    )

    public Object[][] getExcelData(Method m) throws IOException {
    if(m.isAnnotationPresent(ExcelDataProvider.class)){
        ExcelDataProvider texcelDataProvider;
        texcelDataProvider = (ExcelDataProvider) m.getAnnotation(ExcelDataProvider.class);
        return !StringUtility.isNullOrEmpty(texcelDataProvider.sheetName())? ExcelUtility.getExcelData(System.getProperty("user.dir").split("target")[0] + texcelDataProvider.fileName(), texcelDataProvider.sheetName(), texcelDataProvider.hasHeaderRow(), new Method[]{m}) : ExcelUtility.getExcelData(System.getProperty("user.dir").split("target")[0] + texcelDataProvider.fileName(), texcelDataProvider.hasHeaderRow(), new Method[]{m});
    }
    else{
        return new Object[0][];
    }
    }
}

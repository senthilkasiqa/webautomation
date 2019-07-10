package com.lti.utilities;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;

public class ExcelUtility {


    private static int getFirstRow(Sheet sheet, boolean skipHeaderRow) {
       int getrow = 0;
       return getrow;
    }

    public static Object[][] getExcelData(String fileName, String sheetName, boolean headerRow, Method... m) throws IOException {
        Object[][] arrayExcelData=null;
        XSSFWorkbook myWorkBook = null;
        try {
            int row=0;
            File myFile = new File(fileName);
            FileInputStream fis = new FileInputStream(myFile);

            myWorkBook = new XSSFWorkbook(fis);

            XSSFSheet mySheet = myWorkBook.getSheet(sheetName);

            Iterator<Row> rowIterator = mySheet.iterator();

            arrayExcelData = new String[mySheet.getPhysicalNumberOfRows()][];

            if(headerRow) {
                row = 1;
            }
            for (int i = row; i < arrayExcelData.length; i++) {
                arrayExcelData[i] = new String[mySheet.getRow(i).getPhysicalNumberOfCells()];
                for (int j = 0; j < mySheet.getRow(i).getPhysicalNumberOfCells(); j++) {
                    Cell cell = mySheet.getRow(i).getCell(j);
                    arrayExcelData[i][j] = String.valueOf(cell.getStringCellValue());
                }
            }

            System.out.println("Complete");

        } catch (Exception e) {
            System.out.println("Exception Occur");
            System.out.println(e.getMessage());
        }
        finally {
            myWorkBook.close();
        }
        return arrayExcelData;
    }

    public static Object[][] getExcelData(String file, boolean headerRow, Method... m) throws IOException {
        return getExcelData(file, "", headerRow, m);
    }
}

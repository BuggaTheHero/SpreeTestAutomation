package com.testng.SpreeTestAutomation.ReadInputData;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;

import com.testng.SpreeTestAutomation.Enummerables.Enums;
import com.testng.SpreeTestAutomation.HelperObjects.Logging;
import com.testng.SpreeTestAutomation.HelperObjects.TestData;


public class ExcelWorkbook 
{
    private List<TestData> testDataList;
    private TestData testData;
    private HSSFSheet sheet;
    private HSSFWorkbook workbook;
    
    Logging logger = new Logging();
    
    public ExcelWorkbook()
    {
        this.testDataList = new ArrayList<TestData>();
        this.testData =  new TestData();
        this.sheet = null;
    }

    @SuppressWarnings("deprecation")
	public List<TestData> ReadExcelWorkbook(String path)
    {
        try
        {
            FileInputStream file = new FileInputStream(new File(path)); 

            String key = null;
            String value = null;

            Cell keyCell = null;
            Cell valueCell = null;
            
            //Get the workbook instance for XLS file
            this.workbook = new HSSFWorkbook(file);
            
            //Get first sheet from the workbook
            this.sheet = workbook.getSheetAt(0);

            //Iterate through each rows from first sheet
            Iterator<Row> rowIterator = sheet.iterator();

            while(rowIterator.hasNext())
            {
               Row keyRow = rowIterator.next();
               Row valueRow;
               
                //For each row, iterate through each columns
                Iterator<Cell> keyCellIterator = keyRow.cellIterator();
                keyCell = keyRow.getCell(0, Row.CREATE_NULL_AS_BLANK);
                
                if(keyCell.getCellType() != Cell.CELL_TYPE_BLANK)
                {
                        while(keyCellIterator.hasNext())
                        {
                            keyCell = keyCellIterator.next();
                            valueRow = this.sheet.getRow(keyRow.getRowNum() + 1);
                            valueCell = valueRow.getCell(keyCell.getColumnIndex(), Row.CREATE_NULL_AS_BLANK);

                            key = CheckCellTypeAndAssignValue(keyCell);
                            value = CheckCellTypeAndAssignValue(valueCell);

                            if(!key.equals(""))
                            {
                                switch(keyCell.getColumnIndex())
                                {
                                    case 0: 
                                        testData.SetTestDataID(key);
                                        break;
                                    case 1:   
                                        testData.SetTestDataName(key);
                                        break;
                                    default:
                                        testData.AddToTestData(key, value);
                                }
                            }

                        }
                        this.testDataList.add(testData); 
                        testData = new TestData();
                }

                file.close();
            }
        }
        catch(FileNotFoundException fileNotfound)
        {
           logger.printLog(Enums.LogType.ERROR,"File not found. " + fileNotfound.getMessage());
           return testDataList; 
        }
        catch(IOException inputOutput)
        {
           logger.printLog(Enums.LogType.ERROR,"Input / Output. " + inputOutput.getMessage());
           return testDataList; 
        }

        return this.testDataList;
    }
    @SuppressWarnings("deprecation")
	private String CheckCellTypeAndAssignValue(Cell cell)
    {
        final DataFormatter df = new DataFormatter();
        String cellValue = String.valueOf("");
        
        switch(cell.getCellType())
        {
            case Cell.CELL_TYPE_BLANK:
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_NUMERIC:
                cellValue = df.formatCellValue(cell);
                break;
            case Cell.CELL_TYPE_STRING:
                cellValue = cell.getStringCellValue();
                break;
        }
        
        return cellValue;
    }
    
}

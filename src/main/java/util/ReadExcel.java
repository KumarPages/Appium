package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ReadExcel {
	@Test
	public Object[][] readData() throws InvalidFormatException, IOException{
		
		 
		
		FileInputStream fis = new FileInputStream(new File("./data/Book3.xlsx"));		
		XSSFWorkbook wbook=new XSSFWorkbook(fis);
		XSSFSheet sheet=wbook.getSheetAt(0);
		
		int rowCount=sheet.getLastRowNum();
		int columnCount=sheet.getRow(0).getLastCellNum();
		 
		
		Object[][] ss =new Object[rowCount][columnCount];		
		
		
		for (int i = 1; i <= rowCount; i++) {
			XSSFRow row = sheet.getRow(i);

			for (int j = 0; j < columnCount; j++) {
				XSSFCell Cell = row.getCell(j);
				if (Cell.getCellType()==1) {
					ss[i-1][j]= Cell.getStringCellValue();	
				}
				else if (Cell.getCellType()==0) {
					ss[i-1][j]=Cell.getNumericCellValue();
				}
				
				
			}
		}
		return ss;

	}

}

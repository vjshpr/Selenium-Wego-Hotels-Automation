package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadTestData {

	public ArrayList<String> getTestData(String excelSheetName, String[] columnNames, String TcId) throws IOException {

		File file = new File(System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TestData.xlsx");
		FileInputStream fis = new FileInputStream(file);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet ws = wb.getSheet(excelSheetName);
		XSSFRow row = ws.getRow(0); 
		XSSFCell cell;
		
		int totalRowCount = ws.getLastRowNum();
		int totalColCount = row.getLastCellNum();
		int colValue;
		int rowValue;
		String dataFound = null;
		ArrayList<String> testDataValues = new ArrayList<String>();
		String methodName = TcId;


		for(String col: columnNames) {
			row = ws.getRow(0); 
		for(int i=0;i<totalColCount;i++) {
			cell = row.getCell(i);	
			if(cell.getStringCellValue().equalsIgnoreCase(col)) {	
				colValue = i; 
				for(int j=1;j<=totalRowCount;j++) {
					row = ws.getRow(j);
					cell = row.getCell(0);
					DataFormatter dataFormat = new DataFormatter();
					dataFormat.formatCellValue(cell);
					if(cell.getStringCellValue().equalsIgnoreCase(methodName)) {
						rowValue = j;
						row=ws.getRow(rowValue);
						cell=row.getCell(colValue);
						if(cell.getCellType().toString().equalsIgnoreCase("NUMERIC")) {
							int testint = (int) cell.getNumericCellValue();
							testDataValues.add(String.valueOf(testint));
							dataFound = String.valueOf(cell.getNumericCellValue());
							break;	
							
						}
						else if(cell.getCellType().toString().equalsIgnoreCase("STRING")) {
						testDataValues.add(cell.getStringCellValue());
						dataFound = cell.getStringCellValue();
						break;
						}
						}
				}
				if(dataFound!=null) {
					dataFound=null;
					break;
				}
			}
		}
		}
		wb.close();
		return testDataValues;
		
		

	}


}

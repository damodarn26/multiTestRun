package utility;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataProvider {
	
	
	XSSFWorkbook wb;
	
	public ExcelDataProvider() {
		System.out.println("I am in ConfigExcelDataProvide");
		// To construct the file path
		File src=new File("./TestData/testData.xlsx");
		
		try {
			// To convert the file into Raw Data
			FileInputStream fis = new FileInputStream(src);
			
			// To read XLS file 
			wb = new XSSFWorkbook(fis);
		} catch (Exception e) {
			System.out.println("Unable to read Excel File"+e.getMessage());
		}
	}
	
	
	public String getStringData(String sheetName, int row, int column) {
		return wb.getSheet(sheetName).getRow(row).getCell(column).getStringCellValue();
	}
	
	public double getNumberData(String sheetName, int row, int column) {
		return wb.getSheet(sheetName).getRow(row).getCell(column).getNumericCellValue();
	}

}

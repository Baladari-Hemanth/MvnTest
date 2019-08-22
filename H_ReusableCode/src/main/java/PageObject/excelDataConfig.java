package PageObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class excelDataConfig {

	XSSFWorkbook workbook;
	XSSFSheet sheet;

	public excelDataConfig(String filePath) {
			try {
				File fileObj = new File(filePath);
				FileInputStream fileInputStreamobj = new FileInputStream(fileObj);
				workbook = new XSSFWorkbook(fileInputStreamobj);
			} catch (Exception e) {
				// TODO: handle exception
				e.getStackTrace();
			} 
	}

	public String getDataFromExcel(int sheetNo, int row, int col) {
		sheet=workbook.getSheetAt(sheetNo);
		String data = sheet.getRow(row).getCell(col).getStringCellValue();
		return data;
	}

	public int getRowCount(int sheetNo) {
		int rowCount = workbook.getSheetAt(sheetNo).getLastRowNum();
		rowCount=rowCount+1;
		return rowCount;	
	}

	public void writeData(String path, int row, String errorMessage) throws IOException {
		try {
			FileOutputStream fileOutput=new FileOutputStream(path);
			//			String message = "Hemanth";
			sheet.getRow(row).createCell(2).setCellValue(errorMessage);
			// Specify the file in which data needs to be written.
			workbook.write(fileOutput);
			fileOutput.close();	
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}		
	}
}

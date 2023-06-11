package utils;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excelutils {
	public String[] getUserFromExcel(int n) throws IOException, InterruptedException {
    	String [] temp=new String[2];
    	FileInputStream file = new FileInputStream("D:\\Book1.xlsx");
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheet("Sheet1");
        Row row = sheet.getRow(n);
        Cell usernameCell = row.getCell(0); 
        Cell passwordCell = row.getCell(1); 
        temp[0] = usernameCell.getStringCellValue();
        temp[1] = passwordCell.getStringCellValue();  
        return temp;
    }}

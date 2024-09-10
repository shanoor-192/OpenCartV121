package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	
	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook wb;
	public static XSSFSheet ws;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static CellStyle style;
	public static XSSFFont fstyle;
	
	
	String path;
	
	public ExcelUtils(String path) {
		this.path = path;
	}

	
	// static method with an int return type that takes the file location and sheet name as parameter
	public int getRowCount(String xlsheet) throws IOException {
		
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlsheet);
		int rowCount = ws.getLastRowNum();
		wb.close();
		fi.close();
		return rowCount;
		
	}
	
	
	public int getCellCount(String xlsheet, int rowNum) throws IOException {
		
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlsheet);
		row = ws.getRow(rowNum);
		int cellCount = row.getLastCellNum();
		wb.close();
		fi.close();
		return cellCount;
		
	}
	
	public String getCellData(String xlsheet, int rownum, int colnum) throws IOException {
		
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlsheet);
		row = ws.getRow(rownum);
		cell = row.getCell(colnum);
		String data;
		
		try {
//			 data = cell.toString();
			DataFormatter formatter = new DataFormatter();
			data = formatter.formatCellValue(cell);
		} catch (Exception e) {
			// TODO: handle exception
			data="";
		}
		
		
		wb.close();
		fi.close();
		return data;
		
	}
	
	public void setCellData(String xlsheet, int rownum, int colnum, String data) throws IOException {
		
		File xlfile = new File(path);
		if (xlfile.exists()) {
			
			wb = new XSSFWorkbook();
			fo = new FileOutputStream(path);
			wb.write(fo);
			
		}
		
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		
		if (wb.getSheetIndex(xlsheet)==-1) /*if sheet doesn't exist create one*/{
			wb.createSheet(xlsheet);
			ws = wb.getSheet(xlsheet);
		}
		
		if (ws.getRow(rownum)==null) /*if row doesn't exist*/ {
			ws.createRow(rownum);
			row = ws.getRow(rownum);
		}
		
		cell = row.createCell(colnum);
		cell.setCellValue(data);
		fo = new FileOutputStream(path);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
		
		
	}
	
	
	
	
	public void fillGreenColor(String xlsheet, int rownum, int colnum) throws IOException {
		
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlsheet);
		row = ws.getRow(rownum);
		cell = row.getCell(colnum);
		
		style = wb.createCellStyle();
		
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
		style.setBorderTop(BorderStyle.THIN);
		
		cell.setCellStyle(style);
		fo = new FileOutputStream(path);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
		
		
	}
	
	public void fillRedColor(String xlsheet, int rownum, int colnum) throws IOException {
		
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlsheet);
		row = ws.getRow(rownum);
		cell = row.getCell(colnum);
		
		style = wb.createCellStyle();
		
		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
		style.setBorderTop(BorderStyle.THIN);
		
		cell.setCellStyle(style);
		
		fo = new FileOutputStream(path);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
		
	}
	
	public void setFontBold(String xlsheet, int rownum, int colnum) throws IOException {

		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlsheet);
		row = ws.getRow(rownum);
		cell = row.getCell(colnum);

		fstyle = wb.createFont();
		fstyle.setBold(true);
		style = wb.createCellStyle();
		
		style.setFont(fstyle);
		cell.setCellStyle(style);
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
		style.setBorderTop(BorderStyle.THIN);
		
		

		fo = new FileOutputStream(path);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();


	}
	
//	public static void setCellBorderAll(String xlfile, String xlsheet, int rownum, int colnum) throws IOException {
//		
//		fi = new FileInputStream(xlfile);
//		wb = new XSSFWorkbook(fi);
//		ws = wb.getSheet(xlsheet);
//		row = ws.getRow(rownum);
//		cell = row.getCell(colnum);
//		
//		style = wb.createCellStyle();
//
//		
//		cell.setCellStyle(style);
//		
//		fo = new FileOutputStream(xlfile);
//		wb.write(fo);
//		wb.close();
//		fi.close();
//		fo.close();
//		
//	}
	
	
	

}

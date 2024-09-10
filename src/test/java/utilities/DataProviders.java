package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	//DataProviders 1
	
	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException {
		
		String path = ".\\testData\\Opencart_LoginData.xlsx"; // taking xl file from testData
		
		ExcelUtils xlutil = new ExcelUtils(path); // creating object of xlUtils
		
		int totalRows = xlutil.getRowCount("Sheet1"); // getting total rows
		int totalCols = xlutil.getCellCount("Sheet1", 1);
		
		String loginData[][] = new String[totalRows][totalCols]; // created 2d array 
		
		for (int i = 1; i<= totalRows; i++) { //1 	// read the data stored in 2d array
			for (int j = 0; j < totalCols; j++) {
				loginData[i-1][j] = xlutil.getCellData("Sheet1", i, j);
			}
		}
		
		return loginData;
		
	}
	
	
	
	
	
}

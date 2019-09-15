package commons;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ExcelReader
{

  /*  public HashMap loadExcelLines() throws IOException
    {
        // Used the LinkedHashMap and LikedList to maintain the order
        HashMap<Integer, HashMap<String, String>> outerMap = new LinkedHashMap<Integer, HashMap<String, String>>();
        LinkedHashMap<String, String> hashMap = new LinkedHashMap<String, String>();
        LinkedHashMap<String, String> resultMap = new LinkedHashMap<String, String>();
        List<String> colmName=new ArrayList<String>();        
        
        
        LinkedHashMap<String, String> datamap = new LinkedHashMap<String, String>();

        String sheetName = null;
        // Create an ArrayList to store the data read from excel sheet.
        // List sheetData = new ArrayList();
        //FileInputStream fis = null;
        File fileName=new File("Test Data/MasterSheet.xlsx");
        FileInputStream fis=new FileInputStream(fileName);
        
        XSSFWorkbook workbook=new XSSFWorkbook(fis);
        XSSFSheet sheet=workbook.getSheet("Sheet1");
        
        int rowcnt=sheet.getLastRowNum();
        
        XSSFRow row=sheet.getRow(0);
        int columncnt=row.getLastCellNum();
        System.out.println("Column count: "+columncnt);        
        
        for (int i = 0; i < columncnt; i++) 
        {
        	 XSSFCell cell=row.getCell(i);
        	colmName.add(i, cell.getStringCellValue());
		}
        
        for (int j = 1; j <= rowcnt; j++) 
        {
        	
        	//resultMap.clear();
        	XSSFRow row1=sheet.getRow(j);
        	for (int k = 0; k < columncnt; k++) 
            {
            	 XSSFCell cell=row1.getCell(k);
            	 hashMap.put(colmName.get(k), cell.getStringCellValue());
            	 System.out.println("Hi");            	
    		}
        	//resultMap.putAll(hashMap);
        	outerMap.put(j, hashMap);
        	hashMap=new LinkedHashMap<String, String>();
        	//hashMap.clear();    	
        	System.out.println("Hallo");        	
		}        
		return outerMap;   
    }*/
    
    /*public static void main(String[] args) throws IOException {
    	ExcelReader exl=new ExcelReader();
    	exl.loadExcelLines();
	}*/
    
    @Test(dataProvider="loadExcelLines")
    public void getDate(Map<Object, Object> map)
    {
    	map.get(1);
    	System.out.println("Inside");
    }
    
    @DataProvider(name="loadExcelLines")
    
    public Object[][] loadExcelLines() throws IOException
    {
        // Used the LinkedHashMap and LikedList to maintain the order
    	
        HashMap<Integer, HashMap<String, String>> outerMap = new LinkedHashMap<Integer, HashMap<String, String>>();
        Map<Object, Object> hashMap = new HashMap<Object, Object>();
        LinkedHashMap<String, String> resultMap = new LinkedHashMap<String, String>();
        List<String> colmName=new ArrayList<String>();        
        
        
        LinkedHashMap<String, String> datamap = new LinkedHashMap<String, String>();

        String sheetName = null;
        // Create an ArrayList to store the data read from excel sheet.
        // List sheetData = new ArrayList();
        //FileInputStream fis = null;
        File fileName=new File("Test Data/MasterSheet.xlsx");
        FileInputStream fis=new FileInputStream(fileName);
        
        XSSFWorkbook workbook=new XSSFWorkbook(fis);
        XSSFSheet sheet=workbook.getSheet("Sheet1");
        
        int rowcnt=sheet.getLastRowNum();
        Object[][] obj = new Object[rowcnt][];
        
        XSSFRow row=sheet.getRow(0);
        int columncnt=row.getLastCellNum();
        System.out.println("Column count: "+columncnt);        
        
        for (int i = 0; i < columncnt; i++) 
        {
        	 XSSFCell cell=row.getCell(i);
        	colmName.add(i, cell.getStringCellValue());
		}
        
        for (int j = 1; j <= rowcnt; j++) 
        {
        	
        	//resultMap.clear();
        	XSSFRow row1=sheet.getRow(j);
        	for (int k = 0; k < columncnt; k++) 
            {
            	 XSSFCell cell=row1.getCell(k);
            	 hashMap.put(colmName.get(k), cell.getStringCellValue());
            	 System.out.println("Hi");            	
    		}
        	//resultMap.putAll(hashMap);
        	//outerMap.put(j, hashMap);
        	obj[j][0]=hashMap;
        	hashMap=new LinkedHashMap<Object, Object>();
        	//hashMap.clear();    	
        	System.out.println("Hallo");
        	
		}        
		//return outerMap;
        return obj;
		
    }    
    
}



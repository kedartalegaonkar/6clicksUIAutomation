package commons;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Util {
	
	public static String readProperty(String key){
		
		File file = new File("src/test/resources/config.properties");
		  
		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		Properties prop = new Properties();
		
		//load properties file
		try {
			prop.load(fileInput);
			return prop.getProperty(key).toString().trim();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop.getProperty(key).toString().trim();
		
	}
	
	public static void takeSnapShot(WebDriver webdriver,String fileWithPath)
	{
         TakesScreenshot scrShot =((TakesScreenshot)webdriver);
         File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
         File DestFile=new File(fileWithPath);    

        try {
			FileUtils.copyFile(SrcFile, DestFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	public void uploadFileWithRobot (String imagePath) {
		imagePath=imagePath.replace("/", "\\");
        StringSelection stringSelection = new StringSelection(imagePath);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null); 
        Robot robot = null; 
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
 
        robot.delay(4000);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.delay(150);
        robot.keyRelease(KeyEvent.VK_ENTER);
        try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	public void scrollelement(WebDriver driver, int event)
	{
		for (int i = 0; i < event; i++) {
			Actions build = new Actions(driver);
			build.sendKeys(Keys.ARROW_DOWN).build().perform();
		}
	}
	
	/*public void createExcel() throws IOException
	{
		//resultMap.put("1", new Object[] {"TC_Name", "Description", "Status", "Comment"});
		try {
			FileOutputStream fis = new FileOutputStream(new File("Output/Execution Result.xlsx"));
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("Result");
			FileOutputStream out = new FileOutputStream("Output/Execution Result.xlsx");
			workbook.write(out);
			out.close();			
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}   	
	}*/

	private static void autoResizeColumns(XSSFSheet sheet, int columnNumber)

	{
		for(int colIndex = 0; colIndex < columnNumber ; colIndex++)
		{
			sheet.autoSizeColumn(colIndex);
		}
	}
	
	public static String[] compareStringResultOld (String str1, String str2)
	{
		try {
			if(str1.equals(str2))
			{
				String a[]={"Passed",""};
				return a;
			}
			else
			{
				String a[]={"Failed","Expected: "+str1+" Actual: "+str2};
				return a;			
			}
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		
	}
	
	public static String[] compareStringResult (WebElement ele, String str2)
	{
		try {
			ele.isDisplayed();
			
			if((ele.getText().trim()).equals(str2))
			{
				String a[]={"Passed",""};
				return a;
			}
			else
			{
				String a[]={"Failed","Actual: "+ele.getText()+" Expected: "+str2};
				return a;			
			}
			
		} catch (Exception e) {
			String a[]={"Failed","Element not displayed on UI"};
			//takeSnapShot(driver,"Screenshots/CompleteAssessmentstatus.jpg");
			return a;
		}	
	}
	
	public static String[] compareBooleanResult (boolean str1, boolean str2)
	{
		try {
			if(Boolean.compare(str1, str2)==0)
		{
			String a[]={"Passed",""};
			return a;
		}
	else
		{
		String a[]={"Failed","Actual: "+str1+" Expected: "+str2};
		return a;			
		}
			
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		
	}
	
	public static String[] compareBooleanResultDisplay (WebElement ele, boolean str2)
	{
		try {
			ele.isDisplayed();
			if(Boolean.compare(ele.isDisplayed(), str2)==0)
		{
			String a[]={"Passed",""};
			return a;
		}
	else
		{
		String a[]={"Failed","Actual: "+ele.isDisplayed()+" Expected: "+str2};
		return a;			
		}
			
		} catch (Exception e) {
			String a[]={"Failed","Element not displayed on UI"};
			return a;
		}
		
	}

	public static CellStyle setFontStyle(XSSFWorkbook workbook, String color)
	{
		CellStyle Style = workbook.createCellStyle(); 
		if (color.equals("Grey"))
			Style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());

		if (color.equals("Green"))
			Style.setFillForegroundColor(IndexedColors.GREEN.getIndex());

		if (color.equals("Orange"))
			Style.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.getIndex());  

		if (color.equals("Blank"))
			Style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
		
		if (color.equals("Red"))
			Style.setFillForegroundColor(IndexedColors.RED.getIndex());
		
		if (color.equals("Blue"))
			Style.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());

		Style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		// Style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		Style.setBorderBottom(CellStyle.BORDER_THIN);
		Style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		Style.setBorderLeft(CellStyle.BORDER_THIN);
		Style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		Style.setBorderRight(CellStyle.BORDER_THIN);
		Style.setRightBorderColor(IndexedColors.BLACK.getIndex());
		Style.setBorderTop(CellStyle.BORDER_THIN);
		Style.setTopBorderColor(IndexedColors.BLACK.getIndex());
		return Style;
	}

	public static void writeToExl(TreeMap<String, Object[]> data) 
	{    	
		FileInputStream fis = null;
		String path="Output/Execution Result.xlsx";
		try {
			fis = new FileInputStream(path);  //new File("Output/Execution Result.xlsx")
		} catch (FileNotFoundException e1) {			
			e1.printStackTrace();
		}    	
		XSSFWorkbook workbook = null;
		try {
			workbook = new XSSFWorkbook(fis);
		} catch (IOException e1) {			
			e1.printStackTrace();
		}
		XSSFSheet sheet = workbook.getSheetAt(0);

		Set<String> keyset = data.keySet();
		int rownum = sheet.getPhysicalNumberOfRows();

		for (String key : keyset)
		{
			Row row = sheet.createRow(rownum++);
			//int rowNo=sheet.getLastRowNum();
			Object [] objArr = data.get(key);
			int cellnum = 0;
			for (Object obj : objArr)
			{
				Cell cell = row.createCell(cellnum++);
				if(obj instanceof String)
					cell.setCellValue((String)obj);
				if((rownum-1)==0)
					cell.setCellStyle(setFontStyle(workbook,"Grey"));               
				else
					cell.setCellStyle(setFontStyle(workbook,"Blank"));
				String val=(String)obj;
				System.out.println("excel val: "+val);
				if(val.contains("Passed"))
					cell.setCellStyle(setFontStyle(workbook,"Green"));
				if(val.contains("Failed"))
					cell.setCellStyle(setFontStyle(workbook,"Orange"));
				if(val.contains("Server Error"))
					cell.setCellStyle(setFontStyle(workbook,"Red"));
				if(val.contains("Information"))
					cell.setCellStyle(setFontStyle(workbook,"Blue"));
			}
			autoResizeColumns(sheet,cellnum);
		}
		try
		{            
			FileOutputStream out1 = new FileOutputStream("Output/Execution Result.xlsx");
			workbook.write(out1);           
			fis.close();
			System.out.println("Output result excel file written successfully on disk.");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}    
	

}

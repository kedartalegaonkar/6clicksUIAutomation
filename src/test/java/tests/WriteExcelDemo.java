package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.automationtesting.excelreport.Xl;

/*public class generateReport {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Xl.generateReport("excel-report.xlsx");
	}

}
 */

public class WriteExcelDemo
{
	public static void main(String[] args) throws IOException
	{
		TreeMap<String, Object[]> data = new TreeMap<String, Object[]>();
		//createExcel();
		data.put("1", new Object[] {"TC_Name", "Description", "Status", "Comment"});
		writeToExl(data);
		data.clear();
		data.put("2", new Object[] {"Create User", "Default Uesr Creation", "Passed", "User created successfully"});
		writeToExl(data);
		data.clear();
		data.put("3", new Object[] {"Create12", "Looks good1", "Failed","test"});
		writeToExl(data);   
		//data.clear();

		//writeToExl(data);

		//Blank workbook
		/* XSSFWorkbook workbook = new XSSFWorkbook();

        //Create a blank sheet
        XSSFSheet sheet = workbook.createSheet("Employee Data");

        //This data needs to be written (Object[])
        Map<String, Object[]> data = new TreeMap<String, Object[]>();
        data.put("1", new Object[] {"ID", "NAME", "LASTNAME"});
        data.put("2", new Object[] {1, "Amit", "Shukla"});
        data.put("3", new Object[] {2, "Lokesh", "Gupta"});
        data.put("4", new Object[] {3, "John", "Adwards"});
        data.put("5", new Object[] {4, "Brian", "Schultz"});

        //Iterate over data and write to sheet
        Set<String> keyset = data.keySet();
        int rownum = 0;
        for (String key : keyset)
        {
            Row row = sheet.createRow(rownum++);
            Object [] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr)
            {
               Cell cell = row.createCell(cellnum++);
               if(obj instanceof String)
                    cell.setCellValue((String)obj);
                else if(obj instanceof Integer)
                    cell.setCellValue((Integer)obj);
            }
        }
        try
        {
            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(new File("howtodoinjava_demo.xlsx"));
            workbook.write(out);
            out.close();
            System.out.println("howtodoinjava_demo.xlsx written successfully on disk.");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }*/
	}


	public static void createExcel() throws IOException
	{
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
	}

	private static void autoResizeColumns(XSSFSheet sheet, int columnNumber)

	{
		for(int colIndex = 0; colIndex < columnNumber ; colIndex++)
		{
			sheet.autoSizeColumn(colIndex);
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

	public static void writeToExl(TreeMap<String, Object[]> data) throws IOException
	{    	
		FileInputStream fis = new FileInputStream(new File("Output/Execution Result.xlsx"));    	
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Result");

		Set<String> keyset = data.keySet();
		int rownum = sheet.getPhysicalNumberOfRows();        

		/*        CellStyle cs = workbook.createCellStyle(); 
        cs.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex()); 
        cs.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        CellStyle cs1 = workbook.createCellStyle(); 
        cs1.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.getIndex()); 
        cs1.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        CellStyle cs2 = workbook.createCellStyle(); 
        cs2.setFillForegroundColor(IndexedColors.GREEN.getIndex()); 
        cs2.setFillPattern(FillPatternType.SOLID_FOREGROUND);*/

		for (String key : keyset)
		{
			//int rowNo=sheet.getLastRowNum();
			Row row = sheet.createRow(rownum++);
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
				if(val.contains("Passed"))
					cell.setCellStyle(setFontStyle(workbook,"Green"));
				if(val.contains("Failed"))
					cell.setCellStyle(setFontStyle(workbook,"Orange"));
			}
			autoResizeColumns(sheet,cellnum);
		}
		try
		{            
			FileOutputStream out1 = new FileOutputStream("Output/Execution Result.xlsx");
			workbook.write(out1);           
			fis.close();
			System.out.println("howtodoinjava_demo.xlsx written successfully on disk.");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}    
}
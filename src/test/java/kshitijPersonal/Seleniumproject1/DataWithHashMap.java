package kshitijPersonal.Seleniumproject1;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataWithHashMap {


	

	  @Test(dataProvider = "data")
	  public void integrationTest(Map<String, String> map) {
	    System.out.println("-------------Test case started ----------------");
	    System.out.println(map.get("TestCase"));
	    System.out.println(map.get("Data1"));
	    System.out.println(map.get("Data2"));
	    System.out.println(map.get("Data3"));
	    System.out.println(map.get("Data4"));
	    System.out.println(map.get("Data5"));
	    System.out.println(map.get("Data6"));
	    System.out.println(map.get("Data7"));
	    System.out.println(map.get("Data8"));
	    System.out.println(map.get("Data9"));
	    System.out.println(map.get("Data10"));
	    Set<String> arr = map.keySet();
	    Iterator<String> it=arr.iterator();
	    
	    System.out.println("********************************");
	    
	    while(it.hasNext())
	    {
	    	
	    System.out.println(it.next());
	    }

	    System.out.println("-------------Test case Ended ----------------");

	  }

	  @DataProvider(name = "data")
	  public Object[][] dataSupplier() throws IOException {

	    File file = new File(System.getProperty("user.dir")+"//DataSheets//RawData.xlsx");
	    FileInputStream fis = new FileInputStream(file);

	    XSSFWorkbook wb = new XSSFWorkbook(fis);
	    XSSFSheet sheet = wb.getSheetAt(0);
	    wb.close();
	    int lastRowNum = sheet.getLastRowNum() ;
	    int lastCellNum = sheet.getRow(0).getLastCellNum();
	    Object[][] obj = new Object[lastRowNum][1];

	    for (int i = 0; i < lastRowNum; i++) {
	      Map<String, String> datamap = new HashMap<>();
	      for (int j = 0; j < lastCellNum; j++) {
	        datamap.put(sheet.getRow(0).getCell(j).toString(), sheet.getRow(i+1).getCell(j).toString());
	      }
	      obj[i][0] = datamap;

	    }
	    return  obj;
	  }

	}


package App;

import java.io.File;
import java.sql.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileSystemView;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.xml.*;
public class Home {
	  
	    static Connection conn = null;
	    static ResultSet resultat = null;
	    static PreparedStatement prepared = null;
		
	    
	public static void main(String[] args) throws Exception {

		
		ArrayList<Integer> CodeCcp = new ArrayList<Integer>();
		CodeCcp.addAll(Arrays.asList(310269,310271,310398,310405,310407,310410,310470,310494,310495,310537,310649,310900,311384,311492,311493,311494,311638,311639,311640,311686,311700,311767,311824,311825,311872,311917,312216,312466,312467,312468,312469,312470,312471,312620,312869,313052,313125,313432,313496,313497,313514,313515,313649,313661,313662,313664,313702,313899,313902,313971,314252,314349));
		// System.out.print(CodeCcp.size());
		conn = ConnexionMySql.ConnexionDB();
		
		
		// important part dont delet  ---------------------------------------------------------->
		/*
		
		String sql="Update exceltable set test=? where N_COMP=?";
		try {
			prepared = conn.prepareStatement(sql);
			prepared.setFloat(1, 5948);
			prepared.setLong(2, 310494);
			prepared.executeUpdate();
			prepared.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	*/
		// ------------------------------------------------------------------------------------->
		
		
		File myFile = new File("\\\\DESKTOP-8D4C6KG\\Users\\ROG SCHOOL 5\\Desktop\\Application Converter Exel\\lmd\\CONST  RECETTE BUDGET-04-20201111111111111111111111.xlsx");
		FileInputStream fis = new FileInputStream(myFile);   
		XSSFWorkbook Workbook = new XSSFWorkbook (fis); 
		Sheet datasheet = Workbook.getSheetAt(0);
		Iterator<Row> rowIterator = datasheet.iterator();
		
		Cell cell;
		
		cell = datasheet.getRow(2).getCell(2);
		
		java.util.Date celldate;
		celldate = cell.getDateCellValue();
		java.sql.Date sqlDatecell = new java.sql.Date(celldate.getTime());
		System.out.println(sqlDatecell);
		
		Date date = null;
String sql = "SELECT Date FROM exceltable ";
		
		try {
			prepared = conn.prepareStatement(sql);
			resultat = prepared.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while ( resultat.next()) {
			date = resultat.getDate("Date");
			System.out.println(date);
			break;	  
		}
		
		 
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		
		System.out.println(sqlDate);
		
		if (sqlDate.equals(sqlDatecell)) {
			System.out.println("Equals ");
		}
		
	
	
	}
	
	void createtable(int month , int year) {
		
		
			
	
		
	}
	
}
	
	
	


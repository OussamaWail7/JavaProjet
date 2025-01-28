package App;



import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;

import javax.jws.soap.SOAPBinding.Style;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import net.proteanit.sql.DbUtils;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JComboBox;
public class apps {

	    static Connection conn = null;
	    static ResultSet resultat = null;
	    static PreparedStatement prepared = null;
	  
	    boolean filechosed;
	    String filename;
	    
	private JFrame frame;
	private static JTable table;
	

	/**
	 * Launch the application.
	 */  /*
	public static void main(String[] args) {
		conn = ConnexionMySql.ConnexionDB();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					apps window = new apps();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Create the application.
	 */
	public apps() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1444, 1020);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(350, 13, 1069, 834);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		 table.setShowVerticalLines(false);
		 table.setFocusable(false);
		 table.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		 table.setOpaque(false);
		 table.setRowHeight(25);
		 table.setIntercellSpacing(new Dimension(0, 0));
		 table.setSelectionBackground(new Color(122,72,221));
		 table.getTableHeader().setOpaque(false);
		 table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD , 15));
		 table.getTableHeader().setBackground(new Color(32,136,203));
		 table.getTableHeader().setForeground(new Color(255,255,255));
		 table.setRowHeight(25);
		scrollPane.setViewportView(table);
		
	
		  
		
		JButton btnNewButton = new JButton("Import");
		btnNewButton.setBounds(730, 898, 144, 40);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Export");
		btnNewButton_1.setBounds(997, 897, 141, 43);
		frame.getContentPane().add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					if ( filechosed == true) {
						save();
						filechosed = false;
					  }  else {
						  JOptionPane.showMessageDialog(frame, "Veuillez d'abord choisir un fichier excel");
					  }
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			     File f = new File("\\\\DESKTOP-8D4C6KG\\Users\\ROG SCHOOL 5\\Desktop"); 
			     JFileChooser j = new JFileChooser(f, FileSystemView.getFileSystemView()); 
				
				FileNameExtensionFilter filter = new FileNameExtensionFilter("XLS files", "xlsx");
				j.setFileFilter(filter);
				
				int r = j.showSaveDialog(null);
				
				// Open the save dialog 
				if (r == JFileChooser.APPROVE_OPTION) {
					String s = j.getSelectedFile().getAbsolutePath();
					filename = s;
	//			-------->	
					
				/*	 System.out.print(s);    */   // Get addr test ....
					
					try {
						Convert(s);
						filechosed = true;
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						
					}
				}
			}
		});
		
	}
	@SuppressWarnings("deprecation")
	public static void Convert(String s) throws Exception {
		
		
		// Change tab to list 
		ArrayList<Integer> CodeCcp = new ArrayList<Integer>();
	  CodeCcp.addAll(Arrays.asList(310269,310271,310398,310405,310407,310410,310470,310494,310495,310537,310649,310900,311384,311492,311493,311494,311638,311639,311640,311686,311700,311767,311824,311825,311872,311917,312216,312466,312467,312468,312469,312470,312471,312620,312869,313052,313125,313432,313496,313497,313514,313515,313649,313661,313662,313664,313702,313899,313902,313971,314252,314349));
		 
		 
		 Cell cell ;
		 Cell cell2;
		// Read the first file 
		File myFile = new File(s);
		FileInputStream fis = new FileInputStream(myFile);   
		XSSFWorkbook Workbook = new XSSFWorkbook (fis); 
		Sheet datasheet = Workbook.getSheetAt(0);
		Iterator<Row> rowIterator = datasheet.iterator();
		// ****** end of reading first file
		
		//Read second file 
		
		// ***** end of reading file 2 
		
		int x = 0;
	
		// Var BD :
		String City;
		
		
		///// Date Vars  *************************************************************
		
		Date date ;
		SimpleDateFormat formater = null; 
		     // You can delet , and you can use it 
		formater = new SimpleDateFormat("yyyy-MM-dd");
		
		
       ////***************************************************************************
         
		float TAXPAV;
		float TAXRAV;
		float TAXMON;
		float TAXDAB;
		float TAXIDS;
		float TAXVA;
		float TAXSA;
		float total;		 
			        
		int n_comp=0;	
		
		Boolean typeINT = false;
		
		cell = datasheet.getRow(x).getCell(0);
		        
		     
		// Edit ***-*****-****-****-****-*****-*****-*****
		while ( cell == null || typeINT == false ) {
		  if (cell == null) {
			  x++;
			  cell = datasheet.getRow(x).getCell(0);
		  } else {
			  if(cell.getCellType() != CellType.NUMERIC){
			  x++;
			  cell = datasheet.getRow(x).getCell(0);
		  }else {typeINT = true;}
		  }
		}
		//************************************************
		// Test file exist by equals   -------------------------------------------------------------------------------/////////////////////File equals
	    boolean Emptytable=true;
	    boolean equalsdatetest = false;
		 cell2 = datasheet.getRow(x).getCell(2);
		date = cell2.getDateCellValue();
		java.sql.Date sqlDatecell = new java.sql.Date(date.getTime());   //Convert 
		System.out.println(sqlDatecell);
		
String sql1 = "SELECT Date FROM exceltable ";
		
		try {
			prepared = conn.prepareStatement(sql1);
			resultat = prepared.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		java.sql.Date sqlDate2;
		while ( resultat.next()) {
			date = resultat.getDate("Date");
			System.out.println(date);
		    sqlDate2 = new java.sql.Date(date.getTime());  //Convert
			Emptytable = false;
			if (sqlDate2.equals(sqlDatecell)){
				equalsdatetest = true;
				break;
			}
			
		}		
			
		if (Emptytable || !equalsdatetest) {     //******************************************************** The Condition of Date exist or not 
			
		
		//----------------------------------------------------------------------------------------------------//////////////////////
	while (rowIterator.hasNext() && (cell !=null)){
		
		
		n_comp = (int) cell.getNumericCellValue(); 
		for (int i= 0 ; i < CodeCcp.size();i++) {
	
			if(CodeCcp.get(i)==n_comp) {
			    cell = datasheet.getRow(x).getCell(1);
				City = cell.getStringCellValue();
				//----------------------- > Date
				cell =  datasheet.getRow(x).getCell(2);
				date = cell.getDateCellValue();
				java.sql.Date sqlDate = new java.sql.Date(date.getTime());    // Convert my data to use it in mySql database 
				//***********************************TAXPAV
				cell = datasheet.getRow(x).getCell(3);
				TAXPAV = (float) cell.getNumericCellValue();
				//***********************************TAXRAV
				cell = datasheet.getRow(x).getCell(4);
				TAXRAV =(float) cell.getNumericCellValue();
				//*************************************TAXMON
				cell = datasheet.getRow(x).getCell(5);
				TAXMON =(float) cell.getNumericCellValue();
				//*************************************TAXDAB	
				cell = datasheet.getRow(x).getCell(6);
				TAXDAB = (float) cell.getNumericCellValue();
				//*************************************TAXIDS
				cell = datasheet.getRow(x).getCell(7);
				TAXIDS = (float) cell.getNumericCellValue();
				//*************************************TAXVA
				cell = datasheet.getRow(x).getCell(8);
				TAXVA = (float) cell.getNumericCellValue();
				//*************************************TAXSA
				cell = datasheet.getRow(x).getCell(9);
				TAXSA =  (float) cell.getNumericCellValue();
				// ---------------------------> Total
				total = TAXPAV + TAXRAV + TAXMON + TAXDAB + TAXIDS + TAXVA + TAXSA;
				
				
				
				//Conn avec la BD  
			// Create new table 
				
				
				String sql = " insert into exceltable ( N_COMP , BUREAU , Date , TAX_PAV , TAX_RAV , Montant , TAX_DAB , TAX_IDS ,TAX_VAC , TAX_SA , Total) values (?,?,?,?,?,?,?,?,?,?,?)";
			     prepared = conn.prepareStatement(sql);	
			     prepared.setLong(1, n_comp);
			     prepared.setString(2, City);
			     prepared.setDate(3, sqlDate);
			     prepared.setFloat(4,TAXPAV);
			     prepared.setFloat(5,TAXRAV);
			     prepared.setFloat(6,TAXMON);
			     prepared.setFloat(7,TAXDAB);
			     prepared.setFloat(8,TAXIDS);
			     prepared.setFloat(9,TAXVA);
			     prepared.setFloat(10,TAXSA);
			     prepared.setFloat(11,total);
			     prepared.execute();
			     		    		     
       
  //  System.out.println(n_comp +" -----> "+ sqlDate + " -----> " + City + "------> " + total);
			}
					 
		}
		 x++;
		 rowIterator.next();
		 cell = datasheet.getRow(x).getCell(0);
	 }  
	System.out.println("Equals ");
		}else {
			JOptionPane.showMessageDialog(
			        null, "The file is already choosed before", "Error", JOptionPane.ERROR_MESSAGE);
		}
		update_table();
	}
	public static void save() throws IOException {
		String sql = "SELECT * FROM exceltable";
		SimpleDateFormat formater = null; 
		
	     // You can delet , and you can use it 
	formater = new SimpleDateFormat("yyyy-MM-dd");
				try {
					prepared = conn.prepareStatement(sql);
					resultat = prepared.executeQuery();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 XSSFWorkbook workbook = new XSSFWorkbook();
		         XSSFSheet sheet = workbook.createSheet("Reviews");
		
		         DataFormat format = workbook.createDataFormat();
		         
				 String s ="constrecettebudg042020.xlsx";
		         FileOutputStream outputStream;
		         // Create the first Row
		         Row headerRow = sheet.createRow(0);
		         // Create Cells 
		         Cell headerCell = headerRow.createCell(0);
		         headerCell.setCellValue("N_COMP");
		         
		         headerCell = headerRow.createCell(1);
		         headerCell.setCellValue("BUREAU");
		         
		         
		         
		         headerCell = headerRow.createCell(2);
		         headerCell.setCellValue("DATE");
		         
		       
		         
		         headerCell = headerRow.createCell(3);
		         headerCell.setCellValue("TAXE SUR PAV");
		         
		         headerCell = headerRow.createCell(4);
		         headerCell.setCellValue("TAXE SUR RAV ");
		         
		         headerCell = headerRow.createCell(5);
		         headerCell.setCellValue("NMONTANT N AVOIR");
		         
		         headerCell = headerRow.createCell(6);
		         headerCell.setCellValue("TAXE SUR DAB");
		         
		         headerCell = headerRow.createCell(7);
		         headerCell.setCellValue("TAXE SUR IDS");
		         
		         headerCell = headerRow.createCell(8);
		         headerCell.setCellValue("TAXE SUR VAC");
		         
		         headerCell = headerRow.createCell(9);
		         headerCell.setCellValue("TAXE SUR AUTRE OPE");
		         
		         headerCell = headerRow.createCell(10);
		         headerCell.setCellValue("NOM DE REGI");
		         
		         headerCell = headerRow.createCell(11);
		         headerCell.setCellValue("TOTAL");
		         
		         headerCell = headerRow.createCell(12);
		         headerCell.setCellValue("WILAYA");
		         
		        
		         
		         int rowCount = 1;
		         
		         try {
					while (resultat.next()) {
					     int NCOMP = (int) resultat.getLong("N_COMP");
					     String BUREAU = resultat.getString("BUREAU");
					     java.sql.Date Date2 = resultat.getDate("Date");
					     float TAX_PAV2 = resultat.getFloat("TAX_PAV");
					     float TAX_RAV2 = resultat.getFloat("TAX_RAV");
					     float Montant2 = resultat.getFloat("Montant");
					     float TAX_DAB2 = resultat.getFloat("TAX_DAB");
					     float TAX_IDS2 = resultat.getFloat("TAX_IDS");
					     float TAX_VAC2 = resultat.getFloat("TAX_VAC");
					     float TAX_SA2 = resultat.getFloat("TAX_SA");
					     float Total2 = resultat.getFloat("Total");
					     Row row = sheet.createRow(rowCount++);
					     
  
					     int columnCount = 0;
					     Cell cell = row.createCell(columnCount++);
					     cell.setCellValue(NCOMP);
  
					     cell = row.createCell(columnCount++);
					     cell.setCellValue(BUREAU);
					     
					     cell = row.createCell(columnCount++);
					     
				            CellStyle cellStyle = workbook.createCellStyle();
				            cellStyle.setDataFormat((short)14);
				            
				            cell.setCellStyle(cellStyle);
				            
				            cell.setCellValue(Date2);
                         
				            // Important
				            CellStyle cellStyle2 = workbook.createCellStyle();  
					     cell = row.createCell(columnCount++);
					       if (TAX_PAV2 != 0) {
					     cell.setCellValue(TAX_PAV2);
					     cellStyle2.setDataFormat(format.getFormat("# ###.00"));
					     cell.setCellStyle(cellStyle2);} else {
					    	 cell.setCellValue("-");
					     }
                             
					       cell = row.createCell(columnCount++);
					       if (TAX_RAV2 != 0) {
					     cell.setCellValue(TAX_RAV2);
					     cell.setCellStyle(cellStyle2);
					     }else {
					    	 
					    	 cell.setCellValue("-");
					     }
					      
					       cell = row.createCell(columnCount++);
					       if (Montant2 != 0) {
					     cell.setCellValue(Montant2);
					     cell.setCellStyle(cellStyle2);
					     }else {
					    	 cell.setCellValue("-");}
					     
					       cell = row.createCell(columnCount++);
					       if (TAX_IDS2 != 0) {
					     cell.setCellValue(TAX_IDS2);
					     cell.setCellStyle(cellStyle2); 
					     }else {
					    	 cell.setCellValue("-");}
					     
					       cell = row.createCell(columnCount++);
					       if (TAX_RAV2 != 0) {
					     cell.setCellValue(TAX_RAV2);
					     cell.setCellStyle(cellStyle2);
					     }else {
					    	 cell.setCellValue("-");
					     }
					     
					       cell = row.createCell(columnCount++);
					       if (TAX_VAC2 != 0) {
					     cell.setCellValue(TAX_VAC2);
					     cell.setCellStyle(cellStyle2); 
					     } else {
					    	 cell.setCellValue("-");
					     }
					     
					       cell = row.createCell(columnCount++);
					       if (TAX_SA2 != 0) {
					     cell.setCellValue(TAX_SA2);
					     cell.setCellStyle(cellStyle2);
					     }else {
					    	 cell.setCellValue("-");
					     }
					       cell = row.createCell(columnCount++);
					     
					       cell = row.createCell(columnCount++);
					       if (Total2 != 0) {
					     cell.setCellValue(Total2);
					     cell.setCellStyle(cellStyle2);
					          }else {
					    	 cell.setCellValue("-");
					     }
					      
					       cell = row.createCell(columnCount++);
					       cell.setCellValue("Khenchela");
  
					 }
					sheet.setColumnWidth(0, 3000);
			        sheet.setColumnWidth(1, 5000);
					sheet.setColumnWidth(2, 3500);
					sheet.setColumnWidth(3, 5000);
					sheet.setColumnWidth(4, 5000);
					sheet.setColumnWidth(5, 5000);
					sheet.setColumnWidth(6, 5000);
					sheet.setColumnWidth(7, 5000);
					sheet.setColumnWidth(8, 5000);
					sheet.setColumnWidth(9, 5000);
					sheet.setColumnWidth(10, 5000);
					sheet.setColumnWidth(11, 5000);
					sheet.setColumnWidth(12, 3000);
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		         
		         try {
					outputStream = new FileOutputStream("\\\\DESKTOP-8D4C6KG\\Users\\ROG SCHOOL 5\\Desktop\\"+s);
				    workbook.write(outputStream);
				    workbook.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
       
		
		
 }
private static Date formater(java.sql.Date date2) {
		// TODO Auto-generated method stub
		return null;
	}

public static  void update_table() {
		
		String sql=" Select * from exceltable where year(Date)=2020 ";
		
		try {
			prepared = conn.prepareStatement(sql);
			resultat = prepared.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(resultat));	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
}  
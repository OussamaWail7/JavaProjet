package App;

import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;

public class importpage extends JPanel {
	static Connection conn = null;
    static ResultSet resultat = null;
    static PreparedStatement prepared = null;   
	
    static boolean typeINT = false;
	boolean filechosed = false;
    String filename;
    private static JTable table;
    static boolean filechosedcolorchange=true;    
    public static java.sql.Date DateGlobal;
    
	public importpage() {
		conn = ConnexionMySql.ConnexionDB();
		setBounds(421, 237, 1499, 843);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.RED);
		panel.setBounds(12, 93, 266, 67);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\ROG SCHOOL 5\\Desktop\\Application Converter Exel\\Img\\wrong (1).png"));
		lblNewLabel_1.setBounds(220, 13, 34, 41);
		panel.add(lblNewLabel_1);
		
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				File f = new File("\\\\DESKTOP-8D4C6KG\\Users\\ROG SCHOOL 5\\Desktop"); 
				JFileChooser j = new JFileChooser(f, FileSystemView.getFileSystemView()); 
				FileNameExtensionFilter filter = new FileNameExtensionFilter("XLS files", "xlsx");
				j.setFileFilter(filter);
				int r = j.showSaveDialog(null);
				if (r == JFileChooser.APPROVE_OPTION) {
					String s = j.getSelectedFile().getAbsolutePath();
					filename = s;
					try {
						Convert(filename);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					filechosed = true;
						
					panel.setBackground(Color.GREEN);
					lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\ROG SCHOOL 5\\Desktop\\Application Converter Exel\\Img\\correct.png"));
					
				}
				
			}
		});
		
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Import ");
		lblNewLabel.setBounds(12, 13, 139, 41);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD | Font.ITALIC, 22));
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(304, 26, 1183, 804);
		add(scrollPane);
		
		
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
		
		JPanel panel_1 = new JPanel();
		JLabel lblImport = new JLabel("Import 2");
		lblImport.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD | Font.ITALIC, 22));
		lblImport.setBounds(12, 13, 139, 41);
		panel_1.add(lblImport);
		
		panel_1.setLayout(null);
		panel_1.setBackground(Color.RED);
		panel_1.setBounds(12, 263, 266, 67);
		add(panel_1);
		
		
		
		JLabel lblNewLabel_1_1 = new JLabel("");
		lblNewLabel_1_1.setIcon(new ImageIcon("C:\\Users\\ROG SCHOOL 5\\Desktop\\Application Converter Exel\\Img\\wrong (1).png"));
		lblNewLabel_1_1.setBounds(220, 13, 34, 41);
		panel_1.add(lblNewLabel_1_1);
		
		JButton btnNewButton = new JButton("Clear");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel.setBackground(Color.red);
				lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\ROG SCHOOL 5\\Desktop\\Application Converter Exel\\Img\\wrong (1).png"));
				panel_1.setBackground(Color.red);
				lblNewLabel_1_1.setIcon(new ImageIcon("C:\\Users\\ROG SCHOOL 5\\Desktop\\Application Converter Exel\\Img\\wrong (1).png"));
				filechosed = false;
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.setRowCount(0);
			}
		});
		btnNewButton.setBounds(70, 554, 143, 53);
		add(btnNewButton);
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setBounds(0, 207, 306, 8);
		add(separator_5);
		
		JSeparator separator_5_1 = new JSeparator();
		separator_5_1.setBounds(0, 381, 306, 8);
		add(separator_5_1);
		
		
		panel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				if (filechosed == true) {
				File f = new File("\\\\DESKTOP-8D4C6KG\\Users\\ROG SCHOOL 5\\Desktop"); 
				JFileChooser j = new JFileChooser(f, FileSystemView.getFileSystemView()); 
				FileNameExtensionFilter filter = new FileNameExtensionFilter("XLS files", "xlsx");
				j.setFileFilter(filter);
				int r = j.showSaveDialog(null);
				
				if (r == JFileChooser.APPROVE_OPTION) {
					String s = j.getSelectedFile().getAbsolutePath();
					
					try {
						 
						 Convert2(s);
				//		 updateG50(DateGlobal);
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						
					}
					
					panel_1.setBackground(Color.GREEN);
					lblNewLabel_1_1.setIcon(new ImageIcon("C:\\Users\\ROG SCHOOL 5\\Desktop\\Application Converter Exel\\Img\\correct.png")); }
				
			}else {
				JOptionPane.showMessageDialog(null, "Import the first file first", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	});
		
		
		

	}
	@SuppressWarnings("deprecation")
	public static void Convert(String s) throws Exception {
		
		
		// Change tab to list 
		ArrayList<Integer> CodeCcp = new ArrayList<Integer>();
	  CodeCcp.addAll(Arrays.asList(310269,310271,310398,310405,310407,310410,310470,310494,310495,310537,310649,310900,311384,311492,311493,311494,311638,311639,311640,311686,311700,311767,311824,311825,311872,311917,312216,312466,312467,312468,312469,312470,312471,312620,312869,313052,313125,313432,313496,313497,313514,313515,313649,313661,313662,313664,313702,313899,313902,313971,314252,314349,313970));
		 
		 
		 Cell cell;
		 Cell cell2;
		 Cell cell3;
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
	    int wilaya; 
		
		// Var BD :
		String City;
		
		
		///// Date Vars  *************************************************************
		
		Date date ;
		SimpleDateFormat formater = null; 
		     // You can delet , and you can use it 
		formater = new SimpleDateFormat("yyyy-MM-dd");
		
		
       ////***************************************************************************
         
		double TAXPAV;
		double TAXRAV;
		double TAXMON;
		double TAXDAB;
		double TAXIDS;
		double TAXVA;
		double TAXSA;
		double total;		 
			        
		int n_comp=0;	
		int code_c=0;
		
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
		DateGlobal = sqlDatecell;
		
		
		
		
String sql1 = "SELECT Date FROM exceltable ";
		
		try {
			prepared = conn.prepareStatement(sql1);
			resultat = prepared.executeQuery();
		}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();	
		}

		
		java.sql.Date sqlDate2;
		while ( resultat.next()) {
			date = resultat.getDate("Date");
		    sqlDate2 = new java.sql.Date(date.getTime());  //Convert
			Emptytable = false;
			if (sqlDate2.equals(sqlDatecell)){
				equalsdatetest = true;
				break;	
			}
			
		}		
		cell3 = datasheet.getRow(x).getCell(0);	
		
		if (Emptytable || !equalsdatetest) {     //******************************************************** The Condition of Date exist or not 
			
		
		//----------------------------------------------------------------------------------------------------//////////////////////
	while (rowIterator.hasNext() && (cell3 !=null))
	{
		
		 wilaya = (int) cell.getNumericCellValue();
	//	for (int i= 0 ; i < CodeCcp.size();i++) {
	
			
		//	if(CodeCcp.get(i)==n_comp) {
		 if(wilaya == 40) {
			    //-------------------------------------->
				cell = datasheet.getRow(x).getCell(0);
				n_comp = (int) cell.getNumericCellValue();
				//-------------------------------------->
				    if (n_comp == 310269) {
				    	code_c = 40405;
				    }
				    if (n_comp == 310271) {
				    	code_c = 40406;
				    }
				    if (n_comp == 310398) {
				    	code_c = 40208;
				    }
				    if (n_comp == 310405) {
				    	code_c = 40202;
				    }
				    if (n_comp == 310410) {
				    	code_c = 40200;
				    }
				    if (n_comp == 310470) {
				    	code_c = 40301;
				    }
				    if (n_comp == 310494) {
				    	code_c = 40401;
				    }
				    if (n_comp == 310495) {
				    	code_c = 40209;
				    }
				    if (n_comp == 310537) {
				    	code_c = 40203;
				    }
				    if (n_comp == 310649) {
				    	code_c = 40402;
				    }
				    if (n_comp == 310900) {
				    	code_c = 40304;
				    }
				    if (n_comp == 311384) {
				    	code_c = 40412;
				    }
				    if (n_comp == 311494) {
				    	code_c = 40103;
				    }
				    if (n_comp == 311494) {
				    	code_c = 40103;
				    }
				    if (n_comp == 311638) {
				    	code_c = 40403;
				    }
				    if (n_comp == 311639) {
				    	code_c = 40400;
				    }
				    if (n_comp == 311640) {
				    	code_c = 40305;
				    }
				    if (n_comp == 311686) {
				    	code_c = 40104;
				    }
				    if (n_comp == 311700) {
				    	code_c = 40102;
				    }
				    if (n_comp == 311767) {
				    	code_c = 40107;
				    }
				    if (n_comp == 311824) {
				    	code_c = 40307;
				    }
				    if (n_comp == 311825) {
				    	code_c = 40106;
				    }
				    if (n_comp == 311872) {
				    	code_c = 40108;
				    }
				    if (n_comp == 311917) {
				    	code_c = 40105;
				    }
				    if (n_comp == 312216) {
				    	code_c = 40422;
				    }
				    if (n_comp == 312466) {
				    	code_c = 40212;
				    }
				    if (n_comp == 312467) {
				    	code_c = 40205;
				    }
				    if (n_comp == 312468) {
				    	code_c = 40408;
				    }
				    if (n_comp == 312469) {
				    	code_c = 40201;
				    }
				    if (n_comp == 312470) {
				    	code_c = 40407;
				    }
				    if (n_comp == 312471) {
				    	code_c = 40204;
				    }
				    if (n_comp == 312620) {
				    	code_c = 40421;
				    }
				    if (n_comp == 312869) {
				    	code_c = 40306;
				    }
				    if (n_comp == 313052) {
				    	code_c = 40100;
				    }
				    if (n_comp == 313125) {
				    	code_c = 40300;
				    }
				    if (n_comp == 313432) {
				    	code_c = 40111;
				    }
				    if (n_comp == 313496) {
				    	code_c = 40202;
				    }
				    if (n_comp == 313497) {
				    	code_c = 40417;
				    }
				    if (n_comp == 313514) {
				    	code_c = 40308;
				    }
				    if (n_comp == 313515) {
				    	code_c = 40112;
				    }
				    if (n_comp == 313661) {
				    	code_c = 40213;
				    }
				    if (n_comp == 313662) {
				    	code_c = 40420;
				    }
				    if (n_comp == 313664) {
				    	code_c = 40423;
				    }
				    if (n_comp == 313702) {
				    	code_c = 40404;
				    }
				    if (n_comp == 313899) {
				    	code_c = 40309;
				    }
				    if (n_comp == 313971) {
				    	code_c = 40424;
				    }
				    if (n_comp == 314252) {
				    	code_c = 40425;
				    }
				    if (n_comp == 314349) {
				    	code_c = 40115;
				    }
				    if (n_comp == 310407) {
				    	code_c = 40302;
				    }
				    if (n_comp == 311492) {
				    	code_c = 40210;
				    }
				    if (n_comp == 311493) {
				    	code_c = 40303;
				    }
				    if (n_comp == 313649) {
				    	code_c = 40113;
				    }				    				    				    				    				    				   
				
				//-------------------------------------->
				cell = datasheet.getRow(x).getCell(1);
				City = cell.getStringCellValue();
				//----------------------- > Date
				cell =  datasheet.getRow(x).getCell(2);
				date = cell.getDateCellValue();
				java.sql.Date sqlDate = new java.sql.Date(date.getTime());    // Convert my data to use it in mySql database 
				//***********************************TAXPAV
				cell = datasheet.getRow(x).getCell(3);
				TAXPAV =  cell.getNumericCellValue();
				//***********************************TAXRAV
				cell = datasheet.getRow(x).getCell(4);
				TAXRAV = cell.getNumericCellValue();
				//*************************************TAXMON
				cell = datasheet.getRow(x).getCell(5);
				TAXMON = cell.getNumericCellValue();
				//*************************************TAXDAB	
				cell = datasheet.getRow(x).getCell(6);
				TAXDAB =  cell.getNumericCellValue();
				//*************************************TAXIDS
				cell = datasheet.getRow(x).getCell(7);
				TAXIDS =  cell.getNumericCellValue();
				//*************************************TAXVA
				cell = datasheet.getRow(x).getCell(8);
				TAXVA =  cell.getNumericCellValue();
				//*************************************TAXSA
				cell = datasheet.getRow(x).getCell(9);
				TAXSA =   cell.getNumericCellValue();
				// ---------------------------> Total
				total = TAXPAV + TAXRAV + TAXMON + TAXDAB + TAXIDS + TAXVA + TAXSA;	
				
				//Conn avec la BD  
			// Create new table 
				
				try {
				String sql = " insert into exceltable ( N_COMP ,CodeC, BUREAU , Date , TAX_PAV , TAX_RAV , Montant , TAX_DAB , TAX_IDS ,TAX_VAC , TAX_SA , Total) values (?,?,?,?,?,?,?,?,?,?,?,?)";
			     prepared = conn.prepareStatement(sql);	
			     prepared.setLong(1, n_comp);
			     prepared.setLong(2, code_c);
			     prepared.setString(3, City);
			     prepared.setDate(4, sqlDate);
			     prepared.setDouble(5,TAXPAV);
			     prepared.setDouble(6,TAXRAV);
			     prepared.setDouble(7,TAXMON);
			     prepared.setDouble(8,TAXDAB);
			     prepared.setDouble(9,TAXIDS);
			     prepared.setDouble(10,TAXVA);
			     prepared.setDouble(11,TAXSA);
			     prepared.setDouble(12,total);
			     prepared.execute();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();	
			}	 
	//	 }	
		 }    //--------------------ADD 
	//	}
		 code_c = 0;
		 x++;
		 rowIterator.next();
		
		 try {
		  cell = datasheet.getRow(x).getCell(10);
		  }
		 catch (Exception e){
			 e.printStackTrace();
			 JOptionPane.showMessageDialog(null, "Get cell data Error", "Error", JOptionPane.ERROR_MESSAGE);		 
		 }
		 cell3= datasheet.getRow(x).getCell(0);
	 }  
		}else {
			JOptionPane.showMessageDialog(null, "The file is already choosed before", "Error", JOptionPane.ERROR_MESSAGE);
			filechosedcolorchange = false;
		}
	}
	
public static  void update_table() throws SQLException {
		
		String sql=" SELECT * FROM exceltable WHERE Date = "+"'"+DateGlobal+"'";
		
		try {
			prepared = conn.prepareStatement(sql);
			resultat = prepared.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(resultat));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
	}
}

public static void Convert2(String s) throws Exception {
      //	Vars
	 Cell cell ;
	 int x = 3;
	 Date date;
	 
	 int CodeCcp;
	 String StringCodeCcp;
	 double Totaltax;
	 int month = 0;
	 String StringMonth;
	 int day;
	 String StringDay;
	 int year = 0;
	 String StringYear;
		
	// Read the first file 
	File myFile = new File(s);
	FileInputStream fis = new FileInputStream(myFile);   
	XSSFWorkbook Workbook = new XSSFWorkbook (fis); 
	Sheet datasheet = Workbook.getSheetAt(3);
	Iterator<Row> rowIterator = datasheet.iterator();
	
	cell = datasheet.getRow(0).getCell(2);
	  
	if (cell.getCellType() == CellType.NUMERIC) {
	date = cell.getDateCellValue();
	java.sql.Date sqlDatecell = new java.sql.Date(date.getTime());
	
	LocalDate localDate = sqlDatecell.toLocalDate();
	day = localDate.getDayOfMonth();
	month = localDate.getMonthValue();
	year = localDate.getYear();		
	}
	else {
		JOptionPane.showMessageDialog(null, "Date = null Error", "Error", JOptionPane.ERROR_MESSAGE);	
	}
	
	
	  // Change Vars to String ---------------------------------------------------------------------------------------------------------------
			StringMonth = String.valueOf(month);
			StringYear = String.valueOf(year);
	  // -------------------------------------------------------------------------------------------------------------------------------------
			cell = datasheet.getRow(x).getCell(0);
			
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
			typeINT = false;
			
		while (cell.getCellType() == CellType.NUMERIC) {
	CodeCcp = (int) cell.getNumericCellValue();
	cell = datasheet.getRow(x).getCell(12);
	Totaltax = cell.getNumericCellValue();
	 // Change Vars to String --------------------------------------------------------------------------------------------------------------
	StringCodeCcp = String.valueOf(CodeCcp);
	// -------------------------------------------------------------------------------------------------------------------------------------
    prepared = conn.prepareStatement("UPDATE exceltable SET Total2 = ? WHERE N_COMP = "+ StringCodeCcp + " && year(Date) = "+ StringYear +" && month(Date) = " + StringMonth);	
    prepared.setDouble(1,Totaltax);
    prepared.execute();
	          x++;
	          cell = datasheet.getRow(x).getCell(0);
		}
	 update_table();
    }
  public static void updateG50(java.sql.Date Date) throws IOException, SQLException {
	  
	      int x = 0;
		        double TOTALTAX1=0;
		        double TOTALTAX2=0;
	            double FinalTOTAL;
	            double GETTOT1;
	            double GETTOT2;
	            String sql=" SELECT * FROM exceltable WHERE Date = "+"'"+Date+"'";
		    	
				try {
					prepared = conn.prepareStatement(sql);
					resultat = prepared.executeQuery();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();	
			}
				
				
				while(resultat.next()) {
					TOTALTAX1 = TOTALTAX1 + resultat.getDouble("Total");
					TOTALTAX2 = TOTALTAX2 + resultat.getDouble("Total2");
					
				}
				 DecimalFormat df = new DecimalFormat("#");
			        df.setMaximumFractionDigits(8);
				
				FinalTOTAL = TOTALTAX1 + TOTALTAX2;
				 
				System.out.println("TOTAL 1 = "+df.format(TOTALTAX1));
				System.out.println("TOTAL 2 = "+df.format(TOTALTAX2));
				System.out.println("TOTAL FINAL = "+df.format(FinalTOTAL));
			/* try {
				 File myFile = new File("\\\\DESKTOP-8D4C6KG\\Users\\ROG SCHOOL 5\\Desktop\\Application Converter Exel\\UpdateExcelFile\\UpdateFileG50.xlsx");
					FileInputStream fis = new FileInputStream(myFile);   
					XSSFWorkbook Workbook = new XSSFWorkbook (fis); 
					Sheet datasheet = Workbook.getSheetAt(6);
					XSSFRow sheetrow;
		           
					
					
					
					
		            Cell cell = null;
                
		            
		            //Retrieve the row and check for null
		            sheetrow = (XSSFRow) datasheet.getRow(2);
		            if(sheetrow == null){
		                sheetrow = (XSSFRow) datasheet.createRow(2);
		            }
		            //Update the value of cell
		            cell = sheetrow.getCell(3);
		            if(cell == null){
		                cell = sheetrow.createCell(3);
		            }
		            cell.setCellValue(Date);
		            
		            

		            fis.close();

		            FileOutputStream outFile =new FileOutputStream(new File("C:\\Users\\ROG SCHOOL 5\\Desktop\\Output.xlsx"));
		            Workbook.write(outFile);
		            outFile.close();

		        } catch (FileNotFoundException e) {
		            e.printStackTrace();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }  */
  }    
 }

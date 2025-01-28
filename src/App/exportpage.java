package App;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.DateFormatConverter;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class exportpage extends JPanel {
	static Connection conn = null;
    static ResultSet resultat = null;
    static PreparedStatement prepared = null;  
	
    static JComboBox comboBoxmonth;
    static JComboBox comboBoxyear; 
    static JComboBox comboBoxday;
    
       String filename;
       private static JTable table;
       static boolean filechosedcolorchange=true;    
       static java.sql.Date DateGlobal;
       
       static Boolean typeINT = false;
       
	public exportpage() {
		conn = ConnexionMySql.ConnexionDB();
		
		setBounds(421, 237, 1499, 843);
		setLayout(null);
		
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
		
		JLabel lblNewLabel = new JLabel("Date :");
		lblNewLabel.setBounds(26, 50, 54, 35);
		add(lblNewLabel);
		
		 comboBoxday = new JComboBox();
		comboBoxday.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		comboBoxday.setBounds(57, 98, 42, 34);
		add(comboBoxday);
		
		 comboBoxmonth = new JComboBox();
		comboBoxmonth.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
		comboBoxmonth.setBounds(111, 98, 43, 34);
		add(comboBoxmonth);
		
		 comboBoxyear = new JComboBox();
		comboBoxyear.setModel(new DefaultComboBoxModel(new String[] {"2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050", "2051", "2052", "2053", "2054", "2055", "2056", "2057", "2058", "2059", "2060", "2061", "2062", "2063", "2064"}));
		comboBoxyear.setBounds(166, 98, 72, 34);
		add(comboBoxyear);
		
		JButton btnNewButton = new JButton("Get table");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				    String StringMonth = comboBoxmonth.getSelectedItem().toString();
				    String StringYear = comboBoxyear.getSelectedItem().toString();
				    update_table(StringYear,StringMonth);
			}
		});
		btnNewButton.setBounds(93, 156, 113, 34);
		add(btnNewButton);
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setBounds(0, 222, 305, 8);
		add(separator_5);
		
		JLabel lblNewLabel_1 = new JLabel("Create Excel Files :");
		lblNewLabel_1.setBounds(26, 243, 128, 26);
		add(lblNewLabel_1);
		
		JButton btnNewButton_2 = new JButton("Update G50");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					updateG50(DateGlobal);
				} catch (IOException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Empty Date Choose excelfile in import page", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton_2.setBounds(93, 313, 113, 50);
		add(btnNewButton_2);
		
	}
	
	public static void getfile1() throws IOException {
		
		String StringMonth = comboBoxmonth.getSelectedItem().toString();
	    String StringYear = comboBoxyear.getSelectedItem().toString();
  	    
		String sql = "SELECT * FROM exceltable where year(Date) = "+ StringYear +" && month(Date) = " + StringMonth;
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
					     cellStyle2.setDataFormat(format.getFormat("# ### ###.00"));
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
	
	public static  void update_table(String StringYear,String StringMonth) {
	 	
		
		String sql=" Select * from exceltable where year(Date) = "+ StringYear +" && month(Date) = " + StringMonth;
		
		try {
			prepared = conn.prepareStatement(sql);
			resultat = prepared.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(resultat));	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	  public static void updateG50(java.sql.Date Date) throws IOException, SQLException {
		  
	      int x = 0;
		        double TOTALTAX1=0;
		        double TOTALTAX2=0;
	            double FinalTOTAL;
	            double GETTOT1;
	            double GETTOT2;
	            String StringMonth = comboBoxmonth.getSelectedItem().toString();
	    	    String StringYear = comboBoxyear.getSelectedItem().toString();
	    	    String StringDay = comboBoxday.getSelectedItem().toString();
	      	    String allDate = StringDay + "/" + StringMonth + "/" + StringYear;
	    	    
	    		String sql = "SELECT * FROM exceltable where year(Date) = "+ StringYear +" && month(Date) = " + StringMonth;
		    	
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
			 try {
				 File myFile = new File("\\\\DESKTOP-8D4C6KG\\Users\\ROG SCHOOL 5\\Desktop\\Application Converter Exel\\UpdateExcelFile\\UpdateFileG50.xlsx");
					FileInputStream fis = new FileInputStream(myFile);   
					XSSFWorkbook Workbook = new XSSFWorkbook (fis); 
					Sheet datasheet = Workbook.getSheetAt(0);
					Sheet datasheet2 = Workbook.getSheetAt(5);
					Sheet datasheet3 = Workbook.getSheetAt(6);
					DataFormat format = Workbook.createDataFormat();
					
					XSSFRow sheetrow;
					int i=0;										
		            Cell cell = null;
					
		            XSSFFont fontdate2 = Workbook.createFont();  
		            fontdate2.setFontHeightInPoints((short)14);  
		            fontdate2.setBold(true);  

		            CellStyle styledate = Workbook.createCellStyle();
					// styledate.setDataFormat(format.getFormat("mmm-aa"));
		            short df1 = Workbook.createDataFormat().getFormat("mmm");
		            styledate.setDataFormat(df1);
		            styledate.setFont(fontdate2);
		         /*   styledate.setWrapText(true);
		            styledate.setAlignment(HorizontalAlignment.CENTER);   */
		            
						 sheetrow = (XSSFRow) datasheet.getRow(3);
				            if(sheetrow == null){
				                sheetrow = (XSSFRow) datasheet.createRow(3);
				            }   				            
				            cell = sheetrow.getCell(5);				 
				           if(cell == null){
				                cell = sheetrow.createCell(5);
				            }   				            
				            cell.setCellValue(allDate);
					   		cell.setCellStyle(styledate);		        		            
                
					   		
		            
		            //Retrieve the row and check for null
		            sheetrow = (XSSFRow) datasheet.getRow(17);
		            if(sheetrow == null){
		                sheetrow = (XSSFRow) datasheet.createRow(17);
		            }   
		            
		            //Update the value of cell
		          
		            
		            
		            cell = sheetrow.getCell(7);
		            
		           if(cell == null){
		                cell = sheetrow.createCell(7);
		            }   
		            
		            cell.setCellValue(FinalTOTAL);
		            
		            
		            cell = sheetrow.getCell(10);
		           
		            if(cell == null){
		                cell = sheetrow.createCell(10);
		            }   
		            
		            cell.setCellValue(FinalTOTAL);
		            
		            
		            //insert into sheet 5 page (6) 
                 
		            i=8;
		            
		        	
					try {
						prepared = conn.prepareStatement(sql);
						resultat = prepared.executeQuery();
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();	
				}	
		            
					
					
					 
                   // Cells Style format ------------------------------------------------------------
					
					  XSSFFont font = Workbook.createFont();  
			            font.setFontHeightInPoints((short)11);  
			            font.setFontName("Courier New");  
			            font.setBold(true);  
			             										 
					 CellStyle style = Workbook.createCellStyle();
					 style.setDataFormat(format.getFormat("_(* #,##0.00_);_(* (#,##0.00);_(* \"-\"??_);_(@_)"));
					 
					 CellStyle stylecolor = Workbook.createCellStyle();
					 stylecolor.setFillForegroundColor(IndexedColors.YELLOW.getIndex());  
			         stylecolor.setFillPattern(FillPatternType.SOLID_FOREGROUND); 
			         stylecolor.setFont(font);
			            			            
			         XSSFFont fonttotal1 = Workbook.createFont();
					 fonttotal1.setFontHeightInPoints((short)14);
				     fonttotal1.setBold(true); 
				     
			         CellStyle total = Workbook.createCellStyle();
			         total.setWrapText(true);
			         total.setAlignment(HorizontalAlignment.CENTER);
			         total.setVerticalAlignment(VerticalAlignment.CENTER);
			         total.setFont(fonttotal1);
			         total.setDataFormat(format.getFormat("_(* #,##0.00_);_(* (#,##0.00);_(* \"-\"??_);_(@_)"));
			         //----------------------------------------------------------------------------------   
		            while(resultat.next()) {
		            
		            sheetrow = (XSSFRow) datasheet2.getRow(i);
		            if(sheetrow == null){
		                sheetrow = (XSSFRow) datasheet2.createRow(i);    
		            }   
		            
		            //Update the value of cell
		            
		            
		            cell = sheetrow.createCell(0);
		            
		           String ville;
		           ville = resultat.getString("BUREAU");
		           cell.setCellStyle(stylecolor);
		           cell.setCellValue(ville);
		           		          		         		         
		           cell = sheetrow.createCell(1);
		           		           
		           float TAX_PAV;
		           TAX_PAV = resultat.getFloat("TAX_PAV");
		           cell.setCellValue(TAX_PAV);
		           cell.setCellStyle(style);
		           
		          
		           
		           cell = sheetrow.createCell(2);
		           
		           float TAX_RAV;
		           TAX_RAV = resultat.getFloat("TAX_RAV");
		           cell.setCellValue(TAX_RAV);
		           cell.setCellStyle(style);
		          
		           
		           cell = sheetrow.createCell(3);
		           
		           float Montant;
		           Montant = resultat.getFloat("Montant");
		           cell.setCellValue(Montant);
		           cell.setCellStyle(style);
		          
		           
		           cell = sheetrow.createCell(4);
		           
		           float TAX_DAB;
		           TAX_DAB = resultat.getFloat("TAX_DAB");
		           cell.setCellValue(TAX_DAB);
		           cell.setCellStyle(style);
		         
		           
		           cell = sheetrow.createCell(5);
		           
		           float TAX_IDS;
		           TAX_IDS = resultat.getFloat("TAX_IDS");
		           cell.setCellValue(TAX_IDS);
		           cell.setCellStyle(style);
		           
		           cell = sheetrow.createCell(6);
		           
		           float TAX_SA;
		           TAX_SA = resultat.getFloat("TAX_SA");
		           cell.setCellValue(TAX_SA);
		           cell.setCellStyle(style);
		           
		           String is = Integer.toString(i+1);
		           
		          
		           
		           cell = sheetrow.createCell(7);
		           String Sum = "SUM(B" +(is)+":G"+(is)+")";
		           cell.setCellFormula(Sum);
		           cell.setCellStyle(total);
		           
		           
		           i++;
		            sheetrow = (XSSFRow) datasheet2.getRow(i);
		           } 
		            
		           // Last ligne total values  :  ******************************************************************** 
		            XSSFFont fonttotal = Workbook.createFont();  
					  fonttotal.setFontHeightInPoints((short)24);  
					  fonttotal.setFontName("Cambria");  
					  fonttotal.setBold(true);
					
					    CellStyle styletotal = Workbook.createCellStyle();
					    styletotal.setFont(fonttotal); 
					    styletotal.setDataFormat(format.getFormat("_(* #,##0.00_);_(* (#,##0.00);_(* \"-\"??_);_(@_)"));
					    
				    sheetrow = (XSSFRow) datasheet2.getRow(i);
		            if(sheetrow == null){
		                sheetrow = (XSSFRow) datasheet2.createRow(i); 
		            }   
		              sheetrow.setHeight((short) 720);
		            
		            cell = sheetrow.createCell(0);
		            cell.setCellValue("TOTAL");
		            cell.setCellStyle(styletotal);
		            
		            
		            
		          
		            String Sum = "SUM(B9:B"+(i)+")";
		            cell = sheetrow.createCell(1);
		            cell.setCellFormula(Sum);
		            cell.setCellStyle(styletotal);
		            
		            Sum = "SUM(C9:C"+(i)+")";
		            cell = sheetrow.createCell(2);
		            cell.setCellFormula(Sum);
		            cell.setCellStyle(styletotal);
		            
		            Sum = "SUM(D9:D"+(i)+")";
		            cell = sheetrow.createCell(3);
		            cell.setCellFormula(Sum);
		            cell.setCellStyle(styletotal);
		            
		            Sum = "SUM(E9:E"+(i)+")";
		            cell = sheetrow.createCell(4);
		            cell.setCellFormula(Sum);
		            cell.setCellStyle(styletotal);
		            
		            Sum = "SUM(F9:F"+(i)+")";
		            cell = sheetrow.createCell(5);
		            cell.setCellFormula(Sum);
		            cell.setCellStyle(styletotal);
		            
		            Sum = "SUM(G9:G"+(i)+")";
		            cell = sheetrow.createCell(6);
		            cell.setCellFormula(Sum);
		            cell.setCellStyle(styletotal);
		            
		            Sum = "SUM(H9:H"+(i)+")";
		            cell = sheetrow.createCell(7);
		            cell.setCellFormula(Sum);
		            cell.setCellStyle(styletotal);
		            
		            
		            datasheet2.setColumnWidth(1, 9000);
		            datasheet2.setColumnWidth(2, 9000);
		            datasheet2.setColumnWidth(3, 9000);
		            datasheet2.setColumnWidth(4, 9000);
		            datasheet2.setColumnWidth(5, 9000);
		            datasheet2.setColumnWidth(6, 9000);
					datasheet2.setColumnWidth(7, 10000);
					
					
					ArrayList<Integer> CodeC = new ArrayList<Integer>();
					  CodeC.addAll(Arrays.asList(40100,40102,40103,40104,40105,40106,40107,40108,40111,40112,40113,40114,40115,40200,40201,40202,40203,40204,40205,40208,40209,40210,40211,40212,40213,40214,40300,40301,40302,40303,40304,40305,40306,40307,40308,40309,40400,40401,40402,40403,40404,40405,40406,40407,40408,40412,40417,40420,40421,40422,40423,40424,40425));

				x =8;
				float montant=0;
				float totalmnt=0;
                int code=0;
                String BR;
                int comp = 1;
                
				for (int n= 0 ; n < CodeC.size();n++) {
					
							
						
				String sqls = "SELECT * FROM exceltable where year(Date) = "+ StringYear +" && month(Date) = " + StringMonth +"&& CodeC = " + CodeC.get(n);
				try {
										prepared = conn.prepareStatement(sqls);
										resultat = prepared.executeQuery();
										
									} catch (SQLException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();	
								}	
						
				    XSSFFont fontCode = Workbook.createFont();  
		            font.setBold(true);  
				
				    CellStyle style6 = Workbook.createCellStyle();				    
				    style6.setDataFormat(format.getFormat("_(* #,##0.00_);_(* (#,##0.00);_(* \"-\"??_);_(@_)"));
					
				    CellStyle codeCs = Workbook.createCellStyle();
			          codeCs.setWrapText(true);
			         codeCs.setAlignment(HorizontalAlignment.CENTER);
			         codeCs.setVerticalAlignment(VerticalAlignment.CENTER);
			         codeCs.setFont(fontCode);
			         
			         CellStyle BrS = Workbook.createCellStyle();
			         BrS.setFillForegroundColor(IndexedColors.YELLOW.getIndex());  
			         BrS.setFillPattern(FillPatternType.SOLID_FOREGROUND); 
			         BrS.setAlignment(HorizontalAlignment.CENTER);
			         BrS.setVerticalAlignment(VerticalAlignment.CENTER);
			        			         
			         
			         
								 while(resultat.next()) {
								  sheetrow = (XSSFRow) datasheet3.getRow(x);
						            if(sheetrow == null){
						                sheetrow = (XSSFRow) datasheet3.createRow(x);    
						            }   
						            
						            //Update the value of cell
						            
						            
						            
									montant = resultat.getFloat("Montant");	
									totalmnt = resultat.getFloat("Total");	
									code = resultat.getInt("CodeC");
									BR = resultat.getString("BUREAU");
																	
									
									cell = sheetrow.createCell(0);
									cell.setCellValue(comp);
									cell.setCellStyle(BrS);
									comp++;
									
									cell = sheetrow.createCell(1);									
									cell.setCellValue(BR);									
									
									cell = sheetrow.createCell(2);									
									cell.setCellValue(code);
									cell.setCellStyle(codeCs);
									
									cell = sheetrow.createCell(3);
									cell.setCellValue(montant);
									cell.setCellStyle(style6);
									
									cell = sheetrow.createCell(4);									
									cell.setCellValue(totalmnt);
									cell.setCellStyle(style6);
									
									  Sum = "D"+(x+1)+"+E"+(x+1);
							            cell = sheetrow.createCell(5);
							            cell.setCellFormula(Sum);
							            cell.setCellStyle(styletotal);
							            
							            Sum = "F"+(x+1)+"*0.02";
							            cell = sheetrow.createCell(6);
							            cell.setCellFormula(Sum);
							            cell.setCellStyle(styletotal);	
								 }
								 x++;
								 	 
							 
							}
				
				  sheetrow = (XSSFRow) datasheet3.getRow(x);
		            if(sheetrow == null){
		                sheetrow = (XSSFRow) datasheet3.createRow(x);    
		            }   
		            
		            cell = sheetrow.createCell(0);
		            cell.setCellValue("TOTAL");
		            
		            
		            Sum = "SUM(D9:D"+(x)+")";
		            cell = sheetrow.createCell(3);
		            cell.setCellFormula(Sum);
		            cell.setCellStyle(styletotal);	
				
		            Sum = "SUM(E9:E"+(x)+")";
		            cell = sheetrow.createCell(4);
		            cell.setCellFormula(Sum);
		            cell.setCellStyle(styletotal);
		            
		            Sum = "D"+(x+1)+"+E"+(x+1);
		            cell = sheetrow.createCell(5);
		            cell.setCellFormula(Sum);
		            cell.setCellStyle(styletotal);
		            
		            Sum = "F"+(x+1)+"*0.02";
		            cell = sheetrow.createCell(6);
		            cell.setCellFormula(Sum);
		            cell.setCellStyle(styletotal);	
				  
				    comp = 1;
		            x = 7;
		            
		            datasheet3.setColumnWidth(0, 4000);
		            datasheet3.setColumnWidth(1, 8000);
		            datasheet3.setColumnWidth(2, 8000);
		            datasheet3.setColumnWidth(3, 8000);
		            datasheet3.setColumnWidth(4, 9000);
		            datasheet3.setColumnWidth(5, 9000);
		            datasheet3.setColumnWidth(6, 10000);
					datasheet3.setColumnWidth(7, 10000);
		           //*************************************************************************************************
		            Workbook.setForceFormulaRecalculation(true);
		            
		            
		            fis.close();
                    
		            FileOutputStream outFile =new FileOutputStream(new File("C:\\Users\\ROG SCHOOL 5\\Desktop\\Output.xlsx"));
		            Workbook.write(outFile);
		            outFile.close();
                    
		        } catch (FileNotFoundException e) {
		            e.printStackTrace();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }  
  }  
}

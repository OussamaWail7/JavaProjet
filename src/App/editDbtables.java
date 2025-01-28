package App;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;

import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class editDbtables extends JPanel {

	static Connection conn = null;
    static ResultSet resultat = null;
    static PreparedStatement prepared = null;  
	
       
       String filename;
       private static JTable table;
       static boolean filechosedcolorchange=true;    
       static java.sql.Date DateGlobal;
       
	public editDbtables() {
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
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setBounds(0, 222, 305, 8);
		add(separator_5);
		
		JComboBox comboBoxday = new JComboBox();
		comboBoxday.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		comboBoxday.setBounds(57, 98, 42, 34);
		add(comboBoxday);
		
		JComboBox comboBoxmonth = new JComboBox();
		comboBoxmonth.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
		comboBoxmonth.setBounds(111, 98, 43, 34);
		add(comboBoxmonth);
		
		JComboBox comboBoxyear = new JComboBox();
		comboBoxyear.setModel(new DefaultComboBoxModel(new String[] {"2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050", "2051", "2052", "2053", "2054", "2055", "2056", "2057", "2058", "2059", "2060", "2061", "2062", "2063", "2064"}));
		comboBoxyear.setBounds(166, 98, 72, 34);
		add(comboBoxyear);
		
		
		
		JButton btnNewButton = new JButton("Delet");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				int reply = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment supprimer ce tableau","Attention", JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
					String StringMonth = comboBoxmonth.getSelectedItem().toString();
		      	    String StringYear = comboBoxyear.getSelectedItem().toString();
						String sql = " delete from exceltable where year(Date) = "+ StringYear +" && month(Date) = " + StringMonth;
						try {
							prepared = conn.prepareStatement(sql);
							prepared.execute();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							
						}
					JOptionPane.showMessageDialog(null, "Le TABLEAU a été supprimé ");
					    String StringMonthh = comboBoxmonth.getSelectedItem().toString();
					    String StringYearr = comboBoxyear.getSelectedItem().toString();
					update_table(StringYearr,StringMonthh);
				} else {
				    JOptionPane.showMessageDialog(null, "Opération annuler");
				}
				
      		
				
			}
		});
		btnNewButton.setBounds(93, 304, 97, 35);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Get Data");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				    String StringMonth = comboBoxmonth.getSelectedItem().toString();
				    String StringYear = comboBoxyear.getSelectedItem().toString();
				    update_table(StringYear,StringMonth);
			}
		});
		btnNewButton_1.setBounds(93, 243, 97, 40);
		add(btnNewButton_1);
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
}

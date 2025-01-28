package App;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JList;

public class MenuPage extends JFrame {

	private JPanel contentPane;
	private JPanel contentPane_1;
	private JLabel lblNewLabel;
	private JPanel Station_var;
	private JPanel Bus_var;
	private JPanel Buses_var;
	private JPanel User_var;
	private JPanel Info_var;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPage frame = new MenuPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MenuPage() {
		setLocationByPlatform(true);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1514, 1159);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize.width, screenSize.height);
	//	setBounds(100, 100, 1050, 652);
	//	setExtendedState(JFrame.MAXIMIZED_BOTH); 
		
		    
		
		
        contentPane_1 = new JPanel();
        
        exportpage exppg = new exportpage();
        exppg.setLocation(421, 237);
		
		importpage imppg = new importpage();
		imppg.setLocation(421, 237);
		
		editDbtables edtpg = new editDbtables();
		
		
		contentPane_1.add(imppg);
		contentPane_1.add(exppg);
		contentPane_1.add(edtpg);
		
		
		imppg.setVisible(true);
		exppg.setVisible(false);
		edtpg.setVisible(false);
		
		setContentPane(contentPane_1);
		contentPane_1.setLayout(null);
		
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 418, 1080);
		panel.setBackground(new Color(54,33,89));
		
		contentPane_1.add(panel);
		panel.setLayout(null);
		
		 Buses_var = new JPanel();
			Buses_var.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					exppg.setVisible(false);
					edtpg.setVisible(false);
					imppg.setVisible(true);
				}
				public void mousePressed(MouseEvent arg0) {
					setColor(Buses_var);
					resetColor(Station_var);
					resetColor(Bus_var);
					resetColor(User_var);
					resetColor(Info_var);
					
					
					
					
				}
				
			});
			Buses_var.setBounds(0, 133, 406, 83);
			Buses_var.setBackground(new Color(85,65,118));
			panel.add(Buses_var);
			Buses_var.setLayout(null);
			
			JLabel label = new JLabel("");
			label.setIcon(new ImageIcon("C:\\Users\\ROG SCHOOL 5\\Desktop\\Application Converter Exel\\Img\\import (2).png"));
			label.setBounds(25, 24, 43, 35);
			Buses_var.add(label);
			
			JLabel lblTheBuses = new JLabel("IMPORT FILES");
			lblTheBuses.setFont(new Font("Segoe UI", Font.BOLD, 16));
			lblTheBuses.setForeground(new Color(204,204,204));
			lblTheBuses.setBounds(124, 24, 182, 35);
			Buses_var.add(lblTheBuses);
			
			
			
			 Station_var = new JPanel();
			Station_var.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					imppg.setVisible(false);
					edtpg.setVisible(false);
					exppg.setVisible(true);
				}
				@Override
				public void mousePressed(MouseEvent e) {
					setColor(Station_var);
					resetColor(Buses_var);
					resetColor(Bus_var);
					resetColor(User_var);
					resetColor(Info_var);
					;
				}
			});
			Station_var.setLayout(null);
			Station_var.setBackground(new Color(64,43,100));
			Station_var.setBounds(0, 217, 406, 83);
			panel.add(Station_var);
			
			JLabel label_1 = new JLabel("");
			label_1.setIcon(new ImageIcon("C:\\Users\\ROG SCHOOL 5\\Desktop\\Application Converter Exel\\Img\\export.png"));
			label_1.setBounds(26, 24, 44, 35);
			Station_var.add(label_1);
			
			JLabel lblStationsOfBus = new JLabel("EXPORT FILES");
			lblStationsOfBus.setForeground(new Color(204, 204, 204));
			lblStationsOfBus.setFont(new Font("Segoe UI", Font.BOLD, 16));
			lblStationsOfBus.setBounds(124, 24, 182, 35);
			Station_var.add(lblStationsOfBus);
			
			 Bus_var = new JPanel();
			Bus_var.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					exppg.setVisible(false);
					imppg.setVisible(false);
					edtpg.setVisible(true);	
				}
				@Override
				public void mousePressed(MouseEvent e) {
					setColor(Bus_var);
					resetColor(Buses_var);
					resetColor(Station_var);
					resetColor(User_var);
					resetColor(Info_var);
					
				}
			});
			Bus_var.setLayout(null);
			Bus_var.setBackground(new Color(64,43,100));
			Bus_var.setBounds(0, 301, 406, 83);
			panel.add(Bus_var);
			
			JLabel label_2 = new JLabel("");
			label_2.setIcon(new ImageIcon("C:\\Users\\ROG SCHOOL 5\\Desktop\\Application Converter Exel\\Img\\pencil.png"));
			label_2.setBounds(26, 24, 46, 35);
			Bus_var.add(label_2);
			
			JLabel lblBusLine = new JLabel("EDIT FILES");
			lblBusLine.setForeground(new Color(204, 204, 204));
			lblBusLine.setFont(new Font("Segoe UI", Font.BOLD, 16));
			lblBusLine.setBounds(124, 24, 182, 35);
			Bus_var.add(lblBusLine);
			
			 User_var = new JPanel();
			User_var.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
				}
				@Override
				public void mousePressed(MouseEvent e) {
					setColor(User_var);
					resetColor(Station_var);
					resetColor(Buses_var);
					resetColor(Bus_var);
					resetColor(Info_var);
					
				}
			});
			User_var.setLayout(null);
			User_var.setBackground(new Color(64,43,100));
			User_var.setBounds(0, 563, 406, 83);
			panel.add(User_var);
			
			JLabel label_3 = new JLabel("");
			label_3.setIcon(new ImageIcon("C:\\Users\\ROG SCHOOL 5\\Desktop\\Application Converter Exel\\Img\\lawyer-ConvertImage (1).png"));
			label_3.setBounds(26, 13, 56, 46);
			User_var.add(label_3);
			
			JLabel lblUsers = new JLabel("Users");
			lblUsers.setForeground(new Color(204, 204, 204));
			lblUsers.setFont(new Font("Segoe UI", Font.BOLD, 16));
			lblUsers.setBounds(124, 24, 182, 35);
			User_var.add(lblUsers);
			
		    Info_var = new JPanel();
			Info_var.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
				}
				@Override
				public void mousePressed(MouseEvent e) {
					setColor(Info_var);
					resetColor(Station_var);
					resetColor(Buses_var);
					resetColor(Bus_var);
					resetColor(User_var);
					
				}
			});
			Info_var.setLayout(null);
			Info_var.setBackground(new Color(64,43,100));
			Info_var.setBounds(0, 659, 406, 83);
			panel.add(Info_var);
			
			JLabel label_4 = new JLabel("");
			label_4.setIcon(new ImageIcon("C:\\Users\\ROG SCHOOL 5\\Desktop\\Application Converter Exel\\Img\\info.png"));
			label_4.setBounds(31, 13, 40, 57);
			Info_var.add(label_4);
			
			JLabel lblInfo = new JLabel("Information");
			lblInfo.setForeground(new Color(204, 204, 204));
			lblInfo.setFont(new Font("Segoe UI", Font.BOLD, 16));
			lblInfo.setBounds(123, 24, 182, 35);
			Info_var.add(lblInfo);
			
			JSeparator separator = new JSeparator();
			separator.setBounds(0, 489, 424, 8);
			panel.add(separator);
			
			JSeparator separator_1 = new JSeparator();
			separator_1.setBounds(0, 112, 424, 8);
			panel.add(separator_1);
			
			JLabel lblUnivers = new JLabel("Menu");
			lblUnivers.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 46));
			lblUnivers.setForeground(new Color(204,204,204));
			lblUnivers.setBounds(12, 13, 396, 86);
			panel.add(lblUnivers);
			
			JPanel panel_2 = new JPanel();
			panel_2.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					System.exit(0);
				}
			});
			panel_2.setLayout(null);
			panel_2.setBackground(new Color(64, 43, 100));
			panel_2.setBounds(0, 881, 406, 83);
			panel.add(panel_2);
			
			JLabel label_8 = new JLabel("");
			label_8.setIcon(new ImageIcon("C:\\Users\\ROG SCHOOL 5\\Desktop\\Application Converter Exel\\Img\\logout-2-ConvertImage.png"));
			label_8.setBounds(28, 13, 67, 57);
			panel_2.add(label_8);
			
			JLabel lblExit = new JLabel("Exit");
			lblExit.setForeground(new Color(204, 204, 204));
			lblExit.setFont(new Font("Segoe UI", Font.BOLD, 16));
			lblExit.setBounds(124, 24, 182, 35);
			panel_2.add(lblExit);
			
			JSeparator separator_3 = new JSeparator();
			separator_3.setBounds(0, 801, 424, 8);
			panel.add(separator_3);
			
			JPanel panel_1 = new JPanel();
			panel_1.setBounds(421, 52, 1499, 187);
			panel_1.setBackground(new Color(63, 132, 187));   //rgb(63, 132, 187)
			contentPane_1.add(panel_1);
			panel_1.setLayout(null);
			
			JLabel lblNewLabel_1 = new JLabel("Traitement de fichier excel");
			lblNewLabel_1.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 47));
			lblNewLabel_1.setForeground(new Color(246, 203, 7)); // rgb(246, 203, 7)   
			lblNewLabel_1.setBounds(81, 13, 454, 80);
			panel_1.add(lblNewLabel_1);
			
			JLabel lblNewLabel_2 = new JLabel("Alg\u00E9rie poste ");
			lblNewLabel_2.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 30));
			lblNewLabel_2.setForeground(new Color(16, 84, 149)); //    //rgb(16, 84, 149)
			lblNewLabel_2.setBounds(203, 113, 150, 38);
			panel_1.add(lblNewLabel_2);
			
			JSeparator separator_2 = new JSeparator();
			separator_2.setBounds(61, 101, 461, 2);
			panel_1.add(separator_2);
			
			JLabel lblNewLabel_3 = new JLabel("");
			lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\ROG SCHOOL 5\\Desktop\\Application Converter Exel\\Img\\1593254044_tmp_algerie-poste-1.jpg"));
			lblNewLabel_3.setBounds(1282, 61, 150, 80);
			panel_1.add(lblNewLabel_3);
			
			JLabel lblNewLabel_4 = new JLabel("");
			lblNewLabel_4.setIcon(new ImageIcon("C:\\Users\\ROG SCHOOL 5\\Desktop\\Application Converter Exel\\Img\\import Grand.png"));
			lblNewLabel_4.setBounds(1178, 61, 72, 80);
			panel_1.add(lblNewLabel_4);
			
			JLabel lblX = new JLabel("X");
			lblX.setBounds(1877, 0, 31, 47);
			lblX.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					System.exit(0);
				}
			});
			lblX.setFont(new Font("Tahoma", Font.BOLD, 24));
			lblX.setForeground(new Color(122,72,221));
			contentPane_1.add(lblX);
			
			JLabel label_5 = new JLabel("-");
			label_5.setBounds(1840, 3, 25, 36);
			label_5.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					setState(JFrame.ICONIFIED);
				}
			});
			label_5.setForeground(new Color(122, 72, 221));
			label_5.setFont(new Font("Tahoma", Font.BOLD, 25));
			contentPane_1.add(label_5);
			
			
			
			
			
			setLocationRelativeTo(null);
			
		}
		 public void setColor(JPanel panel) {
			 panel.setBackground(new Color(85,65,118));
	     }
		 public void resetColor(JPanel panel) {
			 panel.setBackground(new Color(64,43,100));
		 }
	}



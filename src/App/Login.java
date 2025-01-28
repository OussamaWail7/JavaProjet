package App;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Desktop;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.ImageIcon;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.*;

public class Login {

	private JFrame frame;
	private JTextField user_var;
	private JPasswordField pass_var;
	private JSeparator separator_1;
	private JLabel lblPassword;
    static Connection cnx = null;
    static ResultSet resultat = null;
    PreparedStatement prepared = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		cnx = ConnexionMySql.ConnexionDB();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
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
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setLocationByPlatform(true);
		frame.setForeground(SystemColor.activeCaption);
		frame.setUndecorated(true);
		frame.setSize(915,572);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		//Connection to database mySql
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setForeground(Color.WHITE);
		panel_1.setBounds(467, 0, 448, 572);
		panel_1.setBackground(Color.DARK_GRAY);
		
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel User = new JLabel("User :");
		User.setBounds(50, 87, 56, 16);
		User.setForeground(SystemColor.activeCaption);
		User.setForeground(new Color(57,113,177));
		panel_1.add(User);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(50, 158, 360, 6);
		separator.setBackground(new Color(57,113,177));
		panel_1.add(separator);
		
		user_var = new JTextField();
		user_var.setBounds(50, 116, 335, 29);
		user_var.setCaretColor(Color.WHITE);
		user_var.setForeground(Color.WHITE);
		user_var.setBorder(null);
		user_var.setBackground(Color.DARK_GRAY);
		panel_1.add(user_var);
		user_var.setColumns(10);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(50, 269, 360, 2);
		panel_1.add(separator_1);
		
		pass_var = new JPasswordField();
		pass_var.setCaretColor(SystemColor.text);
		pass_var.setDisabledTextColor(SystemColor.controlLtHighlight);
		pass_var.setBounds(50, 232, 335, 29);
		pass_var.setBorder(null);
		pass_var.setBackground(Color.DARK_GRAY);
		pass_var.setForeground(Color.WHITE);
		panel_1.add(pass_var);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setBounds(53, 203, 69, 16);
		lblPassword.setForeground(Color.GRAY);  
		lblPassword.setBackground(new Color(51,52,54));
		panel_1.add(lblPassword);
		
		JButton btnNewButton = new JButton("Sign in");
		btnNewButton.setBounds(50, 365, 370, 51);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String user_name= user_var.getText().toString();
				String pass = pass_var.getText().toString();
				
				String sql = "select username , password from users ";
				try {
					prepared = cnx.prepareStatement(sql);
					resultat = prepared.executeQuery();
					if (!user_name.equals("") || !pass.equals("")) {
					int cmp=0;
					while (resultat.next())
					{
					  String username1 = resultat.getString("username");
					  String password1 = resultat.getString("password");
					  
					  if (username1.equals(user_name) && password1.equals(pass)) {
						  cmp++;
						  JOptionPane.showMessageDialog(null,"Welcome");
						  frame.dispose();
						  MenuPage mn = new MenuPage();
						  mn.setVisible(true);
						  break;				
						  }
					}
					if (cmp==0) {
						  JOptionPane.showMessageDialog(null,"User Or Password Wrong");
					  }
					 
					}else {
						JOptionPane.showMessageDialog(null,"User Or Password is Empty");
					 }
						
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(new Color(102, 51, 153));
		panel_1.add(btnNewButton);
		
		JLabel lblX = new JLabel("X");
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}
		});
		lblX.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblX.setForeground(Color.WHITE);
		lblX.setBounds(428, 13, 20, 16);
		panel_1.add(lblX);
		
		JLabel label = new JLabel("-");
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setState(JFrame.ICONIFIED);
			}
		});
		label.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label.setForeground(Color.WHITE);
		label.setBounds(401, 12, 20, 16);
		panel_1.add(label);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				 try {	
					Desktop.getDesktop().browse(new URI("https://www.facebook.com/Dr3amZ97"));
				} catch (URISyntaxException e) {
					e.printStackTrace();
				} catch (IOException e) {
					
					e.printStackTrace();
				} 
                 
			}
		});
		lblNewLabel_2.setBounds(399, 530, 37, 29);
		panel_1.add(lblNewLabel_2);
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\ROG SCHOOL 5\\Desktop\\facebook-ConvertImage (2).png"));
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 try {	
						Desktop.getDesktop().browse(new URI("https://www.instagram.com/dr3amz.drift/"));
					} catch (URISyntaxException ex) {
						
						ex.printStackTrace();
					} catch (IOException ex) {
						
						ex.printStackTrace();
					} 
			}
		});
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\ROG SCHOOL 5\\Desktop\\instagram-ConvertImage (2).png"));
		lblNewLabel.setBounds(358, 530, 37, 29);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\ROG SCHOOL 5\\Desktop\\Application Converter Exel\\Img\\gmail-ConvertImage (1).png"));
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null,"Email :\n Oussamadrift97@gmail.com  "+ "\n nihad.rahabi@gmail.com");
			}
		});
		lblNewLabel_3.setBounds(309, 530, 37, 29);
		panel_1.add(lblNewLabel_3);
		
		JPanel pnl_overlay = new JPanel();
		pnl_overlay.setBounds(0, 0, 466, 572);
		frame.getContentPane().add(pnl_overlay);
		pnl_overlay.setBackground(Color.BLACK);
		pnl_overlay.setLayout(null);
		pnl_overlay.setBackground(new Color(0,0,0,200));
		
		JLabel lblTransportManagement = new JLabel("Excel edit ");
		lblTransportManagement.setForeground(Color.WHITE);
		lblTransportManagement.setFont(new Font("Candara Light", Font.ITALIC, 30));
		lblTransportManagement.setBounds(86, 29, 317, 79);
		pnl_overlay.add(lblTransportManagement);
		
		JLabel lblPowerdByOussama = new JLabel("Powerd By Oussama ");
		lblPowerdByOussama.setFont(new Font("Tw Cen MT", Font.PLAIN, 13));
		lblPowerdByOussama.setForeground(SystemColor.inactiveCaptionBorder);
		lblPowerdByOussama.setBounds(296, 535, 170, 24);
		pnl_overlay.add(lblPowerdByOussama);
		
		JLabel lblUniversityTransportation = new JLabel("management");
		lblUniversityTransportation.setFont(new Font("Candara Light", Font.ITALIC, 30));
		lblUniversityTransportation.setForeground(Color.WHITE);
		lblUniversityTransportation.setBounds(142, 93, 170, 32);
		pnl_overlay.add(lblUniversityTransportation);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(0, 0, 476, 572);
		pnl_overlay.add(lblNewLabel_1);
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\ROG SCHOOL 5\\Desktop\\Application Converter Exel\\Img\\computer-2982270_1280.JPG"));
		
		
		pass_var.addFocusListener(new FocusAdapter() {
			@Override
			
			public void focusGained(FocusEvent arg0) {
				separator_1.setBackground(new Color(57,113,177));
				lblPassword.setForeground(new Color(57,113,177));
				User.setForeground(new Color(51,52,54));
				separator.setBackground(new Color(255,255,255));
			}
		});
		
		user_var.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				separator_1.setBackground(new Color(255,255,255));
				lblPassword.setForeground(new Color(51,52,54));
				User.setForeground(new Color(57,113,177));
				separator.setBackground(new Color(57,113,177));
			}
		});
		
	}

}

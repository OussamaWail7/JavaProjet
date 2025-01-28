package App;

import java.sql.DriverManager;
import java.sql.Connection;
import javax.swing.JOptionPane;



public class ConnexionMySql {

static Connection conn = null;
    
    public static Connection ConnexionDB() {
    	 try {
     		  Class.forName("com.mysql.jdbc.Driver");
     		  Connection conn=  DriverManager.getConnection("jdbc:mysql://localhost:3306/postedb?useTimezone=true&serverTimezone=UTC", "root","");
     		  return conn;
     	  }catch(Exception e)   
     	  {
     		  JOptionPane.showMessageDialog(null , e);
     		  return null;
     	  }
    }
}

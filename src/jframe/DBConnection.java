/**
 * this class connects our code to MySQL server
 */

package jframe;


import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author juans
 */
public class DBConnection {
    static Connection con = null;
    
    public static Connection getConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver"); //java loads mysql driver
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_managament_system","root","");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
        }

}


package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static String url = "jdbc:mysql://localhost:3306/prueba_java2";    
    private static String driverName = "com.mysql.jdbc.Driver";   
    private static String username = "root";   
    private static String password = "";
    private static Connection con;


    public static Connection getConnection() {
        try {
            Class.forName(driverName);
            try {
                con = DriverManager.getConnection(url, username, password);
            } catch (SQLException ex) {
             
                System.out.println("Fallo en la conexion."); 
            }
        } catch (ClassNotFoundException ex) {
            
            System.out.println("No se encuentra driver."); 
        }
        System.out.println("Conexion exitosa");
        return con;
    }
}
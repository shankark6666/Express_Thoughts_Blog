
package com.ExpressThoughts.helper;

import java.sql.*;
public class ConnectionProvider {
    
    private static Connection con;
     public static Connection getConnection(){
     
         try {
             if (con==null) {
                   
            //Load driver class
             Class.forName("com.mysql.jdbc.Driver");
             
             //create connection
             
             con=DriverManager.
                     getConnection("jdbc:mysql://localhost:3306/blogg", "root", "root");
             
             }
         } catch (Exception e) {
        
         
         e.printStackTrace();
         }
     
     
         
         return con;
     
     
     }
    
    
}

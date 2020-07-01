package com.example;

import java.sql.*;


public class ConnectionManager {

   static Connection con;
   static String url;
         
   public static Connection getConnection()
   {
     
      try
      {
         String url = "jdbc:mysql://localhost:3306/demo";
         // assuming "DataSource" is your DataSource name

         Class.forName("com.mysql.cj.jdbc.Driver");
         
         try
         {            	
            con = DriverManager.getConnection(url,"root",""); 
             							
            System.out.println("connected to db");
              
         }
         
         catch (SQLException ex)
         {
         }
      }

      catch(ClassNotFoundException e)
      {
         System.out.println(e);
      }

   return con;
}
}
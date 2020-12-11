package co;

import java.sql.*;

public class Dbconnection
{
public static java.sql.Connection conn;
 
public Dbconnection(){}
public static java.sql.Connection Getconnection()throws ClassNotFoundException,SQLException
    {
      Class.forName("oracle.jdbc.driver.OracleDriver");
      conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","assignment7","vivek123");
      return conn;
    }
}
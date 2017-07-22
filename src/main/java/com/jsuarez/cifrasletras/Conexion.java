package com.jsuarez.cifrasletras;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Conexion
{ 
  private Connection conn;
  private Statement stmt;
  private PreparedStatement prestmt;
  private CallableStatement cstm;
  
 
  public Conexion() throws SQLException, NamingException {
	  DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
      conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "test", "test");
         
      stmt = conn.createStatement();
       
  }
 
  public ResultSet ejecutarQuery(String query) throws SQLException {
      
     if ( stmt == null ){
        return null;
     }
     else{
        return stmt.executeQuery(query);
     }
       
  }
 
  public void ejecutar(String query) throws SQLException {
    stmt.execute(query);
  }
 
  public void cerrarConexion() throws SQLException {
	  if (stmt!=null) stmt.close();
    if (conn!=null) conn.close();
    if (prestmt!=null) prestmt.close();
    if (cstm!=null) cstm.close();
  }
 
  public Connection getConnection(){
      return conn;
  }
 
  public Statement getStatement(){
      return stmt;
  }
  public PreparedStatement getPreparedStatement(String sql) throws SQLException {	  

		prestmt = conn.prepareStatement(sql);
    return prestmt;
  }
  
  public CallableStatement getCallableStatement(String sql) throws SQLException {
    cstm = conn.prepareCall(sql);
    return cstm;
  }
  
}


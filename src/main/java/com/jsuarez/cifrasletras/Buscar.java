package com.jsuarez.cifrasletras;

import java.sql.ResultSet;

public class Buscar extends Thread {

	private String palabra;
	private String longitud;
	
	public Buscar(String palabra, String longitud){
		this.palabra = palabra;
		this.longitud = longitud;
	}
	
	public void run(){
		try {
			Conexion con = new Conexion();
			ResultSet rs = con.ejecutarQuery("select palabra from cifrasletras where contains(ordenada,'"+palabra.toUpperCase()+"') > 0");
			while ( rs.next() ){
				System.out.println("PALABRA de "+longitud+": "+rs.getString(1));
			}
			rs.close();
			con.cerrarConexion();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

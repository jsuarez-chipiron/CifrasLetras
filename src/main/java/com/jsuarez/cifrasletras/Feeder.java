package com.jsuarez.cifrasletras;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.SQLException;
import java.util.StringTokenizer;

import javax.naming.NamingException;

public class Feeder {
	
	private Conexion con;
	private int i = 0;
	
	public Feeder() throws SQLException, NamingException{
		con = new Conexion();
	}

	public static void addPalabra(Palabra p) throws SQLException, NamingException{
		Conexion con = new Conexion();
		con.ejecutar("insert into cifrasletras values('"+p.getPalabra().toUpperCase()+"','"+p.getOrdenada().toUpperCase()+"')");
		con.ejecutar("alter index ot_cifrasletras rebuild");
		con.cerrarConexion();
	}
	
	public void alimentar() throws Exception{
		BufferedReader br = new BufferedReader(new FileReader("/home/jasuarez/Escritorio/feed3.txt"));
		String linea = null;
		int j = 0;
		while ((linea = br.readLine()) != null ){
			this.procesarLinea(linea);			
			j++;
			if ( (j%500) == 0){
				System.out.println(".");
			}
		}
		br.close();
		this.finalizar();
	}
	
	private void procesarLinea(String texto){
		texto = texto.toUpperCase();
		
		//ELIMINAMOS ACENTOS
		texto = texto.replaceAll("Á", "A");
		texto = texto.replaceAll("É", "E");
		texto = texto.replaceAll("Í", "I");
		texto = texto.replaceAll("Ó", "O");
		texto = texto.replaceAll("Ú", "U");
		texto = texto.replaceAll("Ü", "U");
		
		//ELIMINAMOS LOS CARACTERES EXTRAÑOS
//		texto = texto.replaceAll(".", " ");
		texto = this.replacePuntos(texto);
		texto = this.replaceInter(texto);
		texto = this.replaceExclamacion(texto);
		texto = texto.replaceAll(",", " ");
		texto = texto.replaceAll(";", " ");
		texto = texto.replaceAll(":", " ");
		texto = texto.replaceAll("'", " ");
		texto = texto.replaceAll("¡", " ");
		texto = texto.replaceAll("¿", " ");
		texto = texto.replaceAll("-", " ");
		texto = texto.replaceAll("_", " ");
//		texto = texto.replaceAll("(", " ");
//		texto = texto.replaceAll(")", " ");				
		
		StringTokenizer st = new StringTokenizer(texto);
								
		while ( st.hasMoreTokens() ){
			String pal = st.nextToken();
			Palabra p = new Palabra(pal);
			try {
				con.ejecutar("insert into cifrasletras values('"+p.getPalabra().toUpperCase()+"','"+p.getOrdenada().toUpperCase()+"')");
				i++;
			} catch (Exception e) {
				//System.err.println("ERROR AÑADIENDO LA PALABRA \""+pal+"\"");
			} 
		}						
	
	}
	
	private void finalizar() throws SQLException{
		System.out.println("SE HAN AÑADIDO "+i+" NUEVAS PALABRAS AL DICCIONARIO");
		
		con.ejecutar("alter index ot_cifrasletras rebuild");
		System.out.println("INDICE RECONSTRUIDO");
		con.cerrarConexion();
	}
	
	private String replacePuntos(String c){
		String resul = "";
		for (int i=0; i<c.length(); i++){
			if ( c.charAt(i) != '.' )
				resul += c.charAt(i);
		}
		return resul;
	}
	
	private String replaceInter(String c){
		String resul = "";
		for (int i=0; i<c.length(); i++){
			if ( c.charAt(i) != '?' )
				resul += c.charAt(i);
		}
		return resul;
	}
	
	private String replaceExclamacion(String c){
		String resul = "";
		for (int i=0; i<c.length(); i++){
			if ( c.charAt(i) != '!' )
				resul += c.charAt(i);
		}
		return resul;
	}

	public static void main(String[] args) throws Exception {
		
//		Palabra p = new Palabra("casa");
//		try {
//			Feeder.addPalabra(p);
//		} catch (Exception e) {
//			e.printStackTrace();
//		} 
		
		long inicio = System.currentTimeMillis();
		
		Feeder f = new Feeder();
		f.alimentar();
		
		long fin = System.currentTimeMillis();
		
		System.out.println("EL PROCESO HA TARDADO "+(fin-inicio)+"ms");
	}

}

package com.jsuarez.cifrasletras;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.SQLException;

public class Diccionario {

	public Diccionario() throws Exception{
		BufferedReader br = new BufferedReader(new FileReader("/home/jasuarez/Escritorio/feed2.txt"));
		FileWriter w = new FileWriter("/home/jasuarez/Escritorio/feed5.txt");
//		Conexion con = new Conexion();
		
		int i = 0;
		String linea = null;
		
		while ( (linea = br.readLine()) != null ){			
			
			if ( linea.startsWith("->") ){
				int x = linea.indexOf(".");
				String palabra = linea.substring(2,x);	
				
				if ( palabra.contains(", ") ){
					
					x = palabra.indexOf(", ");
					String p1 = palabra.substring(0,x);
					String p4 = null;
					try {
						String p2 = palabra.substring(x+2);
						String p3 = p1.substring(0, p1.length()-p2.length());
						p4 = p3.concat(p2); 
						
//						this.insertar(p1, con);
//						this.insertar(p4, con);
						
						this.insertar(p1, w);
						this.insertar(p4, w);
						
//						w.write("insert into palabras values('"+p1+"');\n");
//						w.write("insert into palabras values('"+p4+"');\n");
					} catch (Exception e) {
						i++;
						System.err.println(e.getMessage());
						System.err.println("error "+i);
					}
				}
				else{
					if ( palabra.contains(" (1)") || palabra.contains(" (2)") ){						
						String p5 = palabra.substring(0,palabra.length()-4);
//						this.insertar(p5, con);
						this.insertar(p5, w);
//						w.write("insert into palabras values('"+p5+"');\n");
					}
					else{
//						this.insertar(palabra, con);
						this.insertar(palabra, w);
//						w.write("insert into palabras values('"+palabra+"');\n");
					}

				}
			}
						
		}
		
		br.close();
		w.close();
//		con.cerrarConexion();
	}
	
//	private void insertar(String pl, Conexion con) throws SQLException{
	private void insertar(String pl, FileWriter w) throws SQLException{
		pl = pl.toUpperCase();
		pl = pl.replaceAll("Á", "A");
		pl = pl.replaceAll("É", "E");
		pl = pl.replaceAll("Í", "I");
		pl = pl.replaceAll("Ó", "O");
		pl = pl.replaceAll("Ú", "U");
		pl = pl.replaceAll("Ü", "U");
		Palabra p = new Palabra(pl);
		try {
//			con.ejecutar("insert into cifrasletras values('"+p.getPalabra()+"','"+p.getOrdenada()+"')");
			w.write("insert into cifrasletras values('"+p.getPalabra()+"','"+p.getOrdenada()+"');\n");
		} catch (Exception e) {

		}
	}
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		new Diccionario();
	}

}

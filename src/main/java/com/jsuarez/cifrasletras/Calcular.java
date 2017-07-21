package com.jsuarez.cifrasletras;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.naming.NamingException;

public class Calcular {

	private String letras;
	private List<String> grupos;
	//private List<String> resultados = new ArrayList<String>();
	
	public Calcular(String letras) {
		this.letras = letras;
		grupos = new ArrayList<String>();
		for (int i=0; i<letras.length(); i++){
			String c = "";
			c += letras.charAt(i);
			grupos.add(c);
		}
				
	}
	
	public void getGrupos(int n){
		IteradorCombinacion it = new IteradorCombinacion(grupos, n);
		Iterator s = it.iterator();		

		while (s.hasNext()) {

			List<String> listares = (List<String>) s.next();
			String p = "";
			
			for (int i = 0; i < listares.size(); i++) {
				p += (String) listares.get(i);
			}
			Palabra pl = new Palabra(p);					
			
			Buscar b = new Buscar(pl.getOrdenada(), String.valueOf(n));
			b.start();
			//resultados.add(pl.getOrdenada());

		}
	}
	
	public void existe(String palabra) throws SQLException, NamingException{
		Conexion con = new Conexion();
		ResultSet rs = con.ejecutarQuery("select palabra from cifrasletras where contains(ordenada,'"+palabra.toUpperCase()+"') > 0");
		while ( rs.next() ){
			System.out.println("PALABRA: "+rs.getString(1));
		}
		rs.close();
		con.cerrarConexion();
	}

	/**
	 * @param args
	 * @throws NamingException 
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException, NamingException {
		
//		long inicio = System.currentTimeMillis();
		
		Calcular c = new Calcular(args[0]);		
		c.getGrupos(9);
		c.getGrupos(8);
		c.getGrupos(7);
		c.getGrupos(6);	
		c.getGrupos(5);
		//c.getGrupos(4);
		//c.getGrupos(3);
		
//		List<String> lista = c.getResultados();
//		for(int i=0; i<lista.size(); i++){
//			c.existe(lista.get(i));
//		}
		
//		long fin = System.currentTimeMillis();
//		System.out.println("EL PROCESO HA FINALIZADO EN "+(fin-inicio)+"ms");
	}

//	public List<String> getResultados() {
////		return resultados;
//	}

}

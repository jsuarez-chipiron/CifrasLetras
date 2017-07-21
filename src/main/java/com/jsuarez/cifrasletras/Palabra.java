package com.jsuarez.cifrasletras;

import java.util.Arrays;

public class Palabra {

	private String palabra;
	private String ordenada;

	public String getOrdenada() {
		return ordenada;		
	}

	public void setOrdenada(String ordenada) {
		this.ordenada = ordenada;
	}

	public Palabra(String palabra) {		
		this.palabra = palabra;
		ordenada = this.ordenarPalabra();
	}

	public String getPalabra() {
		return palabra;
	}

	public void setPalabra(String palabra) {
		this.palabra = palabra;
	}
	
	private String ordenarPalabra(){
		char[] cars = new char[palabra.length()];
		for (int i=0; i< palabra.length(); i++){
			cars[i] = palabra.charAt(i);
		}
		Arrays.sort(cars);
		String resul = "";
		for (int i=0; i< palabra.length(); i++){
			resul += cars[i];
		}
		return resul;
	}
	
	public static void main(String[] args){
		Palabra p = new Palabra("jazmin");
		System.out.println(p.getPalabra());
		System.out.println(p.getOrdenada());
	}
	
}

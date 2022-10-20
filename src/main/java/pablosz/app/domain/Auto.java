package pablosz.app.domain;

import pablosz.app.persistance.ann.Persistable;

public class Auto {
	@Persistable
	public String marca;
	
	@Persistable
	public String modelo;

	public Auto(String marca, String modelo) {
		super();
		this.marca = marca;
		this.modelo = modelo;
	}
	
}

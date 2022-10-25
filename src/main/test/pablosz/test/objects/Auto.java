package pablosz.test.objects;

import lombok.Getter;
import lombok.Setter;
import pablosz.app.persistance.ann.NotPersistable;
import pablosz.app.persistance.ann.Persistable;

public class Auto {
    @Persistable
    String marca;

    @Persistable
    String modelo;

    @NotPersistable
    String numeroDeSerie;

    public Auto(String marca, String modelo) {
        super();
        this.marca = marca;
        this.modelo = modelo;
        this.numeroDeSerie = marca + "-" + modelo;
    }

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getNumeroDeSerie() {
		return numeroDeSerie;
	}

	public void setNumeroDeSerie(String numeroDeSerie) {
		this.numeroDeSerie = numeroDeSerie;
	}

    
}

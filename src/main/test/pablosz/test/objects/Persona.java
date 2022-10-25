package pablosz.test.objects;

import lombok.Getter;
import lombok.Setter;
import pablosz.app.persistance.ann.NotPersistable;
import pablosz.app.persistance.ann.Persistable;

public class Persona {

    @Persistable
    public String nombre;

    @Persistable
    public String apellido;

    @Persistable
    public int edad;

    @NotPersistable
    public int altura;

    @Persistable
    Auto auto;

    public Persona(String nombre, String apellido, int edad, int altura) {
        super();
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.altura = altura;
        this.auto = new Auto("Volkswagen", "Golf");
    }

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public Auto getAuto() {
		return auto;
	}

	public void setAuto(Auto auto) {
		this.auto = auto;
	}

}

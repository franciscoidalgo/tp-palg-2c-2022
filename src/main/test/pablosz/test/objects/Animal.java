package pablosz.test.objects;
import pablosz.app.persistance.ann.NotPersistable;
import pablosz.app.persistance.ann.Persistable;

@NotPersistable
public class Animal {

    @Persistable
    String nombre;

	public Animal(String nombre) {
		super();
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
    
}

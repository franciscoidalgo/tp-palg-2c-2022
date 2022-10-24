package pablosz.test.objects;

import lombok.Getter;
import lombok.Setter;
import pablosz.app.persistance.ann.NotPersistable;
import pablosz.app.persistance.ann.Persistable;

@Getter
@Setter
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

}

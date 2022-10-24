package pablosz.test.objects;

import lombok.Getter;
import lombok.Setter;
import pablosz.app.persistance.ann.NotPersistable;
import pablosz.app.persistance.ann.Persistable;

@Getter
@Setter
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

}

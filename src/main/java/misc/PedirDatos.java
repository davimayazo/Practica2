package misc;

import java.io.Serializable;

/**
 * Created by david on 08/03/2017.
 */
public class PedirDatos implements Serializable {

    private static final long serialVersionUID = 9215384449310481608L;
    private MostrarDatos mostrar;
    private LeerDatos leer;

    public PedirDatos() {
        super();
        mostrar = new MostrarDatos();
        leer = new LeerDatos();
    }

    public String pedirDatos(String dato) {
        mostrar.mostrarDatos("Introduce " + dato + ": ");

        return leer.leerDatos();
    }
}

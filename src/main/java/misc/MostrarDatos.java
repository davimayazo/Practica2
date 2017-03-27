package misc;

import java.io.Serializable;

/**
 * Created by david on 08/03/2017.
 */
public class MostrarDatos implements Serializable {

    private static final long serialVersionUID = 2486731756538378422L;

    public MostrarDatos() {
        super();
    }

    public void mostrarDatos(Object dato) {
        System.out.print(dato);
    }
}

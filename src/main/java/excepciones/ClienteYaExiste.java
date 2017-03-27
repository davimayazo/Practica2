package excepciones;

import misc.Mensaje;

/**
 * Created by david on 26/03/2017.
 */
public class ClienteYaExiste extends Exception {

    public ClienteYaExiste() {
        super(Mensaje.CLIENTE_EXISTE.getDescripcion());
    }
}

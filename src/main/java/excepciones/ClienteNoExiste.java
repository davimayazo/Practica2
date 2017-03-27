package excepciones;

import misc.Mensaje;

/**
 * Created by david on 26/03/2017.
 */
public class ClienteNoExiste extends Exception {

    public ClienteNoExiste() { super(Mensaje.CLIENTE_NO_EXISTE.getDescripcion()); }
}

package excepciones;

import misc.Mensaje;

/**
 * Created by david on 27/03/17.
 */
public class FacturaNoExiste extends Exception {

    public FacturaNoExiste() { super(Mensaje.FACTURA_NO_EXISTE.getDescripcion()); }
}

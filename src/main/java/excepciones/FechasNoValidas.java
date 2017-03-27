package excepciones;

import misc.Mensaje;

/**
 * Created by david on 27/03/17.
 */
public class FechasNoValidas extends Exception{

    public FechasNoValidas() { super(Mensaje.FECHAS_NO_VALIDAS.getDescripcion()); }
}

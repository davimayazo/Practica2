package clientes;

import facturas.Tarifa;

import java.io.Serializable;

/**
 * Created by david on 08/03/2017.
 */
public class Empresa extends Cliente implements Serializable {

    private static final long serialVersionUID = 8276823791230187049L;

    public Empresa() {
        super();
    }

    public Empresa(String nif, String nombre, Direccion direccion, String correo, Tarifa tarifa) {
        super(nif, nombre, direccion, correo, tarifa);
    }

}

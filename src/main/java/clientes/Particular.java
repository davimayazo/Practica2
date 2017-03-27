package clientes;

import facturas.Tarifa;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by david on 08/03/2017.
 */
public class Particular extends Cliente implements Serializable {

    private static final long serialVersionUID = -4732433821236610207L;
    private String apellidos;

    public Particular() {
        super();
    }

    public Particular(String nif, String nombre, String apellidos, Direccion direccion, String correo, Tarifa tarifa) {
        super(nif, nombre, direccion, correo, tarifa);
        this.apellidos = apellidos;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Particular that = (Particular) o;

        return apellidos.equals(that.apellidos);
    }

    @Override
    public String toString() {
        StringBuilder cliente = new StringBuilder();
        cliente.append("NIF: " + getNif() + "\n");
        cliente.append("Nombre: " + getNombre() + " " + getApellidos() + "\n");
        cliente.append("Dirección: " + getDireccion() + "\n");
        cliente.append("Fecha de alta: " + getFecha().get(Calendar.DAY_OF_MONTH) + "/" + (getFecha().get(Calendar.MONTH) + 1) + "/" + getFecha().get(Calendar.YEAR) + "\n");
        cliente.append("Tarifa aplicada: " + getTarifa() + "\n");

        return cliente.toString();
    }

}

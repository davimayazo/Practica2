package clientes;

import facturas.Tarifa;
import interfaces.Fecha;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by david on 08/03/2017.
 */
public abstract class Cliente implements Fecha, Serializable {

    private static final long serialVersionUID = -991923246874114116L;
    private String nif;
    private String nombre;
    private Direccion direccion;
    private String correo;
    private Calendar fechaDeAlta;
    private Tarifa tarifa;

    public Cliente() {
        super();
    }

    public Cliente(String nif, String nombre, Direccion direccion, String correo, Tarifa tarifa) {
        super();
        this.nif = nif;
        this.nombre = nombre;
        this.direccion = direccion;
        this.correo = correo;
        this.tarifa = tarifa;
        this.fechaDeAlta = new GregorianCalendar();
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Calendar getFecha() {
        return fechaDeAlta;
    }

    public void setFecha(Calendar fechaDeAlta) {
        this.fechaDeAlta = fechaDeAlta;
    }

    public Tarifa getTarifa() {
        return tarifa;
    }

    public void setTarifa(Tarifa tarifa) {
        this.tarifa = tarifa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cliente cliente = (Cliente) o;

        if (!nif.equals(cliente.nif)) return false;
        if (!nombre.equals(cliente.nombre)) return false;
        if (!direccion.equals(cliente.direccion)) return false;
        if (!correo.equals(cliente.correo)) return false;
        if (!fechaDeAlta.equals(cliente.fechaDeAlta)) return false;
        return tarifa.equals(cliente.tarifa);
    }

    @Override
    public String toString() {
        StringBuilder cliente = new StringBuilder();
        cliente.append("NIF: " + nif + "\n");
        cliente.append("Nombre: " + nombre + "\n");
        cliente.append("Dirección: " + direccion + "\n");
        cliente.append("Fecha de alta: " + fechaDeAlta.get(Calendar.DAY_OF_MONTH) + "/" + (fechaDeAlta.get(Calendar.MONTH) + 1) + "/" + fechaDeAlta.get(Calendar.YEAR) + "\n");
        cliente.append("Tarifa: " + tarifa + "\n");

        return cliente.toString();
    }

}

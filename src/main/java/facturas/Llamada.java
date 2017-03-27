package facturas;

import interfaces.Fecha;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by david on 08/03/2017.
 */
public class Llamada implements Fecha, Serializable {

    private static final long serialVersionUID = -7496283446039718597L;
    private int numero;
    private Calendar fecha;
    private double duracion;

    public Llamada() {
        super();
    }

    public Llamada(int numero, double duracion) {
        super();
        this.numero = numero;
        this.fecha = new GregorianCalendar();
        this.duracion = duracion;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Calendar getFecha() {
        return fecha;
    }

    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }

    public double getDuracion() {
        return duracion;
    }

    public void setDuracion(double duracion) {
        this.duracion = duracion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Llamada llamada = (Llamada) o;

        if (numero != llamada.numero) return false;
        if (Double.compare(llamada.duracion, duracion) != 0) return false;
        return fecha.equals(llamada.fecha);
    }

    @Override
    public String toString() {
        StringBuilder llamada = new StringBuilder();
        llamada.append("Número de teléfono: " + numero + "\n");
        llamada.append("Fecha de realización: " + fecha.get(Calendar.HOUR_OF_DAY) + ":" + fecha.get(Calendar.MINUTE) + ":" + fecha.get(Calendar.SECOND));
        llamada.append(" " + fecha.get(Calendar.DAY_OF_MONTH) + "/" + (fecha.get(Calendar.MONTH) + 1) + "/" + fecha.get(Calendar.YEAR) + "\n");
        llamada.append("Duración de la llamada: " + duracion + "\n");

        return llamada.toString();
    }
}

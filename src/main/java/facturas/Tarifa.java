package facturas;

import java.io.Serializable;

/**
 * Created by david on 08/03/2017.
 */
public class Tarifa implements Serializable {

    private static final long serialVersionUID = -3729961501326051750L;
    private double precio;

    public Tarifa() {
        super();
    }

    public Tarifa(double precio) {
        super();
        this.precio = precio;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tarifa tarifa = (Tarifa) o;

        return Double.compare(tarifa.precio, precio) == 0;
    }

    @Override
    public String toString() {
        StringBuilder tarifa = new StringBuilder();
        tarifa.append("Precio: " + precio + " €/min");

        return tarifa.toString();
    }

}

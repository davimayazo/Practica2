package clientes;

import java.io.Serializable;

/**
 * Created by david on 08/03/2017.
 */
public class Direccion implements Serializable {

    private static final long serialVersionUID = 6974354670068130490L;
    private int codigoPostal;
    private String poblacion;
    private String provincia;

    public Direccion() {
        super();
    }

    public Direccion(int codigoPostal, String poblacion, String provincia) {
        super();
        this.codigoPostal = codigoPostal;
        this.poblacion = poblacion;
        this.provincia = provincia;
    }

    public int getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(int codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Direccion direccion = (Direccion) o;

        if (codigoPostal != direccion.codigoPostal) return false;
        if (!poblacion.equals(direccion.poblacion)) return false;
        return provincia.equals(direccion.provincia);
    }

    @Override
    public String toString() {
        StringBuilder direccion = new StringBuilder();
        direccion.append(codigoPostal + " " + poblacion + ", " + provincia);

        return direccion.toString();
    }

}

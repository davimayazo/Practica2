package facturas;

import interfaces.Fecha;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by david on 08/03/2017.
 */
public class Factura implements Fecha, Serializable {

    private static final long serialVersionUID = -3832643076891665464L;
    private Integer codigoFactura;
    private Tarifa tarifaAplicada;
    private Calendar fechaEmision;
    private Calendar[] periodoFacturacion = new Calendar[2];
    private double importe;

    public Factura() {
        super();
    }

    public Factura(Integer codigoFactura, Tarifa tarifaAplicada, Calendar fechaInicio, Calendar fechaFinal, double importe) {
        this.codigoFactura = codigoFactura;
        this.tarifaAplicada = tarifaAplicada;
        this.fechaEmision = new GregorianCalendar();
        this.periodoFacturacion[0] = fechaInicio;
        this.periodoFacturacion[1] = fechaFinal;
        this.importe = importe;
    }

    public Factura(Factura factura) {
        this.codigoFactura = factura.codigoFactura;
        this.tarifaAplicada = factura.tarifaAplicada;
        this.fechaEmision = factura.fechaEmision;
        this.periodoFacturacion = factura.periodoFacturacion;
        this.importe = factura.importe;
    }

    public int getCodigoFactura() {
        return codigoFactura;
    }

    public void setCodigoFactura(Integer codigoFactura) {
        this.codigoFactura = codigoFactura;
    }

    public Tarifa getTarifaAplicada() {
        return tarifaAplicada;
    }

    public void setTarifaAplicada(Tarifa tarifaAplicada) {
        this.tarifaAplicada = tarifaAplicada;
    }

    public Calendar getFecha() {
        return fechaEmision;
    }

    public void setFecha(Calendar fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Calendar[] getPeriodoFacturacion() {
        return periodoFacturacion;
    }

    public void setPeriodoFacturacion(Calendar[] periodoFacturacion) {
        this.periodoFacturacion = periodoFacturacion;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Factura factura = (Factura) o;

        if (codigoFactura != factura.codigoFactura) return false;
        if (Double.compare(factura.importe, importe) != 0) return false;
        if (!tarifaAplicada.equals(factura.tarifaAplicada)) return false;
        if (!fechaEmision.equals(factura.fechaEmision)) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(periodoFacturacion, factura.periodoFacturacion);
    }

    @Override
    public String toString() {
        StringBuilder factura = new StringBuilder();
        factura.append("Código de factura: " + codigoFactura + "\n");
        factura.append("Tarifa aplicada: " + tarifaAplicada + "\n");
        factura.append("Fecha de emisión: " + fechaEmision.get(Calendar.DAY_OF_MONTH) + "/" + (fechaEmision.get(Calendar.MONTH) + 1) + "/" + fechaEmision.get(Calendar.YEAR) + "\n");
        factura.append("Periodo de facturación: " + periodoFacturacion[0].get(Calendar.DAY_OF_MONTH) + "/" + (periodoFacturacion[0].get(Calendar.MONTH) + 1) + "/" + periodoFacturacion[0].get(Calendar.YEAR));
        factura.append(" a " + periodoFacturacion[1].get(Calendar.DAY_OF_MONTH) + "/" + (periodoFacturacion[1].get(Calendar.MONTH) + 1) + "/" + periodoFacturacion[1].get(Calendar.YEAR) + "\n");
        factura.append("Importe de la factura: " + importe + "€\n");

        return factura.toString();
    }
}

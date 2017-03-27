package gestion;

import clientes.Cliente;
import excepciones.ClienteNoExiste;
import excepciones.ClienteYaExiste;
import excepciones.FechasNoValidas;
import facturas.Factura;
import facturas.Llamada;
import facturas.Tarifa;
import interfaces.Fecha;

import java.io.*;
import java.util.*;

/**
 * Created by david on 16/03/2017.
 */
public class Gestion implements Serializable {

    private static TreeMap<String, Cliente> clientes;
    private static String ficheroClientes = "Clientes.bin";
    private static TreeMap<String, HashSet<Llamada>> llamadas;
    private static String ficheroLlamadas = "Llamadas.bin";
    private static TreeMap<String, HashSet<Integer>> facturasCliente;
    private static String ficheroFacturasCodigo = "FacturasCliente.bin";
    private static TreeMap<Integer, Factura> facturasCodigo;
    private static String ficheroFacturasCliente = "FacturasCodigo.bin";
    private static Integer codigoFactura = 0;

    public Gestion() {
        super();
        clientes = new TreeMap<String, Cliente>();
        llamadas = new TreeMap<String, HashSet<Llamada>>();
        facturasCliente = new TreeMap<String, HashSet<Integer>>();
        facturasCodigo = new TreeMap<Integer, Factura>();
    }

    public void añadirCliente(Cliente cliente) throws ClienteYaExiste {
        if (clientes.containsKey(cliente.getNif()))
            throw new ClienteYaExiste();

        clientes.put(cliente.getNif(), cliente);
    }

    public boolean borrarCliente(String nif) throws ClienteNoExiste {
        if(!clientes.isEmpty()) {
            if(!clientes.containsKey(nif)) {
                throw new ClienteNoExiste();
            }
            clientes.remove(nif);
            return true;
        }

        return false;
    }

    public boolean modificarTarifa(String nif, Tarifa tarifa) {
        if(!clientes.isEmpty()) {
            if(clientes.containsKey(nif)) {
                clientes.get(nif).setTarifa(tarifa);
                return true;
            }
        }

        return false;
    }

    public Cliente recuperarDatosCliente(String nif) {
        if(!clientes.isEmpty()) {
            if(clientes.containsKey(nif)) {
                return clientes.get(nif);
            }
        }

        return null;
    }

    public HashSet<String> recuperarListaClientes() {
        if(!clientes.isEmpty())
            return new HashSet<String>(clientes.keySet());

        return null;
    }

    public HashSet<String> recuperarListaClientesEntreFechas(Calendar fechaInicio, Calendar fechaFinal) throws FechasNoValidas{
        if (fechaInicio.after(fechaFinal)) {
            throw new FechasNoValidas();
        }
        if (!clientes.isEmpty()) {
            HashSet<String> clientesEntreFechas = new HashSet<String>();
            for (Cliente cliente : clientes.values()) {
                if (cliente.getFecha().after(fechaInicio) && cliente.getFecha().before(fechaFinal)) {
                    clientesEntreFechas.add(cliente.getNif());
                }
            }
            return clientesEntreFechas;
        }

        return null;
    }

    public Collection<Cliente> recuperarClientesEntreFechas(Calendar fechaInicio, Calendar fechaFinal) {
        Collection<Cliente> listaClientes = new HashSet<Cliente>(entreFechas(clientes.values(), fechaInicio, fechaFinal));

        return listaClientes;
    }

    public boolean añadirLlamada(String nif, Llamada llamada) {
        if (clientes.containsKey(nif)) {
            if (llamadas.containsKey(nif))
                llamadas.get(nif).add(llamada);
            else {
                HashSet<Llamada> listaLlamadas = new HashSet<Llamada>();
                listaLlamadas.add(llamada);
                llamadas.put(nif, listaLlamadas);
            }

            return true;
        }

        return false;
    }

    public HashSet<Llamada> recuperarLlamadasCliente(String nif) {
        if(!llamadas.isEmpty())
            if(llamadas.containsKey(nif))
                return llamadas.get(nif);

        return null;
    }

    public HashSet<Llamada> recuperarListaLlamadasEntreFechas(String nif, Calendar fechaInicio, Calendar fechaFinal) throws FechasNoValidas{
        if (fechaInicio.after(fechaFinal)) {
            throw new FechasNoValidas();
        }
        if (!llamadas.isEmpty()) {
            HashSet<Llamada> llamadasEntreFechas = new HashSet<Llamada>();
            for (Llamada llamada : llamadas.get(nif))
                if (llamada.getFecha().after(fechaInicio) && llamada.getFecha().before(fechaFinal))
                    llamadasEntreFechas.add(llamada);

            return llamadasEntreFechas;
        }

        return null;
    }

    public boolean emitirFactura(String nif) {
        if(llamadas.containsKey(nif)) {
            Calendar fechaActual = new GregorianCalendar();
            Calendar fechaInicio = fechaActual;
            fechaInicio.set(Calendar.MONTH, Calendar.MONTH - 1);
            double duracion = 0;
            try {
                for (Llamada llamada : recuperarListaLlamadasEntreFechas(nif, fechaInicio, fechaActual))
                    duracion += llamada.getDuracion();
            } catch (FechasNoValidas e) {
                System.out.println(e.getMessage());
            }
            codigoFactura++;
            Tarifa tarifa = recuperarDatosCliente(nif).getTarifa();
            double importe = duracion * tarifa.getPrecio();
            Factura factura = new Factura(codigoFactura, tarifa, fechaInicio, fechaActual, importe);
            if(facturasCliente.containsKey(nif))
                facturasCliente.get(nif).add(codigoFactura);
            else {
                HashSet<Integer> listaFacturas = new HashSet<Integer>();
                listaFacturas.add(codigoFactura);
                facturasCliente.put(nif, listaFacturas);
            }
            facturasCodigo.put(codigoFactura, factura);

            return true;
        }

        return false;
    }

    public HashSet<Integer> recuperarFacturasCliente(String nif) {
        if(!facturasCliente.isEmpty())
            if(facturasCliente.containsKey(nif))
                return new HashSet<Integer>(facturasCliente.get(nif));

        return null;
    }

    public Factura recuperarDatosFactura(Integer codigo) {
        if(!facturasCodigo.isEmpty())
            if(facturasCodigo.containsKey(codigo))
                return facturasCodigo.get(codigo);

        return null;
    }

    public HashSet<Integer> recuperarListaFacturasEntreFechas(String nif, Calendar fechaInicio, Calendar fechaFinal) throws FechasNoValidas{
        if (fechaInicio.after(fechaFinal)) {
            throw new FechasNoValidas();
        }
        if (!facturasCodigo.isEmpty()) {
            HashSet<Integer> facturasEntreFechas = new HashSet<Integer>();
            for (Integer codigo : facturasCliente.get(nif))
                if(facturasCodigo.get(codigo).getFecha().after(fechaInicio) && facturasCodigo.get(codigo).getFecha().before(fechaFinal))
                    facturasEntreFechas.add(codigo);

            return facturasEntreFechas;
        }

        return null;
    }

    public <T extends Fecha> Collection<T> entreFechas(Collection<T> datos, Calendar fechaInicio, Calendar fechaFinal) {
        Collection<T> conjunto = new HashSet<T>(datos);
        for(T elem : datos) {
            if(elem.getFecha().after(fechaInicio) && elem.getFecha().before(fechaFinal)) {
                conjunto.add(elem);
            }
        }

        return conjunto;
    }

    public void cargarDatos() {
        ObjectInputStream ois = null;
        try {
            try {
                FileInputStream fis;
                fis = new FileInputStream(ficheroClientes);
                ois = new ObjectInputStream(fis);
                clientes = (TreeMap<String, Cliente>) ois.readObject();
                fis = new FileInputStream(ficheroLlamadas);
                ois = new ObjectInputStream(fis);
                llamadas = (TreeMap<String, HashSet<Llamada>>) ois.readObject();
                fis = new FileInputStream(ficheroFacturasCliente);
                ois = new ObjectInputStream(fis);
                facturasCliente = (TreeMap<String, HashSet<Integer>>) ois.readObject();
                fis = new FileInputStream(ficheroFacturasCodigo);
                ois = new ObjectInputStream(fis);
                facturasCodigo = (TreeMap<Integer, Factura>) ois.readObject();
                if (!facturasCodigo.isEmpty()) {
                    codigoFactura = facturasCodigo.lastKey();
                }
            }
            finally {
                if(ois != null) ois.close();
            }
        }
        catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void guardarDatos() {
        ObjectOutputStream oos = null;
        try {
            try {
                FileOutputStream fos;
                fos = new FileOutputStream(ficheroClientes);
                oos = new ObjectOutputStream(fos);
                oos.writeObject(clientes);
                fos = new FileOutputStream(ficheroLlamadas);
                oos = new ObjectOutputStream(fos);
                oos.writeObject(llamadas);
                fos = new FileOutputStream(ficheroFacturasCliente);
                oos = new ObjectOutputStream(fos);
                oos.writeObject(facturasCliente);
                fos = new FileOutputStream(ficheroFacturasCodigo);
                oos = new ObjectOutputStream(fos);
                oos.writeObject(facturasCodigo);
            }
            finally {
                if(oos != null) oos.close();
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

}

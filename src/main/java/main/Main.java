package main;

import clientes.Cliente;
import clientes.Direccion;
import clientes.Empresa;
import clientes.Particular;
import excepciones.ClienteNoExiste;
import excepciones.ClienteYaExiste;
import excepciones.FechasNoValidas;
import facturas.Llamada;
import facturas.Tarifa;
import gestion.Gestion;
import menu.Menu;
import menu.MenuClientes;
import misc.Consola;
import misc.Mensaje;

import java.io.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by david on 08/03/2017.
 */
public class Main implements Serializable {

    private Gestion gestion = new Gestion();
    private Consola consola = new Consola();

    public static void main(String[] args) {
        new Main().start();
    }

    private Main() {
        super();
    }

    private void start() {
        load();
        Menu opcion;
        do {
            consola.mostrarDatos(Menu.getMenu());
            opcion = Menu.getOpcion(Integer.parseInt(consola.pedirDatos("una opción")));
            elegirOpcion(opcion);
        } while(opcion != Menu.SALIR);
    }

    private void elegirOpcion(Menu opcion) {
        switch (opcion) {
            case CLIENTE_AÑADIR:
                añadirCliente();
                break;
            case CLIENTE_BORRAR:
                borrarCliente();
                break;
            case CLIENTE_MODIFICAR_TARIFA:
                modificarTarifa();
                break;
            case CLIENTE_DATOS:
                recuperarDatosCliente();
                break;
            case CLIENTE_LISTA:
                recuperarListaClientes();
                break;
            case LLAMADA_AÑADIR:
                añadirLlamada();
                break;
            case LLAMADA_LISTA:
                recuperarListaLlamadas();
                break;
            case FACTURA_AÑADIR:
                emitirFactura();
                break;
            case FACTURA_CODIGO:
                recuperarFacturaPorCodigo();
                break;
            case FACTURA_LISTA:
                recuperarFacturasDeCliente();
                break;
            case CLIENTES_ENTRE_FECHAS:
                recuperarListaClientesEntreFechas();
                break;
            case LLAMADAS_ENTRE_FECHAS:
                recuperarListaLlamadasEntreFechas();
                break;
            case FACTURAS_ENTRE_FECHAS:
                recuperarListaFacturasEntreFechas();
                break;
            case SALIR:
                exit();
                break;
        }
        save();
    }

    private void elegirOpcionClientes(MenuClientes opcion) {
        switch(opcion) {
            case TIPO_EMPRESA:
                añadirEmpresa();
                break;
            case TIPO_PARTICULAR:
                añadirParticular();
                break;
            case VOLVER:
                break;
        }
    }

    /**
     * GESTIÓN DE CLIENTES
     */

    private void añadirCliente() {
        MenuClientes opcion;
        consola.mostrarDatos(MenuClientes.getMenu());
        opcion = MenuClientes.getOpcion(Integer.parseInt(consola.pedirDatos("una opción")));
        elegirOpcionClientes(opcion);
    }

    private void añadirEmpresa() {
        String nif = consola.pedirDatos("el NIF");
        String nombre = consola.pedirDatos("el nombre");
        int codigoPostal = Integer.parseInt(consola.pedirDatos("el código postal"));
        String poblacion = consola.pedirDatos("la población");
        String provincia = consola.pedirDatos("la provincia");
        Direccion direccion = new Direccion(codigoPostal, poblacion, provincia);
        String correo = consola.pedirDatos("el correo electrónico");
        Tarifa tarifa = new Tarifa(Double.parseDouble(consola.pedirDatos("la tarifa (€/min)")));
        Cliente cliente = new Empresa(nif, nombre, direccion, correo, tarifa);

        try {
            gestion.añadirCliente(cliente);
            consola.mostrarDatos(Mensaje.CLIENTE_CREADO);
        } catch(ClienteYaExiste e) {
            consola.mostrarDatos(e.getMessage());
        }
    }

    private void añadirParticular() {
        String nif = consola.pedirDatos("el NIF");
        String nombre = consola.pedirDatos("el nombre");
        String apellidos = consola.pedirDatos("los apellidos");
        int codigoPostal = Integer.parseInt(consola.pedirDatos("el código postal"));
        String poblacion = consola.pedirDatos("la población");
        String provincia = consola.pedirDatos("la provincia");
        Direccion direccion = new Direccion(codigoPostal, poblacion, provincia);
        String correo = consola.pedirDatos("el correo electrónico");
        Tarifa tarifa = new Tarifa(Double.parseDouble(consola.pedirDatos("la tarifa (€/min)")));
        Cliente cliente = new Particular(nif, nombre, apellidos, direccion, correo, tarifa);

        try {
            gestion.añadirCliente(cliente);
            consola.mostrarDatos(Mensaje.CLIENTE_CREADO);
        } catch(ClienteYaExiste e) {
            consola.mostrarDatos(e.getMessage());
        }
    }

    private void borrarCliente() {
        String nif = consola.pedirDatos("el NIF");
        try {
            if (gestion.borrarCliente(nif))
                consola.mostrarDatos(Mensaje.CLIENTE_BORRADO);
            else {
                consola.mostrarDatos(Mensaje.CLIENTES_VACÍO);
            }
        } catch(ClienteNoExiste e) {
            consola.mostrarDatos(e.getMessage());
        }
    }

    private void modificarTarifa() {
        String nif = consola.pedirDatos("el NIF");
        double precio = Double.parseDouble(consola.pedirDatos("la nueva tarifa"));
        if(gestion.modificarTarifa(nif, new Tarifa(precio)))
            consola.mostrarDatos(Mensaje.CLIENTE_MODIFICAR_TARIFA);
        else
            consola.mostrarDatos(Mensaje.CLIENTE_NO_EXISTE);
    }

    private void recuperarDatosCliente() {
        String nif = consola.pedirDatos("el NIF");
        if(gestion.recuperarDatosCliente(nif) != null)
            consola.mostrarDatos(gestion.recuperarDatosCliente(nif));
        else
            consola.mostrarDatos(Mensaje.CLIENTE_NO_EXISTE);
    }

    private void recuperarListaClientes() {
        if(gestion.recuperarListaClientes() != null) {
            consola.mostrarDatos(Mensaje.LISTA_CLIENTES);
            for(String nif : gestion.recuperarListaClientes()) {
                consola.mostrarDatos("\n" + gestion.recuperarDatosCliente(nif));
            }
        }
        else
            consola.mostrarDatos(Mensaje.CLIENTES_VACÍO);
    }

    /**
     * GESTIÓN DE LLAMADAS
     */

    private void añadirLlamada() {
        String nif = consola.pedirDatos("el NIF");
        int numero = Integer.parseInt(consola.pedirDatos("el número de teléfono"));
        double duracion = Double.parseDouble(consola.pedirDatos("la duración"));
        Llamada llamada = new Llamada(numero, duracion);
        if(gestion.añadirLlamada(nif, llamada))
            consola.mostrarDatos(Mensaje.LLAMADA_CREADO);
        else
            consola.mostrarDatos(Mensaje.CLIENTE_NO_EXISTE);

    }

    private void recuperarListaLlamadas() {
        String nif = consola.pedirDatos("el NIF");
        if(gestion.recuperarLlamadasCliente(nif) != null) {
            consola.mostrarDatos(Mensaje.LISTA_LLAMADAS);
            for (Llamada llamada : gestion.recuperarLlamadasCliente(nif))
                consola.mostrarDatos("\n" + llamada);
        }
        else
            consola.mostrarDatos(Mensaje.CLIENTE_NO_LLAMADAS);
    }

    /**
     * GESTIÓN DE FACTURAS
     */

    private void emitirFactura() {
        String nif = consola.pedirDatos("el NIF");
        if(gestion.emitirFactura(nif))
            consola.mostrarDatos(Mensaje.FACTURA_CREADO);
        else
            consola.mostrarDatos(Mensaje.CLIENTE_NO_LLAMADAS);
    }

    private void recuperarFacturaPorCodigo() {
        int codigo = Integer.parseInt(consola.pedirDatos("el código de la factura: "));
        if(gestion.recuperarDatosFactura(codigo) != null)
            consola.mostrarDatos(gestion.recuperarDatosFactura(codigo));
        else
            consola.mostrarDatos(Mensaje.FACTURA_NO_EXISTE);
    }

    private void recuperarFacturasDeCliente() {
        String nif = consola.pedirDatos("el NIF");
        if(gestion.recuperarFacturasCliente(nif) != null) {
            consola.mostrarDatos(Mensaje.LISTA_FACTURAS);
            for (Integer codigo : gestion.recuperarFacturasCliente(nif))
                consola.mostrarDatos("\n" + codigo);
        }
        else
            consola.mostrarDatos(Mensaje.CLIENTE_NO_FACTURAS);
    }

    /**
     * OTRAS OPCIONES
     */

    private void recuperarListaClientesEntreFechas() {
        String[] fechaString;
        fechaString = consola.pedirDatos("la fecha de inicio (DD/MM/AAAA)").split("/");
        Calendar fechaInicio = new GregorianCalendar();
        fechaInicio.set(Integer.parseInt(fechaString[2]), Integer.parseInt(fechaString[1]) - 1, Integer.parseInt(fechaString[0]));
        fechaString = consola.pedirDatos("la fecha de finalización (DD/MM/AAAA)").split("/");
        Calendar fechaFinal = new GregorianCalendar();
        fechaFinal.set(Integer.parseInt(fechaString[2]), Integer.parseInt(fechaString[1]) - 1, Integer.parseInt(fechaString[0]));
        try {
            if (gestion.recuperarListaClientesEntreFechas(fechaInicio, fechaFinal) != null) {
                consola.mostrarDatos(Mensaje.LISTA_CLIENTES);
                for (String cliente : gestion.recuperarListaClientesEntreFechas(fechaInicio, fechaFinal))
                    consola.mostrarDatos(gestion.recuperarDatosCliente(cliente));
            } else
                consola.mostrarDatos(Mensaje.CLIENTE_NO_ENCONTRADO);
        } catch(FechasNoValidas e) {
            consola.mostrarDatos(e.getMessage());
        }
    }

    private void recuperarListaLlamadasEntreFechas() {
        String nif = consola.pedirDatos("el NIF");
        String[] fechaString;
        fechaString = consola.pedirDatos("la fecha de inicio (DD/MM/AAAA)").split("/");
        Calendar fechaInicio = new GregorianCalendar();
        fechaInicio.set(Integer.parseInt(fechaString[2]), Integer.parseInt(fechaString[1]) - 1, Integer.parseInt(fechaString[0]));
        fechaString = consola.pedirDatos("la fecha de finalización (DD/MM/AAAA)").split("/");
        Calendar fechaFinal = new GregorianCalendar();
        fechaFinal.set(Integer.parseInt(fechaString[2]), Integer.parseInt(fechaString[1]) - 1, Integer.parseInt(fechaString[0]));
        try {
            if (gestion.recuperarListaLlamadasEntreFechas(nif, fechaInicio, fechaFinal) != null) {
                consola.mostrarDatos(Mensaje.LISTA_LLAMADAS);
                for (Llamada llamada : gestion.recuperarListaLlamadasEntreFechas(nif, fechaInicio, fechaFinal))
                    consola.mostrarDatos(llamada);
            } else
                consola.mostrarDatos(Mensaje.LLAMADA_NO_ENCONTRADO);
        } catch(FechasNoValidas e) {
            consola.mostrarDatos(e.getMessage());
        }
    }

    private void recuperarListaFacturasEntreFechas() {
        String nif = consola.pedirDatos("el NIF");
        String[] fechaString;
        fechaString = consola.pedirDatos("la fecha de inicio (DD/MM/AAAA)").split("/");
        Calendar fechaInicio = new GregorianCalendar();
        fechaInicio.set(Integer.parseInt(fechaString[2]), Integer.parseInt(fechaString[1]) - 1, Integer.parseInt(fechaString[0]));
        fechaString = consola.pedirDatos("la fecha de finalización (DD/MM/AAAA)").split("/");
        Calendar fechaFinal = new GregorianCalendar();
        fechaFinal.set(Integer.parseInt(fechaString[2]), Integer.parseInt(fechaString[1]) - 1, Integer.parseInt(fechaString[0]));
        try {
            if (gestion.recuperarListaFacturasEntreFechas(nif, fechaInicio, fechaFinal) != null) {
                consola.mostrarDatos(Mensaje.LISTA_FACTURAS);
                for (Integer codigo : gestion.recuperarListaFacturasEntreFechas(nif, fechaInicio, fechaFinal))
                    consola.mostrarDatos(gestion.recuperarDatosFactura(codigo));
            } else
                consola.mostrarDatos(Mensaje.FACTURA_NO_ENCONTRADO);
        } catch(FechasNoValidas e) {
            consola.mostrarDatos(e.getMessage());
        }
    }

    /**
     * GESTIÓN DE DATOS
     */

    private void load() {
        gestion.cargarDatos();
    }

    private void save() {
        gestion.guardarDatos();
    }

    private void exit() {
        consola.mostrarDatos(Mensaje.SALIR);
    }

}

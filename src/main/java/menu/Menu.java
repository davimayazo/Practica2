package menu;

import misc.Mensaje;

/**
 * Created by david on 08/03/2017.
 */
public enum Menu {

    // Salir
    SALIR("Salir"),

    // Opciones de clientes
    CLIENTE_AÑADIR("Dar de alta un nuevo cliente"),
    CLIENTE_BORRAR("Borrar un cliente"),
    CLIENTE_MODIFICAR_TARIFA("Cambiar la tarifa de un cliente"),
    CLIENTE_DATOS("Recuperar los datos de un cliente a partir de su NIF"),
    CLIENTE_LISTA("Recuperar el listado de todos los clientes"),

    // Opciones de llamadas
    LLAMADA_AÑADIR("Dar de alta una llamada"),
    LLAMADA_LISTA("Listar todas las llamadas de un cliente"),

    // Opiones de facturas
    FACTURA_AÑADIR("Emitir una factura para un cliente"),
    FACTURA_CODIGO("Recuperar los datos de una factura a partir de su codigo"),
    FACTURA_LISTA("Recuperar todas las facturas de un cliente"),

    // Opciones entre fechas
    CLIENTES_ENTRE_FECHAS("Mostrar un listado de clientes que fueron dados de alta entre dos fechas"),
    LLAMADAS_ENTRE_FECHAS("Mostrar un listado de llamadas de un cliente que fueron realizadas entre dos fechas"),
    FACTURAS_ENTRE_FECHAS("Mostrar un listado de facturas de un cliente emitidas entre dos fechas");

    private String descripcion;

    private Menu(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public static Menu getOpcion(int posicion) throws ArrayIndexOutOfBoundsException {
        try {
            return values()[posicion];
        }
        catch(ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        return SALIR;
    }

    public static String getMenu() {
        StringBuilder menu = new StringBuilder();
        menu.append(Mensaje.MENU_GESTION);
        for(Menu opcion: Menu.values()) {
            menu.append(opcion.ordinal());
            menu.append(".- ");
            menu.append(opcion.getDescripcion());
            menu.append("\n");
        }

        return menu.toString();
    }
}

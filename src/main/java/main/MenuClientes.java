package main;

import interfaces.Descripcion;
import misc.Mensaje;

/**
 * Created by david on 08/03/2017.
 */
public enum MenuClientes implements Descripcion {

    TIPO_EMPRESA("Añadir empresa"),
    TIPO_PARTICULAR("Añadir particular"),
    VOLVER("Volver");

    private String descripcion;

    private MenuClientes(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public static MenuClientes getOpcion(int posicion) {
        return values()[posicion];
    }

    public static String getMenu() {
        StringBuilder menu = new StringBuilder();
        menu.append(Mensaje.MENU_CLIENTES);
        for(MenuClientes opcion: MenuClientes.values()) {
            menu.append(opcion.ordinal());
            menu.append(".- ");
            menu.append(opcion.getDescripcion());
            menu.append("\n");
        }

        return menu.toString();
    }
}

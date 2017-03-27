package misc;

/**
 * Created by david on 08/03/2017.
 */
public enum Mensaje {

    // Menú
    MENU_GESTION("\nMENÚ DE GESTIÓN DE DATOS\n"),
    MENU_CLIENTES("\nMENÚ DE CREACIÓN DE CLIENTES\n"),

    // Resultado de operaciones
    CLIENTE_CREADO("\nCLIENTE CREADO CON ÉXITO\n"),
    CLIENTE_BORRADO("\nCLIENTE BORRADO CON ÉXITO\n"),
    CLIENTE_MODIFICAR_TARIFA("\nTARIFA MODIFICADA CON ÉXITO\n"),
    CLIENTE_EXISTE("\nEL CLIENTE YA EXISTE\n"),
    CLIENTE_NO_EXISTE("\nEL CLIENTE NO EXISTE\n"),
    CLIENTE_NO_ENCONTRADO("\nNO SE HA ENCONTRADO NINGÚN CLIENTE\n"),
    CLIENTE_NO_LLAMADAS("\nEL CLIENTE NO HA REALIZADO NINGUNA LLAMADA\n"),
    CLIENTE_NO_FACTURAS("\nEL CLIENTE NO TIENE NINGUNA FACTURA"),
    LLAMADA_CREADO("\nLLAMADA CREADA CON ÉXITO\n"),
    LLAMADA_NO_ENCONTRADO("\nNO SE HA ENCONTRADO NINGUNA LLAMADA\n"),
    FACTURA_CREADO("\nFACTURA CREADA CON ÉXITO\n"),
    FACTURA_NO_EXISTE("\nLA FACTURA NO EXISTE\n"),
    FACTURA_NO_ENCONTRADO("\nNO SE HA ENCONTRADO NINGUNA FACTURA\n"),
    LISTA_CLIENTES("\nLISTA DE CLIENTES:\n"),
    LISTA_LLAMADAS("\nLISTA DE LLAMADAS:\n"),
    LISTA_FACTURAS("\nLISTA DE FACTURAS:\n"),
    CLIENTES_VACÍO("\nLA BASE DE DATOS DE CLIENTES ESTÁ VACÍA\n"),
    LLAMADAS_VACÍO("\nLA BASE DE DATOS DE LLAMADAS ESTÁ VACÍA\n"),
    FACTURAS_VACÍO("\nLA BASE DE DATOS DE FACTURAS ESTÁ VACÍA\n"),
    FECHAS_NO_VALIDAS("\nLA FECHA DE INICIO NO PUEDE SER POSTERIOR A LA FECHA DE FIN\n"),

    // Mensajes del sistema
    SALIR("\nHASTA LUEGO MARICARMEN\n");

    private String descripcion;

    private Mensaje(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return getDescripcion();
    }

}

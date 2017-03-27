package misc;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * Created by david on 15/03/2017.
 */
public class Consola {

    private Scanner scanner;
    private PrintStream syso;

    public Consola() {
        super();
        scanner = new Scanner(System.in);
        syso = System.out;
    }

    public void mostrarDatos(Object dato) {
        syso.print(dato);
    }

    public String pedirDatos(String dato) {
        mostrarDatos("Introduce " + dato + ": ");
        return scanner.nextLine();
    }
}

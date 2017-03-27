package misc;

import java.io.Serializable;
import java.util.Scanner;

/**
 * Created by david on 08/03/2017.
 */
public class LeerDatos implements Serializable {

    private static final long serialVersionUID = 8599397788324302742L;
    private Scanner scanner;

    public LeerDatos() {
        super();
        scanner = new Scanner(System.in);
    }

    public String leerDatos() {
        return scanner.nextLine();
    }
}

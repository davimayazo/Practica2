package interfaces;

import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;

/**
 * Created by david on 16/03/2017.
 */
public class FechaGenerica<T extends Fecha> {

    public FechaGenerica() {
        super();
    }

    public Collection<T> getGeneracidad(Collection<T> generacidad, Calendar fechaInicio, Calendar fechaFinal) {
        Collection<T> generico = new HashSet<T>(generacidad);
        for(T elem : generacidad) {
            if(elem.getFecha().after(fechaInicio) && elem.getFecha().before(fechaFinal)) {
                generico.add(elem);
            }
        }

        return generico;
    }
}

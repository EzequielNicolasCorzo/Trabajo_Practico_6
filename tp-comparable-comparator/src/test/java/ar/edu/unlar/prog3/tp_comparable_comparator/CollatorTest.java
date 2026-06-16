package ar.edu.unlar.prog3.tp_comparable_comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.Collator;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import org.junit.jupiter.api.Test;

import ar.edu.unlar.prog3.tp_comparable_comparator.domain.Estudiante;

public class CollatorTest {

    @Test
    public void testOrdenamientoConTildesYEnies() {
        //Configuramos el Collator igual que en nuestro Service
       Collator collator = Collator.getInstance(Locale.of("es", "AR"));
        collator.setStrength(Collator.PRIMARY);

        //Creamos una lista desordenada con casos conflictivos
        List<Estudiante> lista = Arrays.asList(
                new Estudiante("1", "Nuñez", 9.0, 20, 5),
                new Estudiante("2", "Álvarez", 8.0, 20, 5),
                new Estudiante("3", "Molina", 7.0, 20, 5),
                new Estudiante("4", "Benítez", 6.0, 20, 5)
        );

        // 3. Ordenamos usando el Collator
        lista.sort(Comparator.comparing(Estudiante::getNombre, collator));

        // 4. Verificamos que el orden es el correcto para el español
        assertEquals("Álvarez", lista.get(0).getNombre());
        assertEquals("Benítez", lista.get(1).getNombre());
        assertEquals("Molina", lista.get(2).getNombre());
        assertEquals("Nuñez", lista.get(3).getNombre());
    }
}

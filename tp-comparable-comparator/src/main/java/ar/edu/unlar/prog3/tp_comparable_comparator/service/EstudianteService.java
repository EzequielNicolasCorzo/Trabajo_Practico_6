package ar.edu.unlar.prog3.tp_comparable_comparator.service;

import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unlar.prog3.tp_comparable_comparator.domain.Estudiante;
import ar.edu.unlar.prog3.tp_comparable_comparator.repository.EstudianteRepository;

@Service
public class EstudianteService {

    private final EstudianteRepository repository;

    public EstudianteService(EstudianteRepository repository) {
        this.repository = repository;
    }

    public List<Estudiante> ordenarEstudiantes(String sortBy, String order) {
        List<Estudiante> lista = repository.obtenerTodos();
        Comparator<Estudiante> comparator;

        // Seleccion procedural (Anti-patrón que corregiremos en el Ejercicio 8)
        switch (sortBy) {
            case "edad":
                comparator = Comparator.comparing(Estudiante::getEdad);
                break;
            case "nombre":
                comparator = Comparator.comparing(Estudiante::getNombre);
                break;
            case "materiasAprobadas":
                comparator = Comparator.comparing(Estudiante::getCantidadMateriasAprobadas);
                break;
            case "legajo":
                comparator = Comparator.comparing(Estudiante::getLegajo);
                break;
            case "promedio":
            default:
                comparator = Comparator.comparing(Estudiante::getPromedio);
                break;
        }

        // Tie-breaker por defecto exigido por el TP
        comparator = comparator.thenComparing(Estudiante::getLegajo);

        // Invertir si el orden es descendente
        if ("desc".equalsIgnoreCase(order)) {
            comparator = comparator.reversed();
        }

        lista.sort(comparator);
        return lista;
    }
}

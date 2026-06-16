package ar.edu.unlar.prog3.tp_comparable_comparator.service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import ar.edu.unlar.prog3.tp_comparable_comparator.domain.Estudiante;
import ar.edu.unlar.prog3.tp_comparable_comparator.repository.EstudianteRepository;

@Service
public class EstudianteService {

    private final EstudianteRepository repository;
    
    // Nuestro mapa de estrategias
    private final Map<String, Comparator<Estudiante>> estrategiasDeOrdenamiento;

    public EstudianteService(EstudianteRepository repository) {
        this.repository = repository;
        
        // Inicializamos el Map con las estrategias de comparación (Patron Strategy)
        estrategiasDeOrdenamiento = new HashMap<>();
        estrategiasDeOrdenamiento.put("edad", Comparator.comparing(Estudiante::getEdad));
        estrategiasDeOrdenamiento.put("nombre", Comparator.comparing(Estudiante::getNombre));
        estrategiasDeOrdenamiento.put("materiasAprobadas", Comparator.comparing(Estudiante::getCantidadMateriasAprobadas));
        estrategiasDeOrdenamiento.put("legajo", Comparator.comparing(Estudiante::getLegajo));
        estrategiasDeOrdenamiento.put("promedio", Comparator.comparing(Estudiante::getPromedio));
    }

    public List<Estudiante> ordenarEstudiantes(String sortBy, String order) {
        List<Estudiante> lista = repository.obtenerTodos();
        
        //Buscar el comparator en el mapa de forma directa
        Comparator<Estudiante> comparator = estrategiasDeOrdenamiento.get(sortBy);

        //Si no existe en el mapa, lanzamos una excepción
        if (comparator == null) {
            throw new IllegalArgumentException("Criterio de ordenamiento invalido: " + sortBy);
        }

        //Desempate por defecto
        comparator = comparator.thenComparing(Estudiante::getLegajo);

        //Aplicar orden inverso si se solicita "desc"
        if ("desc".equalsIgnoreCase(order)) {
            comparator = comparator.reversed();
        }

        //Ordenar la lista delegando la ejecución a la estrategia seleccionada
        lista.sort(comparator);
        return lista;
    }
}
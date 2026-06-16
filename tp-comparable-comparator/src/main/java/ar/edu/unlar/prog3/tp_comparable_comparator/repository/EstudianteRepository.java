package ar.edu.unlar.prog3.tp_comparable_comparator.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import ar.edu.unlar.prog3.tp_comparable_comparator.domain.Estudiante;
import jakarta.annotation.PostConstruct;

@Repository
public class EstudianteRepository {

    private final List<Estudiante> estudiantes = new ArrayList<>();

    @PostConstruct
    public void init() {
        // 10 estudiantes con empates intencionales en promedio, materias y edad
        estudiantes.add(new Estudiante("LU-001", "Ana Gomez", 8.5, 22, 15));
        estudiantes.add(new Estudiante("LU-002", "Juan Perez", 7.2, 24, 10));
        estudiantes.add(new Estudiante("LU-003", "Maria Lopez", 9.1, 21, 18));
        estudiantes.add(new Estudiante("LU-004", "Carlos Ruiz", 6.8, 25, 8));
        estudiantes.add(new Estudiante("LU-005", "Luis Torres", 8.5, 23, 14)); // Empate promedio con LU-001
        estudiantes.add(new Estudiante("LU-006", "Belen Arce", 9.1, 21, 18)); // Empate multiple con LU-003
        estudiantes.add(new Estudiante("LU-007", "Pedro Diaz", 5.5, 26, 5));
        estudiantes.add(new Estudiante("LU-008", "Sofia Paz", 7.2, 20, 10)); // Empate promedio y materias con LU-002
        estudiantes.add(new Estudiante("LU-009", "Diego Luna", 8.9, 22, 17));
        estudiantes.add(new Estudiante("LU-010", "Gaston Soler", 6.0, 24, 6));
        // Estudiantes de prueba con tildes en el apellido
        estudiantes.add(new Estudiante("LU-011", "Álvarez Ana", 8.0, 22, 10));
        estudiantes.add(new Estudiante("LU-012", "Benítez Luis", 7.5, 23, 11));
        estudiantes.add(new Estudiante("LU-013", "Nuñez Carla", 9.0, 21, 15));
    }

    public List<Estudiante> obtenerTodos() {
        // Devolvemos una copia de la lista para no modificar la original al ordenar
        return new ArrayList<>(estudiantes);
    }
}
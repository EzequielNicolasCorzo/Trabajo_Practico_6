package ar.edu.unlar.prog3.tp_comparable_comparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ar.edu.unlar.prog3.tp_comparable_comparator.domain.Estudiante;

@SpringBootApplication
public class TpComparableComparatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(TpComparableComparatorApplication.class, args);
		
		//Metodo de prueba en main
		List<Estudiante> lista = new ArrayList<>(); //Crear lista de estudiantes
		
		//Instanciar 5 estudiantes a la lista estudiantes
		lista.add(new Estudiante("LU-2024-001", "Ana Gomez", 8.5, 22, 15));
        lista.add(new Estudiante("LU-2024-002", "Juan Perez", 7.2, 24, 10));
        lista.add(new Estudiante("LU-2024-003", "Maria Lopez", 9.1, 21, 18));
        lista.add(new Estudiante("LU-2024-004", "Carlos Ruiz", 6.8, 25, 8));
        lista.add(new Estudiante("LU-2024-005", "Luis Torres", 8.5, 23, 14));
		
		//Mostrar la lista antes y después de ordenar, con promedios en orden decreciente
		System.out.println("Lista original:");
		for (Estudiante e : lista) {
			System.out.println(e);
		}
		
		//Ordenar lista
		Collections.sort(lista);
		
		System.out.println("Lista ordenada por promedio (descendente):");
		for (Estudiante e : lista) {
			System.out.println(e);
		}
	}
	
	
	//	Ya no da error de compilacion
	/*	Error de compilacion proveniente de la linea 27: Collections.sort(lista);
		The method sort(List<T>) in the type Collections is not applicable 
		for the arguments (List<Estudiante>)Java(67108979) 					*/
}

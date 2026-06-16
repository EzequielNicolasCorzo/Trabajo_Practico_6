package ar.edu.unlar.prog3.tp_comparable_comparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

		//Ordenar por cantidadMateriasAprobadas de forma ascendente con funcion Lambda
		Comparator <Estudiante> porMaterias = (e1,e2) -> Integer.compare(e1.getCantidadMateriasAprobadas(),e2.getCantidadMateriasAprobadas());

		//Comparators para nombre (alfabetico) y edad (ascendente) con Comparator.comparing() + method reference
		Comparator<Estudiante> porNombre = Comparator.comparing(Estudiante::getNombre);
		Comparator<Estudiante> porEdad = Comparator.comparing(Estudiante::getEdad);

		//Verificacion mediante list.sort(comparator)
		
		System.out.println("Ordenado por materias aprobadas (Ascendente-Lambda)");
		lista.sort(porMaterias);
		for (Estudiante e : lista) {
			System.out.println(e);
		}

		System.out.println("Ordenado por nombre(Alfabetico-method reference)");
		lista.sort(porNombre);
		for (Estudiante e : lista) {
			System.out.println(e);
		}

		System.out.println("Ordenado por edad(Ascendente-method reference)");
		lista.sort(porEdad);
		for (Estudiante e : lista) {
			System.out.println(e);
		}

        //Creamos un comparador base por promedio descendente
        Comparator<Estudiante> porPromedioDescendente = Comparator.comparing(Estudiante::getPromedio).reversed();

        //Desempate con thenComparing(): Primero por promedio descendente, luego por nombre (alfabetico)
        Comparator<Estudiante> porPromedioYNombre = porPromedioDescendente.thenComparing(Estudiante::getNombre);

        //Orden inverso con reversed(): A partir del descendente, generamos el ascendente
        Comparator<Estudiante> porPromedioAscendente = porPromedioDescendente.reversed();

        //Combinamos todo: Materias aprobadas descendente y, a igual cantidad, nombre ascendente
        Comparator<Estudiante> porMateriasDescYNombreAsc = Comparator
                .comparing(Estudiante::getCantidadMateriasAprobadas)
                .reversed()
                .thenComparing(Estudiante::getNombre);

		//Verificamos en consola
        
        System.out.println("Ordenado por promedio (Desc) y nombre(Asc)");
        lista.sort(porPromedioYNombre);
        for (Estudiante e : lista) {
            System.out.println(e);
        }

        System.out.println("Ordenado por promedio (Ascendente - Invertido)");
        lista.sort(porPromedioAscendente);
        for (Estudiante e : lista) {
            System.out.println(e);
        }

        System.out.println("Ordenado por materias (Desc) y nombre(Asc)");
        lista.sort(porMateriasDescYNombreAsc);
        for (Estudiante e : lista) {
            System.out.println(e);
        }	

        //Creamos los dos estudiantes con edades extremas
        Estudiante estMax = new Estudiante("LU-MAX", "Estudiante Viejo", 5.0, Integer.MAX_VALUE, 10);
        Estudiante estMin = new Estudiante("LU-MIN", "Estudiante Joven", 5.0, -1, 10);

        List<Estudiante> listaEdades = new ArrayList<>();
        listaEdades.add(estMax);
        listaEdades.add(estMin);

        //Comparator con el "truco de la resta" (INCORRECTO)
        Comparator<Estudiante> comparadorMalo = (e1, e2) -> e1.getEdad() - e2.getEdad();

        listaEdades.sort(comparadorMalo);
        System.out.println("Orden con resta (Falla por overflow, el de edad -1 quedara segundo):");
        for (Estudiante e : listaEdades) {
            System.out.println(e);
        }

        //Corregido con Integer.compare() (CORRECTO)
        Comparator<Estudiante> comparadorBueno = (e1, e2) -> Integer.compare(e1.getEdad(), e2.getEdad());
        
        listaEdades.sort(comparadorBueno);
        System.out.println("Orden con Integer.compare() (Correcto, el de edad -1 queda primero):");
        for (Estudiante e : listaEdades) {
            System.out.println(e);
        }
	}
	
	
	//	Ya no da error de compilacion
	/*	Error de compilacion proveniente de la linea 27: Collections.sort(lista);
		The method sort(List<T>) in the type Collections is not applicable 
		for the arguments (List<Estudiante>)Java(67108979) 					*/
}

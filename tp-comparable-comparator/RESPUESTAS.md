# Parte 1 — El problema

# Pregunta 1: ¿Por qué Collections.sort() no compila cuando le pasamos una List<Estudiante>? 
#             ¿Qué contrato exige Java que nuestra clase no está cumpliendo?

# Respuesta 1:

A) Collections.sort() no compila porque la clase `Estudiante` carece de un orden natural definido. 
   Java no tiene forma de deducir automaticamente si un estudiante va antes o despues que otro, es decir, 
   no sabe si priorizar el legajo, el promedio o la edad.

B) Para que el algoritmo de ordenamiento funcione, Java exige que la clase cumpla un contrato especifico,
   es decir, implementar la interfaz `Comparable<T>`. 
   Al no implementar esta interfaz ni tener el metodo `compareTo()`, nuestra clase rompe ese contrato, 
   por lo que el compilador rechaza la operación para evitar inconsistencias en tiempo de ejecucion.
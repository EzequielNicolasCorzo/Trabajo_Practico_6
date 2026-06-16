# Parte 1 — El problema

# Pregunta 1: ¿Por qué Collections.sort() no compila cuando le pasamos una List<Estudiante>? 
#             ¿Qué contrato exige Java que nuestra clase no está cumpliendo?

# Respuesta 1:

A) 
    Collections.sort() no compila porque la clase `Estudiante` carece de un orden natural definido. 
    Java no tiene forma de deducir automaticamente si un estudiante va antes o despues que otro, es decir, 
    no sabe si priorizar el legajo, el promedio o la edad.

B) 
    Para que el algoritmo de ordenamiento funcione, Java exige que la clase cumpla un contrato especifico,
    es decir, implementar la interfaz `Comparable<T>`. 
    Al no implementar esta interfaz ni tener el metodo `compareTo()`, nuestra clase rompe ese contrato, 
    por lo que el compilador rechaza la operación para evitar inconsistencias en tiempo de ejecucion.

# Parte 2 — Comparable: el orden natural

# Pregunta 2: ¿Por qué elegiste el atributo promedio como orden natural? ¿Qué pasaría si mañana un nuevo requisito pide ordenar por
#             cantidadMateriasAprobadas? ¿Modificarías compareTo? ¿Qué consecuencias tendría?**

A) 
    Elegimos el promedio academico porque representa el merito general y suele ser el criterio por defecto mas "natural" al evaluar estudiantes en este
    contexto. 

B)C)D) 
    Si un nuevo requisito pidiera ordenar por materias aprobadas, no deberíamos modificar el metodo `compareTo()`. 
    Hacerlo cambiaría el "orden natural" en  toda la aplicación, afectando potencialmente a otras partes del sistema que ya dependian de que los estudiantes se ordenaran por promedio. 
    La consecuencia de modificarlo sería generar un alto acoplamiento y fragilidad en el código.

#   Pregunta 3: Comparable nos ata a un único criterio de ordenamiento. ¿Qué problemas de diseño introduce esto si nuestro sistema necesitara ordenar      #   la misma lista de estudiantes de 4 formas distintas según el contexto? Relacioná tu respuesta con los principios de responsabilidad única (SRP) y
#   abierto/cerrado (OCP).**

A)
    Obligar a la clase `Estudiante` a manejar multiples logicas de ordenamiento violaria fuertemente dos principios SOLID:
1.  `Single Responsibility Principle (SRP)`: 
    La responsabilidad de la clase `Estudiante` es modelar los datos del dominio. 
    Conocer como debe ordenarse segun el contexto de una interfaz es una responsabilidad ajena que "ensucia" el dominio, aumentando su acoplamiento.
2. `Open/Closed Principle (OCP)`: 
    La clase debería estar abierta a la extensión pero cerrada a la modificación. 
    Si necesitamos un nuevo ordenamiento (ej. por edad), tendriamos que abrir y modificar el codigo fuente de `Estudiante`, lo cual es un síntoma de mal diseño.

# Pregunta 4: Explicá con tus palabras qué es un overflow de enteros, por qué el "truco de la resta" lo provoca, qué parte del contrato de Comparator
# rompe, y por qué Integer.compare() no sufre este problema.

A)
    Un `overflow de enteros` ocurre cuando una operacion matematica excede la capacidad de almacenamiento del tipo de dato `int` en Java (cuyo limite superior es equivalente a 2.147.483.647). 
B)
    El `truco de la resta` (`e1.getEdad() - e2.getEdad()`) provoca este error porque al restar un numero negativo a un numero positivo maximo (ej. `2147483647 - (-1)`), el resultado matemático supera el limite de memoria. Java no lanza un error, sino que el valor da "la vuelta" y se convierte en un numero extremadamente negativo (`-2147483648`). 
C)
    Al devolver un numero negativo, el comparador interpreta erroneamente que el primer elemento es `menor` que el segundo, rompiendo el contrato de Comparator al generar un orden inconsistente y falso.
D)
    Por el contrario, `Integer.compare()` no sufre este problema porque no realiza operaciones aritmeticas; utiliza internamente operadores relacionales (`<` y `>`) para devolver estrictamente `-1`, `0` o `1`, garantizando una evaluación segura y libre de desbordamientos.

# Pregunta 5: ¿Qué patrón de diseño estás aplicando al usar un Map<String, Comparator> en lugar de un switch? Explicá cómo se relaciona este patrón con el # polimorfismo y por qué es preferible a la alternativa procedural.

A)
    Al utilizar un `Map<String, Comparator>` estamos aplicando el `Patron de Diseño Strategy` 
B)
    Este patron permite definir una familia de algoritmos (en nuestro caso, las distintas logicas de comparacion), encapsular cada uno de ellos (dentro de las implementaciones de `Comparator`) y hacerlos intercambiables dinamicamente en tiempo de ejecución. 

C)
    En lugar de preguntar proceduralmente ¿que parametro llego? usando un `switch` o multiples `if-else` para ejecutar un bloque de codigo distinto, utilizamos el polimorfismo de la interfaz `Comparator`. El metodo `sort()` simplemente invoca al metodo `compare()` del objeto que extrajimos del mapa, sin importarle cual de todas las estrategias especificas se esta ejecutando por detras.

D)
    Es superior a la alternativa procedural porque respeta el `Principio Abierto/Cerrado (OCP)` de SOLID. Si en el futuro necesitamos agregar un nuevo criterio de ordenamiento (ej. por "año de ingreso"), solo necesitamos agregar un nuevo elemento al `Map` en el constructor. El metodo central `ordenarEstudiantes` no sufrira ninguna modificación, reduciendo el riesgo de introducir bugs en logicas preexistentes.
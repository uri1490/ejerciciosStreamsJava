import java.lang.StackWalker.Option;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class StreamsChatGpt {
    List<Persona> personas = Arrays.asList(
            new Persona("Ana", 25, "Guanajuato"),
            new Persona("Luis", 17, "Queretaro"),
            new Persona("Maria", 32, "Queretaro"),
            new Persona("Fabi", 32, "Queretaro"),
            new Persona("Pedro", 19, "Guanajuato"),
            new Persona("Zambi", 19, "Guanajuato"));
    List<Estudiante> estudiantes = Arrays.asList(
            new Estudiante("Ana", 25, "Guanajuato", "Matematicas", 90.0),
            new Estudiante("Luis", 17, "Queretaro", "Matematicas", 85.0),
            new Estudiante("Maria", 32, "Queretaro", "Espanol", 60.0),
            new Estudiante("Fabi", 32, "Queretaro", "Espanol", 80.0),
            new Estudiante("Pedro", 19, "Guanajuato", "Geografia", 70.0),
            new Estudiante("Zambi", 19, "Guanajuato", "Ciencias", 78.0));

    public void palabrasMayores() {
        List<String> palabras = Arrays.asList("java", "python", "kotlin", "scala", "go");
        List<String> palabrasUpperCase = palabras.stream().filter(w -> w.length() > 5).map(b -> b.toUpperCase())
                .toList();
        palabrasUpperCase.forEach(System.out::println);
    }

    public void sumaPares() {
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 7, 8, 9);
        BinaryOperator<Integer> suma = (a, b) -> a + b;
        int res = numeros.stream().filter(numero -> numero % 2 == 0).reduce(suma).get();
        System.out.println(res);
    }

    public void groupByLong() {
        List<String> palabras = Arrays.asList("a", "bb", "ccc", "d", "ee");
        Function<String, Integer> mapaLong = w -> w.length();
        Map<Integer, Long> mapa = palabras.stream().collect(Collectors.groupingBy(mapaLong, Collectors.counting()));
        mapa.forEach((key, value) -> {
            System.out.print(key + "-->");
            System.out.print(value);
            System.out.println();
        });
        System.out.println(mapa);
    }

    public void calcularPromedioEdad() {

        Function<Persona, String> x = s -> s.getCiudad();
        Function<Persona, Integer> edad1 = d -> d.getEdad();
        ToIntFunction<Persona> ageExtractor = Persona::getEdad;
        Map<String, Double> map = personas.stream()
                .collect(Collectors.groupingBy(x, Collectors.averagingInt(Persona::getEdad)));
        map.forEach((key, value) -> {
            System.out.println(key + "-->");
            System.out.println(value);
        });
    }

    public void ordenarEdadAndNombre() {
        Comparator<Persona> edad = (a, b) -> Integer.compare(a.getEdad(), b.getEdad());
        personas.stream()
                .sorted(Comparator.comparing(Persona::getEdad)
                        .thenComparing(Comparator.comparing(Persona::getNombre)))
                .forEach(System.out::println);
    }

    public void calcularPromedioCurso() {
        Function<Estudiante, String> curso = c -> c.getMateria();
        Map<String, Double> mapCurso = estudiantes.stream()
                .collect(Collectors.groupingBy(curso, Collectors.averagingDouble(Estudiante::getCalificacion)));
        mapCurso.forEach(
                (clave, valor) -> {
                    System.out.print(clave + "-->");
                    System.out.print(" Promedio" + valor);
                    System.out.println();
                });
    }

    public void buscarNombre(String s) {
        Predicate<Persona> dato = x -> x.getNombre().equals(s);
        Consumer<Persona> buscar = r -> System.out.println("Search: " + r.getNombre().toUpperCase());
        personas.stream().filter(dato).findFirst().ifPresentOrElse(buscar,
                () -> System.out.println("Serach: Desconocido"));

    }

    public void calcularVentasMes() {
        LocalDate fechaEspecifica = LocalDate.of(2023, 5, 1);
        List<Ventas> ventas = Arrays.asList(
                new Ventas(LocalDate.of(2023, 5, 1), 80.0),
                new Ventas(LocalDate.of(2023, 5, 11), 80.0),
                new Ventas(LocalDate.of(2024, 6, 4), 40.0),
                new Ventas(LocalDate.of(2022, 1, 1), 30.0),
                new Ventas(LocalDate.of(2023, 5, 10), 60.0),
                new Ventas(LocalDate.of(2024, 6, 15), 10.0),
                new Ventas(LocalDate.of(2023, 3, 1), 80.0));
        Function<Ventas, Integer> month = v -> v.fecha.getYear();
        Function<Ventas, Integer> date = d -> d.getFecha().getMonthValue();

        Map<Integer, Map<Integer, Double>> mapVentas = ventas.stream()
                .collect(Collectors.groupingBy(month,
                        Collectors.groupingBy(date, Collectors.reducing(0.0, Ventas::getMonto, Double::sum))));
        Map<Integer, Long> namesByCity = ventas.stream().collect(
                Collectors.groupingBy(month,
                        TreeMap::new,
                        Collectors.counting()));
        namesByCity.forEach((key, value) -> {
            System.out.print(key + "key-->");
            System.out.print(value);
            System.out.println();
        });
        mapVentas.forEach((key, value) -> {
            System.out.print(key + "--");
            System.out.print(value);
            System.out.println();
        });
        System.out.println(fechaEspecifica.getDayOfMonth());
    }
}

/*
 * Agrupación y
 * suma por
 * 
 * mes (pequeño proyecto)
 * Descripción: dado un listado de ventas con fecha (LocalDate) y monto, agrupar
 * por año y mes y sumar los importes.
 * Entrada:
 * 
 * List<Sale> sales (getDate(), getAmount())
 * Salida: Map<YearMonth, Double> ventasPorMes
 * Implementación sugerida: sales.stream().collect(Collectors.groupingBy(s ->
 * YearMonth.from(s.getDate()), Collectors.summingDouble(Sale::getAmount)))
 * Ejemplo: ventas en 2024-01 con 100, 2024-02 con 150 → {2024-01=100.0,
 * 2024-02=150.0}dame plantillas de pruebas unitarias (JUnit 5) para alguno de
 * estos ejercicios
 */
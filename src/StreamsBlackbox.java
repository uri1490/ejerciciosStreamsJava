import java.lang.StackWalker.Option;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.print.attribute.standard.PresentationDirection;

public class StreamsBlackbox {
    List<Integer> arrayNumEnteros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
    List<Integer> arrayNumDesordenados = Arrays.asList(9, 2, 50, 70, 1, 40, 60, 3, 80);
    List<String> listaCadenas = Arrays.asList("hola mundo", "hola a todos", "viva mexico");
    List<Producto> listaProductos = Arrays.asList(
            new Producto("Laptop", "Electrónica", 999.99),
            new Producto("Libro Java", "Libros", 45.50),
            new Producto("Camiseta", "Ropa", 25.00),
            new Producto("Zapatillas", "Ropa", 89.99),
            new Producto("Cámara", "Electrónica", 599.00),
            new Producto("Reloj", "Accesorios", 120.00),
            new Producto("Tablet", "Electrónica", 349.99),
            new Producto("Café", "Alimentos", 12.50),
            new Producto("Juego de mesa", "Juguetes", 35.75),
            new Producto("Bicicleta", "Deportes", 450.00));
    List<Persona> personas = Arrays.asList(
            new Persona("Ana", 25, "Guanajuato", Optional.ofNullable(null)),
            new Persona("Luis", 17, "Queretaro", Optional.ofNullable(null)),
            new Persona("Maria", 32, "Queretaro", Optional.ofNullable(null)),
            new Persona("Fabi", 32, "Queretaro", Optional.ofNullable("235235")),
            new Persona("Pedro", 19, "Guanajuato", Optional.ofNullable("325235")),
            new Persona("Zambi", 19, "Guanajuato", Optional.ofNullable("23423")));
    List<Empleado> empleados = Arrays.asList(
            new Empleado("Ana", 25, "Guanajuato", Optional.ofNullable("23423"), 3900),
            new Empleado("Luis", 17, "Queretaro", Optional.ofNullable("345345"), 4500),
            new Empleado("Maria", 32, "Queretaro", Optional.ofNullable("4565"), 2900),
            new Empleado("Fabi", 32, "Queretaro", Optional.ofNullable("6756"), 7800),
            new Empleado("Pedro", 19, "Guanajuato", Optional.ofNullable("7878"), 1000),
            new Empleado("Zambi", 19, "Guanajuato", Optional.ofNullable("8989"), 3200));

    List<String> palabras = Arrays.asList("casa", "carro", "lapiz", "casa", "silla", "valon", "lapiz");

    public void contarPares() {
        Predicate<Integer> filtroPares = (numero) -> numero % 2 == 0;
        System.out.println("total de numeros pares: " + arrayNumEnteros.stream().filter(filtroPares).count());
    }

    public void transformarMinusculas() {
        List<String> listaMayusculas = listaCadenas.stream().map(cadena -> cadena.toUpperCase()).toList();
        System.out.println(listaMayusculas);
    }

    public void encontrarNumeroMaxMin() {

        System.out.println("numero max: " + arrayNumEnteros.stream().mapToInt(a -> a).max().orElseThrow(
                NoSuchElementException::new));
        System.out.println("numero min: "
                + arrayNumEnteros.stream().mapToInt(a -> a).min().orElseThrow(NoSuchElementException::new));
        Collections.max(arrayNumEnteros, new Comparator<Integer>() {
            public int compare(Integer a, Integer b) {
                return a - b; // o a.compareTo(b)
            }
        });
    }

    public void sumaPrecioTotalProductos() {
        // BinaryOperator<Integer>sumaProductos= (sum, sale) -> sum + sale.getPrecio();
        System.out.println(
                "suma total de precios" + listaProductos.stream().reduce(0.0, (sum, sale) -> sum + sale.getPrecio(),
                        Double::sum));
    }

    public void agruparPorEdad() {
        Function<Persona, Integer> grupoEdad = per -> per.getEdad();
        Map<Integer, List<Persona>> mapaPersonasEdad = personas.stream().collect(Collectors.groupingBy(grupoEdad));
        mapaPersonasEdad.forEach((key, value) -> {
            System.out.println("Edad: " + key + " Nombres: " + value);
        });
    }

    public void obtenerPalabrasUnicas() {

        Map<String, Long> mapaPalabras = palabras.stream()
                .collect(Collectors.groupingBy((palabra -> palabra), Collectors.counting()));
        mapaPalabras.forEach((key, value) -> {
            System.out.println(key + " map " + value);
            Map<Long, List<String>> agrupadoPorFrecuencia = mapaPalabras.entrySet().stream()
                    .collect(Collectors.groupingBy(
                            Map.Entry::getValue,
                            Collectors.mapping(Map.Entry::getKey, Collectors.toList())));
        });
        /*
         * List<String> palabrasUnicas = palabras.stream()
         * .distinct() // elimina duplicados
         * .collect(Collectors.toList());
         * System.out.println(palabrasUnicas);
         */
        List<String> palabrasUnicas = palabras.stream()
                // Contar la frecuencia de cada palabra
                .collect(Collectors.groupingBy(w -> w, Collectors.counting()))
                .entrySet().stream()
                // Filtrar las que aparecen solo una vez
                .filter(entry -> entry.getValue() == 1)
                // Obtener solo la palabra (clave)
                .map(Map.Entry::getKey)
                // Recoger en lista
                .collect(Collectors.toList());
    }

    public void concatenarLista() {
        String nuevaPalabra = "";
        nuevaPalabra = listaCadenas.stream().collect(Collectors.joining(","));
        System.out.println(nuevaPalabra);
    }

    public void ordenarFiltrarNumeros() {
        Predicate<Integer> numerosFiltrados = n -> n > 10;
        arrayNumDesordenados.stream().filter(numerosFiltrados).sorted(Comparator.reverseOrder())
                .forEach(System.out::println);
        ;
    }

    public void obtenerListaEmpleadosMayorSueldo() {
        Predicate<Empleado> filtroSalrio = empleado -> empleado.getSalario() > 3000;
        empleados.stream().filter(filtroSalrio).forEach(empleado -> System.out.println(empleado.getNombre()));

        ;
    }

    public void obtenerNumerosPares() {
        arrayNumEnteros.stream().filter(n -> n % 2 == 0).limit(2).forEach(System.out::println);
    }

    public void verificarValorNull() {
        String valor = "hola";
        Optional<String> testValor = Optional.ofNullable(valor);
        System.out.println(testValor.isPresent());
    }

    public void crearMensajePorDefecto() {
        Optional<String> testMensaje = Optional.ofNullable(null);
        testMensaje.ifPresentOrElse(n -> System.out.println(n), () -> System.out.println("no hay"));
    }

    public void transformarifpresent() {
        Optional<String> testMensaje = Optional.of("null");
        testMensaje.ifPresent((m) -> System.out.println(m.toUpperCase()));
        ;
    }

    public void obtenerCodigoPostal() {
        Persona persona = new Persona("Ana", 25, "Guanajuato", Optional.ofNullable(null));
        Optional<String> codigoPostal = persona.getCodigoPostal();
        codigoPostal.ifPresent(codigo -> System.out.println(codigo));
    }

    public void filtrarIfMayorDiez() {
        Optional<Integer> numeroMayor = Optional.ofNullable(20);
        numeroMayor.filter(n -> n > 10).ifPresentOrElse((n) -> System.out.println(n),
                () -> System.out.println("no hay mayor"));
    }

    public void sumarOptionals() {
        Optional<Integer> num1 = Optional.of(3);
        Optional<Integer> num2 = Optional.empty();
        if (num1.isPresent()) {
            if (num2.isPresent()) {
                System.out.println(num1.get() + num2.get());
            } else
                System.out.println(num2.empty());
        } else
            System.out.println(num1.empty());
        System.out
                .println(num1.isPresent() && num2.isPresent() ? Optional.of(num1.get() + num2.get())
                        : Optional.empty());
    }

    public void imprimirValorIfPresent() {
        Optional<Integer> valor = Optional.of(3);
        valor.ifPresent((x) -> System.out.println("valor " + x));
        System.out.println();
    }

    public void imprimirValorOr() {
        Optional<Integer> valor = Optional.of(2);
        Optional<Integer> valor2 = valor.or(() -> Optional.of(4));
        System.out.println("valor 2 " + valor2.get());
    }
}

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        /*
         * List<Integer> listaNumeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
         * Predicate<Integer> numerosFiltrados = (n) -> n % 2 == 0;
         * listaNumeros.stream().filter(numerosFiltrados).forEach(System.out::println);
         * 
         * List<String> listaNombres = Arrays.asList("hola", "mundo", "java");
         * 
         * listaNombres.stream().map(String::toUpperCase).forEach(System.out::println);
         * ;
         * List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5);
         * System.out.println(numeros.stream().mapToInt(Integer::intValue).sum());
         * 
         * List<Integer> numeros2 = Arrays.asList(1, 2, 3, 4, 6, 7, 8);
         * 
         * Optional<Integer> test = numeros2.stream().filter(n -> n > 7).findFirst();
         * List<Integer> number = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
         * Consumer<Integer> consNumber = n -> System.out.println("--" + n);
         * number.forEach(consNumber);
         * List<String> listaPalabras = Arrays.asList("manzana", "platano", "kiwi",
         * "fresa", "uva");
         * Predicate<String> filtroCadena = palabra -> palabra.length() > 5;
         * List<String> newLista = listaPalabras.stream().filter(filtroCadena).toList();
         * newLista.forEach(System.out::println);
         * List<Integer> listaNumeros2 = Arrays.asList(1, 2, 3, 4, 5);
         * Function<Integer, Integer> multiplos = n -> n * n;
         * List<Integer> newListaMultiplos =
         * listaNumeros2.stream().map(multiplos).toList();
         * newListaMultiplos.forEach(System.out::println);
         * 
         * /// intermedio
         * ///
         * ///
         * List<Persona> personas = Arrays.asList(new Persona("Ana", 25),
         * new Persona("Luis", 17),
         * new Persona("Maria", 32),
         * new Persona("Pedro", 19));
         * Predicate<Persona> filtroEdad = persona -> persona.edad > 18;
         * List<Persona> newPersonas = personas.stream().filter(filtroEdad).toList();
         * newPersonas.forEach(persona -> System.out.println(persona.nombre));
         * List<Integer> numeros4 = Arrays.asList(10, 20, 30, 40, 50);
         * BinaryOperator<Integer> sumaLista = (x, y) -> x + y;
         * int res = numeros4.stream().reduce(sumaLista).get();
         * System.out.println(res);
         * List<Producto> productos = Arrays.asList(
         * new Producto("Laptop", "Electrónica"),
         * new Producto("Televisor", "Electrónica"),
         * new Producto("Manzana", "Alimentos"),
         * new Producto("Plátano", "Alimentos"));
         * 
         * Map<String, List<Producto>> categorias = productos
         * .stream()
         * .collect(Collectors.groupingBy(producto -> producto.getCategoria()));
         * categorias.forEach(
         * (key, value) -> {
         * System.out.println(key + ":");
         * System.out.println("-" + value.toString());
         * });
         * 
         * 
         */
        /*
         * StreamsChatGpt stream = new StreamsChatGpt();
         * stream.palabrasMayores();
         * stream.sumaPares();
         * stream.groupByLong();
         * 
         * stream.calcularPromedioEdad();
         * stream.ordenarEdadAndNombre();
         * stream.calcularPromedioCurso();
         * stream.buscarNombre("pepe");
         * stream.calcularVentasMes();
         */
        // streams con blackbox
        StreamsBlackbox stream = new StreamsBlackbox();
        stream.contarPares();
        stream.transformarMinusculas();
        stream.encontrarNumeroMaxMin();
        stream.sumaPrecioTotalProductos();
        stream.agruparPorEdad();
        stream.obtenerPalabrasUnicas();
        stream.concatenarLista();
        stream.ordenarFiltrarNumeros();
        stream.obtenerListaEmpleadosMayorSueldo();
        stream.obtenerNumerosPares();
        // optionals
        stream.verificarValorNull();
        stream.crearMensajePorDefecto();
        stream.transformarifpresent();
        stream.obtenerCodigoPostal();
        stream.filtrarIfMayorDiez();
        stream.sumarOptionals();
        stream.imprimirValorIfPresent();
        stream.imprimirValorOr();
    }

}

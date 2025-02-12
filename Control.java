import java.util.*;

public class Control {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SistemaSaludMental sistema = new SistemaSaludMental();

        // Cargar datos desde archivo (si existe)
        sistema.cargarDesdeArchivo("instrumentos.csv");

        // Menú de opciones
        while (true) {
            System.out.println("\n--- Sistema de Salud Mental ---");
            System.out.println("1. Agregar Instrumento");
            System.out.println("2. Buscar por Autor");
            System.out.println("3. Buscar por Tipo");
            System.out.println("4. Buscar por Tipo de Instrumento");
            System.out.println("5. Buscar por Condición");
            System.out.println("6. Buscar por Evaluación");
            System.out.println("7. Mostrar todos");
            System.out.println("8. Mostrar por Clave");
            System.out.println("9. Mostrar por Primer Autor");
            System.out.println("10. Eliminar por Clave");
            System.out.println("11. Guardar y Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcion) {
                case 1 -> agregarInstrumento(sistema, scanner);
                case 2 -> buscarAutor(scanner, sistema);
                case 3 -> buscarTipo(scanner, sistema);
                case 4 -> buscarTipoDeInstrumento(scanner, sistema);
                case 5 -> buscarCondicion(scanner, sistema);
                case 6 -> buscarEvaluacion(scanner, sistema);
                case 7 -> consultarTodo(sistema);
                case 8 -> consultarOrdenadosClave(scanner, sistema);
                case 9 -> consultarOrdenadosAutor(scanner, sistema);
                case 10 -> eliminarPorClave(scanner,sistema);
                case 11 -> {
                    sistema.guardarEnArchivo("instrumentos.csv");
                    System.out.println("Datos guardados. Saliendo...");
                    return;
                }
                default -> System.out.println("Opción no válida.");
            }
        }
    }

    private static void agregarInstrumento(SistemaSaludMental sistema, Scanner scanner) {
        System.out.print("Clave: ");
        int clave = scanner.nextInt();
        scanner.nextLine(); 
        
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Tipo (1. Identificar / 2. Manejar): ");
        int tipoOp = scanner.nextInt();
        scanner.nextLine();
        String tipo = (tipoOp == 1) ? "identificar" : "manejar";

        System.out.print("Autores (separados por coma): ");
        String autoresInput = scanner.nextLine();
        String[] autores = autoresInput.split(",");
        
        System.out.print("¿Tiene evaluación de validez y confiabilidad? (1. Sí / 2. No): ");
        int evalOp = scanner.nextInt();
        scanner.nextLine(); 
        boolean evaluacion = (evalOp == 1);

        System.out.print("Referencia: ");
        String referencia = scanner.nextLine();

        System.out.print("Tipo de Instrumento (1. Test / 2. Encuesta / 3. Escala): ");
        int tipoInstOp = scanner.nextInt();
        scanner.nextLine(); 
        String tipoDeInstrumento = "";
        switch (tipoInstOp) {
            case 1 -> tipoDeInstrumento = "test";
            case 2 -> tipoDeInstrumento = "encuesta";
            case 3 -> tipoDeInstrumento = "escala";
            default -> {
                System.out.println("Opción no válida. Se asignará 'test' por defecto.");
                tipoDeInstrumento = "test";
            }
        }

        System.out.print("Condición (1. Ansiedad / 2. Estrés): ");
        int condicionOp = scanner.nextInt();
        scanner.nextLine();
        String condicion = (condicionOp == 1) ? "ansiedad" : "estrés";
        
        Instrumento instrumento = new Instrumento(autores, clave, condicion, evaluacion, nombre, referencia, tipo, tipoDeInstrumento);
        sistema.registrar(instrumento);
        System.out.println("Instrumento agregado.");
    }

    private static void buscarAutor(Scanner scanner, SistemaSaludMental sistema) {
        System.out.print("Ingrese el autor a buscar: ");
        String autor = scanner.nextLine();
        for (Instrumento instrumento : sistema.getInstrumentos()) {
            if (instrumento != null) {
                for (String a : instrumento.getAutores()) {
                    if (a.equalsIgnoreCase(autor)) {
                        System.out.println(instrumento);
                        break; 
                    }
                }
            }
        }
    }
    
    private static void buscarTipo(Scanner scanner, SistemaSaludMental sistema) {
        System.out.print("Ingrese el tipo a buscar (identificar/manejar): ");
        String tipo = scanner.nextLine();
        for (Instrumento instrumento : sistema.getInstrumentos()) {
            if (instrumento != null && instrumento.getTipo().equalsIgnoreCase(tipo)) {
                System.out.println(instrumento);
            }
        }
    }
    
    private static void buscarTipoDeInstrumento(Scanner scanner, SistemaSaludMental sistema) {
        System.out.print("Ingrese el tipo de instrumento a buscar (test/encuesta/escala): ");
        String tipoDeInstrumento = scanner.nextLine();
        for (Instrumento instrumento : sistema.getInstrumentos()) {
            if (instrumento != null && instrumento.getTipoDeInstrumento().equalsIgnoreCase(tipoDeInstrumento)) {
                System.out.println(instrumento);
            }
        }
    }

    private static void buscarCondicion(Scanner scanner, SistemaSaludMental sistema) {
        System.out.print("Ingrese la condición a buscar (ansiedad/estrés): ");
        String condicion = scanner.nextLine();
        for (Instrumento instrumento : sistema.getInstrumentos()) {
            if (instrumento != null && instrumento.getCondicion().equalsIgnoreCase(condicion)) {
                System.out.println(instrumento);
            }
        }
    }

    private static void buscarEvaluacion(Scanner scanner, SistemaSaludMental sistema) {
        System.out.print("¿Buscar instrumentos con evaluación de validez y confiabilidad? (true/false): ");
        boolean evaluacion = scanner.nextBoolean();
        scanner.nextLine(); 
        for (Instrumento instrumento : sistema.getInstrumentos()) {
            if (instrumento != null && instrumento.isEvaluacion() == evaluacion) {
                System.out.println(instrumento);
            }
        }
    }

    private static void consultarTodo(SistemaSaludMental sistema) {
        System.out.println("\n--- Todos los instrumentos ---");
        sistema.mostrarTodos();
    }
    
    private static void consultarOrdenadosClave(Scanner scanner, SistemaSaludMental sistema) {
        System.out.println("\n--- Instrumentos ordenados por clave ---");
        Instrumento[] instrumentos = sistema.getInstrumentos();

        for (int i = 0; i < sistema.getContador() - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < sistema.getContador(); j++) {
                if (instrumentos[j] != null && instrumentos[j].getClave() < instrumentos[minIndex].getClave()) {
                    minIndex = j;
                }
            }
            Instrumento temp = instrumentos[i];
            instrumentos[i] = instrumentos[minIndex];
            instrumentos[minIndex] = temp;
        }

        for (Instrumento instrumento : instrumentos) {
            if (instrumento != null) {
                System.out.println(instrumento);
            }
        }
    }

    private static void consultarOrdenadosAutor(Scanner scanner, SistemaSaludMental sistema) {
        System.out.println("\n--- Instrumentos ordenados por primer autor ---");
        Instrumento[] instrumentos = sistema.getInstrumentos();

        for (int i = 0; i < sistema.getContador() - 1; i++) {
        int minIndex = i;
        for (int j = i + 1; j < sistema.getContador(); j++) {
            if (instrumentos[j] != null && instrumentos[minIndex] != null) {
                String autorJ = instrumentos[j].getAutores()[0]; 
                String autorMin = instrumentos[minIndex].getAutores()[0]; 
                if (autorJ.compareToIgnoreCase(autorMin) < 0) {
                    minIndex = j;
                }
            }
        }

            Instrumento temp = instrumentos[i];
            instrumentos[i] = instrumentos[minIndex];
            instrumentos[minIndex] = temp;
        }

        for (Instrumento instrumento : instrumentos) {
            if (instrumento != null) {
                System.out.println(instrumento);
            }
        }
    }

    private static void eliminarPorClave(Scanner scanner, SistemaSaludMental sistema) {
        System.out.print("Ingrese la clave del instrumento a eliminar: ");
        int clave = scanner.nextInt();
        scanner.nextLine(); 
        sistema.eliminar(clave);
    }
}
import java.io.*;

public class SistemaSaludMental {
    private Instrumento[] instrumentos;
    private int contador;

    public SistemaSaludMental() {
        this.instrumentos = new Instrumento[100];
        this.contador = 0;
    }

    // Registrar un instrumento
    public void registrar(Instrumento instrumento) {
        for (int i = 0; i < contador; i++) {
            if (instrumentos[i].getClave() == instrumento.getClave()) {
                System.out.println("Error: La clave ya existe.");
                return;
            }
        }

        if (contador < instrumentos.length) {
            instrumentos[contador] = instrumento;
            contador++;
        } else {
            System.out.println("Error: No hay espacio para más instrumentos.");
        }
    }

    // Eliminar un instrumento por clave
    public void eliminar(int clave) {
        for (int i = 0; i < contador; i++) {
            if (instrumentos[i].getClave() == clave) {
                for (int j = i; j < contador - 1; j++) {
                    instrumentos[j] = instrumentos[j + 1];
                }
                contador--;
                return;
            }
        }
        System.out.println("Instrumento con clave " + clave + " no encontrado.");
    }
    
    // Consultar todos
    public void mostrarTodos() {
        for (int i = 0; i < contador; i++) {
            System.out.println(instrumentos[i]);
        }
    }

    // Guardar en archivo CSV
    public void guardarEnArchivo(String nombreArchivo) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(nombreArchivo))) {
            for (int i = 0; i < contador; i++) {
                writer.println(instrumentos[i].getClave() + "," +
                        instrumentos[i].getNombre() + "," +
                        instrumentos[i].getTipo() + "," +
                        String.join(";", instrumentos[i].getAutores()) + "," +
                        instrumentos[i].isEvaluacion() + "," +
                        instrumentos[i].getReferencia() + "," +
                        instrumentos[i].getTipoDeInstrumento() + "," +
                        instrumentos[i].getCondicion());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Cargar desde archivo CSV
    public void cargarDesdeArchivo(String nombreArchivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null && contador < instrumentos.length) {
                String[] datos = linea.split(",");
                int clave = Integer.parseInt(datos[0]);
                String nombre = datos[1];
                String tipo = datos[2];
                String[] autores = datos[3].split(";");
                boolean evaluacion = Boolean.parseBoolean(datos[4]);
                String referencia = datos[5];
                String tipoDeInstrumento = datos[6];
                String condicion = datos[7];
                instrumentos[contador] = new Instrumento(autores, clave, condicion, evaluacion, nombre, referencia, tipo, tipoDeInstrumento);
                contador++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Instrumento[] getInstrumentos() {
        return instrumentos;
    }
    
    public int getContador() {
        return contador;
    }
}

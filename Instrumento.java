
public class Instrumento {
    private String nombre;
    private String tipo; 
    private String[] autores;
    private boolean evaluacion;
    private String referencia;
    private String tipoDeInstrumento; 
    private int clave;
    private String condicion; 

    public Instrumento(String[] autores, int clave, String condicion, boolean evaluacion, String nombre, String referencia, String tipo, String tipoDeInstrumento) {
        this.clave = clave;
        this.nombre = nombre;
        this.tipo = tipo;
        this.autores = autores;
        this.condicion = condicion;
        this.evaluacion = evaluacion;
        this.referencia = referencia;
        this.tipoDeInstrumento = tipoDeInstrumento;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String[] getAutores() {
        return autores;
    }

    public void setAutores(String[] autores) {
        this.autores = autores;
    }

    public boolean isEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(boolean evaluacion) {
        this.evaluacion = evaluacion;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getTipoDeInstrumento() {
        return tipoDeInstrumento;
    }

    public void setTipoDeInstrumento(String tipoDeInstrumento) {
        this.tipoDeInstrumento = tipoDeInstrumento;
    }

    public int getClave() {
        return clave;
    }

    public void setClave(int clave) {
        this.clave = clave;
    }

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    @Override
    public String toString() {
        return "**********************************************************" +
                "\nInstrumento:" +
                "\n\tclave: " + clave +
                "\n\tnombre: " + nombre +
                "\n\ttipo: " + tipo + 
                "\n\tautor: " + String.join(", ", autores) +
                "\n\tevaluacion: " + evaluacion +
                "\n\treferencia: " + referencia +
                "\n\ttipoDeInstrumento: " + tipoDeInstrumento + 
                "\n\tcondicion: " + condicion ;
    }
}

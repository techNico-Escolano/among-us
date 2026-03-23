public class Sala {

    private int id;
    private String nombre;
    private boolean saboteada;

    public Sala(int id, String nombre) {

        this.id = id;
        this.nombre = nombre;
        this.saboteada = false;
    }

    public int getId() { return this.id; }

    public String getNombre() { return this.nombre; }

    public boolean isSaboteada() { return this.saboteada; }

    public void setId(int id) { this.id = id; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public void setSaboteada(boolean saboteada) { this.saboteada = saboteada; }

    @Override
    public String toString() {
        return "Sala {" +
                " id = " + id +
                ", nombre = '" + nombre + '\'' +
                ", saboteada = " + saboteada +
                " }";
    }
}
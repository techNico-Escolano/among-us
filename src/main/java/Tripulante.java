public abstract class Tripulante {

    private int id;
    private String nombre;
    private String rol;
    private boolean vivo;

    public Tripulante(String nombre, String rol) {

        this.id = id;
        this.nombre = nombre;
        this.rol = rol;
        this.vivo = true;
    }

    public int getId() { return this.id; }

    public String getNombre() { return this.nombre; }

    public String getRol() { return this.rol; }

    public boolean isVivo() { return this.vivo; }

    public void setId(int id) { this.id = id; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public void setVivo(boolean vivo) { this.vivo = vivo; }

    public void realizarTarea(Tarea tarea) {}

    public void votar (Tripulante sospechoso) {}

    public abstract void habilidadEspecial();

    @Override
    public String toString() {

        return "Tripulante { " +
                "id = " + id +
                ", nombre = '" + nombre + '\'' +
                ", rol = '" + rol + '\'' +
                ", vivo = " + vivo +
                " }";
    }
}
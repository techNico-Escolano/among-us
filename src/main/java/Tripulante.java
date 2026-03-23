public abstract class Tripulante implements Trabajable, Votable{

    private int id;
    private String nombre;
    private String rol;
    private boolean vivo;

    public Tripulante(String nombre, String rol) {
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

    @Override
    public void realizarTarea(Tarea tarea) {

        if (!isVivo()) {

            System.out.println("El jugador " + this.id + ". " + this.nombre + " no está vivo, no puede realizar tareas.");
            return;
        }

        if (!tarea.getTripulanteAsignado().getNombre().equals(this.nombre)) {
            System.out.println("El jugador " + this.id + ". " + this.nombre + " no tiene esta tarea asignada.");
            return;
        }

        if (tarea.isCompletada()) {

            System.out.println("El jugador " + this.id + ". " + this.nombre + " ya ha completado esta tarea.");
            return;
        }

        tarea.setCompletada(true);
        System.out.println("La tarea ha sido completada con éxito por el jugador " + this.id + ". " + this.nombre + ".");
    }

    @Override
    public void votar (Tripulante sospechoso) {

        if (!isVivo()) {

            System.out.println("El jugador " + this.id + ". " + this.nombre + " no está vivo, no puede votar.");
            return;
        }

        if (sospechoso == null) {

            System.out.println("Se ha saltado el voto.");
            return;
        }

        System.out.println("El jugador " + sospechoso.getId() + ". " + sospechoso.getNombre() + " ha sido votado.");
    }

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
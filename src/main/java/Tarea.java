public class Tarea {
    private int id;
    private String descripcion;
    private boolean completada;
    private Tripulante tripulanteAsignado;
    private Sala sala;

    public Tarea(Tripulante tripulanteAsignado, Sala sala, String descripcion) {
        this.tripulanteAsignado = tripulanteAsignado;
        this.sala = sala;
        this.descripcion = descripcion;
        this.completada = false;
    }

    public boolean isCompletada() {
        return completada;
    }

    public void setCompletada(boolean completada) {
        this.completada = completada;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Tripulante getTripulanteAsignado() {
        return tripulanteAsignado;
    }

    public void setTripulanteAsignado(Tripulante tripulanteAsignado) {
        this.tripulanteAsignado = tripulanteAsignado;
    }

    @Override
    public String toString() {
        return "Tarea [" + descripcion + "] - Sala: " + sala.getNombre() +
                " | Asignada a: " + tripulanteAsignado.getNombre() +
                " | Completada: " + completada;
    }
}

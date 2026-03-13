import java.util.ArrayList;

public class Nave {
    private ArrayList<Tripulante> tripulantes;
    private ArrayList<Sala> salas;
    private ArrayList<Tarea> tareas;

    public Nave(ArrayList<Sala> salas, ArrayList<Tripulante> tripulantes) {
        this.salas = salas;
        this.tareas = new ArrayList<>();
        this.tripulantes = tripulantes;
    }

    public ArrayList<Sala> getSalas() {
        return salas;
    }

    public ArrayList<Tarea> getTareas() {
        return tareas;
    }

    public ArrayList<Tripulante> getTripulantes() {
        return tripulantes;
    }

    public void mostrarEstadoNave(){

    }

    public void iniciarVotacion(){

    }

    public void agregarTarea(Tarea tarea){
        this.tareas.add(tarea);
    }

    public boolean verificarVictoriaTripulantes(){
        return false;
    }

    public boolean verificarVictoriaImpostor(){
        return false;
    }

    public void limpiarPantalla(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void turno(){

    }
}

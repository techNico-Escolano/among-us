import java.util.ArrayList;

public class Nave {
    private ArrayList<Tripulante> tripulantes;
    private ArrayList<Sala> salas;
    private ArrayList<Tarea> tareas;

    public Nave(ArrayList<Sala> salas, ArrayList<Tarea> tareas, ArrayList<Tripulante> tripulantes) {
        this.salas = salas;
        this.tareas = tareas;
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

    public void mostrarEstadoNave(){}

    public void iniciarVotacion(){}

    public agregarTarea(Tarea){
    }

    public boolean verificarVictoriaTripulantes(){

    }

    public boolean verificarVictoriaImpostor(){

    }

    public void limpiarPantalla(){

    }

    public void turno(){

    }


}

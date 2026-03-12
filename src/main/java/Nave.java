import java.awt.*;
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

    public agregarTarea(Tarea){

    }


}

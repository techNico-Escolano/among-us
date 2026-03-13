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

    public void mostrarEstadoNave(){
        String vivos = "";
        String eliminados = "";

        //Tripulantes vivos
        for (Tripulante t : tripulantes){
            if (t.isVivo()){
                vivos += t.getNombre() + ", ";
            } else {
                eliminados += t.getNombre() + ", ";
            }
        }
        if (vivos.isEmpty()) vivos = "ninguno  ";
        if (eliminados.isEmpty()) eliminados = "ninguno  ";

        System.out.println("Jugadores vivos: " + vivos);
        System.out.println("Jugadores eliminados: " + eliminados);

        String saboteada = "";
        for (Sala s : salas){
            if (s.isSaboteada()){
                saboteada += s.getNombre() + ", ";
            }
        }
        if (saboteada.isEmpty()) saboteada = "Ninguna sala saboteada.";
        System.out.println("Salas saboteadas: " + saboteada);

        int completadas = 0;
        for (Tarea t : tareas){
            if (t.isCompletada()){
                completadas++;
            }
        }
        System.out.println("Tareas completadas: " + completadas + "/" + tareas.size());
        System.out.println("============================================");

    }

    public void iniciarVotacion(){}

    public void agregarTarea(Tarea tarea){
        this.tareas.add(tarea);
    }

    public boolean verificarVictoriaTripulantes(){
        int tareasCompletadas = 0;
        boolean tareasTerminadas = false;
        for (Tarea t : tareas){
            if (t.isCompletada()){
                tareasCompletadas++;
            }
            if (tareasCompletadas == tareas.size()){
                tareasTerminadas = true;
            }
        }

        boolean impostorMuerto = false;
        for (Tripulante tripulante : tripulantes){
            if (tripulante.getRol().equalsIgnoreCase("Impostor")){
                if (!tripulante.isVivo()){
                    impostorMuerto = true;
                }
            }
        }
        boolean victoria = false;
        if (impostorMuerto || tareasTerminadas){
            victoria = true;
        }
        return victoria;
    }

    public boolean verificarVictoriaImpostor(){
        int impostoresVivos = 0;
        int tripulantesVivos = 0;

        for (Tripulante tripulante : tripulantes){
            if (tripulante.isVivo()){
                if (tripulante.getRol().equalsIgnoreCase("Impostor")){
                    impostoresVivos++;
                } else {
                    tripulantesVivos++;
                }
            }
        }

        boolean resultado = false;
        if (impostoresVivos >= tripulantesVivos){
            resultado = true;
        }
        return resultado;
    }

    public void limpiarPantalla(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void turno(){

    }


}

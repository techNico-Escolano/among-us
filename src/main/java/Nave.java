import java.util.ArrayList;
import java.util.Scanner;

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

    public void iniciarVotacion(){
        int[] conteoVotos = new int[tripulantes.size()];
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < tripulantes.size(); i++) {
            Tripulante votante = tripulantes.get(i);

            if (votante.isVivo()){
                limpiarPantalla();
                System.out.println("Turno de voto de: " + votante.getNombre() + ":");
                System.out.println("Tripulantes que puedes votar:");
                for (int j = 0; j < tripulantes.size(); j++) {
                    Tripulante candidato = tripulantes.get(j);
                    if (candidato.isVivo()){
                        System.out.println((j + 1) + ") " + candidato.getNombre());
                    }
                }
                System.out.println("0) Saltar voto.");

                System.out.println("A quién deseas votar? (1-" + tripulantes.size() + ", 0 para skip): ");
                int opcion = scanner.nextInt();

                if (opcion > 0 && opcion <= tripulantes.size()){
                    int indiceVotado = opcion - 1;
                    conteoVotos[indiceVotado]++;
                    System.out.println(votante.getNombre() + " ha votado.");
                } else {
                    System.out.println(votante.getNombre() + " ha saltado el voto.");
                }
                System.out.println("Pulsa enter para continuar...");
                scanner.nextLine();
                scanner.nextLine();
            }
        }

        int maxVotos = 0;
        int indiceExpulsado = -1;
        boolean empate = false;

        for (int i = 0; i < conteoVotos.length; i++) {
            if (conteoVotos[i] > maxVotos){
                maxVotos = conteoVotos[i];
                indiceExpulsado = i;
                empate = false;
            } else if (conteoVotos[i] == maxVotos && maxVotos > 0){
                empate = true;
            }
        }
        limpiarPantalla();
        System.out.println("=== RESULTADO DE LA VOTACIÓN ===");
        System.out.println("--------------------------------");
        if (empate || maxVotos == 0){
            System.out.println("En la votación ha habido empate (o nadie votó). Nadie es expulsado.");
        } else {
            Tripulante expulsado = tripulantes.get(indiceExpulsado);
            expulsado.setVivo(false);
            System.out.println("EXPULSADO");
            System.out.println(expulsado.getNombre() + " ha sido expulsado de la nave.");

            if (expulsado.getRol().equalsIgnoreCase("impostor")){
                System.out.println("¡" + expulsado.getNombre() + " ERA el IMPOSTOR!");
            } else {
                System.out.println("¡" + expulsado.getNombre() + "NO era el IMPOSTOR");
            }
        }
    }

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

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
                    break;
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
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;
        for (Tripulante tripulanteActual : tripulantes){
            if (tripulanteActual.isVivo()){
                limpiarPantalla();
                System.out.println("Pasa el ordenador a: " + tripulanteActual.getNombre() + "!");
                System.out.println("Pulsa enter cuando estés listo...");
                scanner.nextLine();

                limpiarPantalla();
                System.out.println("TURNO DE " + tripulanteActual.getNombre().toUpperCase());
                System.out.println("Tu rol secreto: " + tripulanteActual.getRol());

                mostrarEstadoNave();
                if (!tripulanteActual.getRol().equalsIgnoreCase("impostor")){
                    System.out.println("1) Realizar tarea.");
                    System.out.println("2) Usar habilidad especial.");
                    System.out.println("3) Convocar votación de emergencia.");
                    System.out.println("4) Pasar turno.");
                    System.out.println("Elige una opción: ");
                    opcion = scanner.nextInt();
                    scanner.nextLine();
                    if (opcion == 1){
                        ArrayList<Tarea> misTareas = new ArrayList<>();
                        System.out.println("Tareas pendientes: ");
                        for (Tarea tarea : tareas){
                            if (!tarea.isCompletada() && tarea.getTripulanteAsignado().getId() == tripulanteActual.getId()){
                                misTareas.add(tarea);
                            }
                        }
                        if (misTareas.isEmpty()){
                            System.out.println("No tienes tareas pendientes!");
                        } else {
                            for (int i = 0; i < misTareas.size(); i++) {
                                System.out.println((i + 1) + ") " + misTareas.get(i).getDescripcion());
                            }
                            System.out.println("Elige una tarea de realizar (0 para cancelar): " );
                            int eleccion = scanner.nextInt();
                            scanner.nextLine();
                            if (eleccion > 0 && eleccion <= misTareas.size()){
                                tripulanteActual.realizarTarea(misTareas.get(eleccion - 1));
                            }
                        }
                    } else if (opcion == 2){
                        tripulanteActual.habilidadEspecial();
                        if (tripulanteActual.getRol().equalsIgnoreCase("ingeniero")){
                            System.out.println("Salas saboteadas: ");
                            ArrayList<Sala> salasSaboteadas = new ArrayList<>();
                            for (Sala sala : salas){
                                if (sala.isSaboteada()){
                                    salasSaboteadas.add(sala);
                                }
                            }
                            if (salasSaboteadas.isEmpty()){
                                    System.out.println("No hay salas saboteadas.");
                            } else {
                                for (int i = 0; i < salasSaboteadas.size(); i++) {
                                        System.out.println((i + 1) + ") " + salasSaboteadas.get(i).getNombre());
                                }
                                System.out.println("Elige sala a reparar: ");
                                int eleccion = scanner.nextInt();
                                scanner.nextLine();
                                if (eleccion > 0 && eleccion <= salasSaboteadas.size()){
                                    ((Ingeniero) tripulanteActual).repararSala(salasSaboteadas.get(eleccion - 1));
                                    System.out.println("Sala reparada!");
                                }
                            }
                        } else if (tripulanteActual.getRol().equalsIgnoreCase("medico")){
                            System.out.println("Tripulantes vivos: ");
                            for (int i = 0; i < tripulantes.size(); i++) {
                                if (tripulantes.get(i).isVivo() && tripulantes.get(i) != tripulanteActual){
                                    System.out.println((i + 1) + ") " + tripulantes.get(i).getNombre());
                                }
                            }
                            System.out.println("Elige a quién examinar: ");
                            int eleccionMedico = scanner.nextInt();
                            scanner.nextLine();
                            if (eleccionMedico > 0 && eleccionMedico <= tripulantes.size()){
                                ((Medico) tripulanteActual).examinar(tripulantes.get(eleccionMedico - 1));
                            }
                        } else if (tripulanteActual.getRol().equalsIgnoreCase("capitan")) {
                            ((Capitan) tripulanteActual).convocarVotacion(this);
                        }
                    } else if (opcion == 3){
                        iniciarVotacion();
                    } else if (opcion == 4){
                        System.out.println("Pasando turno.");
                    }
                } else {
                    System.out.println("1) Simular tarea.");
                    System.out.println("2) Sabotear una sala.");
                    System.out.println("3) Eliminar a un tripulante.");
                    System.out.println("4) Convocar votación.");
                    System.out.println("5) Pasar turno.");
                    System.out.println("Elige una opción: ");
                    opcion = scanner.nextInt();
                    scanner.nextLine();

                    Impostor impostor = (Impostor) tripulanteActual;
                    if (opcion == 1){
                        System.out.println("Simulando tarea...");
                    } else if (opcion == 2){
                        System.out.println("¿Qué sala quieres sabotear?: ");
                        for (int i = 0; i < salas.size(); i++) {
                            if (!salas.get(i).isSaboteada()){
                                System.out.println((i + 1) + ") " + salas.get(i).getNombre());
                            }
                        }
                        System.out.println("Elige sala: ");
                        int eleccionSala = scanner.nextInt();
                        scanner.nextLine();
                        if (eleccionSala > 0 && eleccionSala <= salas.size()){
                            impostor.sabotear(salas.get(eleccionSala - 1));
                            System.out.println("Sabotaje realizado en secreto!");
                        }
                    } else if (opcion == 3){
                        System.out.println("¿A qué tripulante quieres eliminar?: " );
                        ArrayList<Tripulante> posiblesVictimas = new ArrayList<>();
                        for (Tripulante victima : tripulantes) {
                            if (victima.isVivo() && victima != impostor) {
                                posiblesVictimas.add(victima);
                            }
                        }

                        for (int i = 0; i < posiblesVictimas.size(); i++) {
                            System.out.println((i + 1) + ") " + posiblesVictimas.get(i).getNombre());
                        }

                        System.out.println("Elige víctima (0 para cancelar): ");
                        int eleccionVictima = scanner.nextInt();
                        scanner.nextLine();

                        if (eleccionVictima > 0 && eleccionVictima <= posiblesVictimas.size()){
                            impostor.eliminar(posiblesVictimas.get(eleccionVictima - 1));
                        }
                    } else if (opcion == 4){
                        System.out.println("Convocando votación para disimular...");
                        iniciarVotacion();
                    } else if (opcion == 5){
                        System.out.println("Pasando turno...");
                    }
                }
            }
            if (verificarVictoriaTripulantes()){
                limpiarPantalla();
                System.out.println("¡VICTORIA DE LOS TRIPULANTES!");
                System.exit(0);
            }

            if (verificarVictoriaImpostor()){
                limpiarPantalla();
                System.out.println("¡VICTORIA DEL IMPOSTOR!");
                System.exit(0);
            }
        }
    }
}

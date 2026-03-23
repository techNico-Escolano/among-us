import java.sql.Connection;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Connection conexion = DBUtil.getInstance().getConexion();

        TripulanteDAO tripulanteDAO = new TripulanteDAOImpl(conexion);
        SalaDAO salaDAO = new SalaDAOImpl(conexion);
        TareaDAO tareaDAO = new TareaDAOImpl(conexion);

        ArrayList<Sala> listaSalas = salaDAO.obtenerTodods();
        ArrayList<Tarea> listaTareas = tareaDAO.obtenerTodos();

        if (listaSalas.isEmpty() || listaTareas.isEmpty()){
            System.out.println("No hay salas o tareas en la base de datos, inserta datos en MySQL antes de jugar.");
            return;
        }

        System.out.println("🧑‍🚀 --- AMONG US --- 🧑‍🚀");
        System.out.println("-".repeat(30));
        System.out.println("¿Cuántos jugadores vais a ser?: ");
        int numeroParticipantes = scanner.nextInt();
        scanner.nextLine();
        System.out.println("=== BIENVENIDOS A LA NAVE ===");

        ArrayList<Tripulante> listaJugadores = new ArrayList<>();
        for (int i = 0; i < numeroParticipantes; i++) {
            System.out.println("Introduce el nombre del jugador " + (i + 1) + ": ");
            String nombreJugador = scanner.nextLine();
            Tripulante nuevoTripulante;

            if (i == 0){
                nuevoTripulante = new Capitan(nombreJugador);
            } else if (i == 1) {
                nuevoTripulante = new Medico(nombreJugador);
            } else {
                nuevoTripulante = new Ingeniero(nombreJugador);
            }
            nuevoTripulante.setId(i + 1);
            listaJugadores.add(nuevoTripulante);
        }
        int indiceImpostor = new Random().nextInt(listaJugadores.size());
        Tripulante victimaImpostor = listaJugadores.get(indiceImpostor);
        Impostor imp = new Impostor(victimaImpostor.getNombre());

        listaJugadores.set(indiceImpostor, imp);
        int turno = 0;
        for (Tarea tarea : listaTareas){
            Tripulante jugadorAsignado = listaJugadores.get(turno);
            tarea.setTripulanteAsignado(jugadorAsignado);
            turno++;

            if (turno == listaJugadores.size()){
                turno = 0;
            }
        }

        Nave miNave = new Nave(listaSalas, listaTareas, listaJugadores);
        System.out.println("=== COMIENZA LA PARTIDA... ===");
        while(true){
            miNave.turno();
        }
    }
}

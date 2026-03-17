public class Capitan extends Tripulante{

    public Capitan(String nombre) {

        super(nombre, "Capitán");
    }

    @Override
    public void habilidadEspecial() {

        System.out.println("El jugador " + getId() + ". " + getNombre() + " puede convocar votaciones!");
    }

    public void convocarVotacion(Nave nave) {

        System.out.println("Iniciando proceso de votación...");
        nave.iniciarVotacion();
        System.out.println("Votación finalizada!");
    }

    @Override
    public void realizarTarea(Tarea tarea) {

        super.realizarTarea(tarea);
    }
}
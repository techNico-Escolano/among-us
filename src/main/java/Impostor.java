public class Impostor extends Tripulante implements Saboteable{

    public Impostor(String nombre) {

        super(nombre, "Impostor");
    }

    @Override
    public void habilidadEspecial() {

        System.out.println("El jugador " + this.getId() + ". " + this.getNombre() + " puede sabotear salas y eliminar tripulantes!");
    }

    public void eliminar(Tripulante tripulante) {

        if (!tripulante.isVivo()) {

            System.out.println("El jugador " + tripulante.getId() + ". " + tripulante.getNombre() + " no está vivo, no puedes eliminarle.");
            return;
        }

        if (tripulante == this) {

            System.out.println("No puedes eliminarte a ti mismo!");
            this.setVivo(true);
            return;
        }

        tripulante.setVivo(false);
        System.out.println("Has eliminado al jugador " + tripulante.getId() + ". " + tripulante.getNombre() + "!");
    }

    @Override
    public void sabotear(Sala sala) {

        System.out.println("Saboteando sala " + sala.getNombre() + "...");
        sala.setSaboteada(true);
        System.out.println("Sala " + sala.getNombre() + " saboteada!");
    }
}
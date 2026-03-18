public class Ingeniero extends Tripulante{

    public Ingeniero(String nombre) {

        super(nombre, "Ingeniero");
    }

    @Override
    public void habilidadEspecial() {

        System.out.println("El jugador " + this.getId() + ". " + this.getNombre() + " puede reparar salas saboteadas!");
    }

    public void repararSala(Sala sala) {

        if (!sala.isSaboteada()) {

            sala.setSaboteada(true);
            System.out.println("El jugador " + this.getId() + ". " + this.getNombre() + " ha reparado la sala" + sala.getNombre() + "!");

        } else {

            System.out.println("No existen salas saboteadas actualmente.");
        }
    }
}
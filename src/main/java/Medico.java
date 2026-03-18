public class Medico extends Tripulante{

    public Medico(String nombre) {

        super(nombre, "Médico");
    }

    @Override
    public void habilidadEspecial() {

        System.out.println("El jugador " + getId() + ". " + getNombre() + "puede examinar a los tripulantes!");
    }

    public void examinar(Tripulante tripulante) {

        System.out.println("Examinando jugador...");
        System.out.println("El jugador " + tripulante.getId() + ". " + tripulante.getNombre() + " es " + tripulante.getRol() + "!");
        System.out.println("Examen finalizado!");
    }
}
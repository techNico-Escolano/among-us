public class Ingeniero extends Tripulante{

    public Ingeniero(String nombre) {

        super(nombre, "Ingeniero");
    }

    @Override
    public void habilidadEspecial() {}

    public void repararSala(Sala sala) {}
}
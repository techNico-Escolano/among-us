public class Medico extends Tripulante{

    public Medico(String nombre) {

        super(nombre, "Médico");
    }

    @Override
    public void habilidadEspecial() {}

    public void examinar(Tripulante tripulante) {}
}
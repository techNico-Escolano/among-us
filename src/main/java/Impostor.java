public class Impostor extends Tripulante implements Saboteable{

    public Impostor(String nombre) {

        super(nombre, "Impostor");
    }

    @Override
    public void habilidadEspecial() {}
    public void eliminar(Tripulante tripulante) {}


    @Override
    public void sabotear(Sala sala) {

    }
}
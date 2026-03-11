public class Capitan extends Tripulante{

    public Capitan(String nombre) {

        super(nombre, "Capitán");
    }

    @Override
    public void habilidadEspecial() {}

    public void convocarVotacion(Nave nave) {}

    @Override
    public void sabotear(Sala sala) {}

    public void eliminar(Tripulante tripulante) {}

    @Override
    public void realizarTarea(Tarea tarea) {

        super.realizarTarea(tarea);
    }
}
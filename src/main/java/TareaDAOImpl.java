import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class TareaDAOImpl implements TareaDAO{
    @Override
    public void insertar(Tarea tarea) {
        try(PreparedStatement preparedStatement = this.conexion.prepareStatement("INSERT INTO tarea(id_tarea, descripcion, completada, id_tripulante, id_sala) VALUES (?, ? ,?, ?, ?)")){

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Tarea obtener(int id) {
        return null;
    }

    @Override
    public ArrayList<Tarea> obtenerTodos() {
        return null;
    }

    @Override
    public ArrayList<Tarea> obtenerPorTripulante(int idTrip) {
        return null;
    }

    @Override
    public void actualizar(Tarea tarea) {

    }

    @Override
    public void eliminar(int id) {

    }
}

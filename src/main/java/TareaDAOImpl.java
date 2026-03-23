import java.sql.*;
import java.util.ArrayList;

public class TareaDAOImpl implements TareaDAO{
    private Connection conexion;
    private TripulanteDAO tripulanteDAO;
    private SalaDAO salaDAO;

    public TareaDAOImpl(Connection conexion) {
        this.conexion = DBUtil.getInstance().getConexion();
        this.tripulanteDAO = new TripulanteDAOImpl(this.conexion);
        this.salaDAO = new SalaDAOImpl(this.conexion);
    }

    @Override
    public void insertar(Tarea tarea) {
        try(PreparedStatement preparedStatement = this.conexion.prepareStatement("INSERT INTO tarea(descripcion, completada, id_tripulante, id_sala) VALUES (? ,?, ?, ?)")){
            preparedStatement.setString(1, tarea.getDescripcion());
            preparedStatement.setBoolean(2, tarea.isCompletada());
            preparedStatement.setInt(3, tarea.getTripulanteAsignado().getId());
            preparedStatement.setInt(4, tarea.getSala().getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Tarea obtener(int id) {
        String sqlRead = "SELECT * FROM tarea WHERE id_tarea=?";
        try(PreparedStatement preparedStatement= this.conexion.prepareStatement(sqlRead)){
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()){
                    int idTarea = resultSet.getInt("id_tarea");
                    String descripcion = resultSet.getString("descripcion");
                    boolean completada = resultSet.getBoolean("completada");
                    int idTripulante = resultSet.getInt("id_tripulante");
                    int idSala = resultSet.getInt("id_sala");

                    Tripulante tripulanteAsignado = this.tripulanteDAO.obtener(idTripulante);
                    Sala salaAsignada = this.salaDAO.obtener(idSala);

                    return new Tarea(idTarea, descripcion, completada, tripulanteAsignado, salaAsignada);
                }
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<Tarea> obtenerTodos() {
        String sqlReadAll = "SELECT * FROM tarea";
        ArrayList<Tarea> tareas = new ArrayList<>();
        try(
                Statement statement = this.conexion.createStatement();
                ResultSet resultSet = statement.executeQuery(sqlReadAll)
        ){
            while(resultSet.next()){
                int idTarea = resultSet.getInt("id_tarea");
                String descripcion = resultSet.getString("descripcion");
                boolean completada = resultSet.getBoolean("completada");
                int idTripulante = resultSet.getInt("id_tripulante");
                int idSala = resultSet.getInt("id_sala");

                Tripulante tripulante = this.tripulanteDAO.obtener(idTripulante);
                Sala sala = this.salaDAO.obtener(idSala);

                Tarea tarea = new Tarea(idTarea, descripcion, completada, tripulante, sala);
                tareas.add(tarea);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tareas;
    }

    @Override
    public ArrayList<Tarea> obtenerPorTripulante(int idTrip) {
        return null;
    }

    @Override
    public void actualizar(Tarea tarea) {
        String sqlUpdate = "UPDATE tarea SET descripcion=?, completada=?, id_tripulante=?, id_sala=? WHERE id_tarea=?";
        try(PreparedStatement preparedStatement = this.conexion.prepareStatement(sqlUpdate)){
            preparedStatement.setString(1, tarea.getDescripcion());
            preparedStatement.setBoolean(2, tarea.isCompletada());
            preparedStatement.setInt(3, tarea.getTripulanteAsignado().getId());
            preparedStatement.setInt(4, tarea.getSala().getId());
            preparedStatement.setInt(5, tarea.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void eliminar(int id) {
        String sqlDelete = "DELETE FROM tarea WHERE id_tarea = ?";
        try(PreparedStatement preparedStatement = this.conexion.prepareStatement(sqlDelete)){
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

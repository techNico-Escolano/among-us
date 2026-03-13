import java.sql.*;
import java.util.ArrayList;

public class SalaDAOImpl implements SalaDAO{

    private Connection connection;

    public SalaDAOImpl(Connection connection) { this.connection = connection; }

    @Override
    public void insertar(Sala sala) {

        String sqlInsert = "INSERT INTO sala (nombre, saboteada) VALUES (?, ?)";

        try (PreparedStatement pst = this.connection.prepareStatement(sqlInsert)) {

            pst.setString(1, sala.getNombre());
            pst.setBoolean(2, sala.isSaboteada());

            pst.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Sala obtener(int id) {

        String sqlRead = "SELECT * FROM sala WHERE id = ?";

        try (PreparedStatement pst = this.connection.prepareStatement(sqlRead)) {

            pst.setInt(1, id);

            try (ResultSet rs = pst.executeQuery()) {

                if (rs.next()) {

                    int idSala = rs.getInt("id");
                    String nombreSala = rs.getString("nombre");
                    boolean saboteoSala = rs.getBoolean("saboteada");

                    return new Sala(nombreSala);
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public ArrayList<Sala> obtenerTodods() {

        String sqlReadAll = "SELECT * FROM sala";
        ArrayList<Sala> todasSalas = new ArrayList<>();

        try (Statement st = this.connection.createStatement();
             ResultSet rs = st.executeQuery(sqlReadAll)) {

            while (rs.next()) {

                int idSala = rs.getInt("id");
                String nombreSala = rs.getString("nombre");
                boolean saboteoSala = rs.getBoolean("saboteada");

                todasSalas.add(new Sala(nombreSala));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return todasSalas;
    }

    @Override
    public void actualizar(Sala sala) {

        String sqlUpdate = "UPDATE sala SET nombre = ?, saboteada = ? WHERE id = ?";

        try (PreparedStatement pst = this.connection.prepareStatement(sqlUpdate)) {

            pst.setString(1, sala.getNombre());
            pst.setBoolean(2, sala.isSaboteada());
            pst.setInt(3, sala.getId());

            pst.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void eliminar(int id) {

        String sqlDelete = "DELETE FROM sala WHERE id = ?";

        try (PreparedStatement pst = this.connection.prepareStatement(sqlDelete)) {

            pst.setInt(1, id);

            pst.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
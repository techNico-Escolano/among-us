import java.sql.*;
import java.util.ArrayList;

public class TripulanteDAOImpl implements TripulanteDAO{

    private Connection connection;

    public TripulanteDAOImpl(Connection connection) { this.connection = connection; }

    @Override
    public void insertar(Tripulante tripulante) {

        String sqlInsert = "INSERT INTO tripulante (nombre, rol, vivo) VALUES (?, ?, ?)";

        try (PreparedStatement pst = this.connection.prepareStatement(sqlInsert)) {

            pst.setString(1, tripulante.getNombre());
            pst.setString(2, tripulante.getRol());
            pst.setBoolean(3, tripulante.isVivo());

            pst.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Tripulante obtener(int id) {

        String sqlRead = "SELECT * FROM tripulante WHERE id = ?";

        try (PreparedStatement pst = this.connection.prepareStatement(sqlRead)) {

            pst.setInt(1, id);

            try (ResultSet rs = pst.executeQuery()) {

                if (rs.next()) {

                    int idTripulante = rs.getInt("id");
                    String nombreTripulante = rs.getString("nombre");
                    String rolTripulante = rs.getString("rol");
                    boolean vivoTripulante = rs.getBoolean("vivo");

                    if (rolTripulante.equals("Impostor")) {

                        return new Impostor(nombreTripulante);
                    }

                    if (rolTripulante.equals("Capitan")) {

                        return new Capitan(nombreTripulante);
                    }

                    if (rolTripulante.equals("Ingeniero")) {

                        return new Ingeniero(nombreTripulante);
                    }

                    if (rolTripulante.equals("Medico")) {

                        return new Medico(nombreTripulante);
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public ArrayList<Tripulante> obtenerTodos() {

        String sqlReadAll = "SELECT * FROM tripulante";
        ArrayList<Tripulante> todosTripulantes = new ArrayList<>();

        try (Statement st = this.connection.createStatement();
             ResultSet rs = st.executeQuery(sqlReadAll)) {

            while (rs.next()) {

                int idTripulante = rs.getInt("id");
                String nombreTripulante = rs.getString("nombre");
                String rolTripulante = rs.getString("rol");
                boolean vivoTripulante = rs.getBoolean("vivo");

                if (rolTripulante.equals("Impostor")) {

                    todosTripulantes.add(new Impostor(nombreTripulante));
                }

                if (rolTripulante.equals("Capitan")) {

                    todosTripulantes.add(new Capitan(nombreTripulante));
                }

                if (rolTripulante.equals("Ingeniero")) {

                    todosTripulantes.add(new Ingeniero(nombreTripulante));
                }

                if (rolTripulante.equals("Medico")) {

                    todosTripulantes.add(new Medico(nombreTripulante));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return todosTripulantes;
    }

    @Override
    public void actualizar(Tripulante tripulante) {

        String sqlUpdate = "UPDATE tripulante SET nombre = ?, rol = ?, vivo = ? WHERE id = ?";

        try (PreparedStatement pst = this.connection.prepareStatement(sqlUpdate)) {

            pst.setString(1, tripulante.getNombre());
            pst.setString(2, tripulante.getRol());
            pst.setBoolean(3, tripulante.isVivo());
            pst.setInt(4, tripulante.getId());

            pst.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void eliminar(int id) {

        String sqlDelete = "DELETE FROM tripulante WHERE id = ?";

        try (PreparedStatement pst = this.connection.prepareStatement(sqlDelete)) {

            pst.setInt(1, id);

            pst.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
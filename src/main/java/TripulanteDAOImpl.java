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

        String sqlRead = "SELECT * FROM tripulante WHERE id_tripulante = ?";

        try (PreparedStatement pst = this.connection.prepareStatement(sqlRead)) {

            pst.setInt(1, id);

            try (ResultSet rs = pst.executeQuery()) {

                if (rs.next()) {

                    int idTripulante = rs.getInt("id_tripulante");
                    String nombreTripulante = rs.getString("nombre");
                    String rolTripulante = rs.getString("rol");
                    boolean vivoTripulante = rs.getBoolean("vivo");

                    Tripulante t = null;
                    if (rolTripulante.equalsIgnoreCase("Impostor")) {
                        t = new Impostor(nombreTripulante);
                    } else if (rolTripulante.equalsIgnoreCase("Capitan")) {
                        t = new Capitan(nombreTripulante);
                    } else if (rolTripulante.equalsIgnoreCase("Ingeniero")) {
                        t = new Ingeniero(nombreTripulante);
                    } else if (rolTripulante.equalsIgnoreCase("Medico")) {
                        t = new Medico(nombreTripulante);
                    }

                    if (t != null) {
                        t.setId(idTripulante);
                        t.setVivo(vivoTripulante);
                    }

                    return t;
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

                int idTripulante = rs.getInt("id_tripulante");
                String nombreTripulante = rs.getString("nombre");
                String rolTripulante = rs.getString("rol");
                boolean vivoTripulante = rs.getBoolean("vivo");

                Tripulante t = null;
                // Escudo Anti-Mayúsculas y Anti-Tildes activado 🛡️
                if (rolTripulante.equalsIgnoreCase("Impostor")) {
                    t = new Impostor(nombreTripulante);
                } else if (rolTripulante.equalsIgnoreCase("Capitán") || rolTripulante.equalsIgnoreCase("Capitan")) {
                    t = new Capitan(nombreTripulante);
                } else if (rolTripulante.equalsIgnoreCase("Ingeniero")) {
                    t = new Ingeniero(nombreTripulante);
                } else if (rolTripulante.equalsIgnoreCase("Médico") || rolTripulante.equalsIgnoreCase("Medico")) {
                    t = new Medico(nombreTripulante);
                }

                // Si hemos reconocido el rol, le metemos los datos de BD y lo subimos a la nave
                if (t != null) {
                    t.setId(idTripulante);
                    t.setVivo(vivoTripulante);
                    todosTripulantes.add(t); // ¡Ahora está a salvo aquí dentro!
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return todosTripulantes;
    }


    @Override
    public void actualizar(Tripulante tripulante) {

        String sqlUpdate = "UPDATE tripulante SET nombre = ?, rol = ?, vivo = ? WHERE id_tripulante = ?";

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

        String sqlDelete = "DELETE FROM tripulante WHERE id_tripulante = ?";

        try (PreparedStatement pst = this.connection.prepareStatement(sqlDelete)) {

            pst.setInt(1, id);

            pst.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
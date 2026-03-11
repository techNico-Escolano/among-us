import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    private static DBUtil instance;

    private Connection connection;

    private DBUtil(){

        // Ajustar puerto y nombre de la base de datos 👇

        final String DB_URL = "jdbc:mysql://localhost:3306/jdbc";
        final String USER = "root";
        final String PASS = "";

        // Ajustar puerto y nombre de la base de datos 👆

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            this.connection = conn;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static DBUtil getInstance(){
        if (instance == null) {
            instance = new DBUtil();
        }
        return instance;
    }

    public Connection getConexion() { return this.connection; }
}
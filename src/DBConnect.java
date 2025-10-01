import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:squaregames.db";

        try {
            // Charger explicitement le driver SQLite
            Class.forName("org.sqlite.JDBC");

            try (Connection conn = DriverManager.getConnection(url)) {
                if (conn != null) {
                    System.out.println("✅ Connexion à SQLite réussie !");
                }
            }
        } catch (ClassNotFoundException e) {
            System.out.println("❌ Driver SQLite non trouvé : " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("❌ Erreur de connexion : " + e.getMessage());
        }
    }
}

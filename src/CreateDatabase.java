import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDatabase {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:squaregames.db";

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                System.out.println("✅ Base de données créée (ou ouverte) avec succès.");

                // Création de la table users
                String sql = """
                    CREATE TABLE IF NOT EXISTS users (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        username TEXT NOT NULL UNIQUE,
                        password TEXT NOT NULL,
                        avatar TEXT,
                        age INTEGER,
                        is_minor BOOLEAN,
                        parental_control BOOLEAN DEFAULT FALSE
                    );
                """;

                Statement stmt = conn.createStatement();
                stmt.execute(sql);
                System.out.println("✅ Table 'users' créée ou déjà existante.");
            }
        } catch (SQLException e) {
            System.out.println("❌ Erreur : " + e.getMessage());
        }
    }
}

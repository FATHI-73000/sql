import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CreateGameTables {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:squaregames.db";

        try (Connection conn = DriverManager.getConnection(url)) {
            Statement stmt = conn.createStatement();

            // Table des jeux
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS games (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT NOT NULL,
                    min_age INTEGER DEFAULT 0,
                    description TEXT
                );
            """);

            // Plateaux personnalisés
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS boards (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    user_id INTEGER,
                    game_id INTEGER,
                    size TEXT,
                    color TEXT,
                    display_size TEXT,
                    FOREIGN KEY(user_id) REFERENCES users(id),
                    FOREIGN KEY(game_id) REFERENCES games(id)
                );
            """);

            // Jetons achetés
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS tokens (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    user_id INTEGER,
                    amount INTEGER,
                    date TEXT,
                    FOREIGN KEY(user_id) REFERENCES users(id)
                );
            """);

            // Achats
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS purchases (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    user_id INTEGER,
                    item TEXT,
                    token_cost INTEGER,
                    date TEXT,
                    FOREIGN KEY(user_id) REFERENCES users(id)
                );
            """);

            // Sessions de jeux
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS game_sessions (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    user_id INTEGER,
                    game_id INTEGER,
                    start_time TEXT,
                    end_time TEXT,
                    result TEXT,
                    move_history TEXT,
                    FOREIGN KEY(user_id) REFERENCES users(id),
                    FOREIGN KEY(game_id) REFERENCES games(id)
                );
            """);

            // Avatars
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS avatars (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    user_id INTEGER,
                    image_url TEXT,
                    pseudo TEXT,
                    FOREIGN KEY(user_id) REFERENCES users(id)
                );
            """);

            // Pions
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS pions (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    user_id INTEGER,
                    game_id INTEGER,
                    color TEXT,
                    image TEXT,
                    is_favorite INTEGER DEFAULT 0,
                    FOREIGN KEY(user_id) REFERENCES users(id),
                    FOREIGN KEY(game_id) REFERENCES games(id)
                );
            """);

            // Contrôle parental
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS parental_controls (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    child_id INTEGER,
                    parent_id INTEGER,
                    is_approved INTEGER DEFAULT 0,
                    FOREIGN KEY(child_id) REFERENCES users(id),
                    FOREIGN KEY(parent_id) REFERENCES users(id)
                );
            """);

            System.out.println("✅ Toutes les tables ont été créées avec succès !");
        } catch (Exception e) {
            System.out.println("❌ Erreur : " + e.getMessage());
        }
    }
}

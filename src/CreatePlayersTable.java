import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CreatePlayersTable {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:game.db";

        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS Players (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT NOT NULL," +
                    "score INTEGER NOT NULL" +
                    ");";

            stmt.execute(sql);
            System.out.println("? Table 'Players' créée ou déjà existante.");

            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Erreur lors de la création de la table : " + e.getMessage());
            e.printStackTrace();
        }
    }
}

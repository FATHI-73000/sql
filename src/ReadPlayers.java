import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ReadPlayers {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:game.db";

        try {
            // Charger le driver SQLite (optionnel depuis Java 6+, mais souvent recommandé)
            Class.forName("org.sqlite.JDBC");

            // Connexion à la base
            Connection conn = DriverManager.getConnection(url);
            System.out.println("? Connexion établie.");

            // Création d'un statement pour exécuter la requête SQL
            Statement stmt = conn.createStatement();

            // Requête SQL pour lire toutes les données de la table Players
            String sql = "SELECT * FROM Players";

            // Exécution de la requête
            ResultSet rs = stmt.executeQuery(sql);

            // Parcourir les résultats et afficher
            while (rs.next()) {
                int id = rs.getInt("id"); // suppose que la colonne id existe
                String name = rs.getString("name"); // suppose que la colonne name existe
                int score = rs.getInt("score"); // suppose que la colonne score existe
                System.out.println("Player ID: " + id + ", Name: " + name + ", Score: " + score);
            }

            // Fermeture des ressources
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Erreur lors de la lecture : " + e.getMessage());
            e.printStackTrace();
        }
    }
}

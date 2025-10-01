import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertGameData {

    public static void main(String[] args) {
        String url = "jdbc:sqlite:squaregames.db";  // nom correct de ta base
        String sqlInsert = "INSERT INTO Players (id, name, score) VALUES (?, ?, ?)";

        // Exemple de données à insérer
        Object[][] players = {
                {1, "Alice", 1500},
                {2, "Bob", 1200},
                {3, "Charlie", 1800}
        };

        try {
            // Charger le driver SQLite (optionnel avec les versions récentes)
            Class.forName("org.sqlite.JDBC");

            // Connexion à la base
            try (Connection conn = DriverManager.getConnection(url)) {
                if (conn != null) {
                    System.out.println("? Connexion établie.");

                    // Préparer la requête
                    try (PreparedStatement pstmt = conn.prepareStatement(sqlInsert)) {
                        for (Object[] player : players) {
                            pstmt.setInt(1, (int) player[0]);
                            pstmt.setString(2, (String) player[1]);
                            pstmt.setInt(3, (int) player[2]);
                            pstmt.executeUpdate();
                        }
                        System.out.println("? Utilisateurs insérés avec succès.");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'insertion : " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Driver SQLite non trouvé : " + e.getMessage());
        }
    }
}

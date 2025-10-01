import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertUser {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:squaregames.db";

        String sql = "INSERT INTO users(username, password, avatar, age, is_minor, parental_control) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Exemple d’utilisateur 1
            pstmt.setString(1, "fathi");
            pstmt.setString(2, "1234");
            pstmt.setString(3, "avatar1.png");
            pstmt.setInt(4, 14);
            pstmt.setBoolean(5, true);
            pstmt.setBoolean(6, true);
            pstmt.executeUpdate();

            // Exemple d’utilisateur 2
            pstmt.setString(1, "parent");
            pstmt.setString(2, "azerty");
            pstmt.setString(3, "avatar2.png");
            pstmt.setInt(4, 40);
            pstmt.setBoolean(5, false);
            pstmt.setBoolean(6, false);
            pstmt.executeUpdate();

            System.out.println("✅ Utilisateurs insérés avec succès.");

        } catch (SQLException e) {
            System.out.println("❌ Erreur lors de l'insertion : " + e.getMessage());
        }
    }
}

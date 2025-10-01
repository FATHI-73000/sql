import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class InsertPlayers {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:game.db";

        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();

            String sql1 = "INSERT INTO Players (name, score) VALUES ('Alice', 1000);";
            String sql2 = "INSERT INTO Players (name, score) VALUES ('Bob', 850);";

            stmt.executeUpdate(sql1);
            stmt.executeUpdate(sql2);

            System.out.println("? Utilisateurs insérés avec succès.");

            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Erreur lors de l'insertion : " + e.getMessage());
            e.printStackTrace();
        }
    }
}

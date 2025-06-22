package BoardGame;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public static Connection getConnection() throws Exception {
        String url = "jdbc:mysql://localhost:3306/adivina_quien?useSSL=false&serverTimezone=UTC";
        String user = "root"; // Cambia si tu MySQL tiene otro usuario
        String password = ""; // Cambia si tu MySQL tiene contraseña

        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, user, password);
    }
}

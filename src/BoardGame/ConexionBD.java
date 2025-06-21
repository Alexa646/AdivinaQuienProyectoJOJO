package BoardGame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConexionBD {
    public static Connection conectar() {
        try {
            String url = "jdbc:mysql://localhost:3306/NomDePersonaje";
            String user = "root";
            String password = "";
            Connection conn = DriverManager.getConnection(url, user, password);
            return conn;
        } catch (SQLException e) {
            return null;
        }
    }
    
    public static List<Personaje> obtenerTodasLosPersonajes() {
        List<Personaje> lista = new ArrayList<>();
        String sql = "SELECT * FROM Personajes";

        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) { 

            while (rs.next()) {
                Personaje perso = new Personaje(
                        rs.getInt("id"),
                        rs.getString("Nombre")
                        
                );
                lista.add(perso);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

}
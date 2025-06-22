package BoardGame;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class PartidaDAO {
    public static void guardarPartida(String jugador1, String jugador2, String ganador, String personajeGanador, int duracion) {
        String sql = "INSERT INTO partidas (jugador1, jugador2, ganador, personaje_ganador, fecha, duracion) VALUES (?, ?, ?, ?, NOW(), ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, jugador1);
            stmt.setString(2, jugador2);
            stmt.setString(3, ganador);
            stmt.setString(4, personajeGanador);
            stmt.setInt(5, duracion);

            stmt.executeUpdate();
            System.out.println("Partida guardada con éxito.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

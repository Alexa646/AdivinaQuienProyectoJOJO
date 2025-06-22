package BoardGame;

import java.sql.*;

public class PartidaConsulta {

    public static void buscarPorNombre(String nombre) {
        String sql = "SELECT * FROM partidas WHERE jugador1 = ? OR jugador2 = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, nombre);
            stmt.setString(2, nombre);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.println("Ganador: " + rs.getString("ganador") +
                    ", Personaje: " + rs.getString("personaje_ganador") +
                    ", Fecha: " + rs.getTimestamp("fecha") +
                    ", Duración: " + rs.getInt("duracion") + " segundos");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void mostrarOrdenadoPorDuracion() {
        String sql = "SELECT * FROM partidas ORDER BY duracion ASC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                System.out.println("Partida entre " + rs.getString("jugador1") + " y " + rs.getString("jugador2") +
                    " | Ganador: " + rs.getString("ganador") +
                    " | Duración: " + rs.getInt("duracion") + " segundos");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
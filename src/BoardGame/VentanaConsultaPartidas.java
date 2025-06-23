package BoardGame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class VentanaConsultaPartidas extends JFrame {

    private JTable tablaPartidas;
    private DefaultTableModel modeloTabla;

    public VentanaConsultaPartidas() {
        setTitle("Consulta de Partidas");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        modeloTabla = new DefaultTableModel();
        modeloTabla.setColumnIdentifiers(new Object[]{
            "Jugador", "Ganador", "Personaje", "Fecha", "Duración (s)"
        });

        tablaPartidas = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaPartidas);

        JButton btnBuscarNombre = new JButton("Buscar por nombre");
        JButton btnOrdenarDuracion = new JButton("Ordenar por duración");

        btnBuscarNombre.addActionListener(e -> buscarPorNombre());
        btnOrdenarDuracion.addActionListener(e -> ordenarPorDuracion());

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnBuscarNombre);
        panelBotones.add(btnOrdenarDuracion);

        add(panelBotones, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        ordenarPorDuracion(); // ⬅ Carga las partidas al abrir

    }

private void buscarPorNombre() {
    String nombre = JOptionPane.showInputDialog(this, "Ingresa el nombre del jugador:");
    if (nombre != null && !nombre.isEmpty()) {
        modeloTabla.setRowCount(0); // Limpiar tabla

        String sql = "SELECT * FROM partidas WHERE jugador1 = ?"; // Solo filtramos por jugador1
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nombre);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // Mostramos directamente jugador1 (ya no hay comparación con jugador2)
                modeloTabla.addRow(new Object[]{
                    rs.getString("jugador1"),
                    rs.getString("ganador"),
                    rs.getString("personaje_ganador"),
                    rs.getTimestamp("fecha"),
                    rs.getInt("duracion")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al consultar: " + e.getMessage());
        }
    }
}


    private void ordenarPorDuracion() {
        modeloTabla.setRowCount(0); // Limpiar tabla
        String sql = "SELECT * FROM partidas ORDER BY duracion ASC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                // Mostramos solo jugador1 como referencia
                modeloTabla.addRow(new Object[]{
                    rs.getString("jugador1"),
                    rs.getString("ganador"),
                    rs.getString("personaje_ganador"),
                    rs.getTimestamp("fecha"),
                    rs.getInt("duracion")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al consultar: " + e.getMessage());
        }
    }
}


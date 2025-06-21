package BoardGame;

import BoardGame.ui.*;
import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {

    private CardLayout cardLayout;
    private JPanel contenedor;

    public VentanaPrincipal() {
        setTitle("Adivina Quién");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        contenedor = new JPanel(cardLayout);

        contenedor.add(new PantallaMenu(this), "menu");
        contenedor.add(new PantallaRegistro(this), "juego");
        contenedor.add(new PantallaCreditos(this), "creditos");
        contenedor.add(new PantallaInstrucciones(this), "instrucciones");

        add(contenedor);
        cardLayout.show(contenedor, "menu");
        
    }

    public void mostrarPantalla(String nombre) {
        cardLayout.show(contenedor, nombre);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new VentanaPrincipal().setVisible(true);
        });
    }
}



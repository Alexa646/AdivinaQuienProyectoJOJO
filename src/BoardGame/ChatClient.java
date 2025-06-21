import java.io.*;
import java.net.*;
import javax.swing.*; // Opcional: para una interfaz gráfica simple

public class ChatClient {

    private static final String SERVER_ADDRESS = "192.168.1.73"; // Dirección IP del servidor
    private static final int SERVER_PORT = 12345; // Puerto del servidor

    private BufferedReader in;
    private PrintWriter out;
    private JFrame frame = new JFrame("Chat de Java");
    private JTextArea messageArea = new JTextArea(20, 50);
    private JTextField textField = new JTextField(40);

    public ChatClient() {
        // Configuración de la interfaz gráfica (Swing)
        messageArea.setEditable(false);
        frame.getContentPane().add(new JScrollPane(messageArea), "Center");
        frame.getContentPane().add(textField, "South");
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        textField.addActionListener(e -> {
            out.println(textField.getText());
            textField.setText("");
        });
    }

    private String getUserName() {
        return JOptionPane.showInputDialog(
                frame,
                "Elige un nombre de usuario:",
                "Nombre de Usuario",
                JOptionPane.PLAIN_MESSAGE);
    }

    public void run() {
        try {
            Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            while (true) {
                String line = in.readLine();
                if (line == null) {
                    break; // Servidor desconectado
                }
                if (line.startsWith("SUBMITNAME")) {
                    String name = getUserName();
                    out.println(name);
                } else if (line.startsWith("NAMEACCEPTED")) {
                    textField.setEditable(true);
                } else {
                    messageArea.append(line + "\n");
                }
            }
        } catch (IOException e) {
            messageArea.append("Error de conexión: " + e.getMessage() + "\n");
            System.err.println("Error de conexión: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (in != null) in.close();
                if (out != null) out.close();
            } catch (IOException e) {
                System.err.println("Error al cerrar streams: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        ChatClient client = new ChatClient();
        client.run();
    }
}
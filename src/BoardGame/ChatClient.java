package BoardGame;

import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.*;
import java.util.concurrent.Semaphore; 

public class ChatClient {
    
    private static final String SERVER_ADDRESS = "192.168.1.22";
    private static final int SERVER_PORT = 12345;

    private BufferedReader in;
    private PrintWriter out;
    public JFrame frame = new JFrame("Chat de Java"); 
    private JTextArea messageArea = new JTextArea(20, 50);
    private JTextField textField = new JTextField(40);
    private Board boardReference;
    private String userName; 
    private ExecutorService messageSenderExecutor = Executors.newSingleThreadExecutor();

    // Constructor existente
    public ChatClient() {
        messageArea.setEditable(false);
        frame.getContentPane().add(new JScrollPane(messageArea), "Center");
        frame.getContentPane().add(textField, "South");
        frame.pack();
        
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        
        textField.addActionListener(e -> {
            // Llama al nuevo método para enviar el mensaje desde el JTextField
            sendMessage(textField.getText());
            textField.setText("");
        });
    }
    
    public void setBoardReference(Board board) {
        this.boardReference = board;
    }
    

    // =========================================================================
    // ¡NUEVO MÉTODO PARA ENVIAR MENSAJES PROGRAMÁTICAMENTE!
    // =========================================================================
    /**
     * Envía un mensaje al servidor del chat.
     * Es seguro de llamar desde cualquier hilo, ya que usa SwingUtilities.invokeLater
     * para manejar la lógica de red si se llama desde el EDT,
     * o simplemente envía si se llama desde el hilo del chat.
     *
     * @param message El String que se desea enviar al chat.
     */
    public void sendMessage(String message) {
        // Debemos asegurarnos de que 'out' esté inicializado antes de intentar enviar.
        // Y que la operación de red no bloquee el Event Dispatch Thread (EDT).
        if (out != null) {
            // Si ya estamos en el hilo de red (donde se ejecuta run()), podemos enviar directamente.
            // Si estamos en el EDT (ej. desde un botón), podríamos necesitar un hilo pequeño.
            // Para simplificar y asegurar, siempre usamos un nuevo hilo para la operación de red.
            new Thread(() -> {
                try {
                    out.println(message);
                    // Opcional: Mostrar el mensaje enviado en el propio messageArea del cliente
                    // SwingUtilities.invokeLater(() -> messageArea.append("Yo: " + message + "\n"));
                } catch (Exception e) {
                    SwingUtilities.invokeLater(() -> messageArea.append("Error al enviar mensaje: " + e.getMessage() + "\n"));
                    System.err.println("Error al enviar mensaje: " + e.getMessage());
                }
            }).start();
        } else {
            // Si 'out' es nulo, significa que no estamos conectados todavía.
            SwingUtilities.invokeLater(() -> messageArea.append("Error: No conectado al servidor. No se puede enviar mensaje.\n"));
        }
    }

    private String getUserName() {
        final String[] name = {null};
        Semaphore semaphore = new Semaphore(0);
        SwingUtilities.invokeLater(() -> {
            name[0] = JOptionPane.showInputDialog(
                frame,
                "Elige un nombre de usuario:",
                "Nombre de Usuario",
                JOptionPane.PLAIN_MESSAGE);
            semaphore.release();
        });
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Interrupción al esperar el nombre de usuario: " + e.getMessage());
        }

        this.userName = name[0]; 
        return name[0];
    }

 
    public String getMyUserName() {
        return userName;
    }

    public void run() {
        try {
            Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            SwingUtilities.invokeLater(() -> {
                messageArea.append("Conectado al servidor.\n");
                textField.setEditable(true);
            });

            while (true) {
                String line = in.readLine();
                if (line == null) {
                    SwingUtilities.invokeLater(() -> {
                        messageArea.append("Servidor desconectado. Saliendo del chat.\n");
                        textField.setEditable(false);
                    });
                    break;
                }
                
                // =============================================================
                // ¡Manejar comandos especiales aquí!
                // =============================================================
                if (line.startsWith("/CMD_SEED ")) {
                    try {
                        long receivedSeed = Long.parseLong(line.substring("/CMD_SEED ".length()));
                        // Llamar a un método en Board para usar esta semilla
                        // Esto requiere una referencia a la instancia de Board
                        // SOLUCIÓN: Pasar una referencia al Board al ChatClient
                        SwingUtilities.invokeLater(() -> { // Asegurarse de que esto se ejecuta en el EDT
                            messageArea.append("[Sistema]: Semilla recibida: " + receivedSeed + "\n");
                            // Llamar al método en el Board para aplicar la semilla
                            // Necesitas una forma de pasar la referencia del Board al ChatClient
                            if (boardReference != null) { // boardReference es una nueva variable que definiremos
                                boardReference.applyReceivedSeed(receivedSeed);
                            }
                        });
                    } catch (NumberFormatException e) {
                        SwingUtilities.invokeLater(() -> messageArea.append("[Sistema]: Error al parsear semilla: " + line + "\n"));
                    }
                }else if (line.startsWith("/GAME_ACTION HIT ")) {
                    // Esperamos un formato como: /GAME_ACTION HIT <value> <userNameOfAction>
                    String[] parts = line.split(" ", 4); // Dividimos en 4 partes como máximo
                    if (parts.length >= 4) { // Aseguramos que tenemos valor y nombre de usuario
                        try {
                            int actionValue = Integer.parseInt(parts[2]);
                            String userNameOfAction = parts[3]; // El nombre del jugador que realizó la acción

                            SwingUtilities.invokeLater(() -> {
                                if (boardReference != null) {
                                    // Llamar al método en el Board para manejar la acción HIT con el valor y el nombre
                                    boardReference.handleHitAction(actionValue, userNameOfAction);
                                }
                                messageArea.append("[Juego]: " + userNameOfAction + " realizó una acción HIT (Valor: " + actionValue + ")\n");
                            });
                        } catch (NumberFormatException e) {
                            SwingUtilities.invokeLater(() -> messageArea.append("[Sistema]: Error al parsear valor de acción HIT: " + line + "\n"));
                        }
                    } else {
                        SwingUtilities.invokeLater(() -> messageArea.append("[Sistema]: Formato incorrecto para /GAME_ACTION HIT: " + line + "\n"));
                    }
                }
                // =============================================================
                else if (line.startsWith("SUBMITNAME")) {
                    String name = getUserName();
                    if (name != null && !name.trim().isEmpty()) {
                        out.println(name);
                    } else {
                        SwingUtilities.invokeLater(() -> messageArea.append("Nombre de usuario no proporcionado. Desconectando.\n"));
                        break;
                    }
                } else if (line.startsWith("NAMEACCEPTED")) {
                    SwingUtilities.invokeLater(() -> {
                        textField.setEditable(true);
                        messageArea.append("Nombre aceptado. ¡Bienvenido al chat!\n");
                    });
                } else {
                    String finalLine = line;
                    SwingUtilities.invokeLater(() -> messageArea.append(finalLine + "\n"));
                }
            }
        } catch (IOException e) {
            SwingUtilities.invokeLater(() -> {
                messageArea.append("Error de conexión: " + e.getMessage() + "\n");
                textField.setEditable(false);
            });
            System.err.println("Error de conexión: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (in != null) in.close();
                if (out != null) out.close();
                SwingUtilities.invokeLater(() -> {
                    if (frame.isVisible()) {
                        frame.dispose();
                    }
                });
            } catch (IOException e) {
                System.err.println("Error al cerrar streams: " + e.getMessage());
            }
        }
    }
    

}
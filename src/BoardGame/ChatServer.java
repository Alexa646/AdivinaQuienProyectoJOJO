package BoardGame;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*; // Para manejar el pool de hilos

public class ChatServer {

    private static final int PORT = 12345; // Puerto para la comunicación
    private static Set<PrintWriter> clientWriters = new HashSet<>(); // Para enviar mensajes a todos los clientes
    private static ExecutorService pool = Executors.newFixedThreadPool(10); // Pool de hilos para clientes

    public static void main(String[] args) {
        System.out.println("Servidor de chat iniciado en el puerto " + PORT);
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                // Espera por nuevas conexiones de clientes
                Socket clientSocket = serverSocket.accept();
                System.out.println("Nuevo cliente conectado: " + clientSocket);

                // Crea un nuevo hilo para manejar este cliente
                pool.execute(new ClientHandler(clientSocket));
            }
        } catch (IOException e) {
            System.err.println("Error del servidor: " + e.getMessage());
            e.printStackTrace();
        } finally {
            pool.shutdown(); // Apaga el pool de hilos cuando el servidor termina
        }
    }

    // Método para retransmitir un mensaje a todos los clientes conectados
    public static void broadcastMessage(String message) {
        for (PrintWriter writer : clientWriters) {
            writer.println(message);
        }
    }

    // Clase interna para manejar cada cliente en un hilo separado
    private static class ClientHandler implements Runnable {
        private Socket clientSocket;
        private BufferedReader in;
        private PrintWriter out;
        private String userName; // Para identificar al usuario

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        @Override
        public void run() {
            try {
                // Obtener streams de entrada y salida
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new PrintWriter(clientSocket.getOutputStream(), true); // 'true' para autoFlush

                // Pedir el nombre de usuario al cliente
                out.println("SUBMITNAME"); // Mensaje especial para que el cliente sepa que debe enviar el nombre
                userName = in.readLine();
                if (userName == null) {
                    return; // El cliente se desconectó antes de enviar el nombre
                }
                System.out.println("Usuario '" + userName + "' conectado.");
                broadcastMessage(userName + " se ha unido al chat.");

                // Añadir el escritor del cliente a la lista global
                synchronized (clientWriters) { // Sincronizar para evitar problemas de concurrencia
                    clientWriters.add(out);
                }

                String message;
                while ((message = in.readLine()) != null) {
                    System.out.println("Mensaje de " + userName + ": " + message);
                    broadcastMessage(userName + ": " + message);
                }

            } catch (IOException e) {
                System.err.println("Error de cliente " + userName + ": " + e.getMessage());
            } finally {
                if (userName != null) {
                    System.out.println("Usuario '" + userName + "' se ha desconectado.");
                    broadcastMessage(userName + " ha dejado el chat.");
                }
                if (out != null) {
                    synchronized (clientWriters) {
                        clientWriters.remove(out); // Eliminar el escritor del cliente
                    }
                }
                try {
                    clientSocket.close(); // Cerrar el socket del cliente
                } catch (IOException e) {
                    System.err.println("Error al cerrar el socket del cliente: " + e.getMessage());
                }
            }
        }
    }
}
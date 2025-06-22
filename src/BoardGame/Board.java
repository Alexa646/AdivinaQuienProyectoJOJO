
package BoardGame;

import java.util.Random;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import java.util.List;
import java.util.Map;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.Timer;
import javax.swing.JButton; 

public class Board extends javax.swing.JFrame {
    
    
     private javax.swing.Timer timer;
    private int segundosTranscurridos = 0;
    private ChatClient activeChatClient;
    private boolean musicaReproduciendo = false;
    private Clip clip;
    private long posicionPausa = 0; // Guarda la posición cuando se pausa
    private final String rutaMusica = "/sounds/cancion.wav";
    
    ArrayList<String> mylist; 
    private static final String BACK_POKER_IMAGE_PATH = "/Images/BackPoker.jpg";
    private JLabel[] labels;
    
    private boolean gameStarted = false; // Indica si el juego ya ha sido iniciado por alguna semilla
    
    public void RNG() {
    RNG(System.nanoTime()); // Llama al nuevo RNG con una semilla aleatoria por defecto
}

public void RNG(long seed) { // Nuevo método RNG que acepta una semilla
    labels = new JLabel[]{
        jLabel1, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6, jLabel7, jLabel8, jLabel9, jLabel10,
        jLabel11, jLabel12, jLabel13, jLabel14, jLabel15, jLabel16, jLabel17, jLabel18,
        jLabel19, jLabel20, jLabel21, jLabel22, jLabel23, jLabel26, jLabel27
    };

    mylist = new ArrayList<String>();
    // ... (rellena mylist del 1 al 34 como lo tienes) ...
    mylist.add("1");
    mylist.add("2");
    mylist.add("3");
    mylist.add("4");
    mylist.add("5");
    mylist.add("6");
    mylist.add("7");
    mylist.add("8");
    mylist.add("9");
    mylist.add("10");
    mylist.add("11");
    mylist.add("12");
    mylist.add("13");
    mylist.add("14");
    mylist.add("15");
    mylist.add("16");
    mylist.add("17");
    mylist.add("18");
    mylist.add("19");
    mylist.add("20");
    mylist.add("21");
    mylist.add("22");
    mylist.add("23");
    mylist.add("24");
    mylist.add("25");
    mylist.add("26");
    mylist.add("27");
    mylist.add("28");
    mylist.add("29");
    mylist.add("30");
    mylist.add("31");
    mylist.add("32");
    mylist.add("33");
    mylist.add("34");
    // ... hasta mylist.add("34");

    System.out.println("Original List : \n" + mylist);

    // =================================================================
    // ¡Cambio clave aquí! Usa la semilla para Collections.shuffle
    // =================================================================
    Collections.shuffle(mylist, new Random(seed)); // Baraja usando la semilla

    System.out.println("\nShuffled List (with seed " + seed + "): \n" + mylist);

    // =================================================================
    // Usa la misma semilla para el Random r para la imagen del "chus" (si aplica)
    // =================================================================
    Random r = new Random(seed); // Usa la misma semilla
    int r1 = r.nextInt(24);

    for (int i = 0; i < 24; i++) {
        System.out.println("Valor de i: " + i);
        String imagePath = "/Images/" + mylist.get(i) + ".png";
        String imageIdentifier = mylist.get(i);
        ImageIcon originalIcon = new ImageIcon(getClass().getResource(imagePath));
        Image image = originalIcon.getImage();
        Image scaledImage = image.getScaledInstance(labels[i].getWidth(), labels[i].getHeight(), Image.SCALE_SMOOTH);
        labels[i].setIcon(new ImageIcon(scaledImage));
        labels[i].setName(imageIdentifier);

        if (r1 == i) { // Esta es tu lógica para el lblPersonajeJugador (el "chus")
            ImageIcon personajeIcon = new ImageIcon(getClass().getResource(imagePath));
            Image personajeImg = personajeIcon.getImage();
            Image scaledPersonaje = personajeImg.getScaledInstance(
                    lblPersonajeJugador.getWidth(),
                    lblPersonajeJugador.getHeight(),
                    Image.SCALE_SMOOTH
            );
            lblPersonajeJugador.setIcon(new ImageIcon(scaledPersonaje));
        }
    }
}
    
    public void RNGchus()
    {
        labels = new JLabel[]{
        jLabel1,jLabel2,jLabel3,jLabel4,jLabel5,jLabel6,jLabel7,jLabel8,jLabel9,jLabel10,
        jLabel11, jLabel12,jLabel13,jLabel14,jLabel15,jLabel16,jLabel17,jLabel18,
        jLabel19,jLabel20,jLabel21,jLabel22,jLabel23,jLabel26,jLabel27
        };
        
        Random r= new Random();
        int r1 = r.nextInt(24);
        for (int i = 0; i < 24; i++) {
            System.out.println("Valor de i: " + i);
            String imagePath = "/Images/" + mylist.get(i) + ".png";
            String imageIdentifier = mylist.get(i);
            ImageIcon originalIcon = new ImageIcon(getClass().getResource(imagePath));
            Image image = originalIcon.getImage();
            Image scaledImage = image.getScaledInstance(labels[i].getWidth(), labels[i].getHeight(), Image.SCALE_SMOOTH);
            if(r1==i)
            {
                originalIcon = new ImageIcon(getClass().getResource(imagePath));
                image = originalIcon.getImage();
                scaledImage = image.getScaledInstance(labels[24].getWidth(), labels[24].getHeight(), Image.SCALE_SMOOTH);
                labels[24].setIcon(new ImageIcon(scaledImage));
                
                 // Actualizar imagen en el panel superior
                ImageIcon personajeIcon = new ImageIcon(getClass().getResource(imagePath));
                Image personajeImg = personajeIcon.getImage();
                Image scaledPersonaje = personajeImg.getScaledInstance(
                        lblPersonajeJugador.getWidth(),
                        lblPersonajeJugador.getHeight(),
                        Image.SCALE_SMOOTH
                );
                lblPersonajeJugador.setIcon(new ImageIcon(scaledPersonaje));
            }
        }
    }

    public Board() {
        initComponents();
        labels = new JLabel[]{
            jLabel1, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6, jLabel7, jLabel8, jLabel9, jLabel10,
            jLabel11, jLabel12, jLabel13, jLabel14, jLabel15, jLabel16, jLabel17, jLabel18,
            jLabel19, jLabel20, jLabel21, jLabel22, jLabel23, jLabel26, jLabel27
        };

        for (JLabel label : labels) {
            if (label != null) {
                label.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent evt) {
                        // When a JLabel is clicked, call the fliperino method with the clicked JLabel
                        fliperino((JLabel) evt.getSource());
                    }
                });
            }
        }
        setDefaultImages();
        scaleInformationButtonIcon(); 
        
        //elementos
        actualizarFecha();
        iniciarTemporizador();
        //lblNombreJugadorsetText("Jugador: " + nombreJugador);
        cargarMusica();
        jButton1.setEnabled(true); 
    }
                                            

    public void applyReceivedSeed(long seed) {
        if (!gameStarted) { // Solo aplica la semilla si el juego no ha sido iniciado localmente
            System.out.println("Board recibió semilla del chat: " + seed);
            RNG(seed); // Llama a tu función RNG con la semilla recibida
            LlenarTabla(); // Vuelve a llenar la tabla
            gameStarted = true; // Marca el juego como iniciado
            jButton1.setEnabled(false); // Deshabilita el botón de "Comenzar" para evitar que se genere otra semilla
            // Opcional: podrías mostrar un mensaje en la UI para el usuario indicando que el juego ha comenzado.
        } else {
            System.out.println("El juego ya está iniciado. Ignorando semilla recibida: " + seed);
        }
    }
    
    // El método que envía mensajes al chat (que ya hemos agregado)
    public void sendGameMessage(String message) {
        if (activeChatClient != null && activeChatClient.frame.isVisible()) {
            activeChatClient.sendMessage("[Juego]: " + message); // Puedes personalizar el prefijo
        } else {
            System.out.println("No se pudo enviar el mensaje del juego: El chat no está activo.");
            // Opcional: podrías mostrar un JOptionPane si el chat no está abierto
            // JOptionPane.showMessageDialog(this, "El chat no está abierto.", "Error de Envío", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void scaleInformationButtonIcon() {
    String imagePath = "/Images/clear-information-icon-070831--icons-etc-30.png";
    try {
        ImageIcon originalIcon = new ImageIcon(getClass().getResource(imagePath));
        Image image = originalIcon.getImage();

        // Get the current width and height of the button
        int buttonWidth = jButtonInformation.getWidth();
        int buttonHeight = jButtonInformation.getHeight();

        // Ensure the button has a size before trying to scale.
        // If the button hasn't been laid out yet, its width/height might be 0.
        // You might need to call this after the JFrame is visible or after a pack().
        if (buttonWidth == 0 || buttonHeight == 0) {
            // As a fallback, you can set a default size or defer this call.
            // For now, let's just make sure it's not 0 to avoid errors.
            // A common practice is to call this after the frame is packed or set visible.
            // For immediate setup, you might need to give the button preferred size or similar.
            System.out.println("Warning: jButtonInformation has 0 width or height. Icon might not scale correctly initially.");
            // You can set a default size for scaling if the button isn't yet rendered
            buttonWidth = 30; // Example default width
            buttonHeight = 30; // Example default height
        }

        Image scaledImage = image.getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_SMOOTH);
        jButtonInformation.setIcon(new ImageIcon(scaledImage));
    } catch (NullPointerException e) {
        System.err.println("Error: Information icon not found at " + imagePath);
    }
}
    
    private void setDefaultImages() {
        // Array of all JLabels you want to set the image for
        labels = new JLabel[]{
            jLabel1, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6, jLabel7, jLabel8, jLabel9, jLabel10,
            jLabel11, jLabel12, jLabel13, jLabel14, jLabel15, jLabel16, jLabel17, jLabel18,
            jLabel19, jLabel20, jLabel21, jLabel22, jLabel23, jLabel26, jLabel27
        };

        String defaultImagePath = "/Images/BackPoker.jpg"; // Change this to your desired default image
        ImageIcon originalIcon = null;
        Image image = null;
        Image scaledImage = null;

        try {
            originalIcon = new ImageIcon(getClass().getResource(defaultImagePath));
            image = originalIcon.getImage();
        } catch (NullPointerException e) {
            System.err.println("Error: default_image.png not found at " + defaultImagePath);
            
            return; 
        }

        for (JLabel label : labels) {
            if (label != null) { 
                
                scaledImage = image.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
                label.setIcon(new ImageIcon(scaledImage));
            }
        }
    }
    
    //fecha
    private void actualizarFecha() {
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        lblfecha.setText(fechaActual.format(formatter));
    }
    
        //temporizador para duracion
    private void iniciarTemporizador() {
        timer = new Timer(1000, e -> {
            segundosTranscurridos++;
            int minutos = segundosTranscurridos / 60;
            int segundos = segundosTranscurridos % 60;
            lbltemporizador.setText(String.format("%02d:%02d", minutos, segundos));
        });

        timer.start();
    }
    
        //Metodo cargar Musica
    private void cargarMusica() {
        if (clip != null) {
            
            return;
        }

        try {
            
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
                    getClass().getResource(rutaMusica));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            // Iniciar la música automáticamente
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            musicaReproduciendo = true;
            btnMusica.setIcon(new ImageIcon(getClass().getResource("/images/sonido.png")));

            
            System.out.println("DEBUG: musicaReproduciendo = " + musicaReproduciendo);

        } catch (Exception e) {
            System.err.println("ERROR al cargar música: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    // Método para controlar la música
    private void Musica() {
        System.out.println("DEBUG: Método Musica() llamado");
        System.out.println("DEBUG: Estado actual musicaReproduciendo = " + musicaReproduciendo);
        System.out.println("DEBUG: Clip es null? " + (clip == null));

        if (clip != null) {
            System.out.println("DEBUG: Clip está corriendo? " + clip.isRunning());
            System.out.println("DEBUG: Posición actual: " + clip.getMicrosecondPosition());
        }

        try {
            if (clip == null) {
                System.out.println("DEBUG: Clip es null, cargando música...");
                cargarMusica();
                return; // Salir después de cargar, el usuario tendrá que hacer clic otra vez
            }

            if (musicaReproduciendo) {
                System.out.println("DEBUG: Pausando música...");
                // Pausar
                if (clip.isRunning()) {
                    posicionPausa = clip.getMicrosecondPosition();
                    clip.stop();
                    System.out.println("DEBUG: Música pausada en posición: " + posicionPausa);
                }
                btnMusica.setIcon(new ImageIcon(getClass().getResource("/images/sin-sonido.png")));
                musicaReproduciendo = false;
                System.out.println("DEBUG: Estado cambiado a pausado");

            } else {
                System.out.println("DEBUG: Reanudando música...");
                // Reanudar
                if (posicionPausa > 0) {
                    System.out.println("DEBUG: Reanudando desde posición: " + posicionPausa);
                    clip.setMicrosecondPosition(posicionPausa);
                    // Usar loop en lugar de start para continuar el bucle
                    clip.loop(Clip.LOOP_CONTINUOUSLY);
                } else {
                    System.out.println("DEBUG: Iniciando desde el principio con loop");
                    clip.loop(Clip.LOOP_CONTINUOUSLY);
                }
                btnMusica.setIcon(new ImageIcon(getClass().getResource("/images/sonido.png")));
                musicaReproduciendo = true;
                System.out.println("DEBUG: Estado cambiado a reproduciendo");
            }

            System.out.println("DEBUG: Nuevo estado musicaReproduciendo = " + musicaReproduciendo);
            btnMusica.repaint();

        } catch (Exception e) {
            System.out.println("ERROR en método Musica(): " + e.getMessage());
            e.printStackTrace();
        }
    }
    
        public void cerrarRecursos() {
        if (clip != null) {
            clip.stop();
            clip.close();
        }
    }



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListNames = new javax.swing.JList<>();
        jButtonAzar = new javax.swing.JButton();
        jButtonInformation = new javax.swing.JButton();
        jPanel26 = new javax.swing.JPanel();
        lblPersonajeJugador = new javax.swing.JLabel();
        lblfecha = new javax.swing.JLabel();
        lblNombreJugador = new javax.swing.JLabel();
        lblNombreJuego = new javax.swing.JLabel();
        lbltemporizador = new javax.swing.JLabel();
        btnMusica = new javax.swing.JButton();
        jButtonChat = new javax.swing.JButton();
        jButtonQuestion = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel28 = new javax.swing.JLabel();
        jButtonYes = new javax.swing.JButton();
        jButtonNo = new javax.swing.JButton();
        jButtonHit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 204, 204));

        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(255, 204, 204));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(255, 204, 204));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(255, 204, 204));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel5.setBackground(new java.awt.Color(255, 204, 204));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel6.setBackground(new java.awt.Color(255, 204, 204));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel8.setBackground(new java.awt.Color(255, 204, 204));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel9.setBackground(new java.awt.Color(255, 204, 204));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel10.setBackground(new java.awt.Color(255, 204, 204));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
        );

        jPanel11.setBackground(new java.awt.Color(255, 204, 204));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel12.setBackground(new java.awt.Color(255, 204, 204));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel13.setBackground(new java.awt.Color(255, 204, 204));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel14.setBackground(new java.awt.Color(255, 204, 204));

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel15.setBackground(new java.awt.Color(255, 204, 204));

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
        );

        jPanel16.setBackground(new java.awt.Color(255, 204, 204));

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel17.setBackground(new java.awt.Color(255, 204, 204));

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel18.setBackground(new java.awt.Color(255, 204, 204));

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel19.setBackground(new java.awt.Color(255, 204, 204));

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel20.setBackground(new java.awt.Color(255, 204, 204));

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel21.setBackground(new java.awt.Color(255, 204, 204));

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel22.setBackground(new java.awt.Color(255, 204, 204));

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel23.setBackground(new java.awt.Color(255, 204, 204));

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel24.setBackground(new java.awt.Color(255, 204, 204));

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel25.setBackground(new java.awt.Color(255, 204, 204));

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Logo.png"))); // NOI18N

        jButton1.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        jButton1.setText("Comenzar el Juego");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel7.setBackground(new java.awt.Color(255, 204, 255));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jListNames.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jListNamesValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jListNames);

        jButtonAzar.setFont(new java.awt.Font("Comic Sans MS", 3, 15)); // NOI18N
        jButtonAzar.setText("Escoger al azar");
        jButtonAzar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAzarActionPerformed(evt);
            }
        });

        jButtonInformation.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/clear-information-icon-070831--icons-etc-30.png"))); // NOI18N
        jButtonInformation.setBorder(null);
        jButtonInformation.setBorderPainted(false);
        jButtonInformation.setContentAreaFilled(false);
        jButtonInformation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInformationActionPerformed(evt);
            }
        });

        lblfecha.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        lblfecha.setForeground(new java.awt.Color(255, 0, 0));
        lblfecha.setText("fecha");

        lblNombreJugador.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        lblNombreJugador.setForeground(new java.awt.Color(255, 0, 0));
        lblNombreJugador.setText("nombre del jugador");

        lblNombreJuego.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        lblNombreJuego.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblPersonajeJugador, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNombreJugador)
                .addGap(402, 402, 402)
                .addComponent(lblNombreJuego)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblfecha)
                .addGap(19, 19, 19))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblfecha)
                    .addComponent(lblNombreJugador))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNombreJuego)
                .addContainerGap(21, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel26Layout.createSequentialGroup()
                .addComponent(lblPersonajeJugador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        lbltemporizador.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        lbltemporizador.setForeground(new java.awt.Color(255, 0, 0));
        lbltemporizador.setText("00:00");

        btnMusica.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/sonido.png"))); // NOI18N
        btnMusica.setText("jButton2");
        btnMusica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMusicaActionPerformed(evt);
            }
        });

        jButtonChat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Chattito.png"))); // NOI18N
        jButtonChat.setToolTipText("");
        jButtonChat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonChatActionPerformed(evt);
            }
        });

        jButtonQuestion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/askito.png"))); // NOI18N
        jButtonQuestion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonQuestionActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Comic Sans MS", 3, 15)); // NOI18N
        jButton2.setText("Escoger de la matriz");

        jLabel28.setFont(new java.awt.Font("Comic Sans MS", 3, 15)); // NOI18N
        jLabel28.setText("   Personaje escogido");

        jButtonYes.setBackground(new java.awt.Color(204, 255, 204));
        jButtonYes.setFont(new java.awt.Font("Comic Sans MS", 3, 18)); // NOI18N
        jButtonYes.setText("SI");
        jButtonYes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonYesActionPerformed(evt);
            }
        });

        jButtonNo.setBackground(new java.awt.Color(255, 102, 102));
        jButtonNo.setFont(new java.awt.Font("Comic Sans MS", 3, 18)); // NOI18N
        jButtonNo.setText("NO");
        jButtonNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNoActionPerformed(evt);
            }
        });

        jButtonHit.setBackground(new java.awt.Color(255, 204, 153));
        jButtonHit.setFont(new java.awt.Font("Comic Sans MS", 3, 18)); // NOI18N
        jButtonHit.setText("Le atinaste");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(284, 284, 284)))
                        .addComponent(lbltemporizador)
                        .addGap(60, 60, 60))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 97, Short.MAX_VALUE)
                                .addComponent(jButtonInformation, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonHit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(52, 52, 52)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jButtonQuestion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButtonChat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(26, 26, 26)
                                    .addComponent(jButtonAzar, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(68, 68, 68)
                            .addComponent(jButton1)
                            .addGap(43, 43, 43)
                            .addComponent(jButtonYes, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jButtonNo, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 0, 0)
                .addComponent(btnMusica, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel24, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButtonInformation, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonHit, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonNo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jButtonYes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonAzar, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButtonChat, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonQuestion)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnMusica, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbltemporizador)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel28)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(56, 56, 56))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (!gameStarted) { // Solo si el juego no ha comenzado
            long currentSeed = System.nanoTime(); // Genera una semilla única solo si eres el primero
            RNG(currentSeed); // Llama a RNG con la semilla
            LlenarTabla();
            
            // Marca el juego como iniciado para esta instancia y las demás si reciben la semilla
            gameStarted = true; 
            jButton1.setEnabled(false); // Deshabilita el botón una vez que se ha iniciado

            if (activeChatClient != null && activeChatClient.frame.isVisible()) {
                activeChatClient.sendMessage("/CMD_SEED " + currentSeed);
                System.out.println("Enviando semilla al chat: " + currentSeed);
            } else {
                System.out.println("Chat no activo, no se pudo enviar la semilla.");
                // Opcional: Puedes decidir si el juego se inicia solo localmente si el chat no está abierto.
                // En este caso, gameStarted ya es true, así que no se generaría otra semilla.
            }
        } else {
            System.out.println("El juego ya ha sido iniciado con una semilla. No se generará una nueva.");
        }
    }//GEN-LAST:event_jButton1ActionPerformed
    
    public void LlenarTabla(){
                // Usaremos un ArrayList temporal para recolectar los nombres primero
        List<String> nombresParaMostrar = new ArrayList<>();

        List<Personaje> todas = ConexionBD.obtenerTodasLosPersonajes();

        if (todas == null) {
            System.err.println("No se pudieron obtener los personajes de la base de datos.");
            return;
        }

        // OPTIMIZACIÓN: Crear un mapa para búsqueda rápida por ID
        Map<Integer, Personaje> personajesById = new HashMap<>();
        for (Personaje p : todas) {
            personajesById.put(p.getID(), p);
        }

        for (String idStr : mylist) {
            try {
                int idBuscado = Integer.parseInt(idStr);
                Personaje p = personajesById.get(idBuscado);
                if (p != null) {
                    nombresParaMostrar.add(p.getNombre()); // Añadir al ArrayList temporal
                }
            } catch (NumberFormatException e) {
                System.err.println("Error: El valor en mylist '" + idStr + "' no es un número válido.");
            }
        }

        // Ahora, una vez que tenemos todos los nombres que coinciden, omitimos los últimos 10
        DefaultListModel<String> model = new DefaultListModel<>();
        jListNames.setModel(model);

        int countToAdd = nombresParaMostrar.size();
        if (countToAdd > 10) {
            countToAdd -= 10;
        } else {
            countToAdd = 0; // Si hay 10 o menos, no se añade nada
        }

        for (int i = 0; i < countToAdd; i++) {
            model.addElement(nombresParaMostrar.get(i));
        }
    
    }
    
    public void fliperino(JLabel clickedLabel) {
        String faceUpImageIdentifier = clickedLabel.getName(); // Get the stored face-up image name (e.g., "1", "2")

    if (faceUpImageIdentifier == null || faceUpImageIdentifier.isEmpty()) {
        System.out.println("JLabel has no assigned face-up image identifier.");
        return;
    }

    ImageIcon currentIcon = (ImageIcon) clickedLabel.getIcon();
    String imageToLoadPath;


    if (currentIcon != null && currentIcon.getDescription() != null &&
        currentIcon.getDescription().endsWith(BACK_POKER_IMAGE_PATH)) {
        imageToLoadPath = "/Images/" + faceUpImageIdentifier + ".png";
    } else {

        imageToLoadPath = BACK_POKER_IMAGE_PATH;
    }

        setAndScaleImage(clickedLabel, imageToLoadPath);
    }
    
    private void setAndScaleImage(JLabel label, String imagePath) {
    if (label == null) {
        System.err.println("Attempted to set image on a null JLabel.");
        return;
    }
    try {
        ImageIcon originalIcon = new ImageIcon(getClass().getResource(imagePath));
        if (originalIcon.getImageLoadStatus() == java.awt.MediaTracker.ERRORED) {
             System.err.println("Error loading image: " + imagePath + ". Check if file exists and path is correct.");
             
             return;
        }
        Image image = originalIcon.getImage();
        Image scaledImage = image.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
        // Store the imagePath as the description for future checks in fliperino
        label.setIcon(new ImageIcon(scaledImage, imagePath));
    } catch (NullPointerException e) {
        // This usually happens if getResource returns null (image not found)
        System.err.println("Image resource not found: " + imagePath);
        // You might want to set a default "missing image" icon here
    }
}
    
    private void jLabelMouseClicked(java.awt.event.MouseEvent evt) {
                JLabel clickedLabel = (JLabel) evt.getSource();
        String faceUpImageName = clickedLabel.getName(); // This should be the '1', '2', etc.

        if (faceUpImageName != null && !faceUpImageName.isEmpty()) { // Added check for empty string
            System.out.println("Clicked on label. Assigned image name: " + faceUpImageName);
            fliperino(clickedLabel); 
        } else {
            System.out.println("This label has no assigned 'faceUpImageName' (from setName()).");
        }
    }
    
    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_jLabel1MouseClicked

    private void jButtonAzarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAzarActionPerformed
        // TODO add your handling code here:
        RNGchus();
    }//GEN-LAST:event_jButtonAzarActionPerformed

    private void jButtonInformationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInformationActionPerformed
        // TODO add your handling code here:
        JFrameHowToPlay Howto = new JFrameHowToPlay();
        Howto.setVisible(true);
    }//GEN-LAST:event_jButtonInformationActionPerformed

    private void btnMusicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMusicaActionPerformed
        // TODO add your handling code here:
        Musica();
    }//GEN-LAST:event_btnMusicaActionPerformed

    public String obtenerimagen(String charName)
    {
        List<Personaje> todas = ConexionBD.obtenerTodasLosPersonajes();
        Personaje foundPersonaje = null;
        if (todas != null) { 
            for (Personaje p : todas) { 
                if (p.getNombre().equals(charName)) {
                    foundPersonaje = p; 
                    break; 
                }
            }
        }
        
        if(foundPersonaje != null)
        {
            int charId = foundPersonaje.getID();
            String match = charId + "";
            System.out.print(match);
            return match;
        }
        
        return null;
    }
    
    private void jListNamesValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jListNamesValueChanged
        // TODO add your handling code here:
                if (!evt.getValueIsAdjusting()) {
        String selectedCharacterName = jListNames.getSelectedValue();

        if (selectedCharacterName != null) {

            String characterIdString = obtenerimagen(selectedCharacterName); 

            if (characterIdString != null) {
              
                String imagePath = "/Images/" + characterIdString + ".png"; 
                System.out.println("DEBUG: Intentando cargar imagen desde: " + imagePath); 

                ImageIcon originalIcon = new ImageIcon(getClass().getResource(imagePath));

                if (originalIcon.getImageLoadStatus() == java.awt.MediaTracker.ERRORED) {
                    System.err.println("Error al cargar la imagen: " + imagePath);

                } else {
                    Image image = originalIcon.getImage(); 
                    if (labels[24] != null && labels[24].getWidth() > 0 && labels[24].getHeight() > 0) {
                        Image scaledImageForLabel24 = image.getScaledInstance(
                            labels[24].getWidth(),
                            labels[24].getHeight(),
                            Image.SCALE_SMOOTH
                        );
                        labels[24].setIcon(new ImageIcon(scaledImageForLabel24));
                    } else {
                        System.err.println("labels[24] (jLabel26) no está inicializado o no tiene dimensiones válidas para escalar.");
                    }

                    if (lblPersonajeJugador != null && lblPersonajeJugador.getWidth() > 0 && lblPersonajeJugador.getHeight() > 0) {
                        Image scaledImageForPlayer = image.getScaledInstance(
                            lblPersonajeJugador.getWidth(),
                            lblPersonajeJugador.getHeight(),
                            Image.SCALE_SMOOTH
                        );
                        lblPersonajeJugador.setIcon(new ImageIcon(scaledImageForPlayer));
                    } else {
                        System.err.println("lblPersonajeJugador no está inicializado o no tiene dimensiones válidas para escalar.");
                    }
                }
            } else {
                System.out.println("No se pudo obtener el ID del personaje: " + selectedCharacterName + ". ¿Existe en la BD?");
            }
        } else {
            System.out.println("No se ha seleccionado ningún elemento de la lista.");
        }
    }
    }//GEN-LAST:event_jListNamesValueChanged

    private void jButtonChatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonChatActionPerformed
        if (activeChatClient == null || !activeChatClient.frame.isVisible()) {
        activeChatClient = new ChatClient();
        
        // =============================================================
        // ¡Pasa la referencia de esta instancia de Board al ChatClient!
        activeChatClient.setBoardReference(this); 
        // =============================================================

        activeChatClient.frame.setVisible(true);

        new Thread(() -> {
            activeChatClient.run();
        }).start();
    } else {
        activeChatClient.frame.toFront();
        activeChatClient.frame.requestFocus();
    }
    }//GEN-LAST:event_jButtonChatActionPerformed

    
    private void jButtonQuestionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonQuestionActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jButtonQuestionActionPerformed

    private void jButtonYesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonYesActionPerformed
        // TODO add your handling code here:
        sendGameMessage("¡SÍ!");
        
    }//GEN-LAST:event_jButtonYesActionPerformed

    private void jButtonNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNoActionPerformed
        // TODO add your handling code here:
        sendGameMessage("¡NO!");
    }//GEN-LAST:event_jButtonNoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Board.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Board.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Board.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Board.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Board().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMusica;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtonAzar;
    private javax.swing.JButton jButtonChat;
    private javax.swing.JButton jButtonHit;
    private javax.swing.JButton jButtonInformation;
    private javax.swing.JButton jButtonNo;
    private javax.swing.JButton jButtonQuestion;
    private javax.swing.JButton jButtonYes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jListNames;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblNombreJuego;
    private javax.swing.JLabel lblNombreJugador;
    private javax.swing.JLabel lblPersonajeJugador;
    private javax.swing.JLabel lblfecha;
    private javax.swing.JLabel lbltemporizador;
    // End of variables declaration//GEN-END:variables
}

package frontend;

import backend.Mascota;
import backend.Tienda;
import backend.MascotaObserver;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Ventana de interacción con las mascotas de la tienda.
 * Permite al jugador ver y cuidar las mascotas disponibles,
 * mostrando una lista de ellas.
 */
public class V3_mascotas extends JFrame implements MascotaObserver{
    private JButton volverMascotas;
    private JPanel panelMascotas;
    private JLabel Mascotas;
    private JButton limpiarHabitatButton;
    private JButton atenderSaludButton;
    private JButton alimentarButton;
    private JButton jugarButton;
    private JProgressBar barraFelicidad;
    private JProgressBar barraHambre;
    private JProgressBar barraSalud;
    private JProgressBar barraHigiene;
    private JButton btnSiguiente;
    private JButton btnAnterior;
    private JLabel estado;
    private JLabel nombre;
    private JLabel imagen;
    private JLabel felicidad;
    private JLabel hambre;
    private JLabel salud;
    private JLabel higiene;
    private V1_inicio v1;

    private Tienda tienda;
    private ArrayList<Mascota> listaMascotas;
    private int indiceActual;
    private Mascota mascotaObservada;

    private Timer timer;

    /**
     * Constructor de la ventana de mascotas.
     * @param v1 referencia a la ventana principal para poder volver a ella.
     * @param tienda instancia compartida de la tienda con la lista de mascotas.
     * @param indiceInicial índice de la mascota a mostrar al abrir la ventana.
     */
    public V3_mascotas(V1_inicio v1, Tienda tienda, int indiceInicial) {
        this.tienda = tienda;
        this.listaMascotas = tienda.getMascotas();
        this.indiceActual = indiceInicial;

        setTitle("Mascotas");
        setSize(720, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(panelMascotas);

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                timer.stop();
                desregistrarObserver();
            }
        });

        if (!listaMascotas.isEmpty()) {
            registrarObserver(getMascotaActual());
        }

        actualizarBotones();

        timer = new Timer(3000, e -> {
            for (Mascota m : tienda.getMascotas()) {
                m.pasarTiempo();
            }
        });

        timer.start();

        /**
         * Navega a la mascota anterior en la lista (dirige al final si está en la primera).
         */
        btnAnterior.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listaMascotas.isEmpty()) return;
                if (indiceActual > 0) {
                    indiceActual--;
                } else {
                    indiceActual = listaMascotas.size() - 1;
                }
                registrarObserver(getMascotaActual());
                actualizarBotones();
            }
        });

        /**
         * Navega a la mascota siguiente en la lista (dirige al inicio si está en la última).
         */
        btnSiguiente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listaMascotas.isEmpty()) return;
                if (indiceActual < listaMascotas.size() - 1) {
                    indiceActual++;
                } else {
                    indiceActual = 0;
                }
                registrarObserver(getMascotaActual());
                actualizarBotones();
            }
        });

        /**
         * Alimenta a la mascota actual.
         */
        alimentarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tienda.getCantidadComida() == 0) {
                    JOptionPane.showMessageDialog(null,
                            "No tienes comida en el inventario.\nCompra una en la tienda.");
                    return;
                }
                getMascotaActual().alimentar();
                tienda.gastarComida();
            }
        });

        /**
         * Juega con la mascota actual.
         */
        jugarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getMascotaActual().jugar();
            }
        });

        /**
         * Limpia el hábitat de la mascota actual.
         */
        limpiarHabitatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getMascotaActual().limpiar();
            }
        });

        /**
         * Atiende la salud de la mascota actual si está enferma o en estado crítico.
         * Si la mascota está saludable, muestra un aviso al jugador.
         */
        atenderSaludButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tienda.getCantidadMedicamento() == 0) {
                    JOptionPane.showMessageDialog(null,
                            "No tienes medicamentos en el inventario.\nCompra uno en la tienda.");
                    return;
                }
                boolean atendida = getMascotaActual().atenderSalud();
                if (atendida) {
                    tienda.gastarMedicamento(); // ← limpio y claro
                    JOptionPane.showMessageDialog(null, "Salud atendida con éxito.");
                } else {
                    JOptionPane.showMessageDialog(null,
                            "La mascota ya está saludable. No se gastó medicamento.");
                }
            }
        });

        /**
         * Vuelve a la ventana principal.
         */
        volverMascotas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.stop();
                desregistrarObserver();
                v1.setVisible(true);
                V3_mascotas.this.setVisible(false);
            }
        });
    }

    /**
     * Metodo requerido por el observer de mascotas, actualiza sus cambios.
     * @param mascota la mascota que notificó el cambio.
     */
    @Override
    public void actualizar(Mascota mascota) {
        SwingUtilities.invokeLater(this::actualizarBotones);
    }

    /**
     * Desregistra esta ventana como observer de la mascota anterior
     * y la registra como observer de la nueva mascota recibida.
     * @param nueva la mascota que se comenzará a observar
     */
    private void registrarObserver(Mascota nueva) {
        if (mascotaObservada != null) {
            mascotaObservada.eliminarObserver(this); // deja de observar la anterior
        }
        mascotaObservada = nueva;
        mascotaObservada.agregarObserver(this); // empieza a observar la nueva
    }

    /**
     * Desregistra esta ventana como observer de la mascota actual.
     * Debe llamarse al cerrar la ventana para liberar la referencia
     * y evitar que observers queden activos sobre objetos sin ventana.
     */
    private void desregistrarObserver() {
        if (mascotaObservada != null) {
            mascotaObservada.eliminarObserver(this);
            mascotaObservada = null;
        }
    }

    /**
     * Retorna la mascota en el índice actual de la lista.
     * @return mascota actualmente seleccionada.
     */
    private Mascota getMascotaActual() {
        if (listaMascotas.isEmpty()) return null;

        return listaMascotas.get(indiceActual);
    }

    /**
     * Actualiza todos los componentes visuales de la ventana con los datos
     * de la mascota actualmente seleccionada.
     * Si no hay mascotas, desactiva todos los botones de acción.
     * Si el índice quedó fuera de rango (por una venta), lo ajusta al último válido.
     */
    public void actualizarBotones(){

        this.listaMascotas = tienda.getMascotas();

        if (listaMascotas.isEmpty()) {
            nombre.setText("Sin mascotas");
            estado.setText("-");
            barraHambre.setValue(0);
            barraFelicidad.setValue(0);
            barraHigiene.setValue(0);
            barraSalud.setValue(0);
            btnAnterior.setEnabled(false);
            btnSiguiente.setEnabled(false);
            alimentarButton.setEnabled(false);
            jugarButton.setEnabled(false);
            limpiarHabitatButton.setEnabled(false);
            atenderSaludButton.setEnabled(false);
            return;
        }

        alimentarButton.setEnabled(true);
        jugarButton.setEnabled(true);
        limpiarHabitatButton.setEnabled(true);
        atenderSaludButton.setEnabled(true);

        if (indiceActual >= listaMascotas.size()) {
            indiceActual = listaMascotas.size() - 1;
        }

        Mascota m = getMascotaActual();

        nombre.setText(m.getNombre());
        estado.setText(m.getEstado().getEstado() + ": " + m.getEstado().getDescripcion());

        barraHambre.setValue(m.getHambre());
        barraFelicidad.setValue(m.getFelicidad());
        barraHigiene.setValue(m.getHigiene());
        barraSalud.setValue(m.getSalud());

        boolean hayVarias = listaMascotas.size()>1;
        btnAnterior.setEnabled(hayVarias);
        btnSiguiente.setEnabled(hayVarias);

        imagen.setText("Cargando...");
        cargarImagenMascota(m);

    }

    /**
     * Carga y muestra la imagen correspondiente al tipo de la mascota recibida.
     * Las imágenes se leen desde la carpeta resources.
     * Si la imagen no se encuentra o falla la lectura, muestra un texto de error.
     * @param m la mascota cuya imagen se desea cargar.
     */
    private void cargarImagenMascota(Mascota m) {

        String nombreArchivo;

        //imagenes por tipo de mascota
        switch (m.getTipo()) {
            case PERRO: nombreArchivo = "/perro.jpg"; break;
            case GATO: nombreArchivo = "/gato.jpg"; break;
            case PEZ: nombreArchivo = "/pez.jpg"; break;
            case PAJARO: nombreArchivo = "/pajaro.jpg"; break;
            default:
                imagen.setText("Sin imagen");
                return;
        }

        try {
            java.net.URL imgUrl = getClass().getResource(nombreArchivo);
            if (imgUrl == null) {
                imagen.setText("Imagen no encontrada");
                return;
            }

            java.awt.image.BufferedImage img = javax.imageio.ImageIO.read(imgUrl);

            int ancho;
            int alto;

            //ajusta el tamaño de la imagen
            if (imagen.getWidth() >0) {
                ancho = imagen.getWidth();
            } else {
                ancho=300;
            }
            if (imagen.getHeight() >0) {
                alto = imagen.getHeight();
            } else {
                alto=300;
            }
            //imagen con el tamaño
            java.awt.Image redimensionada = img.getScaledInstance(ancho, alto, java.awt.Image.SCALE_SMOOTH);

            imagen.setText("");
            imagen.setIcon(new ImageIcon(redimensionada));

        } catch (Exception ex) {
            imagen.setText("Error al cargar la imagen");
            imagen.setIcon(null);
        }
    }

}

package frontend;

import backend.Mascota;
import backend.Tienda;
import backend.MascotaObserver;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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

    //ventana
    public V3_mascotas(V1_inicio v1, Tienda tienda, int indiceInicial) {
        this.tienda = tienda;
        this.listaMascotas = tienda.getMascotas();
        this.indiceActual = indiceInicial;

        setTitle("Mascotas");
        setSize(720, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(panelMascotas);

        registrarObserver(getMascotaActual());
        actualizarBotones();

        //botones para cambiar de mascota
        btnAnterior.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (indiceActual > 0) {
                    indiceActual--;
                } else {
                    indiceActual = listaMascotas.size() -1;
                }
                registrarObserver(getMascotaActual());
                actualizarBotones();
            }
        });

        btnSiguiente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (indiceActual < listaMascotas.size() -1) {
                    indiceActual++;
                } else {
                    indiceActual = 0;
                }
                registrarObserver(getMascotaActual());
                actualizarBotones();
            }
        });

        //boton alimentar
        alimentarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getMascotaActual().alimentar();
            }
        });

        //boton jugar
        jugarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getMascotaActual().jugar();
            }
        });

        //boton limpiar
        limpiarHabitatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getMascotaActual().limpiar();
            }
        });

        //boton salud
        atenderSaludButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean atendida = getMascotaActual().atenderSalud();
                if (atendida) {
                    JOptionPane.showMessageDialog(null, "Salud atendida con éxito.");
                } else {
                    JOptionPane.showMessageDialog(null,
                            "La mascota ya está saludable.");
                }
            }
        });

        //boton volver
        volverMascotas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                desregistrarObserver();
                v1.setVisible(true);
                V3_mascotas.this.setVisible(false);
            }
        });
    }

    @Override
    public void actualizar(Mascota mascota) {
        SwingUtilities.invokeLater(this::actualizarBotones);
    }

    private void registrarObserver(Mascota nueva) {
        if (mascotaObservada != null) {
            mascotaObservada.eliminarObserver(this); // deja de observar la anterior
        }
        mascotaObservada = nueva;
        mascotaObservada.agregarObserver(this); // empieza a observar la nueva
    }
    private void desregistrarObserver() {
        if (mascotaObservada != null) {
            mascotaObservada.eliminarObserver(this);
            mascotaObservada = null;
        }
    }

    //obtendra la mascota que se visualiza en pantalla
    private Mascota getMascotaActual() {
        return listaMascotas.get(indiceActual);
    }

    //actualizará los datos de las mascotas
    public void actualizarBotones(){

        this.listaMascotas = tienda.getMascotas();

        //si no hay mascotas
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

        //por si hay un reajuste de mascotas
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

package frontend;

import backend.Mascota;
import backend.Tienda;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class V3_mascotas extends JFrame {
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

    private void cargarImagenMascota(Mascota m) {

        String nombreArchivo;

        //imagenes por tipo de mascota
        if (m instanceof backend.Perro) {
            nombreArchivo = "";
        } else if (m instanceof backend.Gato) {
            nombreArchivo = "";
        } else if (m instanceof backend.Pez) {
            nombreArchivo = "";
        } else if (m instanceof backend.Pajaro) {
            nombreArchivo = "";
        } else {
            imagen.setText("No hay imagen");
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
                ancho=150;
            }
            if (imagen.getHeight() >0) {
                alto = imagen.getHeight();
            } else {
                alto=150;
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

    //ventana
    public V3_mascotas(V1_inicio v1, Tienda tienda, int indiceInicial) {
        this.tienda = tienda;
        this.listaMascotas = tienda.getMascotas();
        this.indiceActual = indiceInicial;

        setTitle("Ventana 3");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(panelMascotas);

        actualizar();

        //botones para cambiar de mascota
        btnAnterior.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (indiceActual > 0) {
                    indiceActual--;
                } else {
                    indiceActual = listaMascotas.size() -1;
                }
                actualizar();
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
                actualizar();
            }
        });

        //boton alimentar
        alimentarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getMascotaActual().alimentar();
                actualizar();
            }
        });

        //boton jugar
        jugarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getMascotaActual().jugar();
                actualizar();
            }
        });

        //boton limpiar
        limpiarHabitatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getMascotaActual().limpiar();
                actualizar();
            }
        });

        //boton salud
        atenderSaludButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getMascotaActual().pasarTiempo();
                actualizar();
            }
        });

        //boton volver
        volverMascotas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                v1.setVisible(true);
                V3_mascotas.this.setVisible(false);
            }
        });
    }
    //obtendra la mascota que se visualiza en pantalla
    private Mascota getMascotaActual() {
        return listaMascotas.get(indiceActual);
    }

    //actualizará los datos de las mascotas
    public void actualizar(){

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
            return;
        }
        //por si hay un reajuste de mascotas
        if (indiceActual >= listaMascotas.size()) {
            indiceActual = listaMascotas.size() - 1;
        }

        Mascota m = getMascotaActual();

        nombre.setText(m.getNombre());
        estado.setText(m.getEstado().getEstado() + m.getEstado().getDescripcion());

        barraHambre.setValue(m.getHambre());
        barraFelicidad.setValue(m.getFelicidad());
        barraHigiene.setValue(m.getHigiene());
        barraSalud.setValue(m.getSalud());

        boolean hayVarias = listaMascotas.size()>1;
        btnAnterior.setEnabled(hayVarias);
        btnSiguiente.setEnabled(hayVarias);

        cargarImagenMascota(m);

    }
}

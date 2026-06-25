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
    private V1_inicio v1;

    private Tienda tienda;
    private ArrayList<Mascota> listaMascotas;
    private int indiceActual;


    public void setV1(V1_inicio v1){
        this.v1 = v1;
    }

    //ventana
    public V3_mascotas(V1_inicio v1, Tienda tienda, int indiceInicial) {
        setTitle("Ventana 2");
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

    }
}

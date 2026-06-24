package frontend;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public void setV1(V1_inicio v1){
        this.v1 = v1;
    }

    public V3_mascotas(V1_inicio v_inicial) {
        setTitle("Ventana 2");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(panelMascotas);

        //boton volver
        volverMascotas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                v_inicial.setVisible(true);
                V3_mascotas.this.setVisible(false);
            }
        });
    }
}

package frontend;

//import.backend.ClienteVirtual;
import backend.Mascota;
import backend.Tienda;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class V2_tienda extends JFrame {
    private JButton volverGestion;
    private JPanel panelgestion;
    private JButton btnVenderMascota;
    private JButton btnComprarMascota;
    private JButton btnSuministros;
    private JButton btnInventario;
    private JLabel Presupuesto;
    private V1_inicio v1;

    private Tienda tienda;

    public V2_tienda(V1_inicio v1, Tienda tienda) {

        this.tienda = tienda;

        setTitle("Ventana 2");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(panelgestion);

        //boton volver
        volverGestion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                v1.setVisible(true);
                V2_tienda.this.setVisible(false);
            }
        });

        actualizarPresupuesto();

        btnSuministros.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] opciones = {"Comida ($5.000)", "Medicamento ($15.000)"};
                int eleccion = JOptionPane.showOptionDialog(null,
                        "¿Qué suministro deseas comprar?",
                        "Comprar Suministros",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                        null, opciones, opciones[0]
                );
                if (eleccion== 0 || eleccion== 1) {
                    String resultado = tienda.ComprarSuministros(eleccion+1);
                    JOptionPane.showMessageDialog(null, resultado);
                    actualizarPresupuesto();
                }
            }
        });

    }
    private void actualizarPresupuesto() {
        Presupuesto.setText("Presupuesto: $" +tienda.getPresupuesto());
    }
}

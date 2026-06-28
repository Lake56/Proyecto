package frontend;

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

    public void setV1(V1_inicio v1){
        this.v1 = v1;
    }
    public V2_tienda(V1_inicio v1, Tienda tienda) {
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
    }
}

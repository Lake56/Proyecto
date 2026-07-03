package frontend;

import backend.Tienda;
import backend.Mascota;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class V1_inicio extends JFrame {

    public JButton tiendagestion;
    public JButton mascotasButton;
    private JPanel panelinicio;
    public JLabel Tienda;

    private backend.Tienda tienda = new backend.Tienda(70000);

    public V1_inicio() {
        setTitle("Inicio");
        setSize(720, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(panelinicio);

        //boton para ir a gestion
        tiendagestion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                V2_tienda v2 = new V2_tienda(V1_inicio.this, tienda);
                //v2.setV1(V1_inicio.this);
                v2.setVisible(true);
                V1_inicio.this.setVisible(false);
            }
        });
        //boton para ir a las mascotas
        mascotasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                V3_mascotas v3 = new V3_mascotas(V1_inicio.this, tienda, 0);
                //v3.setV1(V1_inicio.this);
                v3.setVisible(true);
                V1_inicio.this.setVisible(false);
            }
        });
    }
}

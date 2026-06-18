package frontend;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class v_inicial extends JFrame {

    public JButton tiendagestion;
    public JButton mascotasButton;
    private JPanel panelinicio;
    private JLabel Tienda;


    public v_inicial() {
        setTitle("Ventana 1");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(panelinicio);

        tiendagestion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                v_tienda v_tienda = new v_tienda(v_inicial.this);
                v_tienda.setVisible(true);
                v_inicial.this.setVisible(false);
            }
        });
        mascotasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                v_mascotas v_mascotas = new v_mascotas(v_inicial.this);
                v_mascotas.setVisible(true);
                v_inicial.this.setVisible(false);
            }
        });
    }
}

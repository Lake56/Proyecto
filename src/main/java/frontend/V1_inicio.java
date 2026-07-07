package frontend;

import backend.Tienda;
import backend.Mascota;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Ventana principal del simulador de  la tienda de mascotas.
 * Es la entrada del programa y conecta con las demas ventanas.
 */
public class V1_inicio extends JFrame {

    public JButton tiendagestion;
    public JButton mascotasButton;
    private JPanel panelinicio;
    public JLabel Tienda;
    private backend.Tienda tienda = new backend.Tienda(100000);

    /**
     * Constructor de la ventana principal.
     * Configura la ventana e inicializa los listeners de los botones de navegación.
     */
    public V1_inicio() {
        setTitle("Inicio");
        setSize(720, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        if (panelinicio == null) {
            panelinicio = new JPanel();
        }
        setContentPane(panelinicio);

        /**
         * Dirige a la ventana de gestión de tienda.
         */
        tiendagestion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                V2_tienda v2 = new V2_tienda(V1_inicio.this, tienda);
                v2.setVisible(true);
                V1_inicio.this.setVisible(false);
            }
        });

        /**
         * Dirige a la ventana de mascotas.
         */
        mascotasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                V3_mascotas v3 = new V3_mascotas(V1_inicio.this, tienda, 0);
                v3.setVisible(true);
                V1_inicio.this.setVisible(false);
            }
        });
    }
}

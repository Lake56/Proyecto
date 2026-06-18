package frontend;

import javax.swing.*;

public class v_tienda extends JFrame {
    private JButton volverGestion;
    private JPanel panelgestion;
    public v_inicial v_inicial;

    public v_tienda(v_inicial v_inicial) {
        setTitle("Ventana 3");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(panelgestion);
    }
}

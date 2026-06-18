package frontend;

import javax.swing.*;

public class v_mascotas extends JFrame {
    private JButton volverMascotas;
    private JPanel panelMascotas;
    private JLabel Mascotas;
    private v_inicial v1;

    public void setV1(v_inicial v1){
        this.v1 = v1;
    }

    public v_mascotas(v_inicial v_inicial) {
        setTitle("Ventana 2");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(panelMascotas);

    }
}

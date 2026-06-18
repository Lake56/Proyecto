import frontend.v_inicial;

import javax.swing.*;
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            v_inicial v1 = new v_inicial();
            v1.setVisible(true);
        });
    }
}

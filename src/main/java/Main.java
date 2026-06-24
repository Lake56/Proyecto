import frontend.V1_inicio;

import javax.swing.*;
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            V1_inicio v1 = new V1_inicio();
            v1.setVisible(true);
        });
    }
}

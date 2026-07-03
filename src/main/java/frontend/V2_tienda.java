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

        setTitle("Gestionar Tienda");
        setSize(720, 720);
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
        //ver inventario(suministros y mascotas)
        btnInventario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Mascota> lista = tienda.getMascotas();
                StringBuilder sb = new StringBuilder();

                //suministros
                sb.append("SUMINISTROS\n");
                sb.append("-Comida: ").append(tienda.getCantidadComida()).append(" unidades\n");
                sb.append("-Medicamento: ").append(tienda.getCantidadMedicamento()).append(" unidades\n\n");

                //mascotas
                sb.append("MASCOTAS\n");

                if (lista.isEmpty()) {
                    sb.append("No hay mascotas en la tienda.");
                }
                else {
                    sb.append("Mascotas en la tienda:\n");
                    for (Mascota m : lista) {
                        sb.append("-").append(m.getNombre())
                                .append(" (").append(m.getTipo()).append(")") //tipo de ani,al
                                .append(" | Estado: ").append(m.getEstado().getEstado()) //estado del animal
                                .append(" | Precio: $").append((int) m.getPrecio()).append("\n"); // precio
                    }
                }
                JOptionPane.showMessageDialog(null, sb.toString(), "Inventario", JOptionPane.INFORMATION_MESSAGE);
            }
        });


    }

    private void actualizarPresupuesto() {
        Presupuesto.setText("Presupuesto: $" +tienda.getPresupuesto());
    }
}

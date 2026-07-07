package frontend;

import backend.ClienteVirtual;
import backend.Mascota;
import backend.Tienda;
import backend.TipoMascota;
import backend.Perro;
import backend.Gato;
import backend.Pajaro;
import backend.Pez;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Ventana de gestión de la tienda de mascotas.
 * Permite al jugador comprar mascotas y suministros, además de ver el inventario
 * y simular la llegada de clientes virtuales que desean adoptar mascotas.
 */
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

    /**
     * Constructor de la ventana de gestión de tienda.
     * @param v1 referencia a la ventana principal, para poder volver a ella.
     * @param tienda instancia compartida de la tienda con todas sus mascotas y suministros.
     */
    public V2_tienda(V1_inicio v1, Tienda tienda) {
        this.tienda = tienda;

        setTitle("Gestionar Tienda");
        setSize(720, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        if (panelgestion == null) {
            panelgestion = new JPanel();
        }

        setContentPane(panelgestion);

        actualizarPresupuesto();

        /**
         * Vuelve a la ventana principal.
         */
        volverGestion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                v1.setVisible(true);
                V2_tienda.this.setVisible(false);
            }
        });

        /**
         * Permite al jugador comprar suministros para la tienda.
         * Muestra un diálogo con las opciones disponibles (comida o medicamento)
         * y verifica si hay presupuesto suficiente.
         */
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

        /**
         * Muestra el inventario completo de la tienda, con su
         * presupuesto actual, cantidad de suministros y lista de mascotas
         * con su nombre, tipo, estado y precio.
         */
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

        /**
         * Permite al jugador adquirir una nueva mascota para la tienda.
         * El jugador elige el tipo y el nombre de la mascota y
         * esta acción descuenta del presupuesto de la tienda.
         */
        btnComprarMascota.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] tipos = {"Perro ($10.000)", "Gato ($8.000)",
                        "Pájaro ($8.000)", "Pez ($6.000)"};
                int eleccion = JOptionPane.showOptionDialog(
                        null,
                        "¿Qué mascota deseas comprar?\nPresupuesto: $" + tienda.getPresupuesto(),
                        "Comprar Mascota", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                        null, tipos, tipos[0]
                );

                if (eleccion<0) return;

                String nombre = JOptionPane.showInputDialog(null,
                        "¿Qué nombre le pondrás?", "Nombre de la mascota", JOptionPane.QUESTION_MESSAGE);

                if (nombre==null || nombre.trim().isEmpty()) return;

                Mascota nueva = switch (eleccion) {
                    case 0 -> new Perro(nombre, "salchicha", 10000);
                    case 1 -> new Gato(nombre, 8000);
                    case 2 -> new Pajaro(nombre, 8000);
                    default -> new Pez(nombre, 6000);
                };

                String resultado = tienda.ComprarMascota(nueva);
                JOptionPane.showMessageDialog(null, resultado);
                actualizarPresupuesto();
            }
        });

        /**
         * Simula la llegada de un cliente que desea adoptar una mascota.
         * Si el cliente generado no quiere comprar, se informa al jugador.
         * Si el cliente quiere comprar, se busca una mascota del tipo de su interés
         * y el jugador decide si venderla.
         * La venta agrega ingresos al presupuesto de la tienda.
         */
        btnVenderMascota.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (tienda.getMascotas().isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "No hay mascotas en la tienda para vender.");
                    return;
                }

                ClienteVirtual cliente = new ClienteVirtual();

                if (cliente.getEleccion() != 1) {
                    JOptionPane.showMessageDialog(null,
                            "El cliente que llegó no quiere adoptar.\nSe fue de la tienda.");
                    return;
                }

                TipoMascota tipoDeseado = cliente.getInteres();

                JOptionPane.showMessageDialog(null,
                        "Llegó un cliente\nBusca adoptar un/a: " + tipoDeseado + "\nDinero disponible: $" + cliente.getDinero(),
                        "Cliente en tienda",
                        JOptionPane.INFORMATION_MESSAGE);

                ArrayList<Mascota> coincidencias = new ArrayList<>();
                for (Mascota m : tienda.getMascotas()) {
                    if (m.getTipo() == tipoDeseado) {
                        coincidencias.add(m);
                    }
                }

                if (coincidencias.isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "No tienes " + tipoDeseado + " disponibles.\nEl cliente se fue sin adoptar.",
                            "Sin disponibilidad",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                String[] opciones = new String[coincidencias.size()];
                for (int i = 0; i < coincidencias.size(); i++) {
                    Mascota m = coincidencias.get(i);
                    opciones[i] = m.getNombre()
                            + " | Estado: " + m.getEstado().getEstado()
                            + " | Precio: $" + (int) m.getPrecio();
                }

                String seleccion = (String) JOptionPane.showInputDialog(
                        null,
                        "El cliente busca un/a " + tipoDeseado + ". ¿Cuál deseas vender?",
                        "Elegir mascota a vender",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        opciones,
                        opciones[0]
                );

                if (seleccion == null) {
                    JOptionPane.showMessageDialog(null,
                            "Decidiste no vender. El cliente se fue.");
                    return;
                }

                int idx = java.util.Arrays.asList(opciones).indexOf(seleccion);
                Mascota mascotaAVender = coincidencias.get(idx);

                int confirmar = JOptionPane.showConfirmDialog(
                        null,
                        "¿Confirmas vender a " + mascotaAVender.getNombre() + "?\n" + "Precio: $" + (int) mascotaAVender.getPrecio() + "\n"
                                + "Dinero del cliente: $" + cliente.getDinero(), "Confirmar venta", JOptionPane.YES_NO_OPTION
                );

                if (confirmar == JOptionPane.YES_OPTION) {
                    String resultado = tienda.VenderMascota(mascotaAVender.getTipo(), cliente.getDinero());
                    JOptionPane.showMessageDialog(null, resultado);
                    actualizarPresupuesto();
                } else {
                    JOptionPane.showMessageDialog(null, "Venta cancelada. El cliente se fue.");
                }
            }
        });
    }

    /**
     * Actualiza el label del presupuesto con el valor actual de la tienda.
     * Se llama después de cada operación que modifique el presupuesto.
     */
    private void actualizarPresupuesto() {
        Presupuesto.setText("Presupuesto: $" +tienda.getPresupuesto());
    }
}

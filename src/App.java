import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.*;

public class App {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> new App().mostrarMenuPrincipal());
    }

    // Ahora es public para poder volver desde las otras ventanas
    public void mostrarMenuPrincipal() {
        JFrame frame = new JFrame("Panel de Control - Concesionario");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300); 
        frame.setLocationRelativeTo(null);
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        JLabel lblTitulo = new JLabel("SISTEMA DE GESTIÓN INTEGRAL", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(30, 0, 10, 0));
        mainPanel.add(lblTitulo, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 40));
        buttonPanel.setBackground(Color.WHITE);

        // Botón Coches - Centrado y sin saltos
        JButton btnCoches = new JButton("<html><center><font size='5'>🚗</font> <b>COCHES</b></center></html>");
        estilizarBoton(btnCoches, new Color(18, 150, 225)); 
        btnCoches.addActionListener(e -> {
            try {
                CochesVista cv = new CochesVista();
                new CochesMVC(cv);
                cv.setVisible(true);
                frame.dispose();
            } catch (Exception ex) { ex.printStackTrace(); }
        });

        // Botón Clientes - Centrado y sin saltos
        JButton btnClientes = new JButton("<html><center><font size='5'>📒</font> <b>CLIENTES</b></center></html>");
        estilizarBoton(btnClientes, new Color(152, 196, 142)); 
        btnClientes.addActionListener(e -> {
            try {
                ClienteVista uv = new ClienteVista();
                new ClienteMVC(uv); 
                uv.setVisible(true);
                frame.dispose();
            } catch (Exception ex) { ex.printStackTrace(); }
        });

        buttonPanel.add(btnCoches);
        buttonPanel.add(btnClientes);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        frame.add(mainPanel);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    private static void estilizarBoton(JButton btn, Color color) {
        btn.setPreferredSize(new Dimension(170, 55)); 
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.putClientProperty("JButton.buttonType", "roundRect");
    }
}
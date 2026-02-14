import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

public class ClienteVista extends JFrame {
    public JTextField txtId, txtNombre, txtApellido, txtEmail;
    public JButton btnAgregar, btnEditar, btnEliminar, btnVolver;
    public JTable tabla;
    public DefaultTableModel modeloTabla;

    public ClienteVista() {
        setTitle("Gestión de Clientes - Base de Datos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(750, 500); 
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(15, 15));
        getContentPane().setBackground(new Color(245, 245, 245));

        // Panel Izquierdo (Formulario)
        JPanel panelIzquierdo = new JPanel(new GridBagLayout());
        panelIzquierdo.setBackground(Color.WHITE);
        panelIzquierdo.setBorder(new CompoundBorder(new LineBorder(new Color(230,230,230)), new EmptyBorder(10,10,10,10)));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); gbc.fill = GridBagConstraints.HORIZONTAL;

        txtId = new JTextField(12); txtId.setEditable(false);
        txtNombre = new JTextField(12);
        txtApellido = new JTextField(12);
        txtEmail = new JTextField(12);

        Font fLabel = new Font("Segoe UI", Font.BOLD, 12);
        
        gbc.gridx = 0; gbc.gridy = 0; JLabel l1 = new JLabel("ID:"); l1.setFont(fLabel); panelIzquierdo.add(l1, gbc);
        gbc.gridx = 1; panelIzquierdo.add(txtId, gbc);
        gbc.gridx = 0; gbc.gridy = 1; JLabel l2 = new JLabel("Nombre:"); l2.setFont(fLabel); panelIzquierdo.add(l2, gbc);
        gbc.gridx = 1; panelIzquierdo.add(txtNombre, gbc);
        gbc.gridx = 0; gbc.gridy = 2; JLabel l3 = new JLabel("Apellido:"); l3.setFont(fLabel); panelIzquierdo.add(l3, gbc);
        gbc.gridx = 1; panelIzquierdo.add(txtApellido, gbc);
        gbc.gridx = 0; gbc.gridy = 3; JLabel l4 = new JLabel("Email:"); l4.setFont(fLabel); panelIzquierdo.add(l4, gbc);
        gbc.gridx = 1; panelIzquierdo.add(txtEmail, gbc);

        // Botones
        btnAgregar = crearBoton("Añadir Cliente", new Color(18, 150, 225));
        btnEditar = crearBoton("Actualizar", new Color(152, 196, 142));
        btnEliminar = crearBoton("Eliminar", new Color(220, 53, 69));
        btnVolver = crearBoton("⬅ Volver", new Color(100, 100, 100));

        JPanel pBtns = new JPanel(new GridLayout(4, 1, 0, 8));
        pBtns.setBackground(Color.WHITE);
        pBtns.add(btnAgregar); pBtns.add(btnEditar); pBtns.add(btnEliminar); pBtns.add(btnVolver);

        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        panelIzquierdo.add(pBtns, gbc);

        // Tabla
        modeloTabla = new DefaultTableModel(new String[]{"ID", "Nombre", "Apellido", "Email"}, 0);
        tabla = new JTable(modeloTabla);
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tabla.setRowHeight(25);
        
        add(panelIzquierdo, BorderLayout.WEST);
        add(new JScrollPane(tabla), BorderLayout.CENTER);
    }

    private JButton crearBoton(String t, Color c) {
        JButton b = new JButton(t);
        b.setBackground(c); b.setForeground(Color.WHITE);
        b.setFont(new Font("Segoe UI", Font.BOLD, 12));
        b.setFocusPainted(false);
        b.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return b;
    }
}
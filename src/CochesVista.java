import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

public class CochesVista extends JFrame {
    public JTextField txtId, txtMarca, txtModelo, txtPrecio;
    public JButton btnAgregar, btnEditar, btnEliminar, btnVolver;
    public JTable tabla;
    public DefaultTableModel modeloTabla;

    public CochesVista() {
        setTitle("Gestión de Coches");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(750, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(15, 15));
        getContentPane().setBackground(new Color(245, 245, 245));

        JPanel panelIzquierdo = new JPanel(new GridBagLayout());
        panelIzquierdo.setBackground(Color.WHITE);
        panelIzquierdo.setBorder(new CompoundBorder(new LineBorder(new Color(230,230,230)), new EmptyBorder(10,10,10,10)));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); gbc.fill = GridBagConstraints.HORIZONTAL;

        txtId = new JTextField(12); txtId.setEditable(false);
        txtMarca = new JTextField(12);
        txtModelo = new JTextField(12);
        txtPrecio = new JTextField(12);

        // Labels y Fields
        gbc.gridx = 0; gbc.gridy = 0; panelIzquierdo.add(new JLabel("ID:"), gbc);
        gbc.gridx = 1; panelIzquierdo.add(txtId, gbc);
        gbc.gridx = 0; gbc.gridy = 1; panelIzquierdo.add(new JLabel("Marca:"), gbc);
        gbc.gridx = 1; panelIzquierdo.add(txtMarca, gbc);
        gbc.gridx = 0; gbc.gridy = 2; panelIzquierdo.add(new JLabel("Modelo:"), gbc);
        gbc.gridx = 1; panelIzquierdo.add(txtModelo, gbc);
        gbc.gridx = 0; gbc.gridy = 3; panelIzquierdo.add(new JLabel("Precio:"), gbc);
        gbc.gridx = 1; panelIzquierdo.add(txtPrecio, gbc);

        // Botones
        btnAgregar = crearBoton("Añadir", new Color(18, 150, 225));
        btnEditar = crearBoton("Editar", new Color(152, 196, 142));
        btnEliminar = crearBoton("Borrar", new Color(220, 53, 69));
        btnVolver = crearBoton("⬅ Volver", new Color(100, 100, 100));

        JPanel pBtns = new JPanel(new GridLayout(4, 1, 0, 8));
        pBtns.setBackground(Color.WHITE);
        pBtns.add(btnAgregar); pBtns.add(btnEditar); pBtns.add(btnEliminar); pBtns.add(btnVolver);

        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        panelIzquierdo.add(pBtns, gbc);

        modeloTabla = new DefaultTableModel(new String[]{"ID", "Marca", "Modelo", "Precio"}, 0);
        tabla = new JTable(modeloTabla);
        
        add(panelIzquierdo, BorderLayout.WEST);
        add(new JScrollPane(tabla), BorderLayout.CENTER);
    }

    private JButton crearBoton(String t, Color c) {
        JButton b = new JButton(t);
        b.setBackground(c); b.setForeground(Color.WHITE);
        b.setFont(new Font("Segoe UI", Font.BOLD, 12));
        b.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return b;
    }
}
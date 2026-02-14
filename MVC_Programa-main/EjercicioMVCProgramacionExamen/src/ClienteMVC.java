import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

public class ClienteMVC {
    private ClienteDAO dao;
    private ClienteVista vista;

    public ClienteMVC(ClienteVista vista) throws SQLException {
        this.vista = vista;
        this.dao = new ClienteDAO();
        init();
    }

    private void init() {
        vista.btnAgregar.addActionListener(e -> agregar());
        vista.btnEditar.addActionListener(e -> editar());
        vista.btnEliminar.addActionListener(e -> eliminar());
        
        // Acción para VOLVER al menú principal
        vista.btnVolver.addActionListener(e -> {
            new App().mostrarMenuPrincipal(); 
            vista.dispose();
        });

        // Selección de fila en tabla
        vista.tabla.getSelectionModel().addListSelectionListener(e -> {
            int fila = vista.tabla.getSelectedRow();
            if (fila != -1) {
                vista.txtId.setText(vista.tabla.getValueAt(fila, 0).toString());
                vista.txtNombre.setText(vista.tabla.getValueAt(fila, 1).toString());
                vista.txtApellido.setText(vista.tabla.getValueAt(fila, 2).toString());
                vista.txtEmail.setText(vista.tabla.getValueAt(fila, 3).toString());
            }
        });

        listar();
    }

    private void agregar() {
        try {
            Cliente c = new Cliente(0, vista.txtNombre.getText(), vista.txtApellido.getText(), vista.txtEmail.getText());
            dao.agregar(c);
            limpiarCampos();
            listar();
            JOptionPane.showMessageDialog(vista, "Cliente registrado");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista, "Error: " + e.getMessage());
        }
    }

    private void editar() {
        try {
            int id = Integer.parseInt(vista.txtId.getText());
            Cliente c = new Cliente(id, vista.txtNombre.getText(), vista.txtApellido.getText(), vista.txtEmail.getText());
            dao.actualizar(c);
            listar();
            JOptionPane.showMessageDialog(vista, "Datos actualizados");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista, "Selecciona un cliente");
        }
    }

    private void eliminar() {
        try {
            int id = Integer.parseInt(vista.txtId.getText());
            if (JOptionPane.showConfirmDialog(vista, "¿Eliminar cliente?") == JOptionPane.YES_OPTION) {
                dao.eliminar(id);
                limpiarCampos();
                listar();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista, "Selecciona un registro");
        }
    }

    private void listar() {
        try {
            List<Cliente> lista = dao.listar();
            vista.modeloTabla.setRowCount(0);
            for (Cliente c : lista) {
                vista.modeloTabla.addRow(new Object[]{c.getId(), c.getNombre(), c.getApellido(), c.getEmail()});
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    private void limpiarCampos() {
        vista.txtId.setText("");
        vista.txtNombre.setText("");
        vista.txtApellido.setText("");
        vista.txtEmail.setText("");
    }
}
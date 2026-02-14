import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

public class CochesMVC {
    private CochesDAO dao;
    private CochesVista vista;

    public CochesMVC(CochesVista vista) throws SQLException {
        this.vista = vista;
        this.dao = new CochesDAO();
        init();
    }

    private void init() {
        // Listeners para los botones de gestión
        vista.btnAgregar.addActionListener(e -> agregar());
        vista.btnEditar.addActionListener(e -> editar());
        vista.btnEliminar.addActionListener(e -> eliminar());
        
        // Listener para el botón VOLVER (Regresa a App.java)
        vista.btnVolver.addActionListener(e -> {
            new App().mostrarMenuPrincipal(); // Abre el menú centrado
            vista.dispose(); // Cierra la ventana de Coches
        });

        // Listener para seleccionar filas de la tabla
        vista.tabla.getSelectionModel().addListSelectionListener(e -> {
            int fila = vista.tabla.getSelectedRow();
            if (fila != -1) {
                vista.txtId.setText(vista.tabla.getValueAt(fila, 0).toString());
                vista.txtMarca.setText(vista.tabla.getValueAt(fila, 1).toString());
                vista.txtModelo.setText(vista.tabla.getValueAt(fila, 2).toString());
                vista.txtPrecio.setText(vista.tabla.getValueAt(fila, 3).toString());
            }
        });

        listar(); // Carga los datos al abrir
    }

    private void agregar() {
        try {
            String marca = vista.txtMarca.getText();
            String modelo = vista.txtModelo.getText();
            double precio = Double.parseDouble(vista.txtPrecio.getText());

            Coches c = new Coches(0, marca, modelo, precio);
            dao.agregar(c);
            limpiarCampos();
            listar();
            JOptionPane.showMessageDialog(vista, "Coche añadido con éxito");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista, "Error al añadir: " + e.getMessage());
        }
    }

    private void editar() {
        try {
            int id = Integer.parseInt(vista.txtId.getText());
            String marca = vista.txtMarca.getText();
            String modelo = vista.txtModelo.getText();
            double precio = Double.parseDouble(vista.txtPrecio.getText());

            Coches c = new Coches(id, marca, modelo, precio);
            dao.actualizar(c);
            listar();
            JOptionPane.showMessageDialog(vista, "Registro actualizado");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista, "Selecciona un coche para editar");
        }
    }

    private void eliminar() {
        try {
            int id = Integer.parseInt(vista.txtId.getText());
            int confirmar = JOptionPane.showConfirmDialog(vista, "¿Estás seguro de eliminar este coche?");
            if (confirmar == JOptionPane.YES_OPTION) {
                dao.eliminar(id);
                limpiarCampos();
                listar();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista, "Error: Selecciona un registro");
        }
    }

    private void listar() {
        try {
            List<Coches> lista = dao.listar();
            vista.modeloTabla.setRowCount(0); // Limpia la tabla
            for (Coches c : lista) {
                vista.modeloTabla.addRow(new Object[]{
                    c.getId(), 
                    c.getMarca(), 
                    c.getModelo(), 
                    c.getPrecio()
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void limpiarCampos() {
        vista.txtId.setText("");
        vista.txtMarca.setText("");
        vista.txtModelo.setText("");
        vista.txtPrecio.setText("");
    }
}
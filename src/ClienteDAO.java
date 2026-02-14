
import java.sql.*;
import java.util.*;

/**
 *
 * @author manuel.benavente
 */
public class ClienteDAO {
    private Connection conn;

    public ClienteDAO() throws SQLException {
        conn = Conexion.getConnection();
    }

    // Crear cliente
    public void agregar(Cliente c) throws SQLException {
        String sql ="INSERT INTO cliente (nombre, apellido, email) VALUES (?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, c.getNombre());
        ps.setString(2, c.getApellido());
        ps.setString(3, c.getEmail());
        ps.executeUpdate();
    }

    //Actualizar cliente
    public void actualizar(Cliente c) throws SQLException {
        String sql = "UPDATE cliente SET nombre=?, apellido=?, email=? WHERE id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, c.getNombre());
        ps.setString(2, c.getApellido());
        ps.setString(3, c.getEmail());
        ps.setInt(4, c.getId());
        ps.executeUpdate();
    }

    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM cliente WHERE id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
    }


    // Obtener todos los clientes
    public List<Cliente> listar() throws SQLException {
        List<Cliente> lista = new ArrayList<>();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM cliente");
        while (rs.next()) {
            Cliente c = new Cliente();
            c.setId(rs.getInt("id"));
            c.setNombre(rs.getString("nombre"));
            c.setApellido(rs.getString("apellido"));
            c.setEmail(rs.getString("email"));
            lista.add(c);
        }
        return lista;
    }
}
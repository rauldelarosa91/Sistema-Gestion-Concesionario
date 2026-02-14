import java.sql.*;
import java.util.*;

/**
 *
 * @author raul.de1
 */
public class CochesDAO {
    private Connection conn;

    public CochesDAO() throws SQLException {
        conn = Conexion.getConnection();
    }

    // Crear coche
    public void agregar(Coches c) throws SQLException {
        String sql ="INSERT INTO coche (marca, modelo, precio) VALUES (?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, c.getMarca());
        ps.setString(2, c.getModelo());
        ps.setDouble(3, c.getPrecio());
        ps.executeUpdate();
    }

    //Actualizar coche
    public void actualizar(Coches c) throws SQLException {
        String sql = "UPDATE coche SET marca=?, modelo=?, precio=? WHERE id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, c.getMarca());
        ps.setString(2, c.getModelo());
        ps.setDouble(3, c.getPrecio());
        ps.setInt(4, c.getId());
        ps.executeUpdate();
    }

    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM coche WHERE id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
    }


    // Obtener todos los coches
    public List<Coches> listar() throws SQLException {
        List<Coches> lista = new ArrayList<>();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM coche");
        while (rs.next()) {
            Coches c = new Coches();
            c.setId(rs.getInt("id"));
            c.setMarca(rs.getString("marca"));
            c.setModelo(rs.getString("modelo"));
            c.setPrecio(rs.getDouble("precio"));
            lista.add(c);
        }
        return lista;
    }
}
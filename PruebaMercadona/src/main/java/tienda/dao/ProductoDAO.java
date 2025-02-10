package tienda.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import tienda.modelos.Producto;

public class ProductoDAO {

    private static DataSource dataSource;

    public ProductoDAO() throws Exception {
        Context ctx = new InitialContext();
        dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/PruebaMercadonaDS");
    }

    public List<Producto> getAllProductos() throws SQLException {
        List<Producto> productos = new ArrayList<>();
        String query = "SELECT * FROM productos";
        try (Connection conn = dataSource.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(query); 
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Producto producto = new Producto(rs.getInt("id"), rs.getString("nombre"), rs.getDouble("precio"), rs.getInt("cantidad"));
                productos.add(producto);
            }
        }
        return productos;
    }

    public void addProducto(Producto producto) throws SQLException {
        String query = "INSERT INTO productos (nombre, precio, cantidad) VALUES (?, ?, ?)";
        try (Connection conn = dataSource.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, producto.getNombre());
            ps.setDouble(2, producto.getPrecio());
            ps.setInt(3, producto.getCantidad());
            ps.executeUpdate();
        }
    }

    public Producto findByIdProducto(int id) throws SQLException {
        String query = "SELECT * FROM productos WHERE id = ?";
        try (Connection conn = dataSource.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Producto(rs.getInt("id"), rs.getString("nombre"), rs.getDouble("precio"), rs.getInt("cantidad"));
                }
            }
        }
        return null;
    }

    public void updateProducto(Producto producto) throws SQLException {
        String query = "UPDATE productos SET nombre = ?, precio = ?, cantidad = ? WHERE id = ?";
        try (Connection conn = dataSource.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, producto.getNombre());
            ps.setDouble(2, producto.getPrecio());
            ps.setInt(3, producto.getCantidad());
            ps.setInt(4, producto.getId());
            ps.executeUpdate();
        }
    }

    public void deleteProducto(int[] ids) throws SQLException {
        String query = "DELETE FROM productos WHERE id = ?";
        try (Connection conn = dataSource.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(query)) {
        	for (int id : ids) {
                ps.setInt(1, id);
                ps.addBatch();
            }
        	ps.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

package tienda.controladores;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tienda.dao.ProductoDAO;
import tienda.modelos.Producto;

public class ListadoProductosServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6881282703071956190L;

	private ProductoDAO productoDAO;

	@Override
	public void init() throws ServletException {
		try {
			productoDAO = new ProductoDAO();
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			List<Producto> listadoProductos = productoDAO.getAllProductos();
			request.setAttribute("listadoProductos", listadoProductos);
			request.getRequestDispatcher("listadoProductos.jsp").forward(request, response);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
	
	 @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String[] ids = request.getParameterValues("productoIds");
	        if (ids != null) {
	            try {
	                int[] intIds = new int[ids.length];
	                for (int i = 0; i < ids.length; i++) {
	                    intIds[i] = Integer.parseInt(ids[i]);
	                }
	                productoDAO.deleteProducto(intIds);
	            } catch (SQLException e) {
	                throw new ServletException(e);
	            }
	        }
	        response.sendRedirect("ListadoProductosServlet");
	    }
}

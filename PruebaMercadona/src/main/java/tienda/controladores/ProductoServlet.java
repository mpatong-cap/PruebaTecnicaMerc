package tienda.controladores;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tienda.dao.ProductoDAO;
import tienda.modelos.Producto;

public class ProductoServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 7857940908627441371L;
	
	private ProductoDAO productoDAO;

	@Override
	public void init() throws ServletException {
		try {
			productoDAO = new ProductoDAO();
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "list":
                listadoProductos(request, response);
                break;
            case "add":
                formNuevoProducto(request, response);
                break;
            case "edit":
                formEditarProducto(request, response);
                break;
            default:
                listadoProductos(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "guardar";
        }

        switch (action) {
            case "guardar":
                guardarProducto(request, response);
                break;
            default:
                listadoProductos(request, response);
                break;
        }
    }
    
    private void listadoProductos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
        	List<Producto> listadoProductos = productoDAO.getAllProductos();
	        request.setAttribute("listadoProductos", listadoProductos);
	        request.getRequestDispatcher("listadoProductos.jsp").forward(request, response);
        } catch(Exception e) {
        	throw new ServletException(e);
        }
    }

    private void formNuevoProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("formProductos.jsp").forward(request, response);
    }

    private void formEditarProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
        	int id = Integer.parseInt(request.getParameter("id"));
	        Producto producto = productoDAO.findByIdProducto(id);
	        request.setAttribute("producto", producto);
	        request.getRequestDispatcher("formProductos.jsp").forward(request, response);
        } catch(Exception e) {
        	throw new ServletException(e);
        }
    }

    private void guardarProducto(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try{
        	int id = request.getParameter("id") != null ? Integer.parseInt(request.getParameter("id")) : 0;
	        String nombre = request.getParameter("nombre");
	        double precio = Double.parseDouble(request.getParameter("precio"));
	        Integer cantidad = Integer.parseInt(request.getParameter("cantidad"));
	
	        Producto producto = new Producto(id, nombre, precio, cantidad);
	
	        if (id == 0) {
	            productoDAO.addProducto(producto);
	        } else {
	            productoDAO.updateProducto(producto);
	        }
	
	        response.sendRedirect("ProductoServlet?action=listar");
        } catch(Exception e) {
        	throw new ServletException(e);
        }
    }
}

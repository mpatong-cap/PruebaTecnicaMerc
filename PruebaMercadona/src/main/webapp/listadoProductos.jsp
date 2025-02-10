<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="navBar.jsp" />
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<div class="container">
    <h1 class="text-center">Listado de Productos</h1>
    <form method="post" action="ListadoProductosServlet">
        <table class="table table-striped table-hover">
            <thead>
                <tr>
                    <th>Seleccionar</th>
                    <th>Nombre</th>
                    <th>Precio</th>
                    <th>Cantidad</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="producto" items="${listadoProductos}">
                    <tr>
                        <td><input class="form-check-input" type="checkbox" name="productoIds" value="${producto.id}" /></td>
                        <td>${producto.nombre}</td>
                        <td>${producto.precio}</td>
                        <td>${producto.cantidad}</td>
                        <td>
                            <a href="ProductoServlet?action=edit&id=${producto.id}" class="btn btn-primary btn-sm">Editar</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <button type="submit" class="btn btn-danger">Eliminar seleccionados</button>
    </form>
    <br/>
    <a href="ProductoServlet?action=add" class="btn btn-success">Añadir nuevo producto</a>
</div>
</body>
</html>

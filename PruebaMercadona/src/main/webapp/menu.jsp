<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="navBar.jsp" />
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<div class="container">
    <h1 class="text-center">Menú Principal</h1>
    <div class="list-group">
        <a href="ListadoProductosServlet" class="list-group-item list-group-item-action">Listado de Productos</a>
        <a href="ProductoServlet?action=add" class="list-group-item list-group-item-action">Añadir Producto</a>
    </div>
    <a href="logout" class="btn btn-danger">Cerrar sesión</a>
</div>
</body>
</html>

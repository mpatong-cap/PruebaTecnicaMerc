<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="navBar.jsp" />
<script>
    // Validación de formulario de Bootstrap
    (function () {
        'use strict'
        var forms = document.querySelectorAll('.needs-validation')
        Array.prototype.slice.call(forms)
            .forEach(function (form) {
                form.addEventListener('submit', function (event) {
                    if (!form.checkValidity()) {
                        event.preventDefault()
                        event.stopPropagation()
                    }
                    form.classList.add('was-validated')
                }, false)
            })
    })()
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<div class="container">
    <h1 class="text-center">${producto != null ? "Editar Producto" : "Añadir Nuevo Producto"}</h1>
    <form method="post" action="ProductoServlet" class="needs-validation" novalidate>
    	<input type="hidden" id="id" name="id" value="${producto.id}">
        <div class="mb-3">
            <label for="nombre" class="form-label">Nombre del Producto</label>
            <input type="text" class="form-control" id="nombre" name="nombre" value="${producto != null ? producto.nombre : ''}" required>
        </div>
        <div class="mb-3">
            <label for="precio" class="form-label">Precio</label>
            <input type="number" class="form-control" id="precio" name="precio" step="0.01" value="${producto != null ? producto.precio : ''}" required>
        </div>
        <div class="mb-3">
            <label for="cantidad" class="form-label">Cantidad</label>
            <input type="number" class="form-control" id="cantidad" name="cantidad" value="${producto != null ? producto.cantidad : ''}" required>
        </div>
        <button type="submit" class="btn btn-primary">${producto != null ? "Actualizar Producto" : "Añadir Producto"}</button>
        <a href="ListadoProductosServlet" class="btn btn-secondary">Cancelar</a>
    </form>
</div>
</body>
</html>

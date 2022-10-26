<%@page import="pe.isil.jupiter_dae1.model.Producto"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Producto> listado = (List<Producto>)request.getAttribute("listado");
%>
  <!-- Producto -->
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>E-Commerce | Productos</title>

  <!-- Google Font: Source Sans Pro -->
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
  <!-- Font Awesome Icons -->
  <link rel="stylesheet" href="../plugins/fontawesome-free/css/all.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="../dist/css/adminlte.min.css">
</head>
<body class="hold-transition sidebar-mini">    
<div class="wrapper">

    <jsp:include page="../navbar.jsp"/>
    
    <jsp:include page="../sidebar.jsp"/>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Main content -->
  <div class="content">
      <div class="container-fluid">
          <div class="row">
              <div class="col-lg-12">
                  <div class="card card-primary card-outline mt-3">
                    <div class="card-body">
                        <h5 class="card-title">Lista de Productos</h5>
                        
                        <div class="mt-3 mb-3 float-right">
                            <a href="${URL_APLICACION}/admin/productos/nuevo" class="btn btn-success my-1 ml-1">Nuevo</a>
                        </div>
                        
                        <table class="table table-hover" id="tabla_productos">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>NOMBRE</th> 
                                    <th>Precio</th>
                                    <th>Stock</th>
                                    <th>Categoria</h>
                                    <th>Opciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    for(Producto producto : listado){
                                %>
                                <tr>
                                    <td><%=producto.getId() %></td>
                                    <td><%=producto.getNombre() %></td>
                                    <td><%=producto.getPrecio()%></td>
                                    <td><%=producto.getStock() %></td>
                                    <td><%=producto.getCategoria()%></td>
                                    <td>
                                        <a href="${URL_APLICACION}/admin/productos/editar/<%=producto.getId() %>" class="btn btn-primary fas fa-edit"></a>
                                         <button onclick="abrirModal(<%=producto.getId() %>)" class="btn btn-danger fas fa-trash"></button>
                                    </td>
                                </tr>
                                <% 
                                    }
                                %>
                            </tbody>
                        </table>
                    </div>
                  </div>
              </div>
          </div>
          <!-- /. row -->
      </div><!-- /.containe-fluid -->
  </div>

 <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

    <jsp:include page="../footer.jsp"/>

</div>
    <div class="modal fade" id="modal-producto">
        
    </div>
    
<!-- ./wrapper -->

<!-- REQUIRED SCRIPTS -->

<!-- jQuery -->
<script src="${URL_APLICACION}/plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap 4 -->
<script src="${URL_APLICACION}/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- AdminLTE App -->
<script src="${URL_APLICACION}/dist/js/adminlte.min.js"></script>

<script src="${URL_APLICACION}/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="${URL_APLICACION}/plugins/datatables-bs4/js/dataTables.bootstrap4.min.js"></script>

<script>
    
    function abrirModal(id){
        const enlace = '${URL_APLICACION}/admin/productos/eliminar/' + id;
        $.ajax({
            method: "GET",
            url: enlace
        }).done(function(resultado){
            $('#modal-producto').html(resultado)
            $('#modal-producto').modal('show')
        });
    }
    
</script>

<script>
    $('#tabla_producto').DataTable();
</script>

</body>
</html>
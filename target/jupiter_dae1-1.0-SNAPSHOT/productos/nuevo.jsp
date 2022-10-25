<%@page import="pe.isil.jupiter_dae1.model.Producto"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>E-Commerce | Nuevo</title>
<!--producto/nuevo-->
  <!-- Google Font: Source Sans Pro -->
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
  <!-- Font Awesome Icons -->
  <link rel="stylesheet" href="${URL_APLICACION}/plugins/fontawesome-free/css/all.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="${URL_APLICACION}/dist/css/adminlte.min.css">
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
            <div class="col-lg-6 col-md-6 col-sm-12">
                <div class="card card-primary mt-3">
                    <div class="card card-header">
                        <h3 class="card-title">Registrar Nuevo Producto</h3>
                    </div>
                        
                    <div class="card-body">                        
                        
                        <form id="form_regcat" action="${URL_APLICACION}/admin/productos/registrar" method="post" autocomplete="off">
                            
                            <div class="form-group">
                                <label for="exampleInputEmail1">Nombre</label>
                                <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Ingrese nombre de producto">
                            </div>
                            
                            <div class="form-group">
                                <label for="exampleInputEmail1">Precio</label>
                                <input type="number" class="form-control" id="precio" name="precio" placeholder="precio">
                            </div>
                            
                            <div class="form-group">
                                <label for="exampleInputEmail1">Stock</label>
                                <input type="number" class="form-control" id="stock" name="stock" placeholder="stock">
                            </div>
                            
                            <div class="form-group">
                                <label for="exampleInputEmail1">Categoria</label>
                                <input type="text" class="form-control" id="stock" name="categoria" placeholder="categoria">
                            </div>
                            
                            
                            <div class="justify-content-between">
                                <button type="submit" class="btn btn-primary">Registrar</button>
                                <a href="${URL_APLICACION}/admin/productos" class="btn btn-secondary">Cancelar</a>
                            </div>
                            
                            
                            
                        </form>
                        
                        
                    </div>
                </div>     
            </div>
        </div>
        <!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
 
  </div>
  <!-- /.content-wrapper -->

    <jsp:include page="../footer.jsp"/>

</div>
<!-- ./wrapper -->

<!-- REQUIRED SCRIPTS -->

<!-- jQuery -->
<script src="${URL_APLICACION}/plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap 4 -->
<script src="${URL_APLICACION}/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- AdminLTE App -->
<script src="${URL_APLICACION}/dist/js/adminlte.min.js"></script>
</body>
</html>


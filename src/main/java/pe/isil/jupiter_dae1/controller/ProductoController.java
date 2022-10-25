/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package pe.isil.jupiter_dae1.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pe.isil.jupiter_dae1.dao.AD_Producto;
import pe.isil.jupiter_dae1.model.Producto;


/**
 *
 * @author BSJF
 */
@WebServlet(name = "ProductoController", urlPatterns = {"/admin/productos/*"})
public class ProductoController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProductoController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductoController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
         //Para obtener la ruta que ingrersa usamos request.getpathinfo
        String URL = request.getPathInfo();
        System.out.println("LA URL ES ESTA -produtos: " +URL);
        if(URL == null || URL.equals("/")){
            //1. Obtener los productos de la base de datos
            AD_Producto ad_producto = new AD_Producto();
            List<Producto> listado  = new ArrayList<Producto>();          
            listado = ad_producto.getAll();
            
            
            //2. Agregar como atributo al request la lista de productos
            request.setAttribute("listado", listado);
            
            //3. Envío del listado de productos al front
            request.getRequestDispatcher("/productos/index.jsp").forward(request, response);
            
            
        }
        
        //Nuevo
        if(URL.equals("/nuevo")){
            request.getRequestDispatcher("/productos/nuevo.jsp").forward(request, response);
            }
        
        // /editar/1
        // [editar] y [1]
        String accion = URL.substring(1) ; //--> editar/1/o
        String[] ruta = accion.split("/"); // ruta[0] = "editar", ruta[1] = "1", ruta[2] = "o"
        
        if(ruta[0].equals("editar")){            
            Integer id = Integer.parseInt(ruta[1]);
            Producto producto = new Producto();
            AD_Producto ad = new AD_Producto();
            producto = ad.get(id);
            
            request.setAttribute("producto", producto);
            request.getRequestDispatcher("/productos/editar.jsp").forward(request, response);
        }else{
            System.out.println("Error en Editar");
        }    
        
        //eliminar
         if(ruta[0].equals("eliminar")){
            Integer id = Integer.parseInt(ruta[1]);
             Producto producto = new Producto();
            AD_Producto ad = new AD_Producto();
            producto = ad.get(id);
            
            request.setAttribute("producto", producto);
            request.getRequestDispatcher("/productos/eliminar.jsp").forward(request, response);
        }
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        String URL = request.getPathInfo();
         
        if(URL.equals("/registrar")){
            String nombre = request.getParameter("nombre");
            Double precio = Double.parseDouble(request.getParameter("precio"));
            Integer stock = Integer.parseInt(request.getParameter("stock"));
            String categoria = request.getParameter("categoria");
            
            Producto producto = new Producto();
            producto.setNombre(nombre);
            producto.setPrecio(precio);
            producto.setStock(stock);
            producto.setCategoria(categoria);
            
            AD_Producto ad = new AD_Producto();
            ad.insertar(producto);
            
            //3. Envío del listado de productos al front
            response.sendRedirect(request.getContextPath()+"/admin/productos");
        }
        
        //Editar
        String accion = URL.substring(1) ; // editar/1/o
        String[] ruta = accion.split("/"); // ruta[0] = "editar", ruta[1] = "1"
        
         if(ruta[0].equals("actualizar")){
            Integer id = Integer.parseInt(ruta[1]);
            String nombre = request.getParameter("nombre");
            Double precio = Double.parseDouble(request.getParameter("precio"));
            Integer stock = Integer.parseInt(request.getParameter("stock"));
            String categoria = request.getParameter("categoria");
            
            Producto producto = new Producto();
            producto.setId(id);
            producto.setNombre(nombre);
            producto.setPrecio(precio);
            producto.setStock(stock);
            producto.setCategoria(categoria);
            
            AD_Producto ad = new AD_Producto();
            ad.actualizar(producto);
            response.sendRedirect(request.getContextPath()+"/admin/productos");
            
        }
         
         
        //elimnar
         if(ruta[0].equals("eliminar")){
            Integer id = Integer.parseInt(ruta[1]);
            
            AD_Producto ad = new AD_Producto();
            ad.eliminar(id);
            
            response.sendRedirect(request.getContextPath()+"/admin/productos");
        }
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

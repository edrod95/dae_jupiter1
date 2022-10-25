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
import pe.isil.jupiter_dae1.dao.AD_Categoria;
import pe.isil.jupiter_dae1.model.Categoria;

/**
 *
 * @author BSJF
 */
@WebServlet(name = "CategoriaController", urlPatterns = {"/admin/categorias/*"})
public class CategoriaController extends HttpServlet {

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
            out.println("<title>Servlet CategoriaController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CategoriaController at " + request.getContextPath() + "</h1>");
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
        //FUNCION       DESCRIPCION                             URL                                     METODO
        //Listar        Muestra al inicio todas las categorias  https://localhost:8080/admin/categorias GET
                
        //Para obtener la ruta que ingrersa usamos request.getpathinfo
        String URL = request.getPathInfo();
        System.out.println("LA URL ES ESTA -categorias: " +URL);
        if(URL == null || URL.equals("/")){
            //1. Obtener las categorias de la base de datos
            AD_Categoria ad_categoria = new AD_Categoria();
             List<Categoria> listado  = new ArrayList<Categoria>();
             listado = ad_categoria.getAll();
            
            
            //2. Agregar como atributo al request la lista de categorias
            request.setAttribute("listado", listado);
            
            //3. Envío del listado de categorias al front
            request.getRequestDispatcher("/categorias/index.jsp").forward(request, response);
        } 
        
        if(URL.equals("/nuevo")){
            request.getRequestDispatcher("/categorias/nuevo.jsp").forward(request, response);
        }
        
        // /editar/1
        // [editar] y [1]
        String accion = URL.substring(1) ; // editar/1/o
        String[] ruta = accion.split("/"); // ruta[0] = "editar", ruta[1] = "1", ruta[2] = "o"
        
        if(ruta[0].equals("editar")){
            Integer id = Integer.parseInt(ruta[1]);
            Categoria categoria = new Categoria();
            AD_Categoria ad = new AD_Categoria();
            categoria = ad.get(id);
            
            request.setAttribute("categoria", categoria);
            request.getRequestDispatcher("/categorias/editar.jsp").forward(request, response);
        }
        
        //eliminar
         if(ruta[0].equals("eliminar")){
            Integer id = Integer.parseInt(ruta[1]);
            Categoria categoria = new Categoria();
            AD_Categoria ad = new AD_Categoria();
            categoria = ad.get(id);
            
            request.setAttribute("categoria", categoria);
            request.getRequestDispatcher("/categorias/eliminar.jsp").forward(request, response);
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
        
         //FUNCION       DESCRIPCION                             URL                                                     METODO      VISTA
        //Guardar       Inserta una categoria en la bd1         https://localhost:8080/admin/categorias/registrar        POST       categorias/nuevo.jsp
        //Actualizar    actualizar una categoria en la bd1      https://localhost:8080/admin/categorias/actualizar/{id}  POST       categorias/nuevo.jsp
        
        String URL = request.getPathInfo();
        if(URL.equals("/registrar")){
            String nombre = request.getParameter("nombre");
            Categoria categoria = new Categoria();
            categoria.setNombre(nombre);
            
            AD_Categoria ad = new AD_Categoria();
            ad.insertar(categoria);
            
            //3. Envío del listado de categorias al front
            response.sendRedirect(request.getContextPath()+"/admin/categorias/");
        }
        
        //Editar
        String accion = URL.substring(1) ; // editar/1/o
        String[] ruta = accion.split("/"); // ruta[0] = "editar", ruta[1] = "1"
        
         if(ruta[0].equals("actualizar")){
            Integer id = Integer.parseInt(ruta[1]);
            String nombre = request.getParameter("nombre");
            Categoria categoria = new Categoria();
            categoria.setId(id);
            categoria.setNombre(nombre);
            
            AD_Categoria ad = new AD_Categoria();
            ad.actualizar(categoria);
            response.sendRedirect(request.getContextPath()+"/admin/categorias/");
            
        }
         
         
        //elimnar
         if(ruta[0].equals("eliminar")){
            Integer id = Integer.parseInt(ruta[1]);
            
            AD_Categoria ad = new AD_Categoria();
            ad.eliminar(id);
            
            response.sendRedirect(request.getContextPath()+"/admin/categorias/");
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

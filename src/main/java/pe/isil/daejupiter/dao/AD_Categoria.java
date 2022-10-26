/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.isil.daejupiter.dao;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import pe.isil.daejupiter.model.Categoria;


public class AD_Categoria {
    private PreparedStatement pst = null;
    private ResultSet rst; // obtiene los datos de la db, select
    
    public boolean insertar (Categoria categoria){
        boolean resultado = false;
        Connection Conexion = null;
        try {
               Conexion = ConexionDB.getInstancia().getConnection();
           if(Conexion != null){
               String SQL = "INSERT INTO categoria (nombre) VALUES(?)";
               pst = Conexion.prepareStatement(SQL);
               pst.setString(1, categoria.getNombre());
               int res= 0;
               res = pst.executeUpdate();
               if(res > 0){
                   resultado = true;
               }else{
                   resultado = false ;
               }
               
           }else{
               System.out.println("Error en la conexión a la DD - insertar");
           }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConexionDB.getInstancia().close(Conexion);
        }
    return resultado;
    }
    
    //actualizar
     public boolean actualizar (Categoria categoria){
        boolean resultado = false;
        Connection Conexion = null;
        try {
               Conexion = ConexionDB.getInstancia().getConnection();
           if(Conexion != null){
               String SQL = "UPDATE categoria set nombre=? where id=?";
               pst = Conexion.prepareStatement(SQL);
               pst.setString(1, categoria.getNombre());
               pst.setInt(2, categoria.getId());
               int res= 0;
               res = pst.executeUpdate();
               if(res > 0){
                   resultado = true;
               }else{
                   resultado = false ;
               }
               
           }else{
               System.out.println("Error en la conexión a la DB - actualizar");
           }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConexionDB.getInstancia().close(Conexion);
        }
    return resultado;
    }
    
    //eliminar
        public boolean eliminar (Integer id){
        boolean resultado = false;
        Connection Conexion = null;
        try {
               Conexion = ConexionDB.getInstancia().getConnection();
           if(Conexion != null){
               String SQL = "Delete from categoria where id=?";
               pst = Conexion.prepareStatement(SQL);
               pst.setInt(1, id);
               int res= 0;
               res = pst.executeUpdate();
               if(res > 0){
                   resultado = true;
               }else{
                   resultado = false ;
               }
               
           }else{
               System.out.println("Error en la conexión a la DB - eliminar");
           }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConexionDB.getInstancia().close(Conexion);
        }
    return resultado;
    }
     
     //Listar
     public List<Categoria>  getAll(){
         List<Categoria> listado  = new ArrayList<Categoria>();
         Categoria categoria; 
         Connection Conexion = null;
            try {
               Conexion = ConexionDB.getInstancia().getConnection();
                if(Conexion != null){
                   String SQL = "select * from categoria";
                   pst = Conexion.prepareStatement(SQL);
                   
                   rst = pst.executeQuery(); // se guarda el resutlado del select aqui
                   
                    while (rst.next()) {
                        categoria = new Categoria();
                        categoria.setId(rst.getInt("id"));
                        categoria.setNombre(rst.getString("nombre"));
                        listado.add(categoria);
                    }
                   
                     
               }else{
                   System.out.println("Error en la conexión a la DB - listar");
               }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConexionDB.getInstancia().close(Conexion);
        }
            return listado;
         
     }
     
     public Categoria get(Integer id){          
         Categoria categoria = new Categoria();
         Connection Conexion = null;
            try {
               Conexion = ConexionDB.getInstancia().getConnection();
                if(Conexion != null){
                   String SQL = "select * from categoria where id=?";
                   pst = Conexion.prepareStatement(SQL);
                   pst.setInt(1, id);
                   
                   rst = pst.executeQuery(); // se guarda el resutlado del select aqui
                   
                    while (rst.next()) {
                        categoria.setId(rst.getInt("id"));
                        categoria.setNombre(rst.getString("nombre"));                        
                    }
               }else{
                   System.out.println("Error en la conexión a la DB - get by id");
               }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConexionDB.getInstancia().close(Conexion);
        }
            return categoria;
     }
        
        
    //Hacer para producto y usuario lo mismo para categoria
    
}
